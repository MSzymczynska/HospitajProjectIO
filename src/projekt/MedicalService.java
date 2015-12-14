
package projekt;

import java.util.Date;
import java.util.Vector;

public class MedicalService {
    
    private Vector <String> listNamesOfServices;
    private Date dateAndTime;
    private String room;
    private String staff;
    private String nameOfService;
    private Patient patient;

    public MedicalService(Date dateAndTime, String room, String staff, String nameOfService, Patient patient) {
        this.dateAndTime = dateAndTime;
        this.room = room;
        this.staff = staff;
        this.nameOfService = nameOfService;
        this.patient = patient;
    }
    
    
    public MedicalService(String nameOfService, Patient patient){
        this.nameOfService = nameOfService;
        this.patient = patient;
    }

    
    public MedicalService recordOnMedicalService(){
        
        return null;
    }
    
    public Date setDateAndTime(int year, int month, int date, int hrs, int min){
        
        return null;
    }
    
    public String setRoom(String room){
        
        return this.room = room;
    }
    
    public String getNameOfService(){
        
        return this.nameOfService;
    }
    
    public Date getDateAndTime(){
        
        return this.dateAndTime;
    }
    
    public String getRoom(){
        
        return this.room;
    }
    
    public String getStaff(){
        
        return this.staff;
    }
 
    public Vector<String> getListNamesOfServices()
    {
        return this.listNamesOfServices;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
    
    }
