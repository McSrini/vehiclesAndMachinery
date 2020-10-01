/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textilesproject.math;

import static com.mycompany.textilesproject.Constants.*;
import com.mycompany.textilesproject.Parameters;
import static com.mycompany.textilesproject.Parameters.END_YEAR;
import static com.mycompany.textilesproject.Parameters.NUM_CHARECTERS;
import static com.mycompany.textilesproject.Parameters.START_YEAR;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author admin
 */
public class SquareTermCalculator {
    
    public Map<Integer, Map<String, Double>>  mit_map   = new HashMap <Integer ,Map<String, Double>>  () ;
    public Map<Integer, Map<String, Double>>  nit_map  = new HashMap <Integer ,Map<String, Double>>  () ;
    public Map<Integer, Map<String, Double>>  sit_star_map  = new HashMap <Integer ,Map<String, Double>>  () ;
    
    public Map<Integer, TreeMap<String, List<Double>>>  nijt_map  = new TreeMap <Integer ,TreeMap<String, List<Double>>>  () ;
    public Map<Integer, TreeMap<String, List<Double>>>  qijt_map  = new TreeMap <Integer ,TreeMap<String, List<Double>>>  () ;
    public Map<Integer, TreeMap<String, List<Double>>>  pijt_map  = new TreeMap <Integer ,TreeMap<String, List<Double>>>  () ;
    public Map<Integer, Map<String, Double>>  jit_map  = new TreeMap <Integer ,Map<String, Double>>  () ;
    
    public Map <Integer, Map< String,  List<Double>> >  mitj_map = new HashMap <Integer, Map< String,  List<Double>> >();
    public Map <Integer, Map< String,  List<Double>> > sitj_star_map = new HashMap <Integer, Map< String,  List<Double>> >();
    
     public Map <Integer, Map< String,  Map<Integer,Double>> > sitj_star_map_alternate = 
             new HashMap <Integer, Map< String,  Map<Integer,Double>> >();
    
    public Map <String , Map< Integer,  Double> > IS_it = new HashMap <String , Map< Integer,  Double> >();
    
    public Map <String , Map< Integer,  List<Double>> > titj_star_map = new HashMap <String, Map<Integer ,  List<Double>> >();
    
    public Map<Integer,  List< Double>>  MPjt_map   = new HashMap<Integer,  List< Double>>   () ;
    
    public TreeMap<String, TreeMap <Integer, Double> > I_i_t = new  TreeMap<String, TreeMap <Integer, Double> >();
    
    public void getMPjt_map (Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map,
                             Map <String, Map<Integer,  List<Double>> > normalizedFractionalCharacters_Map,
                             Map<String, Map<Integer, Double>>  V_Map) {
        
        for (Integer  year =START_YEAR ; year <= END_YEAR;year++){
            
            List<String> cmpaniesForThisYear = getCompaniesForThisYear(year, fractionalCharaecters_Map) ;
            final int numCompanies =  cmpaniesForThisYear.size();
            if (ZERO==numCompanies)continue;
            
            List<Double> value = new ArrayList<Double>();
            
            for (int jj=ZERO; jj < NUM_CHARECTERS; jj++) {
                Double value_at_jj = DOUBLE_ZERO;
                for (String companyName : cmpaniesForThisYear){
                    double vit = V_Map.get (companyName).get ( year);
                    List< Double> fractionalChars = fractionalCharaecters_Map.get (companyName ).get (year );
                    List< Double> normalizedFractionalChars = normalizedFractionalCharacters_Map.get (companyName ).get (year );
                    value_at_jj += vit * fractionalChars.get(jj)* normalizedFractionalChars.get(jj);
                }     
                value.add (value_at_jj );
            }
            
            MPjt_map.put (year,value );
        }    
        
    }
    
