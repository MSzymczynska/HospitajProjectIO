/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.mysql.jdbc.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;


/**
 *
 * @author Krzychu
 */
public class DataBaseConnection {

    private final String url = "jdbc:mysql://localhost:3306/";
    private final String user = "root";
    private final String password = "";
    private ResultSet rs;
    Connection connection;
    Statement statement;
    DateConverter dat;

//////////////////////zamyka polaczenie z baza////////////////////    
    public void close() throws Exception {
        statement.close();
        connection.close();
    }
///////////////////////otwiera polaczenie z baza////////////////////

    public void open() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
    }

    ///////////////////Sytstem zapisuje rezewacje do bazy danych/////////////////////////////////
    public void createReservation(Reservation r) throws Exception {
        this.open();

        String sql;
        sql = "\n"
                + "INSERT INTO szpital.Reservations (`reserv_id`,`patient_id`,`h_from`,`room_id`,`name_of_service`,`staff`) \n"
                + "	VALUES ('" + r.getReserv_id() + "','"
                + r.getPatient_id() + "','" + r.getStringh_from() + "','" + r.getRoom_id() + "','" + r.getNameOfService() + "','"
                + r.getStaff() + "')";
        statement.execute(sql);

        this.close();
    }

    ////////////////////////////pobiera obiekt pacjenta(imie,nazwisko,id) po id /////////////////////////
    public Patient getPatient(String id) throws Exception {
        this.open();

        String fName = null;
        String lName = null;
        String index = null;

        rs = statement.executeQuery("SELECT TOP 1 FROM `szpital.Patients p` WHERE p.patient_id=" + id + "");
        while (rs.next()) {
            index = rs.getString(1);
            fName = rs.getString(2);
            lName = rs.getString(3);

        }
        Patient p = new Patient(fName, lName, index);

        this.close();
        return p;
    }

    ///////////////////pobiera ilosc rezerwacji w bazie +1 ///////////////////////////
    public int getFreeResId() throws Exception {
        this.open();
        int tmp = 0;
        rs = statement.executeQuery("SELECT COUNT(*) AS we FROM `Reservations` GROUP BY reserv_id");
        while (rs.next()) {
            tmp = Integer.parseInt(rs.getString(1));
        }
        this.close();
        return tmp++;
    }

    ////////////////////////////pobiera wolny pokoj optymalny do potrzebnej uslugi(brak opcji z tyborem dobrego pokoju z szybszym terminem)/////////////////////////
    public int fitRoom(Reservation r) throws Exception {
        this.open();

        int tmp = 0;
        rs = statement.executeQuery("SELECT DISTINCT s.name FROM `szpital.Rooms r, `szpital.Medical_services` s WHERE r.room_id=s.room_id AND s.name=" + r.getNameOfService() + "");
        while (rs.next()) {
            tmp = Integer.parseInt(rs.getString(1));
        }

        this.close();
        return tmp;
    }

    ///////////////////////funkcja dobiera do pokoju kompetentna zaloge z wolnym terminem/////////////////////////////////////
    public String getStaff(Reservation r) throws Exception {
        this.open();

        String tmp = null;
        ////////////////////////////tu jakies czary powinny byc ////////////////////////////////////////////
        rs = statement.executeQuery("");
        while (rs.next()) {

        }

        this.close();
        return tmp;
    }

    ////////////////////////////pobiera liste zabiegow pacjenta(rezerwacje po id pacjenta)/////////////////////////
    public ObservableList<Reservation> getPatientsServices(String id) throws Exception {
        this.open();

        ObservableList<Reservation> list = ObservableCollections.observableList(new ArrayList<Reservation>());

        int reservId;
        String patientId;
        String data = null;
        int roomId;
        String nameOfService;
        String staff;

        String dataTabPom[];
        String godzinaTab[];

        rs = statement.executeQuery("SELECT * FROM `Reservations` r `Patients` p WHERE r.patient_id=p.patient_id AND p.patient_id=" + id + "");
        while (rs.next()) {
            reservId = Integer.parseInt(rs.getString(1));
            patientId = rs.getString(2);
            data = rs.getString(3);
            roomId = Integer.parseInt(rs.getString(4));
            nameOfService = rs.getString(5);
            staff = rs.getString(6);
        

        dataTabPom = data.split(" ");
        godzinaTab = dataTabPom[3].split(":");

        Date d = new Date("" + dat.convertMonths(dataTabPom[1]) + "/" + dataTabPom[2] + "/" + dataTabPom[5] + " " + godzinaTab[0] + ":" + godzinaTab[1] + "");
        list.add(new Reservation(reservId, patientId, d, roomId, nameOfService, staff));
        }
        
        this.close();
        return list;

    }

    ////////////////////////////pobiera liste zabiegow dla pokoju(rezerwacje po id pokoju)/////////////////////////
    public ObservableList<Reservation> getRoomServices(int id,String y,String m, String d) throws Exception {
        this.open();

        ObservableList<Reservation> list = ObservableCollections.observableList(new ArrayList<Reservation>());

        int reservId;
        String patientId;
        String data = null;
        int roomId;
        String nameOfService;
        String staff;

        String dataTabPom[];
        String godzinaTab[];

        rs = statement.executeQuery("SELECT * FROM `Reservations` r `Patients` p WHERE r.patient_id=p.patient_id AND r.room_id=" + id + 
                "AND YEAR(r.h_from)="+y+"AND MONTH(r.h_from)="+m+"AND DAY(r.h_from)="+d+"");
        while (rs.next()) {
            reservId = Integer.parseInt(rs.getString(1));
            patientId = rs.getString(2);
            data = rs.getString(3);
            roomId = Integer.parseInt(rs.getString(4));
            nameOfService = rs.getString(5);
            staff = rs.getString(6);
        

        dataTabPom = data.split(" ");
        godzinaTab = dataTabPom[3].split(":");

        Date d0 = new Date("" + dat.convertMonths(dataTabPom[1]) + "/" + dataTabPom[2] + "/" + dataTabPom[5] + " " + godzinaTab[0] + ":" + godzinaTab[1] + "");
        list.add(new Reservation(reservId, patientId, d0, roomId, nameOfService, staff));
        }
        
        this.close();
        return list;

    }
    
    
    public void sendQueryToNameOfDataBase(String query) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            try (Connection con = DriverManager.getConnection(url, user, password); Statement stt = con.createStatement()) {

                stt.execute("USE nameOfDataBase");

                stt.execute(query);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
