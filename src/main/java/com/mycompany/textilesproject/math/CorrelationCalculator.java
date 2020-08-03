/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textilesproject.math;

import static com.mycompany.textilesproject.Constants.*;
import static com.mycompany.textilesproject.Parameters.START_YEAR;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

/**
 *
 * @author admin
 */
public class CorrelationCalculator {
    
     public static void findCorrelS_NU (  Map <Integer,  Map < String, Double >> salesPerYear_Map, Map <String, Double > Nu_i){
          System.out.println("\n Correlation coeff between Sit and NU ") ; 
          
          for (int year : salesPerYear_Map.keySet()){
              System.out.print(year) ;
              List<Double>  salesByCompany = new ArrayList<Double>();
              List<Double>  nuByCompany = new ArrayList<Double>();
              for (String name : salesPerYear_Map.get(year).keySet()) {
                  
                  nuByCompany.add (Nu_i.get(name) );
                  salesByCompany.add (salesPerYear_Map.get(year).get(name));
              
              /*double sum = DOUBLE_ZERO;
              int numYears = ZERO;
             
                   Map < String, Double > map = salesPerYear_Map.get(year);
                   if (null!=map.get(name)) {
                       sum += map.get(name);
                       numYears ++;
                   }
              }
              sum = sum / numYears ;
              salesByCompany.add(sum);*/
              }
              System.out.println (","+findCorrelationCoeff(salesByCompany.toArray(new Double[ZERO]), nuByCompany.toArray(new Double[ZERO])));
          }
                           
     }
     
    public static void findCorrelNV ( Map<String, Map<Integer, Double>>  V_Map,  
                                      Map<Integer, Map<String, Double>>  nit_map) {
        
        System.out.println("Correlation coeff between Nit and Vit ") ; 
        System.out.println("Year "  +", Correl coeff ") ; 
        
        for (int year : nit_map.keySet()){
            
            //now get salesList
            List<Double> nitList = new ArrayList <Double> ();
            List<String> avoid = new ArrayList<String>();
            for (String name : nit_map.get(year).keySet()){
                if (nit_map.get(year).get(name) > ZERO) {
                    nitList.add (nit_map.get(year).get(name));
                }else {
                    avoid.add (name );
                }                 
            }
            
            //  prepare v list
            List<Double> vlist = new ArrayList <Double> ();
            for (String name : V_Map.keySet()){
                Double val = V_Map.get(name).get(year);
                if (avoid.contains(name))  continue;
                if (null!=val){
                    vlist.add (val);
                }
            }
            
            System.out.println(year  +"," + findCorrelationCoeff(nitList.toArray(new Double[ZERO]), vlist.toArray(new Double[ZERO])) ) ; 
            
        }
    }
    
    
    
    public static void findCorrelSV ( Map<String, Map<Integer, Double>>  V_Map,  
                                      Map <Integer,  Map < String, Double >> sales_Mapp) {
        
        System.out.println("Correlation coeff between Sit and Vit ") ; 
        System.out.println("Year "  +", Correl coeff ") ; 
        
        for (int year : sales_Mapp.keySet()){
            
            if (year == START_YEAR)  continue;
            
            //first prepare v list
            List<Double> vlist = new ArrayList <Double> ();
            List<Double> salesList = new ArrayList <Double> ();
            
            for (String name : V_Map.keySet()){
                Double val = V_Map.get(name).get(year);
                if (null!=val){
                    vlist.add (val);
                    salesList.add (sales_Mapp.get(year).get(name)) ;
                }
            }
            
            System.out.println(year  +"," + findCorrelationCoeff(salesList.toArray(new Double[ZERO]), vlist.toArray(new Double[ZERO])) ) ; 
            
        }
    }
    
    private static double findCorrelationCoeff (Double[] xArr, Double[] yArr) {
        double[] xArray = new double[xArr.length] ;
        double[] yArray = new double[yArr.length] ;
        
        for (int index = ZERO; index < yArr.length; index ++){
            xArray[index ] = xArr[index];
            yArray[index ] = yArr[index];
        }
        
        PearsonsCorrelation corr = new PearsonsCorrelation ();
        return corr.correlation(xArray, yArray);
        
    }
    
}
