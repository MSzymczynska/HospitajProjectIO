/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author Krzychu
 */
public class HospitalRoomSchedule {
    
    private int number;
    private Vector <String> namesOfServicesAreServe;
    
    public void setNumber(int number){
        
    }
    
    public HospitalRoomSchedule(int number, Vector <String> namesOfServicesAreServe){ //konstruktor, na schemacie był nie do końca poprawnie zapisany chyba
        this.number = number;
        this.namesOfServicesAreServe = namesOfServicesAreServe;
    }
    
    public int getNumber(int number){
        
        return this.number;
    }
    
    public Vector <String> getNamesOfServicesAreServe(){
        
        return this.namesOfServicesAreServe;
    }
    
    public boolean areBusyAtThisTime(Date dateAndTime){
        
        return true;
    }
    
}
