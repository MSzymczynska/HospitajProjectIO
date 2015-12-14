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
public class WorkShiftChangeApplication {
    
    private Vector <Date> selectedDaysToBeChanged;
    private Vector <Date> selectedDaysToChange;
    private boolean frequency;
    
    public double getIndex(){
        
        return 0;
    }
    
    public Vector <Date> getSelectedDaysToBeChange(){
        
        return this.selectedDaysToBeChanged;
    }
    
    public Vector <Date> getSelectedDaysToChange(){
        
        return this.selectedDaysToChange;
    }
    
    public void sendApplication(){
        
    }
    
    public boolean setFrequency(){
        
        return true;
    }
}
