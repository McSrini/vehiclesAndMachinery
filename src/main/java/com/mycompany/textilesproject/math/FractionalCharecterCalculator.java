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

/**
 *
 * @author admin
 */
public class FractionalCharecterCalculator {
     
    private  Map <String, Map<Integer,  List<Double>> > characterRangeList;
    
    //return value
    private Map <String, Map<Integer,  List<Double>> > fractionalCharectersList = new HashMap <String, Map<Integer,  List<Double>> > ();
   
    public FractionalCharecterCalculator ( Map <String, Map<Integer,  List<Double>> > characterRangeList) {
        this.characterRangeList = characterRangeList;         
    }
    
    public Map <String, Map<Integer,  List<Double>> > getNormalizedFractionalCharacterRangeList (){
        Map <String, Map<Integer,  List<Double>> > result = new HashMap <String, Map<Integer,  List<Double>> > () ;
        
        for (String name : fractionalCharectersList.keySet()){
            
            Map<Integer,  List<Double>> innerMap = new HashMap<Integer,  List<Double>> ();
            
            for (Integer year : fractionalCharectersList.get(name).keySet()){
                List<Double> charList = fractionalCharectersList.get(name).get(year);
                Double sum = DOUBLE_ZERO;
                for (int index = ZERO ; index < charList.size(); index ++){
                    sum += charList.get(index);
                }
                List<Double> newList = new ArrayList<Double> () ;
                for (int index = ZERO ; index < charList.size(); index ++){
                    newList.add (charList.get(index)/sum);
                }
                
                innerMap.put (year, newList) ;
                
            }
            result.put(name, innerMap);
        }
        
        return result;
    }
    
    public Map <String, Map<Integer,  List<Double>> > getFractionalCharacterRangeList (){
                
        //key is char position, then year
        Map <Integer, Map<Integer, Double>>     yearlySum  =  new HashMap <Integer, Map<Integer, Double>>    ();
        for (int charIndex=ZERO; charIndex <NUM_CHARECTERS; charIndex++){
            Map<Integer, Double> innermap = new HashMap<Integer, Double> ();
            for (int year = START_YEAR; year <=   END_YEAR; year  ++){                       
               
                innermap.put ( year, getYearlySum (  charIndex , year  ));
            }
            yearlySum.put (charIndex, innermap );
            
        }
        
        //every char value must be divided by the yearly sum
        for (String companyName : characterRangeList.keySet()){
            Map<Integer,  List<Double>> newMap = new HashMap<Integer,  List<Double>> ();
            for (int thisYear :characterRangeList.get( companyName).keySet() ){
                List<Double> currentList = characterRangeList.get( companyName).get(thisYear );
                List<Double> newList = new ArrayList<Double>();
                
                int charIndex = ZERO;
                for (Double charecter: currentList){
                    newList.add (charecter/yearlySum.get(charIndex).get(thisYear)) ;
                    charIndex ++;
                }
                
                //updated list is ready
                
                newMap.put (thisYear, newList);
                
                
            }
            fractionalCharectersList.put (companyName, newMap) ;
        }
        
        return fractionalCharectersList;
    }
    
    private Double getYearlySum (int charIndex , int thisYear) {
        
        Double sum = DOUBLE_ZERO;
        for (String thisCompany :characterRangeList.keySet() ){
            if (null!=characterRangeList.get(thisCompany ).get( thisYear)){
                sum+= characterRangeList.get(thisCompany ).get( thisYear).get( charIndex);                
            }
        }
        return sum; 
    } 
    
}
 