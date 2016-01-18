/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import com.mysql.jdbc.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Privileges {
    private int id_;
    private String path_;
    
    Privileges(int id, String path)
    {
        id_ = id;
        path_ = path;
    }
    
    public int id()
    {
        return id_;
    }
    
    public String path()
    {
        return path_;
    }
    
    public static ArrayList<Privileges> getPrivileges() {        
        ArrayList<Privileges>privileges_ = new ArrayList<Privileges>();
        try {
            Connection conn = DbManager.getInstance().connection();
            PreparedStatement stmt = conn.prepareStatement("SELECT privileges_id, privilege_path FROM privileges");
            java.sql.ResultSet results = stmt.executeQuery();
            
            while (results.next())
            {
                Privileges p = new Privileges(results.getInt(1), results.getString(2));
                privileges_.add(p);
            }
            
            return privileges_;
        } catch (SQLException e)
        {
            System.err.println("Privileges: " + e.getMessage());
        }
        return null;
    }
}
