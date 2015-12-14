/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Diego
 */
public class Patient {
    
    private String firstName;
    public static final String PROP_FIRSTNAME = "firstName";  
    private String lastName;
    public static final String PROP_LASTNAME = "lastName";   
    private String index;
    public static final String PROP_INDEX = "index";

    public Patient(String firstName, String lastName, String index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.index = index;
    }
 
    
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        String oldIndex = this.index;
        this.index = index;
        propertyChangeSupport.firePropertyChange(PROP_INDEX, oldIndex, index);
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        propertyChangeSupport.firePropertyChange(PROP_LASTNAME, oldLastName, lastName);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        String oldFirstName = this.firstName;
        this.firstName = firstName;
        propertyChangeSupport.firePropertyChange(PROP_FIRSTNAME, oldFirstName, firstName);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        return  firstName + " " + lastName ;
    }
    
    

}
