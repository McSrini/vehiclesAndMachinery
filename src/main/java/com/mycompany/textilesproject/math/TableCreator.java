/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textilesproject.math;

import static com.mycompany.textilesproject.Constants.ONE;
import static com.mycompany.textilesproject.Constants.ZERO;
import static com.mycompany.textilesproject.Parameters.END_YEAR;
import static com.mycompany.textilesproject.Parameters.NUM_CHARECTERS;
import static com.mycompany.textilesproject.Parameters.START_YEAR;
import static java.lang.System.exit;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author admin
 */
public class TableCreator {
    
    Map<Integer, TreeMap<String, List<Double>>>  qijt_map;
    Map<String, Map<Integer, Double>>  V_Map ;
    Map <String, Double > Nu_i;
    Map<String, Map<Integer, Double>>  cost_Map;
    Map <Integer,  Map < String, Double >> salesPerYear_Map;
    Map<Integer, Map<String, Double>>  jit_map;
    Map <String , Map< Integer,  Double> > IS_it;
    Map<Integer, Map<String, Double>>  nit_map;
    List<String> companies = new ArrayList<String> ();
    Map <Integer,  Map < String, Double >> rawSalesPerYear_Map;

    String homeFolder= "c:\\temp\\500\\Table";
    
    public TableCreator (
        Map<Integer, TreeMap<String, List<Double>>>  _qijt_map,
        Map<String, Map<Integer, Double>>  _V_Map ,
        Map <String, Double > _Nu_i,
        Map<String, Map<Integer, Double>>  _cost_Map,
        Map <Integer,  Map < String, Double >> _salesPerYear_Map,
        Map<Integer, Map<String, Double>>  _jit_map,
        Map <String , Map< Integer,  Double> > _IS_it,
        Map<Integer, Map<String, Double>>  _nit_map,
        Map <Integer,  Map < String, Double >> _rawSalesPerYear_Map,
        
        Map <String, List<Map <String, String>>> dataItemList
        
        ) {
        qijt_map= _qijt_map;
        V_Map= _V_Map;
        Nu_i= _Nu_i;
        cost_Map= _cost_Map;
        salesPerYear_Map= _salesPerYear_Map;
        jit_map= _jit_map;
        IS_it= _IS_it;
        nit_map= _nit_map;
        
        rawSalesPerYear_Map= _rawSalesPerYear_Map;
        
        for (String name : dataItemList.keySet()){
            companies.add (name );
        }
        
    }
    
    public void createtables () {
        for (String name : companies ){
            createTableOne (name) ;
            createTableTwo(name) ;
        }
    }
    
    private void createTableOne (String companyName ) {
        
        
        
        String filename = homeFolder + "1\\" +companyName ;
        if (!filename.endsWith("."))  filename = filename + ".";
        filename = filename + "txt";
        
        appendToFile (filename, "Year, vit, cit/Sit, Jit, ISit, sit, nit"+ "\n");
        Double nu = this.Nu_i.get( companyName);
        String nuStr = "";
        if (null!=nu)nuStr   =nuStr + "" + this.Nu_i.get( companyName);
        appendToFile (filename, nuStr + "\n");
        
        for (int year = START_YEAR+ONE; year <= END_YEAR; year ++){
            appendToFile (filename,""+year +",");
            
            if (null!=this.V_Map.get( companyName) && null != V_Map.get( companyName).get(year)
                    && Double.NaN != V_Map.get( companyName).get(year)  && ZERO<   V_Map.get( companyName).get(year)){
                appendToFile (filename,""+ this.V_Map.get( companyName).get(year) +",");
            }else {
                appendToFile (filename,""+  ",");
            }
            
            if (null!=this.cost_Map.get( companyName) && null != cost_Map.get( companyName).get(year)
                    && null!=this.rawSalesPerYear_Map.get( year) && null != rawSalesPerYear_Map.get( year).get(companyName)
                     && Double.NaN !=cost_Map.get( companyName).get(year)/rawSalesPerYear_Map.get( year).get(companyName)  && 
                    ZERO<   cost_Map.get( companyName).get(year)/rawSalesPerYear_Map.get( year).get(companyName)){
                double numerator = this.cost_Map.get( companyName).get(year);
                double denominator = rawSalesPerYear_Map.get( year).get(companyName);
                
                
                
                
                appendToFile (filename,""+ numerator/denominator +",");
            }else {
                appendToFile (filename,""+  ",");
            }
            
            if (null!=this.jit_map.get( year) && null != jit_map.get( year).get(companyName)
                    && Double.NaN!=this.jit_map.get( year).get(companyName) && ZERO < jit_map.get( year).get(companyName) ){
                appendToFile (filename,""+ this.jit_map.get( year).get(companyName) +",");
            }else {
                appendToFile (filename,""+  ",");
            }
            
            if (null!=this. IS_it.get( companyName) && null != IS_it .get( companyName).get(year)&&
                    Double.NaN!=this. IS_it.get( companyName).get(year) && ZERO<IS_it .get( companyName).get(year)){
                appendToFile (filename,""+ this.IS_it .get( companyName).get(year) +",");
            }else {
                appendToFile (filename,""+  ",");
            }
            
            if (null!=this. salesPerYear_Map.get( year) && null !=  salesPerYear_Map.get( year).get(companyName)&&
                  Double.NaN!=this. salesPerYear_Map.get( year).get(companyName ) && ZERO<salesPerYear_Map .get( year).get(companyName)  ){
                appendToFile (filename,""+ this.salesPerYear_Map .get( year).get(companyName) +",");
            }else {
                appendToFile (filename,""+  ",");
            }
            
            if (null!=this.nit_map  .get( year) && null !=  nit_map .get( year).get(companyName)&&
                    nit_map .get( year).get(companyName)!=Double.NaN && nit_map .get( year).get(companyName)>ZERO){
                appendToFile (filename,""+ this. nit_map .get( year).get(companyName) +",");
            }else {
                appendToFile (filename,""+  ",");
            }
             
            appendToFile(filename,"\n");
        }
        
    }
        
    private void createTableTwo (String companyName ) {
        String filename = homeFolder + "2\\"+companyName ;
        if (!filename.endsWith("."))  filename = filename + ".";
        filename = filename + "txt";
        
        for (int year = START_YEAR+ONE; year <= END_YEAR; year ++){
            List<Integer> positives = new ArrayList<Integer>();
            for (int jj = ZERO; jj< NUM_CHARECTERS ; jj ++){
                if (this.qijt_map.get( year).get( companyName)!=null && 
                    ZERO < this.qijt_map.get( year).get( companyName).get(jj)){
                    positives.add (ONE + jj) ;
                }
            }
            appendToFile (filename,""+year+"," );
            for (int jj = ZERO; jj< positives.size()  ; jj ++){
                appendToFile (filename, ""+positives.get(jj) +" ");
            }
            appendToFile(filename,"\n");
        }      
        
    }
    
    private void appendToFile (String filename , String text){
        try {
            Files.write(Paths.get(filename), text.getBytes(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch (Exception ex) {
            System.err.print(ex);
            exit (ONE);
        }
    }
    
}
