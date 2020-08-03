/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textilesproject.readers;

import static com.mycompany.textilesproject.Constants.*;
import static com.mycompany.textilesproject.Parameters.*;
import com.mycompany.textilesproject.math.*; 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 *
 * @author admin
 */
public class csvReader {
    
    //Map of column name that we are interested in, and its position
    private  static  Map <  Integer, String> columnIndicesMap = new HashMap   < Integer , String >();
    private  static   Map <   String, Integer> columnIndicesMap_Flipped = new HashMap   <  String,Integer >();
    
   
    
    //simple csv reader 
    public static void main(String[] args) throws Exception {
        
        BufferedReader csvReader = new BufferedReader(new FileReader(PATH_TO_CSV_FILE));
        String row = null;
        int rowNumber = ZERO;
       
        //key is company name, list of data items, each item has a name and a value
        Map <String, List<Map <String, String>>> dataItemList = new HashMap <String, List< Map<String, String>>> ();
        //first key is company name , key to the second map is year
        Map <String, Map<Integer,  List<Double>> > characterRangeList= new HashMap <String,   Map<Integer,  List<Double> >> ();
        
        while ((row = csvReader.readLine()) != null) {
            String[] columns = row.trim().split(",", MILLION);
            
            //System.out.println("rowNumber is "+ rowNumber) ;
            
             
            
            if (ZERO==rowNumber) {
                //column headings
                for (int columnNumber = ZERO ; columnNumber < columns.length ; columnNumber ++){
                    if (columnsOfInterest.contains(columns[columnNumber].trim())){
                        columnIndicesMap.put ( columnNumber, columns[columnNumber].trim());
                        columnIndicesMap_Flipped.put (columns[columnNumber].trim(), columnNumber);
                    }
                }    
                 
            } else {
                //data
                Map <String, String> dataItem  = new HashMap <String, String>() ;
                boolean isCompanyOfInterest = false;
                
                
                for (int columnNumber = ZERO ; columnNumber < columns.length ; columnNumber ++){
                    if (columnIndicesMap.keySet().contains(columnNumber )){
                        //this is a column of interest
                        String thisColName = columnIndicesMap.get(columnNumber);
                        dataItem.put (thisColName, columns[columnNumber].trim());                        
                         
                        
                    }
                }
                
                String companyName = columns[columnIndicesMap_Flipped.get( "Company Name")];
                isCompanyOfInterest = companiesOfInterest.contains(companyName );
                boolean isRecordClean = isRecordClean (dataItem.values()) ;
                                
                 
                
                if (isCompanyOfInterest && isRecordClean){
                    
                    
                    //    
                    //System.out.println ("record id and company name " + 
                    //        columns[columnIndicesMap_Flipped.get( "Id")] +
                    //        columns[columnIndicesMap_Flipped.get( "Company Name")]        ) ;
                    
                    //add dataItem
                    List<Map<String, String>> current = dataItemList.get (companyName) ;
                    if (current==null) current = new ArrayList <Map<String, String>> ( );
                    current.add (dataItem );
                    dataItemList.put (companyName, current) ;
                    
                    populateCharacterRange(characterRangeList,   columns , companyName );
                }
                
            }
            
            rowNumber++;
             
        }
            
        csvReader.close();
        
        
        for ( String   companyWithNotEnoughData : getCompanysWithNotEnoughData(dataItemList)){
            dataItemList.remove( companyWithNotEnoughData);
            characterRangeList.remove(companyWithNotEnoughData );
        };
            
        //testprint (  columnIndicesMap ,    dataItemList,  characterRangeList);
        
        Map<String, Map<Integer, Double>>  V_Map = (new SlopeCalculator(dataItemList)).getV();
        Map <Integer,  Map < String, Double >> salesPerYear_Map =
                (new FractionalSalesCalculator (dataItemList) ).getFractionalSalesPerYear();
        
        Map<String, Map<Integer, Double>>  cost_Map = (new SlopeCalculator(dataItemList)).getCosts();
        
        Map <Integer,  Map < String, Double >> rawSalesPerYear_Map = getRawSales (dataItemList) ;
        
        Map <String, Double > Nu_i = (new SlopeCalculator(dataItemList)).getSlopes();
        
        FractionalCharecterCalculator fractionalCharecterCalculator  = new FractionalCharecterCalculator (  characterRangeList);
        Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map = 
                fractionalCharecterCalculator.getFractionalCharacterRangeList();
        Map <String, Map<Integer,  List<Double>> > normalizedFractionalCharacters_Map = 
                fractionalCharecterCalculator.getNormalizedFractionalCharacterRangeList();
        
        SquareTermCalculator squareTermCalculator =  new SquareTermCalculator();
        squareTermCalculator.getMIT_map( salesPerYear_Map);
        squareTermCalculator.getNIT_map( salesPerYear_Map, V_Map);
        squareTermCalculator.getMITJ_Map(fractionalCharaecters_Map);
        
        squareTermCalculator.getSITJ_star_Map(fractionalCharaecters_Map, V_Map);
        
        
        squareTermCalculator.getIS_map(V_Map, normalizedFractionalCharacters_Map);
        squareTermCalculator.getTITJ_star_Map(normalizedFractionalCharacters_Map);
        
        squareTermCalculator.getNIJT_Map (   fractionalCharaecters_Map,  V_Map);
        squareTermCalculator.getSITJ_star_Map_Alternate(V_Map, salesPerYear_Map);
        
        squareTermCalculator.getMPjt_map(fractionalCharaecters_Map, normalizedFractionalCharacters_Map, V_Map);
        
        squareTermCalculator.getSIT_Star(salesPerYear_Map,    V_Map);
        
        squareTermCalculator.getQIJT_map(fractionalCharaecters_Map);
        squareTermCalculator.getJIT_map (  fractionalCharaecters_Map,  Nu_i );
        
        //Histograms.get_Nu_it_histogram(Nu_i);
        //Histograms.get_ISit_histogram(squareTermCalculator.IS_it);        
        //Histograms.getNIT_histogram(squareTermCalculator.nit_map);
        //Histograms.getNIJT_yearly_histogram(squareTermCalculator.nijt_map);
        //Histograms.getSIT_histogram(salesPerYear_Map);
        //Histograms.getSIJT_histogram (fractionalCharaecters_Map) ;
        //Histograms.getSIT_Star_histogram(squareTermCalculator.sit_star_map);
        //Histograms.get_ISit_histogram(V_Map);
        
        /*Histograms . get5Stats_perYear (  squareTermCalculator.IS_it,
                                          squareTermCalculator.  nit_map ,
                                           salesPerYear_Map ,
                                            V_Map,rawSalesPerYear_Map,
                                            cost_Map, "Hindalco Industries Ltd.")        ;*/
        
        //Histograms.getMultiReression(salesPerYear_Map /* small s */ ,
                                     //rawSalesPerYear_Map, /*big S*/
                                     //cost_Map, /* C */
                                     //characterRangeList,"Kalyani Steels Ltd.") ;
                                             
        
        //Histograms.getNIT_histogram(squareTermCalculator.jit_map) ;
        //Histograms.getQIJT_yearly_histogram (squareTermCalculator.qijt_map) ;
        //Histograms.getQIJT_yearly_histogram (squareTermCalculator.pijt_map) ;
        
        
        
        
        
        
        
        
        
        
        
        
        
        //CorrelationCalculator.findCorrelSV(V_Map, salesPerYear_Map);
        //CorrelationCalculator.findCorrelSV(V_Map, rawSalesPerYear_Map);
        
        
        
        //CorrelationCalculator.findCorrelS_NU(salesPerYear_Map, Nu_i);
        //CorrelationCalculator.findCorrelNV( V_Map, squareTermCalculator.nit_map);
        
        //Histograms.getISIT_mean_histogram(squareTermCalculator.IS_it);
        
      
        //Histograms.getNIJT_yearly_histogram(squareTermCalculator.nijt_map);
        //Histograms.getNIJT_companywise_histogram(squareTermCalculator.nijt_map);
        
        //Histograms.get_ISit_histogram(squareTermCalculator.IS_it);
        //Histograms.get_ISit_histogram(V_Map);
        
        //Histograms.getVIT_SIT_mean_histogram(V_Map, salesPerYear_Map);
        //Histograms.getISIT_mean_histogram(squareTermCalculator.IS_it);
        
        
        //Histograms.getNIT_mean_histogram( squareTermCalculator.nit_map);
        //Histograms.getSIJT_histogram(   fractionalCharaecters_Map);
        
        
        //Grapher.getNu_stats(Nu_i);
        //Grapher. getVIT_stats (    V_Map);
        //Grapher.getSIT_stats(salesPerYear_Map);
        //Grapher.getNIT_stats( squareTermCalculator.nit_map);
        //Grapher.getNIJT_stats(squareTermCalculator.nijt_map,  "Garware Technical Fibres Ltd.", squareTermCalculator.nit_map);
        
        TableCreator table = new TableCreator (
        squareTermCalculator.qijt_map,
        V_Map ,
        Nu_i,
        cost_Map,
        salesPerYear_Map,
        squareTermCalculator.jit_map,
        squareTermCalculator.IS_it,
        squareTermCalculator.nit_map,rawSalesPerYear_Map ,dataItemList);
        table.createtables();
        
        
        System.out.println("Completed.") ;
        
    } //end main
    
