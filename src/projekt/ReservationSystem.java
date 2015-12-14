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
public final class ReservationSystem {

    private static Vector<String> taskList;
    public static final String PROP_TASKLIST = "taskList";

    public static Vector<String> getTaskList() {
        return taskList;
    }

    public static void setTaskList(Vector<String> taskList) {
        Vector<String> oldTaskList = ReservationSystem.taskList;
        ReservationSystem.taskList = taskList;
        //   firePropertyChange(PROP_TASKLIST, oldTaskList, taskList);
    }

    public static void waitForCommand(Reservation rRequest) {

        Runnable[] runners;
        runners = new Runnable[1];
        Thread[] threads = new Thread[1];
        runners[1] = new ReservationThread(rRequest);
        threads[1] = new Thread(runners[1]);    
        threads[1].start();
    }

    
    
    public void getNewWorkShiftChangeRequest() {

    }

    public void getNewMedicalServiceRequest() {

    }

    public Date findFreeDate() {

        return null;
    }

    public int findFreeHospitalRoom() {

        return 0;
    }

    public double findFreeWorker() {

        return 0;
    }

    public boolean findTeam() {

        return true;
    }

    public void sendShiftChangeNotification() {

    }

    public boolean waitForShiftChangeReply() {

        return true;
    }

    public void changeShiftSchedule() {

    }

}
