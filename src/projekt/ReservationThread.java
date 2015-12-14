/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Jakub
 */
//////////////////////////////system dobiera do zgloszenia lekarza sale, termin i zespol(brakuje dobierania daty)/////////////////////////
public class ReservationThread implements Runnable {

    private Reservation rRequest;
    DataBaseConnection d = new DataBaseConnection();

    public ReservationThread(Reservation rRequest) {
        this.rRequest = rRequest;
    }

    @Override
    public void run() {
        Patient p = null;
        try {

            p = d.getPatient(rRequest.getPatient_id());
            Reservation r = new Reservation(d.getFreeResId(), p.getIndex(), new Date(), d.fitRoom(rRequest), rRequest.getNameOfService(), d.getStaff(rRequest));

        } catch (Exception ex) {
            Logger.getLogger(ReservationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
