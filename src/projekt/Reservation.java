/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.Date;

/**
 *
 * @author Jakub
 */
public class Reservation {

    private int reserv_id;
    private String patient_id;
    private Date stringh_from;
    private int room_id;
    private String nameOfService; ///nie ma w bazie
    private String Staff; ///nie ma w bazie

    public Reservation(String patient_id, String nameOfService) {
        this.patient_id = patient_id;
        this.nameOfService = nameOfService;
    }

    public Reservation(int reserv_id, String patient_id, Date stringh_from, int room_id, String nameOfService, String Staff) {
        this.reserv_id = reserv_id;
        this.patient_id = patient_id;
        this.stringh_from = stringh_from;
        this.room_id = room_id;
        this.nameOfService = nameOfService;
        this.Staff = Staff;
    }

    
    
    public String getStaff() {
        return Staff;
    }

    public void setStaff(String Staff) {
        this.Staff = Staff;
    }

    public String getNameOfService() {
        return nameOfService;
    }

    public void setNameOfService(String nameOfService) {
        this.nameOfService = nameOfService;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public Date getStringh_from() {
        return stringh_from;
    }

    public void setStringh_from(Date stringh_from) {
        this.stringh_from = stringh_from;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public int getReserv_id() {
        return reserv_id;
    }

    public void setReserv_id(int reserv_id) {
        this.reserv_id = reserv_id;
    }

}
