/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.ArrayList;
import java.sql.*;

public class UsersPrivileges {
    private ArrayList<Privileges> privileges_;
    
    UsersPrivileges(int id)
    {
        privileges_ = new ArrayList<Privileges>();
        try {
            Connection conn = DbManager.getInstance().connection();
            PreparedStatement stmt = conn.prepareStatement("SELECT p.privileges_id, p.privilege_path FROM users_privileges AS up, privileges AS p WHERE up.privileges_id = p.privileges_id AND up.users_id = ?");
            stmt.setInt(1, id);
            ResultSet results = stmt.executeQuery();
            
            while (results.next())
            {
                Privileges p = new Privileges(results.getInt(1), results.getString(2));
                privileges_.add(p);
            }
        } catch (SQLException e)
        {
            System.err.println("UsersPrivileges: " + e.getMessage());
        }
    }
    
    public boolean can(String privilegePath)
    {
        for (int i = 0; i < privileges_.size(); ++i)
        {
            if (privileges_.get(i).path().compareTo(privilegePath) == 0)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public ArrayList<Privileges> privileges()
    {
        return privileges_;
    }
    
    public static void add(int user, String privilege)
    {
        
    }
    
    public static void remove(int user, String privilegePath)
    {
        
    }
}
