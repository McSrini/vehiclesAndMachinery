/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textilesproject.math;

import static com.mycompany.textilesproject.Constants.*;
import static com.mycompany.textilesproject.Parameters.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap; 
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

/**
 *
 * @author admin
 */
public class Histograms {
    
    public static void getMultiReression (Map <Integer,  Map < String, Double >> salesPerYear_Map /* small s */ ,
                                          Map <Integer,  Map < String, Double >> rawSalesPerYear_Map, /*big S*/
                                          Map<String, Map<Integer, Double>>  cost_Map, /* C */
                                          Map <String, Map<Integer,  List<Double>> > characterRangeList,
                                          String compnayName
                                          ) {
        
        double[] c_over_S = new double[END_YEAR-START_YEAR] ;
        double[] ess = new double[END_YEAR-START_YEAR] ;
        double[][] xChars = new double[END_YEAR-START_YEAR][];
        
        for (int year = START_YEAR+ONE ; year <= END_YEAR ; year ++){
            c_over_S[year - START_YEAR-ONE] = 
                    cost_Map.get(compnayName).get(year)/rawSalesPerYear_Map.get(year).get(compnayName);
            ess [year - START_YEAR-ONE] = salesPerYear_Map.get( year).get(compnayName);
            
            xChars[year - START_YEAR-ONE] = new double [NUM_CHARECTERS] ;
            for (int charNum = ZERO; charNum < NUM_CHARECTERS ; charNum ++ ){
                xChars[year - START_YEAR-ONE][charNum] = characterRangeList.get( compnayName).get(year).get(charNum);
            }
            
        }
        
        OLSMultipleLinearRegression olsregression = new OLSMultipleLinearRegression();
        olsregression.newSampleData(  ess,  xChars);
        double[] regressionparams = olsregression.estimateRegressionParameters() ;
        double[] regressionparams_and_SE =  olsregression.estimateRegressionParametersStandardErrors();
        double adjustedRSquared = olsregression.calculateAdjustedRSquared();
        
        System.out.print(compnayName +",") ;
        for (int index=ZERO; index <regressionparams.length; index ++){
            System.out.print(regressionparams[index] +",") ;
        }
        for (int index=ZERO; index <regressionparams_and_SE.length; index ++){
            System.out.print(regressionparams_and_SE[index] +",") ;
        }
        System.out.println(adjustedRSquared) ; 
    }
    
    public static void get5Stats_perYear (Map <String , Map< Integer,  Double> > IS_it,
                                          Map<Integer, Map<String, Double>>  nit_map ,
                                          Map <Integer,  Map < String, Double >> salesPerYear_Map ,
                                          Map<String, Map<Integer, Double>>  V_Map,
                                          Map <Integer,  Map < String, Double >> rawSalesPerYear_Map,
                                          Map<String, Map<Integer, Double>>  cost_Map, String compnayName
                                          ) {
        
       
        for (int year = START_YEAR +ONE ; year <= END_YEAR ; year ++){
            System.out.print(year+"," );
            System.out.print( V_Map.get(compnayName ).get( year )+ ",");
            System.out.print( cost_Map.get(compnayName ).get( year )/rawSalesPerYear_Map.get(year).get(compnayName) + ",");
            System.out.print( salesPerYear_Map.get(year ).get( compnayName )+ ",");
            System.out.print( IS_it .get( compnayName ).get( year )+ ",");
            System.out.print( nit_map .get(year ).get( compnayName )+ ",");
            System.out.println();
        }
        
    } 
    
    public static void getSIT_Star_histogram ( Map<Integer, Map<String, Double>>  sit_star_map ) {
        Map < String , Boolean> allcompanies = new HashMap<String , Boolean> ();
        for (Integer  year =START_YEAR+ONE ; year <= END_YEAR;year++){             
            for (String companyName : sit_star_map.get(year).keySet()){
                allcompanies.put (companyName, true) ;
            }
        }
        
        System.out.print("Year,") ;
        for (String name : allcompanies.keySet()){
            System.out.print(name+",") ;
        }
        System.out.println();
        
        for (Integer  year =START_YEAR +ONE; year <= END_YEAR;year++){        
             System.out.print(year+",") ;
             for (String name : allcompanies.keySet()){
                 if (null!= sit_star_map.get(year).get( name)) {
                     System.out.print(sit_star_map.get(year).get( name));
                 }
                 System.out.print(",");
             }
             System.out.println();
        }
        
    }
    
    public static void get_Nu_it_histogram (  Map <String, Double > Nu_i ) {
        for (String name :Nu_i.keySet()){
            System.out.println(name + ","+ Nu_i.get(name)) ;
        }
    }
    
    public static void get_ISit_histogram (Map <String , Map< Integer,  Double> > IS_it ) {
        System.out.print ("Name,") ;
        for (int year =START_YEAR; year <=END_YEAR ; year ++){
             System.out.print (year+",") ;
        }
        System.out.println();
        
        for (String name : IS_it.keySet()){
           
            System.out.print( name + ",");
            for (int year =START_YEAR; year <=END_YEAR ; year ++){
                Double val = IS_it.get(name).get(year);
                if (null==val || ZERO>=val){
                    System.out.print(  ",") ;
                }else{
                    System.out.print(val + ",") ;
                }
            }
            System.out.print( "\n") ;
        }
        
    }
    