    public void getIS_map ( Map<Integer, TreeMap<String, List<Double>>>  nijt_map  , 
                            Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map        ) {
        
        for (Integer  year =START_YEAR ; year <= END_YEAR;year++){
            List<String> cmpaniesForThisYear = getCompaniesForThisYear(year, fractionalCharaecters_Map) ;
            final int numCompanies =  cmpaniesForThisYear.size();
            if (ZERO==numCompanies)continue;
            
            for (String companyName : cmpaniesForThisYear){
                
                if (!nijt_map.containsKey( year) || !nijt_map.get( year).containsKey(companyName)) continue;
                
                List<Double> charecters = fractionalCharaecters_Map.get (companyName) .get (year);
                 
                double numerator = ZERO;
                double denominator = ZERO;
                
                for (int jj = ZERO; jj< charecters.size(); jj ++){
                    double nijt = nijt_map.get( year).get (companyName) .get(jj);
                    if (nijt < ZERO) nijt = ZERO;
                    numerator += nijt ;
                    
                    double sijt = fractionalCharaecters_Map.get (companyName) .get (year).get(jj);
                    denominator += sijt;
                    
                }
                
                double isit = numerator/denominator;
                
                // Map <String , Map< Integer,  Double> > IS_it 
                Map< Integer,  Double>  innerMap = IS_it.get( companyName);
                if (innerMap==null )  innerMap= new HashMap < Integer,  Double> ();
                innerMap.put (year, isit) ;
                IS_it.put ( companyName, innerMap);
                                
                if (year == 2015 && companyName.equalsIgnoreCase("Bosch Ltd.")){
                     System.out.println(companyName + " " + year + " DEBUG") ;
                     System.out.println() ;
                     for (int jj = ZERO; jj< charecters.size(); jj ++){
                         System.out.println("nijt " +  nijt_map.get( year).get (companyName) .get(jj)) ;
                         //System.out.println("sijt " +  fractionalCharaecters_Map.get (companyName) .get (year).get(jj)) ;
                     }
                }
                
                
            }
        }
        
    }
    
    /*public void getIS_map (Map<String, Map<Integer, Double>>  V_Map,
                           Map <String, Map<Integer,  List<Double>> > normalizedFractionalCharacters_Map) {
        
        for (String name : normalizedFractionalCharacters_Map.keySet()){
            for (int year : normalizedFractionalCharacters_Map.get(name).keySet()){
                
                 
                
                Map< Integer,  Double>  innermap = IS_it.get( name);
                if (null==innermap) innermap = new HashMap< Integer,  Double>();
                
                Double value = DOUBLE_ZERO;
                final  List<Double> charecters = normalizedFractionalCharacters_Map.get(name).get(year);
                for (int jj = ZERO; jj < charecters.size(); jj++){
                    double sijt = sitj_star_map.get(year).get(name).get(jj);
                    if (sijt> ZERO){
                        value += sijt * charecters.get(jj);
                    }
                   
                }
                
                
                
                innermap.put (year, V_Map.get(name).get(year)*value);
                IS_it.put(name, innermap);
                
                if (year == 2015 && name.equalsIgnoreCase("Bosch Ltd.")){
                    double temp = IS_it.get(name).get( year);
                    System.out.println();
                }
                
            }
        }
        
    }  */
    
    public void getTITJ_star_Map( Map <String, Map<Integer,  List<Double>> > normalizedFractionalCharacters_Map){
        for (String companyName : normalizedFractionalCharacters_Map.keySet()){
            for (Integer year : normalizedFractionalCharacters_Map.get(companyName).keySet()){
                List<Double> newchars = getTITJ_Vector(companyName, year, normalizedFractionalCharacters_Map);
                Map< Integer,  List<Double>> innerMap = titj_star_map.get (companyName);
                if (null==innerMap) innerMap = new HashMap< Integer,  List<Double>>();
                innerMap.put (year,newchars ) ;
                titj_star_map.put (companyName,innerMap) ;
            }
        }
    }
    
  
    
    public void getSITJ_star_Map_Alternate (Map<String, Map<Integer, Double>>  V_Map,  
                                            Map <Integer,  Map < String, Double >> salesPerYear_Map) {
        //
        for (Integer  year :nijt_map.keySet()){            
            
            for (String companyName : nijt_map.get(year).keySet()){
                List<Double> values = nijt_map.get(year).get( companyName);
                for (int jj = ZERO; jj < values.size(); jj++){
                    Double nijt = values.get(jj);
                    double vit = V_Map.get(companyName).get(year);
                    double sit = salesPerYear_Map.get(year).get(companyName);
                    if (nijt<=ZERO || vit*sit  <= ZERO){
                        
                    }else {
                        double sijt_alternate = nijt /(vit*sit) ;
                        //insert into alternate map
                        insertInto_AlternateSIJT_Map (year,companyName, jj,  sijt_alternate) ;
                    }
                }
            }
            
        }
    }
    
