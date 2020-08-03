/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textilesproject.math;

import static com.mycompany.textilesproject.Constants.*;
import static com.mycompany.textilesproject.Constants.ZERO;
import static com.mycompany.textilesproject.Parameters.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

/**
 *
 * @author admin
 */
public class Grapher {
    
    public   static void getNIJT_stats (  Map<Integer, TreeMap<String, List<Double>>>  nijt_map, String companyName ,
                                          Map<Integer, Map<String, Double>>  nit_map) {
        for (int year : nijt_map.keySet()){
            List<Double> chars = nijt_map.get(year).get(companyName);
            if (null==chars)continue;
                
            int numPositives = ZERO ;
            double mean = ZERO;
            double max = -MILLION;
            double min =  MILLION;
            int index =ZERO;
            double[] vals = new double [chars.size()] ;
            for (double val :chars){
                vals[index ++] = val;
                mean += val;
                if (min > val) min = val;
                if (max < val) max = val;
                if (val > ZERO) numPositives ++;
            }
            mean = mean / index;
            
            boolean isNIT_Negative = false;
            if (nit_map.get(year).get(companyName)==null || nit_map.get(year).get(companyName)<=ZERO){
                //System.out.println("nit negative");
                isNIT_Negative = true;
            }else {
                //System.out.println("nit postive");
            }
             
            System.out.print( "" + year + "," + mean);
            System.out.print( "," + (isNIT_Negative ? "":numPositives) + "," + (isNIT_Negative ?numPositives: ""));
            System.out.print( ","  + max);
            System.out.print( ","  + min);
            System.out.println( "," +   (new StandardDeviation (false)).evaluate(vals));
             
            /*System.out.println( "year " + year + " mean " + mean);
            System.out.println( "year " + year + " positives " + numPositives);
            System.out.println( "year " + year + " max " + max);
            System.out.println( "year " + year + " minimum " + min);
            System.out.println( "year " + year + " stddev " + (new StandardDeviation (false)).evaluate(vals));*/
        }
    }
        
    public   static void getNIT_stats (  Map<Integer, Map<String, Double>>  nit_map  ) {
        for (int year : nit_map.keySet()){
            int numPositives = ZERO ;
            double mean = ZERO;
            double max = -MILLION;
            double min =  MILLION;
            int index =ZERO;
            double[] vals = new double [nit_map.get(year).size()] ;
            for (double val :nit_map.get(year).values()){
                vals[index ++] = val;
                mean += val;
                if (min > val) min = val;
                if (max < val) max = val;
                if (val > ZERO) numPositives ++;
            }
            mean = mean / index;
            
            System.out.print(   + year + "," + mean);
            System.out.print( ","   + numPositives);
            System.out.print(  "," + max);
            System.out.print(   "," + min);
            System.out.println(   "," + (new StandardDeviation (false)).evaluate(vals));
            
            /*System.out.println( "year " + year + " mean " + mean);
            System.out.println( "year " + year + " positives " + numPositives);
            System.out.println( "year " + year + " max " + max);
            System.out.println( "year " + year + " minimum " + min);
            System.out.println( "year " + year + " stddev " + (new StandardDeviation (false)).evaluate(vals));*/
            
        }  
    }
    
    public   static void getSIT_stats (  Map <Integer,  Map < String, Double >> salesPerYear_Map ) {
        for (int year : salesPerYear_Map.keySet()){
            double[] vals = new double [salesPerYear_Map.get(year).size()] ;
            int index =ZERO;
            double mean = ZERO;
            double max = -MILLION;
            double min =  MILLION;
            for (double val : salesPerYear_Map.get(year).values()){
                vals[index ++] = val;
                mean += val;
                if (min > val) min = val;
                if (max < val) max = val;
            }
            mean = mean / index;
            
            //System.out.println(  year);
            //System.out.println(  mean);
            //System.out.println(  max);
            //System.out.println(  min);
            System.out.println(  (new StandardDeviation (false)).evaluate(vals));
        }
    }

            
    
    public   static void getVIT_stats ( Map<String, Map<Integer, Double>>  V_Map) {
        
        Map<Integer, Double>  yearlyMeans = new HashMap<Integer, Double>();
        Map<Integer, Double>  yearlyStddevs = new HashMap<Integer, Double>();
        
        for (int year = START_YEAR; year <= END_YEAR ; year ++){
            //find mean, sd, and cv; plot them over t
            Map<String, Double > v_year= get_V_year (  year,  V_Map );
            double[] values = new double[v_year.size()] ;
            int index = ZERO;
            double mean = ZERO;
            double max = -MILLION;
            double min =  MILLION;
            for (Double val : v_year.values()){
                values[index ++] = val;
                mean += val;
                if (min > val) min = val;
                if (max < val) max = val;
            }
            mean = mean / v_year.size();
            yearlyMeans.put (year, mean );
            StandardDeviation sd = new StandardDeviation (false) ;
            yearlyStddevs.put (year, sd.evaluate(values));
            
            //System.out.println(year);
            //System.out.println( " " +  mean);
            //System.out.println(" " +  max);
            System.out.println( " " + min);
            //System.out.println( " " + yearlyStddevs.get(year));
            
            //System.out.println( "year " + year + " mean " + mean);
            //System.out.println( "year " + year + " max " + max);
            //System.out.println( "year " + year + " minimum " + min);
            //System.out.println( "year " + year + " stddev " + yearlyStddevs.get(year));
        }
        
        
    
    }
    
    public   static void getNu_stats (Map <String, Double > Nu_i) {
        double[] values = new double[Nu_i.size()] ;
        int index = ZERO;
        double mean = ZERO;
        double max = -MILLION;
        double min =  MILLION;
        for ( Double val: Nu_i.values()){
            values[index ++] = val;
            mean += val;
            if (min > val) min = val;
            if (max < val) max = val;
        }
        StandardDeviation sd = new StandardDeviation (false) ;
        System.out.println("Mean is "+ mean/Nu_i.size()) ;
        System.out.println("Standard dev is "+ sd.evaluate(values)) ;
        
        double intervalSize = (max - min)/TEN;
        Integer [] count = new Integer[TEN];
        for (int pos = ZERO ; pos < TEN ; pos ++) {
            count [pos ] = ZERO; ///init
        }
        for ( Double val: Nu_i.values()){
            //which position does this value fall into ?
            int pos = (int) ((val-min)/intervalSize);
            if (pos >= TEN) pos = TEN -ONE;
            count[pos]++;
        }
        for (int pos = ZERO ; pos < TEN ; pos ++) {
            System.out.println ( "Number of entries in interval [" +(min + pos*intervalSize)+ "," + 
                                 (min + intervalSize*(pos +ONE))  + "] is " +count [pos ]  );
        }
        
    }
    
    private static Map<String, Double > get_V_year (int year, Map<String, Map<Integer, Double>>  V_Map ) {
        Map<String, Double > result = new HashMap <String, Double >();
        for (String str : V_Map.keySet()){
            if (V_Map.get(str).containsKey(year)){
                result.put (str, V_Map.get(str).get(year)) ;
            }
        }
        return result;
    }
    
}
