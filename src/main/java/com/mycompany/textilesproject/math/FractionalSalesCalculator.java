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

/**
 *
 * @author admin
 */
public class FractionalSalesCalculator {
    
    private  Map <String, List<Map <String, String>>> dataItemList;
    
    public FractionalSalesCalculator ( Map <String, List<Map <String, String>>> dataItemList) {
        this.dataItemList = dataItemList;
    }
    
    public  Map <Integer,  Map < String, Double >> getFractionalSalesPerYear (   ){
       Map <Integer,  Map < String, Double >> result = new HashMap <Integer,  Map < String, Double >>();
       for (int thisYear =START_YEAR; thisYear <=END_YEAR ;thisYear++ ){
           result.put (thisYear, getFractionalSalesPerYear(  thisYear)) ;
       }
       return result;
    }
    
    private  Map < String, Double > getFractionalSalesPerYear (int thisYear  ){
        Map < String, Double > result = new HashMap < String, Double >();
        
        //sum across all companys for the given year
        double yearlySum = ZERO;
        for (Map.Entry <String, List<Map <String, String>>> entry : dataItemList.entrySet()){
            String thisCompnay = entry.getKey();
            for (  Map<String, String> tuple : entry.getValue()){
                int year  = Integer.parseInt(tuple.get( "Year"));
                if (thisYear== year){
                    //
                    yearlySum += Double.parseDouble(tuple.get( "Sales"));
                    result .put (thisCompnay, Double.parseDouble(tuple.get( "Sales"))) ;
                }
            }
        }
        
        //divide every value by the  yearlySum
        for (String companyName : result.keySet()){
            double newValue = result.get(companyName)/yearlySum;
            result.put(companyName, newValue);            
        }
        
        return result;
    }
    
}