    public static void getNIJT_companywise_histogram (Map<Integer, TreeMap<String, List<Double>>>  nijt_map ){
        Map <String , Boolean > allCompanyNames = new HashMap <String , Boolean >();
        for (int year : nijt_map.keySet()){
            for (String name : nijt_map.get(year).keySet()){
                allCompanyNames.put (name, true);
            }
        }
        
        System.out.print("Name;");
        for (int year = START_YEAR; year <=END_YEAR; year++){
             System.out.print(year+";");
        }
        System.out.println();
        
        for (String name : allCompanyNames.keySet()){
            System.out.print(name+";") ;
            for (int year = START_YEAR; year <=END_YEAR; year++){
                 
                List<Double> chars = nijt_map.get(year).get(name);
                boolean isEmpty = true ;
                for (int charNum = ZERO; charNum <NUM_CHARECTERS; charNum ++) {
                    boolean val = (chars!=null && chars.get(charNum)>ZERO) ;
                    if (val ) {
                        if (isEmpty){
                            isEmpty= false;
                            System.out.print(ONE+charNum) ;
                        }else {
                            System.out.print(","+(ONE+charNum)) ;
                        }
                        
                    } 
                }
                System.out.print(";");
            }
            System.out.println();
        }
    }
    
    public static void getNIJT_yearly_histogram (Map<Integer, TreeMap<String, List<Double>>>  nijt_map ){
        for (int year = START_YEAR +ONE; year <= END_YEAR ; year ++){
            System.out.println("\n NIJT matrix for year " + year) ;
            
            for (String name : nijt_map.get(year).keySet()){
                System.out.print(name +",") ;
                for (Double val: nijt_map.get(year).get(name)){
                     System.out.print((val >=ZERO ? val: "") +",") ;
                }
                System.out.println();
            }
            
        }
    }
    
    public static void getQIJT_yearly_histogram (Map<Integer, TreeMap<String, List<Double>>>  qijt_map ){
        for (int year = START_YEAR +ONE; year <= END_YEAR ; year ++){
            System.out.println("\n NIJT matrix for year " + year) ;
            
            for (String name : qijt_map.get(year).keySet()){
                System.out.print(name +",") ;
                for (Double val: qijt_map.get(year).get(name)){
                     System.out.print((val >=ZERO && val!=Double.NaN ? val: "") +",") ;
                }
                System.out.println();
            }
            
        }
    }
    
    public static void getNIT_histogram (Map<Integer, Map<String, Double>>  nit_map ){
        Map <String , Boolean > allCompanyNames = new HashMap <String , Boolean >();
        for (int year : nit_map.keySet()){
            for (String name : nit_map.get(year).keySet()){
                allCompanyNames.put (name, true);
            }
        }

        System.out.print("   Company, ");
        for (String name : allCompanyNames .keySet()){
            System.out.print( name + ", " );
        }
        System.out.println();
        
        for (int year : nit_map.keySet()){
            System.out.print(year+",");
            for (String name : allCompanyNames .keySet()){
                //
                double nit =  nit_map.get(year).get(name)==null? -ONE: nit_map.get(year).get(name);
                if (nit >ZERO) {
                    System.out.print(     nit + ", ") ;
                }else {
                    //System.err.println(nit);
                    System.out.print(      ", ") ;
                }
            }
            System.out.println();
        }
        
        
    }
    
    public static void getSIJT_histogram( Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map){
        for (int year = START_YEAR ; year <=END_YEAR; year++){
            System.out.println(year) ;
            for (String name : fractionalCharaecters_Map.keySet()){
                List<Double> chars = fractionalCharaecters_Map.get ( name).get(year);
                if (chars!=null){
                    System.out.print(name+",") ;
                    for (Double charecterstic : chars){
                        System.out.print(charecterstic+",") ;    
                    }
                    System.out.println();
                }
            }
        }
    }
    
    public static void getSIT_histogram ( Map <Integer,  Map < String, Double >> salesPerYear_Map ){
        Map <String , Boolean > allCompanyNames = new HashMap <String , Boolean >();
        for (int year : salesPerYear_Map.keySet()){
            for (String name : salesPerYear_Map.get(year).keySet()){
                allCompanyNames.put (name, true);
            }
        }
        
        System.out.print("   Year, ");
        for (String name : allCompanyNames .keySet()){
            System.out.print( name + ", " );
        }
        System.out.println();
        
        for (int year : salesPerYear_Map.keySet()){
            System.out.print( year + ", " );
            for (String name : allCompanyNames.keySet()){
                Double val = salesPerYear_Map.get(year).get( name);
                if (null!=val) System.out.print( val );
                System.out.print(  "," );
            }
            
            System.out.println();
        }
    }
    
