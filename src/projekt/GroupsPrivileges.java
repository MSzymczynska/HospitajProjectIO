/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.ArrayList;
import java.sql.*;


public class GroupsPrivileges {
    private ArrayList<Privileges> privileges_;
    
    GroupsPrivileges(int id)
    {
        privileges_ = new ArrayList<Privileges>();
        try {
            Connection conn = DbManager.getInstance().connection();
            PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT p.privileges_id, p.privilege_path FROM groups_privileges AS gp, privileges AS p WHERE gp.privileges_id = p.privileges_id AND gp.groups_id = ?");
            stmt.setInt(1, id);
            ResultSet results = stmt.executeQuery();
            
            while (results.next())
            {
                privileges_.add(new Privileges(results.getInt(1), results.getString(2)));
            }
        } catch (SQLException e)
        {
            System.err.println("GroupsPrivileges: " + e.getMessage());
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
    
    public static void add(int group, String privilegePath)
    {
        
    }
    
    public static void remove(int group, String privilegePath)
    {
        
    }
}
