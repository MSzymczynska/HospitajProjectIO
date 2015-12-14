/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;


/**
 *
 * @author Diego
 */
public class dDate {
    
    private Date startDate;
    public static final String PROP_STARTDATE = "startDate";    
    private Date finalDate;
    public static final String PROP_FINALDATE = "finalDate";

    public dDate(Date startDate, Date finalDate) {
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    
    
    
    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        Date oldFinalDate = this.finalDate;
        this.finalDate = finalDate;
        propertyChangeSupport.firePropertyChange(PROP_FINALDATE, oldFinalDate, finalDate);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        Date oldStartDate = this.startDate;
        this.startDate = startDate;
        propertyChangeSupport.firePropertyChange(PROP_STARTDATE, oldStartDate, startDate);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
