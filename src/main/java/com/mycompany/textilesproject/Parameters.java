/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textilesproject;

import static com.mycompany.textilesproject.Constants.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Parameters {
    
    //public static final String PATH_TO_CSV_FILE = "C:\\91firms_rmrao\\basicmetals.csv";
    public static final String PATH_TO_CSV_FILE = "C:\\91firms_rmrao\\paperindustries.csv";
    //public static final String PATH_TO_CSV_FILE = "C:\\91firms_rmrao\\machinerydata.csv";
    //public static final String PATH_TO_CSV_FILE = "C:\\91firms_rmrao\\vehiclesdata.csv";
    
    //if not even this many records, ignore this company
    public static final int MINIMUM_RECORDS_PER_COMPANY = 10 ;
    
    public static final int NUM_CHARECTERS = 14 ;
    
    public static final int START_YEAR = 2000 ;
    public static final int END_YEAR = 2017 ;
    
    public static final  ArrayList<String> companiesOfInterest_BasicMetals = new ArrayList<String>() { 
        {
            add (           "Alicon Castalloy Ltd.");
            add (           "Amco India Ltd.");
            add (           "Anil Special Steel Inds. Ltd.");
            add (           "Ashiana Ispat Ltd.");
            add (           "Ashirwad Steels & Inds. Ltd.");            
            add (           "Balasore Alloys Ltd.");
            add (           "Bhagwati Autocast Ltd.");
            add (           "Carnation Industries Ltd.");
            add (           "Century Extrusions Ltd.");
            add (           "Cubex Tubings Ltd.");            
            add (           "Electrosteel Castings Ltd.");
            add (           "Ferro Alloys Corpn. Ltd.");
            add ("Gandhi Special Tubes Ltd.");
            add ("Gangotri Iron & Steel Co. Ltd.");
            add ("Golkonda Aluminium Extrusions Ltd.");
            add ("Gujarat Foils Ltd.");
            add ("Gujarat Intrux Ltd.");            
            add ("Hind Aluminium Inds. Ltd.");
            add ("Hindalco Industries Ltd.");
            add ("Hinduja Foundries Ltd. [Merged]");
            add ("Hindustan Copper Ltd.");            
            add ("Hindustan Tin Works Ltd.");
            add ("Hindustan Zinc Ltd.");
            add ("Hisar Metal Inds. Ltd.");  
            add ("I S M T Ltd.") ;
            add("India Steel Works Ltd.");
            add("Indsil Hydro Power & Manganese Ltd.");
            add ("Inducto Steel Ltd.") ;
            add ("Investment & Precision Castings Ltd.");
            add ("J S W Steel Ltd.");
            add ("Jayaswal Neco Inds. Ltd.");            
            add ("Jindal Saw Ltd.");          
            add ("Jindal Steel & Power Ltd.");
            add ("K I C Metaliks Ltd.");
            add ("Kalyani Steels Ltd.");            
            add ("Kanishk Steel Inds. Ltd.");            
            add ("Kirloskar Ferrous Inds. Ltd.") ;            
            add ("Lloyds Metals & Energy Ltd.") ;
            add ("Magna Electro Castings Ltd.");
            add ("Mahalaxmi Seamless Ltd.");            
            add ("Maharashtra Seamless Ltd.");
            add ("Man Industries (India) Ltd.");            
            add ("Modern Steels Ltd.");
            add ("Monnet Ispat & Energy Ltd.");            
            add ("Mukand Ltd.");
            add ("Mukat Pipes Ltd.");            
            add ("Mukesh Steels Ltd.");
            add ("National Aluminium Co. Ltd.");
            add ("National Fittings Ltd.");
            add ("National Steel & Agro Inds. Ltd.");
            add ("Oil Country Tubular Ltd.");            
            add ("P G Foils Ltd.");            
            add ("P S L Ltd.");
            add("Panchmahal Steel Ltd.");
            add("Pennar Industries Ltd.");
            add ("Prakash Industries Ltd.");
            add ("R M G Alloy Steel Ltd.");            
            add ("Rajasthan Tube Mfg. Co. Ltd.");
            add ("Rajratan Global Wire Ltd.") ;
            add ("Rapicut Carbides Ltd.");
            add ("Rathi Steel & Power Ltd.");
            add ("Ratnamani Metals & Tubes Ltd.");
            add ("Real Strips Ltd.");
            add ("Remi Edelstahl Tubulars Ltd.");            
            add ("Sarda Energy & Minerals Ltd.");            
            add ("Shah Alloys Ltd.");            
            add ("Shetron Ltd.");
            add ("Shivagrico Implements Ltd.");   
            add ("Shivalik Bimetal Controls Ltd.");
            add ("Siddhartha Tubes Ltd.");
            add ( "Simplex Castings Ltd.");
            add ("Srikalahasthi Pipes Ltd.");
            add ("Steel Authority Of India Ltd.");
            add ("Steelcast Ltd.");            
            add ("Steelco Gujarat Ltd.");
            add ("Sudal Industries Ltd.");            
            add ("Sunflag Iron & Steel Co. Ltd.");
            add ("Suraj Ltd.");            
            add ("Surya Roshni Ltd.");            
            add ("T I Financial Holdings Ltd.");
            add ("Tata Metaliks Ltd.");            
            add ("Tata Sponge Iron Ltd.");
            add ("Tata Steel B S L Ltd.");            
            add ("Tata Steel Ltd.");
            add ("Tayo Rolls Ltd.");            
            add ("Tinplate Co. Of India Ltd.");
            add ("Tulsyan N E C Ltd.");            
            add ("Uni Abex Alloy Products Ltd.");
            add ("Usha Martin Ltd.");            
            add ("Uttam Galva Steels Ltd."); 
            add ("Uttam Value Steels Ltd.");
            add ("V B C Ferro Alloys Ltd.") ;
            add ("Vallabh Steels Ltd." );
            add ("Vardhman Industries Ltd."); 
            add ("Vishal Malleables Ltd.") ;
            add ("Welcast Steels Ltd.") ;            
            add ("Welspun Corp Ltd.") ;
            add ("Zenith Birla (India) Ltd.") ;
            
        };
    };
    
    public static final  ArrayList<String> companiesOfInterest_PaperIndustries = new ArrayList<String>() { 
        {
            add (           "3P Land Holdings Ltd.");
            add (           "A M J Land Holdings Ltd.");
            add (           "Ballarpur Industries Ltd.");
            add (           "Beckons Industries Ltd.");
           add (           "Cosboard Industries Ltd.");
           add (           "Huhtamaki P P L Ltd.");
           add("International Paper A P P M Ltd.");
           add("J K Paper Ltd.");
           add ("Kay Power & Paper Ltd.");
           add("Mohit Paper Mills Ltd.");
           add("Mysore Paper Mills Ltd.");
           add("N R Agarwal Inds. Ltd.");
           add("Nath Pulp & Paper Mills Ltd.");
           add ("Perfectpac Ltd." );
           add ("Rainbow Papers Ltd.");
           //add ("Rama Paper Mills Ltd.");
           add ("Rama Pulp & Papers Ltd.");
           add ("Saffron Industries Ltd.");
           add ("Seshasayee Paper & Boards Ltd.");
           //add ("Shree Bhawani Paper Mills Ltd.");
           add ("Shree Karthik Papers Ltd.");
           //add ("Shree Krishna Paper Mills & Inds. Ltd.");
           add ("Shree Rajeshwaranand Paper Mills Ltd.");
           //add ("Shree Rama Newsprint Ltd.");
           add ("Shreyans Industries Ltd.");
           //add ("Sirpur Paper Mills Ltd.");
           add ("South India Paper Mills Ltd.");           
           add ("Star Paper Mills Ltd.");           
           add ("Tamil Nadu Newsprint & Papers Ltd.");           
           add ("Vapi Enterprise Ltd.") ;           
           add ("West Coast Paper Mills Ltd.") ;
           add("Yash Papers Ltd.");
             
        }         
    };
   
    public static final  ArrayList<String> companiesOfInterest_Set_One = new ArrayList<String>() { 
        {
            add (           "Ashok Leyland Ltd.");
            add (           "Force Motors Ltd.");
            add (           "Hindustan Motors Ltd.");
            add (           "Mahindra & Mahindra Ltd.");
            add (           "Maruti Suzuki India Ltd.");
            add (           "Tata Motors Ltd.");
             
        }         
    };
    
    public static final  ArrayList<String> companiesOfInterest_Set_Two= new ArrayList<String>() { 
        {
            add ("Amtek Auto Ltd.") ;
            add (           "J B M Auto Ltd.");
            add (           "J M T Auto Ltd.");
            add ("Jamna Auto Inds. Ltd.");
            add ("Munjal Auto Inds. Ltd.");
            add ("Omax Autos Ltd.");
            add ("Rico Auto Inds. Ltd.");
            
            
        }         
    };
    
    public static final  ArrayList<String> companiesOfInterest_Set_Three= new ArrayList<String>() { 
        {
            add ("Automotive Axles Ltd.") ;
            add ("Clutch Auto Ltd.") ;
            add ("Frontier Springs Ltd.") ;
            add ("Gabriel India Ltd.") ;
        }         
    };
    
    public static final  ArrayList<String> companiesOfInterest_Set_Four= new ArrayList<String>() { 
        {
            add ("Bimetal Bearings Ltd.") ;
            add ("Bharat Forge Ltd.");
            add ("Bharat Gears Ltd.");
            add ("Him Teknoforge Ltd.");
            add ("Hi-Tech Gears Ltd.");
            add ("Kalyani Forge Ltd.");
            add ("R A C L Geartech Ltd.") ;
            add ("Z F Steering Gear (India) Ltd.") ;
        }         
    };
    
    public static final  ArrayList<String> companiesOfInterest_Set_Five= new ArrayList<String>() { 
        {
            add ("Duncan Engineering Ltd.") ;
            add ("Exedy India Ltd.") ;
            //add ("Hindustan Composites Ltd.") ;
            add ("Hindustan Hardy Ltd.") ;
            add ("India Nippon Electricals Ltd.") ;
            add ("Jay Bharat Maruti Ltd.");
            add ("Rasandik Engineering Inds. India Ltd.");
            add ("Reil Electricals India Ltd.") ;
            add ("Suprajit Engineering Ltd.");
        }         
    };
    
    public static final  ArrayList<String> companiesOfInterest_Set_Six= new ArrayList<String>() { 
        {
            add ("Automotive Stampings & Assemblies Ltd.") ;
            add ("Steel Strips Wheels Ltd.") ;
            add ("Sundram Fasteners Ltd.");
            add ("Talbros Automotive Components Ltd.");
        }         
    };
    
    public static final  ArrayList<String> companiesOfInterest_Set_Seven= new ArrayList<String>() { 
        {
            add ("A N G Industries Ltd.") ;
            add ("Automobile Corpn. Of Goa Ltd.") ;
             add ("Banco Products (India) Ltd.") ;
             add ("Bharat Seats Ltd.") ;
             add ("Jay Ushin Ltd.") ;
             add ("Motherson Sumi Systems Ltd.");
             add ("Munjal Showa Ltd.");
             add ("Pricol Ltd. [Merged]" );
             add ("Remsons Industries Ltd.");
             add ("S M L Isuzu Ltd.");
             add ("Simmonds Marshall Ltd.");
             add ("Subros Ltd.");
        }         
    };
    
    public static final  ArrayList<String> companiesOfInterest_Set_Machinery= new ArrayList<String>() { 
        {
            add ("A B B India Ltd.") ;
            add ("A B C Bearings Ltd. [Merged]") ;
            add ("Akar Auto Inds. Ltd.") ;
            add ("Austin Engineering Co. Ltd.");
            
            add ("Axtel Industries Ltd.");
            add ("B E M L Ltd.");
            add ("Birla Precision Technologies Ltd.");
            add ( "Bosch Ltd.") ;
            add ( "Brady & Morris Engg. Co. Ltd.") ;
            
            add ( "Cenlub Industries Ltd.") ;
            add ( "Cmi F P E Ltd.") ;
            add ( "Cranex Ltd.") ;
            add ( "D H P India Ltd.") ;
            
            add ( "Disa India Ltd.") ;
            add ("Eimco Elecon (India) Ltd.");
            
            add ("Elecon Engineering Co. Ltd.");
            add ("Elgi Equipments Ltd.");
            
            add ("Ema India Ltd.");
            
            add ("Escorts Ltd.");
            add ("Federal-Mogul Goetze (India) Ltd.");
            
            add ("G E I Industrial Systems Ltd.");
            add ("G G Dandekar Machine Works Ltd.");
            
            add ("G M M Pfaudler Ltd.");
            
            add (" Greaves Cotton Ltd.");
            //add ("Gujarat Apollo Inds. Ltd.");
            
            add ("Hittco Tools Ltd.");
            add("I P Rings Ltd.");
             add ("I T L Industries Ltd.");
                            add ("Ingersoll-Rand (India) Ltd.");
                                    add("International Combustion (India) Ltd.");
                                    add("Johnson Controls-Hitachi Air Conditioning India Ltd.");
                                                 add ("Kabra Extrusion Technik Ltd.");
                                                   add ("Kennametal India Ltd.");
                                                    add ("Kilburn Engineering Ltd.");
                                                       add ("Kirloskar Brothers Ltd.");
                                                                     add ("Kulkarni Power Tools Ltd.");
                 add("Lakshmi Machine Works Ltd.");
                 add ("Lippi Systems Ltd.");
                        add ("Manugraph India Ltd.");
                                 add ("Mazda Ltd.");
                         add ("Menon Bearings Ltd.");
                                add ("Menon Pistons Ltd.");
                               add ("Mipco Seamless Rings (Gujarat) Ltd.");
                                  add("N R B Bearings Ltd.");
                                                
                                                add("Polymechplast Machines Ltd.");
                  add ("Praj Industries Ltd.");
                     add("Premier Ltd.");
                  add("Rajoo Engineers Ltd.");
                    add("Rexnord Electronics & Controls Ltd.");
                          add("S K F India Ltd.");
                                  add("S N L Bearings Ltd.");
                add("Samkrg Pistons & Rings Ltd.");
                
                add("Schaeffler India Ltd.");
                        add("Shakti Pumps (India) Ltd.");
                                add("Shanthi Gears Ltd.");
                 add("Shilp Gravures Ltd.");
                         add("Siemens Ltd.");
                         add("Solitaire Machine Tools Ltd.");
                                                
                   add("Stovec Industries Ltd.");
                           add("Swaraj Engines Ltd.");
                                   add("T R F Ltd.");
                                   add("Timken India Ltd.");
                                           add("Triton Valves Ltd.");
                                                   add("United Drilling Tools Ltd.");
                                                   add("V S T Tillers Tractors Ltd.");
                                                   add("Veljan Denison Ltd.");
                                                           add("W P I L Ltd.");
                                                                   add("Walchandnagar Industries Ltd.");
                                                                           add("Wendt (India) Ltd.");
                                                                                   add("Windsor Machines Ltd.");
                        add ( "Yuken India Ltd.") ;
                                
                         
                        add ("Blue Star Ltd.");
                            // add ( "H M T Ltd.");
                               add ( "Kirloskar Pneumatic Co. Ltd.");
                                        add ( "Thermax Ltd.");
           
            
        }
    };
    
    public static final  ArrayList<String> companiesOfInterest =  companiesOfInterest_Set_Machinery     ;
     
    public static final  ArrayList<String> columnsOfInterest = new ArrayList<String>() { 
            { 
                add("Id"); 
                add("Company Name"); 
                add("Year"); 
                add("Industry"); 
                
                add("Sales"); 
                add("NFA"); 
                add("Debt"); 
                add("Current_Ratio"); 
                add("Total Capital"); 
                add("Net Worth"); 
                add("Debt to Equity Ratio"); 
                add("Retained Profits"); 
                add("Cash Profit"); 
                add("Total Interest Expenses"); 
                add("Export/Sales"); 
                add("GFA"); 
                //add("CA"); 
                add("Cash/CL"); 
                add("Selling and Distribution Expenses"); 
                add("Depreciation"); 
                add("Total Taxes/Total Income"); 
                add("Cumulative Retained Profits"); 
                add("Operating Expenses/Total Expenses"); 
                add("Inventories"); 
                add("Gross Working Capital"); 
                add("Total Liabilities"); 
                add("Borrowings");                  
                add("Total Interest Expenses"); 
                add("Borrowings");  
            } 
        }; 
 
    private static ArrayList<String> getCcompaniesOfInterest_Steel (ArrayList<String> companiesOfInterest_BasicMetals) {
        ArrayList<String> result = new ArrayList<String> () ;
        for (String name : companiesOfInterest_BasicMetals){
            if (name.contains("Steel")|| name.contains("steel") || name.contains("Ispat") || name.contains("ispat")){
                result.add ( name);
            }            
        }
        return result;
    }
  
}