    public void getSIT_Star (Map <Integer,  Map < String, Double >> salesPerYear_Map, 
                             Map<String, Map<Integer, Double>>  V_Map ) {
        //
        for (Integer  year =START_YEAR ; year <= END_YEAR;year++){
            int numCampanies = salesPerYear_Map.get(year).keySet().size();
            for (String companyName : salesPerYear_Map.get(year).keySet()){
                
                double numerator = ZERO;
                for (String innerCompanyName : salesPerYear_Map.get(year).keySet()){
                    if (innerCompanyName.equals(companyName )) continue;
                    double skt = salesPerYear_Map.get(year).get(innerCompanyName );
                    numerator += skt*skt * V_Map.get(innerCompanyName ).get( year);
                    
                    //System.out.println("Skt "+ skt + " vkt "+V_Map.get(innerCompanyName ).get( year) + " numerator "+ numerator);
                    
                }
                
                numerator = numerator/(ONE - numCampanies);
                //System.out.println("numCampanies "+ numCampanies);
                
                //System.out.println("sit "+ salesPerYear_Map.get(year).get(companyName )) ;
                //System.out.println("vit " +  V_Map.get(companyName ).get( year) );
                        
                double sit_star = salesPerYear_Map.get(year).get(companyName ) +
                        (numerator / (V_Map.get(companyName ).get( year) * salesPerYear_Map.get(year).get(companyName ))) ;
                
                 
                
                if (sit_star_map.get(year )==null) {
                    Map<String, Double> yearmap = new HashMap<String, Double> ();
                    sit_star_map.put (year, yearmap) ;
                }
                Map<String, Double> yearmap = sit_star_map.get(year );
                yearmap.put (companyName, sit_star) ; 
                sit_star_map.put(year ,yearmap );
                
            }
        }
        
    }
    
    public void get_IIT_Map  ( Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map,  
                                   Map<String, Map<Integer, Double>>  V_Map) {
        
        for (Integer  year =START_YEAR ; year <= END_YEAR;year++){
            List<String> cmpaniesForThisYear = getCompaniesForThisYear(year, fractionalCharaecters_Map) ;
            final int numCompanies =  cmpaniesForThisYear.size();
            if (ZERO==numCompanies)continue;
            
            for (String companyName : cmpaniesForThisYear){
                List<Double> charecters = fractionalCharaecters_Map.get (companyName) .get (year);
                
                double squareTerm = ZERO;
                double linearTerm = ZERO;
                
               
                
                for (int jj = ZERO; jj< charecters.size(); jj ++){
                    //
                    double sijt = fractionalCharaecters_Map.get(companyName).get(year).get(jj);
                    squareTerm += (sijt*sijt);
                    linearTerm += sijt;
                }
                
                if (year == 2015 && companyName.equalsIgnoreCase("Modern Steels Ltd.")){
                     System.out.println();
                }
                
                double iit = V_Map.get(companyName).get(year);
                iit *= squareTerm;
                iit = iit/ linearTerm;
                
                //insert TreeMap <Integer, Double>
                
                TreeMap <Integer, Double> innerMap =  this.I_i_t.get(companyName );
                if (null==innerMap) innerMap = new TreeMap <Integer, Double>();
                innerMap.put ( year, iit);
                this.I_i_t.put (companyName, innerMap) ;
                
                if (year == 2015 && companyName.equalsIgnoreCase("Modern Steels Ltd.")){
                     System.out.println(companyName + " " + year + " DEBUG IIT") ;
                     System.out.println("VIT " + V_Map.get(companyName).get(year)) ;
                     for (int jj = ZERO; jj< charecters.size(); jj ++){
                         double sijt = fractionalCharaecters_Map.get (companyName) .get (year).get(jj);
                         System.out.println("sijt squared" +  sijt*sijt*V_Map.get(companyName).get(year)) ;
                     }
                }
                
            }//loop on company name
            
        }//loop on year
        
    }
    
