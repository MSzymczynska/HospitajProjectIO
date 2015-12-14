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
public class VacationSchedule {
    
    private int freeDaysToPick;
    private Vector <Date> freeDaysPicked;
    public String backToMainPanel;
    
    public void chooseDay(){
        
    }
    
    public void chooseRange(){
        
    }
    
    public boolean removeDay(){
        
        return true;
    }
    
    public void removeRange(){
        
    }
    
    public void refreshData(){
        
    }
    
    public void backToMainPanel(){
        
    }
    
    public int getFreeDaysToPick(){
        return this.freeDaysToPick;
    }
    
    public Vector <Date> getFreeDaysPicked(){
        return this.freeDaysPicked;
    }
}