    public static void getNIT_mean_histogram (Map<Integer, Map<  String , Double>>  NIT_Map){  
        Map<String, List<Double>> mean = new TreeMap<String, List<Double>>();
        for (int year : NIT_Map.keySet()){
            for (String name : NIT_Map.get(year).keySet()){
                List<Double> current = mean.get(name);
                if (null==current)current = new ArrayList<Double> ();
                
                Double thisVal = NIT_Map.get(year).get(name) ;
                if (!(thisVal.isInfinite() || thisVal.isNaN() || thisVal < ZERO)) current.add (thisVal ) ;
                mean.put (name, current);
            }
        }
        
        for (String name: mean.keySet()){
            double sum = ZERO;
            for (Double val : mean.get(name)){
                sum += val;
            }
            if (ZERO< mean.get(name).size()){
                sum = sum/mean.get(name).size();
                System.out.println(name + ","+ sum);
            }else {
                System.out.println ("No positive entires for "+ name) ;
            }
            
        }
        
    }
    
    public static void getVIT_SIT_mean_histogram (Map<String, Map<Integer, Double>>  V_Map,  
                                                  Map <Integer,  Map < String, Double >> salesPerYear_Map){
          
        Map<String, Double> mean = new TreeMap<String, Double>();
        
        for (String name : V_Map.keySet()){
            double sum = ZERO;
            double count = ZERO;
            for (int year:V_Map.get(name).keySet() ){
                double val = V_Map.get(name).get(year);
                count ++;
                sum+=val* salesPerYear_Map.get (year).get(name);
                //System.out.println ("value is " + val + " and sit is "+ salesPerYear_Map.get (year).get(name)) ; 
            }
            if (count > ZERO) {
                mean.put (name, sum/count) ;
            }else {
                System.err.println("count is zero");
            }
        }
        
        for (String name :mean.keySet()){
            System.out.println(name + ","+ mean.get(name) );
        }
        
    }
    
    public static void getISIT_mean_histogram (Map <String , Map< Integer,  Double> > IS_it){
        Map<String, Double> mean = new TreeMap<String, Double>();
        
        for (String name : IS_it.keySet()){
            double sum = ZERO;
            double count = ZERO;
            for (Double val:IS_it.get(name).values()){
                if (val.isInfinite() || val.isNaN() || val <= ZERO) continue;
                count ++;
                sum+=val;
            }
            if (count > ZERO) {
                mean.put (name, sum/count) ;
            }else {
                System.err.println("count is zero");
            }
        }
        
        for (String name :mean.keySet()){
            System.out.println(name + ","+ mean.get(name) );
        }
    }
    
  
    
    public static void getVIT_mean_histogram (Map<String, Map<Integer, Double>>  V_Map){
        Map<String, Double> mean = new TreeMap<String, Double>();
        
        for (String name : V_Map.keySet()){
            double sum = ZERO;
            double count = ZERO;
            for (double val:V_Map.get(name).values()){
                count ++;
                sum+=val;
            }
            if (count > ZERO) {
                mean.put (name, sum/count) ;
            }else {
                System.err.println("count is zero");
            }
        }
        
        for (String name :mean.keySet()){
            System.out.println(name + ","+ mean.get(name) );
        }
    }
    
    public static void getVIT_histogram (Map<String, Map<Integer, Double>>  V_Map){
        System.out.print("Company,") ;
        for (int year = START_YEAR ; year <=END_YEAR ; year ++) {
             System.out.print(year + ",") ;
        }
        System.out.println();
        
        for (String name : V_Map.keySet()){
            System.out.print(name) ;
            for (int year = START_YEAR ; year <=END_YEAR ; year ++) {
                 Double val = V_Map.get(name).get(year);
                 if (null!=val){
                      System.out.print(val) ;
                 } 
                 System.out.print(",") ;
            }
            System.out.println();
        }
    }
    
    public static void getNIJT_histogram (Map<Integer, TreeMap<String, List<Double>>>  nijt_map){
        
        Map <String , Boolean > allCompanyNames = new HashMap <String , Boolean >();
        for (int year : nijt_map.keySet()){
            for (String name : nijt_map.get(year).keySet()){
                allCompanyNames.put (name, true);
            }
        }
        
        System.out.print("   Company, ");
        for (String name : allCompanyNames .keySet()){
            System.out.print( name + ", " );
        }
        System.out.println();
        
        for (int year : nijt_map.keySet()){
            System.out.print(year+",");
            for (String name : allCompanyNames .keySet()){
                //
                System.out.print(     getNIJT_histogram( nijt_map ,   name,   year) + ", ") ;
            }
            System.out.println();
        }
    }
    
    private static int getNIJT_histogram (Map<Integer, TreeMap<String, List<Double>>>  nijt_map , String companyName, int year) {
        int result = -ONE;
        
         Map<String, List<Double>> map = nijt_map.get (year );
         if (map !=null) {
              List<Double> list = map.get(companyName );
              if (null!=list){
                  result = ZERO;
                  for (Double listItem:list ){
                      if (listItem>ZERO) result++;
                  }
              }
         }
        
        return result;
    }
    
}