    public void getSITJ_star_Map ( Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map,  
                                   Map<String, Map<Integer, Double>>  V_Map) {
        
        for (Integer  year =START_YEAR ; year <= END_YEAR;year++){
            List<String> cmpaniesForThisYear = getCompaniesForThisYear(year, fractionalCharaecters_Map) ;
            final int numCompanies =  cmpaniesForThisYear.size();
            if (ZERO==numCompanies)continue;
            
            for (String companyName : cmpaniesForThisYear){
            
                List<Double> charecters = fractionalCharaecters_Map.get (companyName) .get (year);
                List<Double> newCharecterList = new ArrayList<Double>();
                for (int jj = ZERO; jj< charecters.size(); jj ++){
                    double squareterm = ZERO;
                     
                    for (String thisName :cmpaniesForThisYear  ){
                        if (thisName.equals( companyName)) continue;
                         
                        double sijt = fractionalCharaecters_Map.get(thisName).get(year).get(jj);
                        squareterm += sijt*sijt * V_Map.get(thisName).get(year);
                        
                        
                    }
                    double sijt =fractionalCharaecters_Map.get(companyName).get(year).get(jj);
                    double denominator = (ONE- numCompanies) *V_Map.get( companyName).get(year)*sijt;
                    double sijt_star = (squareterm /denominator) +sijt  ;
                    newCharecterList.add (sijt_star);
                }
                
                Map<String,  List<Double>> innerMap = sitj_star_map.get ( year) ;
                if (null==innerMap) innerMap = new HashMap<String,  List<Double>>();
                innerMap.put (companyName, newCharecterList) ;
                sitj_star_map.put (year, innerMap);
                
            }
            
        }
    }
    
    
    public void getMITJ_Map ( Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map) {
        
        for (Integer  year =START_YEAR ; year <= END_YEAR;year++){
            List<String> cmpaniesForThisYear = getCompaniesForThisYear(year, fractionalCharaecters_Map) ;
            final int numCompanies =  cmpaniesForThisYear.size();
            if (ZERO==numCompanies)continue;
                
            for (String companyName : cmpaniesForThisYear){
            
                List<Double> charecters = fractionalCharaecters_Map.get (companyName) .get (year);
                List<Double> newCharecterList = new ArrayList<Double>();
                for (int jj = ZERO; jj< charecters.size(); jj ++){
                    double squareterm = ZERO;
                    for (String thisName :cmpaniesForThisYear  ){
                        if (thisName.equals( companyName)) continue;
                         
                        double sijt = fractionalCharaecters_Map.get(thisName).get(year).get(jj);
                        squareterm += sijt*sijt;
                    }
                    double sijt =fractionalCharaecters_Map.get(companyName).get(year).get(jj);
                    double mijt = (squareterm /(ONE- numCompanies)) +sijt *sijt;
                    newCharecterList.add (mijt);
                }
                
                Map<String,  List<Double>> innerMap = mitj_map.get ( year) ;
                if (null==innerMap) innerMap = new HashMap<String,  List<Double>>();
                innerMap.put (companyName, newCharecterList) ;
                mitj_map.put (year, innerMap);
            }
            
        }
    }
    
    public void getMIT_map ( Map <Integer,  Map < String, Double >> salesPerYear_Map) {
        
        for (int year : salesPerYear_Map.keySet()){
            final int numCompanies =  salesPerYear_Map.get(year).keySet().size();
            for (String name : salesPerYear_Map.get(year).keySet()){
                //
                double squareterm = ZERO;
                for (String thisName : salesPerYear_Map.get(year).keySet()){
                    if (thisName.equals(name)) continue;
                    double skt = salesPerYear_Map.get(year).get(thisName);
                    squareterm +=   skt*skt;
                }
                
                Map<String, Double> current = mit_map.get( year);
                if (null == current) current = new HashMap<String, Double>  ();
                current.put (name, (squareterm / (ONE-numCompanies)) + salesPerYear_Map.get(year).get(name)* salesPerYear_Map.get(year).get(name) ) ;
                mit_map.put (year, current) ;
            }
             
        }
    } 
            
    public void getNIT_map ( Map <Integer,  Map < String, Double >> salesPerYear_Map,  
                             Map<String, Map<Integer, Double>>  V_Map) {
        
        for (int year : salesPerYear_Map.keySet()){
            final int numCompanies =  salesPerYear_Map.get(year).keySet().size();
            for (String name : salesPerYear_Map.get(year).keySet()){
                //
                double squareterm = ZERO;
                for (String thisName : salesPerYear_Map.get(year).keySet()){
                    if (thisName.equals(name)) continue;
                    double skt = salesPerYear_Map.get(year).get(thisName);
                    squareterm +=   skt*skt* V_Map.get(thisName).get( year);
                }
                
                Map<String, Double> current = nit_map.get( year);
                if (null == current) current = new HashMap<String, Double>  ();
                current.put (name, (squareterm / (ONE-numCompanies)) + 
                        salesPerYear_Map.get(year).get(name)* salesPerYear_Map.get(year).get(name)* V_Map.get(name).get( year) ) ;
                nit_map.put (year, current) ;
            }
             
        }
        
         

        
    } 
    
