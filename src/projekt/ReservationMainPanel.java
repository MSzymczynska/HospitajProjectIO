/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

/**
 *
 * @author Krzychu
 */
public class ReservationMainPanel {
    
    public String vacationSchedule;
    public String workShiftSchedule;
    public String hospitalRoomSchedule;
    public String medicalServices;
    public String logOff;
    public String logOn;
    public String exit;
    private String firstName;
    private String lastName;
    private String occupation;
    private double index;
    private String notifications;
    
    public void goToVacationSchedule(){
        
    }
    
    public void goToWorkSchiftSchedule(){
        
    }
    
    public void goToHospitalRoomSchedule(){
        
    }
    
    public void goToMedicalServices(){
        
    }
    
    public void goToNotifications(){
        
    }
    
    public void logOff(){
        
    }
    
    public void logOn(){
        
    }
    
    public void exit(){
        
    }
    
    public double getIndex(){
        
        return this.index;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public String getNotifications(){ //na schemacie była typu void, ale chyba powinnna Stringa ta metoda
        return this.notifications;
    }
    
    public String getOccupation(){
        
        return this.occupation;
    }
}
