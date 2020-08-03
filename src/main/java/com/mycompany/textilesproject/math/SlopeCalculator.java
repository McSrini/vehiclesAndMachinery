/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textilesproject.math;

import static com.mycompany.textilesproject.Constants.*;
import static com.mycompany.textilesproject.Parameters.*;
import static java.lang.System.exit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.analysis.function.Log;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 *
 * @author admin
 */
public class SlopeCalculator {
    
     private static SimpleRegression regressionEngine = new SimpleRegression ();
     private  Map <String, List<Map <String, String>>> dataItemList;
     
     public SlopeCalculator ( Map <String, List<Map <String, String>>> dataItemList) {
         this.dataItemList = dataItemList;
     }
     
     public Map<String, Map<Integer, Double>> getCosts () {
         Map<String, Map<Integer, Double>> result = new HashMap<String, Map<Integer, Double>>();
         for (Map.Entry <String, List<Map <String, String>>> entry : dataItemList.entrySet()){
             
             String companyName = entry.getKey() ;
             Map<Integer, Double> innerMap = new HashMap<Integer, Double> ();
             
             for ( Map <String, String> dataPoint : entry.getValue()    ){
                 int thisYear  = Integer.parseInt(dataPoint.get( "Year"));
                 double sales =Double.parseDouble(dataPoint.get( "Sales"));
                 double profit =  Double.parseDouble( dataPoint.get( "Cash Profit"));     
                 double cost = sales - profit;
                 
                 if (ZERO==sales) {
                     System.err.println("error: Sales is zero !!");
                     exit (ONE);
                 }
                 
                 innerMap.put (thisYear,cost ) ;
             }
             
             result.put (companyName, innerMap) ;
             
         }
         return result;
     }
     
     public Map<String, Map<Integer, Double>> getV () {
         Map<String, Map<Integer, Double>> result = new HashMap<String, Map<Integer, Double>>();
         Map <String, Double >  companySlopes= getSlopes ();
         
         for (Map.Entry <String, List<Map <String, String>>> entry : dataItemList.entrySet()){
             
             String companyName = entry.getKey() ;
             double slope = companySlopes.get(companyName);
             Map<Integer, Double> innerMap = new HashMap<Integer, Double> ();
             
             for ( Map <String, String> dataPoint : entry.getValue()    ){
                 int thisYear  = Integer.parseInt(dataPoint.get( "Year"));
                 double sales =Double.parseDouble(dataPoint.get( "Sales"));
                 double profit =  Double.parseDouble( dataPoint.get( "Cash Profit"));     
                 double cost = sales - profit;
                 double valueForInsertion = ONE - slope * (cost/sales) ;
                 
                 if (ZERO==sales) {
                     System.err.println("error: Sales is zero !!");
                     exit (ONE);
                 }
                 
                 innerMap.put (thisYear,valueForInsertion ) ;
             }
             
             result.put (companyName, innerMap) ;
             
         }
         
         
         
         return result;
     }
     
     public  Map <String, Double > getSlopes (){
         Map <String, Double >  result = new HashMap   <String, Double > ();
         
         for (Map.Entry <String, List<Map <String, String>>> entry: dataItemList.entrySet()){
             double slope = getSlope (entry.getValue());
             result .put (entry.getKey(), slope  );
             //System.out.println("Company " + entry.getKey() + " Slope "+slope ) ;
         }
             
         return result;
     }
     
     private Double getSlope (List<Map <String, String>> dataPoints){
         regressionEngine = new SimpleRegression ();
         for (Map <String, String> datapoint : dataPoints){
             double sales =Double.parseDouble(datapoint.get( "Sales"));
             double profit =  Double.parseDouble( datapoint.get( "Cash Profit"));     
             double cost = sales - profit;
             double logSales = (new Log()).value(sales);
             double logCost = (new Log()).value(cost);
             regressionEngine.addData (logSales,logCost ) ;
         }
         return regressionEngine.getSlope();
     }
    
}