    public void getJIT_map (Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map,
                            Map<String, Map<Integer, Double>>  V_Map ) {
        for (Integer  year =START_YEAR ; year <= END_YEAR;year++){
            List<String> cmpaniesForThisYear = getCompaniesForThisYear(year, fractionalCharaecters_Map) ;
            final int numCompanies =  cmpaniesForThisYear.size();
            if (ZERO==numCompanies)continue;
                
            for (String companyName : cmpaniesForThisYear){
                
                double jit = ZERO;
                double numerator = ZERO;
                double denominator = ZERO;
                
                List<Double> charecters = fractionalCharaecters_Map.get (companyName) .get (year);
                final int numCHars = charecters.size();
                
                if (year == 2015 && companyName.equalsIgnoreCase("Modern Steels Ltd.")){
                     System.out.println();
                }
                
                for (int jj = ZERO; jj< numCHars; jj ++){
                    if (this.qijt_map.get( year).get(companyName ).get(jj)>= ZERO){
                        numerator += this.qijt_map.get( year).get(companyName ).get(jj);
                    }
                    denominator += fractionalCharaecters_Map.get (companyName) .get (year).get(jj);
                }
                
                jit = ( V_Map.get(companyName).get(year)* numerator)/denominator;
                Map<String, Double > inner = this.jit_map.get(year);
                if (null == inner)  inner = new TreeMap<String, Double >();
                inner.put (companyName, jit);
                this.jit_map.put(year, inner);
                
               
                
            }
        }
        
    }
    
    public void getQIJT_map (Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map) {
        for (Integer  year =START_YEAR ; year <= END_YEAR;year++){
            List<String> cmpaniesForThisYear = getCompaniesForThisYear(year, fractionalCharaecters_Map) ;
            final int numCompanies =  cmpaniesForThisYear.size();
            if (ZERO==numCompanies)continue;
                
            for (String companyName : cmpaniesForThisYear){
                List<Double> charecters = fractionalCharaecters_Map.get (companyName) .get (year);
                final int numCHars = charecters.size();
                List<Double> newCharecterList_q = new ArrayList<Double>();
                List<Double> newCharecterList_p = new ArrayList<Double>();
                
                for (int jj = ZERO; jj< numCHars; jj ++){
                    double qijt = ZERO;
                    for (int kk = ZERO; kk< numCHars; kk ++){
                        if (kk!=jj){
                            double val = fractionalCharaecters_Map.get (companyName) .get (year).get(kk);
                            qijt += val*val;
                        }
                    }
                    double val = fractionalCharaecters_Map.get (companyName) .get (year).get(jj);
                    qijt = val*val - (qijt/(numCHars-ONE));
                    newCharecterList_q.add ( qijt );
                    if (qijt>=ZERO){
                        newCharecterList_p.add ( qijt / val);
                    }else {
                        newCharecterList_p.add (Double.NaN);
                    }
                }
                
                TreeMap<String,  List<Double>> innerMap_q = qijt_map.get ( year) ;
                if (null==innerMap_q) innerMap_q = new TreeMap<String,  List<Double>>();
                innerMap_q.put (companyName, newCharecterList_q) ;
                qijt_map.put (year, innerMap_q);
                
                TreeMap<String,  List<Double>> innerMap_p = pijt_map.get ( year) ;
                if (null==innerMap_p) innerMap_p = new TreeMap<String,  List<Double>>();
                innerMap_p.put (companyName, newCharecterList_p) ;
                pijt_map.put (year, innerMap_p);
                
            }
        }
    }
     