    public static void testprint ( Map <  Integer, String> columnIndicesMap ,  Map <String, List<List <String>>> dataItemList,
             Map <String, List<List <Double>>> characterRangeList) {
        System.out.println("Columns of interest") ;
        for (Map.Entry <  Integer, String> entry  :columnIndicesMap.entrySet()){
            System.out.println("Columns position " + entry.getKey() + " Column Name " + entry.getValue()) ;
        }
         
        System.out.println("\n dataItemList size" + dataItemList.size()) ;
        System.out.println("\n characterRangeList size" + characterRangeList.size()) ;
        
        for (Map.Entry  <String, List<List<String>>> entry : dataItemList.entrySet()){
            System.out.println ("Compant name " + entry.getKey() + " number of records " +entry.getValue().size()) ;
        }
        
        System.out.println(  "\n<END>");
    }
    
    public static void populateCharacterRange ( Map <String, Map<Integer,  List<Double>> > characterRangeList, String[] columns ,
            String companyName ){
        
        Double value1=null;
        Double value2=null;
        List<Double> characterRange  = new ArrayList<Double>();
         
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Operating Expenses/Total Expenses")]);
        characterRange .add ( value1 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Inventories")]);
        value2 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Sales")]);
        characterRange .add ( value1/value2 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Gross Working Capital")]);
        value2 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Sales")]);
        characterRange .add ( value1/value2 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Selling and Distribution Expenses")]);
        value2 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Sales")]);
        characterRange .add ( value1/value2 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Retained Profits")]);
        value2 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Cash Profit")]);
        characterRange .add ( value1/value2 );
                
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "NFA")]);
        value2 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Sales")]);
        characterRange .add ( value1/value2 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get("Cumulative Retained Profits"  )]);
        value2 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "GFA" )]) -
                 Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Depreciation" )]);
        characterRange .add ( value1/value2 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Cash/CL")]);
        characterRange .add ( value1 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Export/Sales")]);
        characterRange .add ( value1 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Debt to Equity Ratio")]);
        characterRange .add ( value1 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Total Interest Expenses")]);
        value2 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Sales")]);
        characterRange .add ( value1/value2 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get("Total Taxes/Total Income")]);
        characterRange .add ( value1 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get("Current_Ratio")]);
        characterRange .add ( value1 );
        
        value1 = Double.parseDouble(columns [columnIndicesMap_Flipped.get(  "Borrowings")]);
        value2 = Double.parseDouble(columns [columnIndicesMap_Flipped.get( "Sales")]);
        characterRange .add ( value1/value2 );
        
        //characterRangeList.add (characterRange) ;
        int thisYear = Integer.parseInt(columns [columnIndicesMap_Flipped.get( "Year")]);
        Map<Integer,  List<Double>>current = characterRangeList.get (companyName) ;
        if (current == null ) current = new HashMap<Integer,  List<Double>>();
        current.put  (thisYear, characterRange) ;
        characterRangeList.put (companyName,current) ;
        
        //check number of characters 
        if (NUM_CHARECTERS !=characterRange.size()){
            System.err.println("Character range does not have required size "+ NUM_CHARECTERS) ;
            exit (ONE);
        }
        
        
    }
    
    private static boolean isRecordClean ( Collection <String> dataItemList ) {
      
        //ensure that no column is blank
        boolean result = true ;
        int index = ZERO;
        for (String str : dataItemList){
            
            result = (str.trim().length() > ZERO);
            if (!result) {
                //System.err.println("dataitem blank at index "+ index) ;
                break;
            }
            
            index ++;
            
        }
        return result;
    }
    
    private static List<String>     getCompanysWithNotEnoughData ( Map <String, List<Map <String, String>>> dataItemList ){
        List<String> companysWithNotEnoughData = new ArrayList<String> ();
        for ( Map.Entry  <String, List<Map <String, String>>>  entry :  dataItemList.entrySet() ){
            if (entry.getValue().size()<MINIMUM_RECORDS_PER_COMPANY){
                companysWithNotEnoughData.add (entry.getKey() );
            }
        }
        
        return companysWithNotEnoughData;
    }
    
   
    
    private static Map <Integer,  Map < String, Double >> getRawSales (Map <String, List<Map <String, String>>> dataItemList) {
        Map <Integer,  Map < String, Double >> result = new HashMap <Integer,  Map < String, Double >> ();
        
        for (int thisYear = START_YEAR; thisYear <= END_YEAR; thisYear ++) {
            
        
            for (Map.Entry <String, List<Map <String, String>>> entry : dataItemList.entrySet()){
                String thisCompnay = entry.getKey();
                for (  Map<String, String> tuple : entry.getValue()){
                    int year  = Integer.parseInt(tuple.get( "Year"));
                    if (thisYear== year){
                        //
                        Double rawSales =  Double.parseDouble(tuple.get( "Sales"));

                        if (null==result.get(year)) result.put (year, new HashMap < String, Double >());
                        Map < String, Double > map = result.get(year);
                        map.put (thisCompnay, rawSales) ;
                        result.put (year, map) ;

                    }
                }
            }
        }
        
        return result;
    }
    
     
    
}//end class
