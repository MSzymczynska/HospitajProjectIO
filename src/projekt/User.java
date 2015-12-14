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
public class User {
    
    private String firstName;
    public static final String PROP_IMIE = "firstName";
    private String lastName;
    public static final String PROP_LASTNAME = "lastName";
    private String occupation;
    public static final String PROP_OCCUPATION = "occupation";
    private String index;
    public static final String PROP_INDEX = "index";

    public User() {
    }

    public User(String firstName, String lastName, String occupation, String index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.index = index;
    }
    
    
    
    /**
     * Get the value of index
     *
     * @return the value of index
     */
    public String getIndex() {
        return index;
    }

    /**
     * Set the value of index
     *
     * @param index new value of index
     */
    public void setIndex(String index) {
        String oldIndex = this.index;
        this.index = index;
        propertyChangeSupport.firePropertyChange(PROP_INDEX, oldIndex, index);
    }

    /**
     * Get the value of occupation
     *
     * @return the value of occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Set the value of occupation
     *
     * @param occupation new value of occupation
     */
    public void setOccupation(String occupation) {
        String oldOccupation = this.occupation;
        this.occupation = occupation;
        propertyChangeSupport.firePropertyChange(PROP_OCCUPATION, oldOccupation, occupation);
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        propertyChangeSupport.firePropertyChange(PROP_LASTNAME, oldLastName, lastName);
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getImie() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param imie new value of firstName
     */
    public void setImie(String imie) {
        String oldImie = this.firstName;
        this.firstName = imie;
        propertyChangeSupport.firePropertyChange(PROP_IMIE, oldImie, imie);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName ;
    }

    
    
}