    public void getNIJT_Map ( Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map,
                              Map<String, Map<Integer, Double>>  V_Map ) {
        
        for (Integer  year =START_YEAR ; year <= END_YEAR;year++){
            List<String> cmpaniesForThisYear = getCompaniesForThisYear(year, fractionalCharaecters_Map) ;
            final int numCompanies =  cmpaniesForThisYear.size();
            if (ZERO==numCompanies)continue;
                
            for (String companyName : cmpaniesForThisYear){
                
                boolean skip= false;
                for (String companyNameAgain : cmpaniesForThisYear){
                    if (V_Map.get(companyNameAgain).get(year) < ZERO) skip = true;
                }
                if (skip) continue;
                
                
                List<Double> charecters = fractionalCharaecters_Map.get (companyName) .get (year);
                List<Double> newCharecterList = new ArrayList<Double>();
                for (int jj = ZERO; jj< charecters.size(); jj ++){
                    
                    if (jj==2 && year== 2015 && companyName.equals( ("Bosch Ltd."))){
                        int temp =1;
                    }
                    
                    double squareterm = ZERO;
                    for (String thisName :cmpaniesForThisYear  ){
                        if (thisName.equals( companyName)) continue;
                        
                        double sijt = fractionalCharaecters_Map.get(thisName).get(year).get(jj);
                        squareterm += sijt*sijt * V_Map.get(thisName).get(year);
                        
                    }
                    double sijt = fractionalCharaecters_Map.get(companyName).get(year).get(jj);
                    newCharecterList.add (sijt*sijt* V_Map.get(companyName).get(year) - (squareterm/ (numCompanies-ONE)) ) ;
                }
                
                TreeMap<String,  List<Double>> innerMap = nijt_map.get ( year) ;
                if (null==innerMap) innerMap = new TreeMap<String,  List<Double>>();
                innerMap.put (companyName, newCharecterList) ;
                nijt_map.put (year, innerMap);
                
                
                
            }
            
        }
        
    }
    
    
    private List<String> getCompaniesForThisYear(int year, Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map) {
        List<String> result = new ArrayList<String> ();
        
        for (String companyName : fractionalCharaecters_Map.keySet()){
            Map<Integer,  List<Double>> innermap = fractionalCharaecters_Map.get( companyName);
            if (null!=innermap.get(year)){
                result.add (companyName);
            }
        }
        
        return result;
    }
 
    private List<Double>   getTITJ_Vector(String companyName, int year, Map <String, Map<Integer,  List<Double>> >normalizedFractionalCharacters_Map){
        List<Double>  result = new ArrayList<Double> ();
        List<Double> currentChars =normalizedFractionalCharacters_Map.get(companyName).get (year);
                
        for (int index= ZERO; index < currentChars.size(); index ++){
            double sum = ZERO;
            for (int kk= ZERO; kk < currentChars.size(); kk ++){
                if (kk==index)  continue;
                sum+= currentChars.get( kk)* currentChars.get( kk);
            }
            sum = sum/ currentChars.get( index);
            sum = sum/(currentChars.size()-ONE);
            sum += currentChars.get (index );
            result.add (sum);
        }
        
        return result;
    }
    
    private void insertInto_AlternateSIJT_Map (int year, String companyName, int jj, double  sijt_alternate) {
        //
        //Map <Integer, Map< String,  Map<Integer,Double>> > sitj_star_map_alternate
        Map< String,  Map<Integer,Double>> map = sitj_star_map_alternate.get( year);
        if (null == map){
            map = new HashMap< String,  Map<Integer,Double>>();
        }
        sitj_star_map_alternate.put (year, map);
        
        Map<Integer,Double>  innerMap = map.get(companyName) ;
        if (null== innerMap){
            innerMap = new HashMap<Integer,Double>();
        }
        map.put (companyName, innerMap);
        
        innerMap.put (jj, sijt_alternate) ;        
        
    }
    
    
    
    
    public void compare_ISIT_IIT (  
                               Map <String, Map<Integer,  List<Double>> >  fractionalCharaecters_Map) {
        
        int totalCount = ZERO;
        int errorCount = ZERO;
        
        for (Integer  year =START_YEAR ; year <= END_YEAR;year++){
            List<String> cmpaniesForThisYear = getCompaniesForThisYear(year, fractionalCharaecters_Map) ;
            final int numCompanies =  cmpaniesForThisYear.size();
            if (ZERO==numCompanies)continue;
            
            for (String companyName : cmpaniesForThisYear){
                
                double iit = ZERO;
                if (I_i_t.containsKey(companyName) &&I_i_t.get(companyName).containsKey(year) ) {
                    iit = I_i_t.get(companyName).get(year);
                }
 
                 
                double isit = ZERO;
                if (IS_it.containsKey( companyName)  && IS_it.get( companyName).containsKey( year) ){
                    isit = IS_it.get( companyName).get( year);
                }
                        
                        //IS_it.get().get( year);
                        
                totalCount++;
                
                if (isit > iit){
                    System.out.println  (isit + " " + iit) ;
                    System.out.println ("isit bigger for "+ companyName + " "+ year);
                    errorCount ++;
                }
                 
            }
            
        }
        
        System.out.println("compare_ISIT_IIT complete "+ totalCount + " -- "+ errorCount);
        System.out.println();
    }
    
    
    
}
