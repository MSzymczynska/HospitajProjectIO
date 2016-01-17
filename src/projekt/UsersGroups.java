/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pbugara
 */
public class UsersGroups {
    private ArrayList<Groups> groups_;
    
    public UsersGroups(int id)
    {
        groups_ = new ArrayList<Groups>();
        try {
            Connection conn = DbManager.getInstance().connection();
            PreparedStatement stmt = conn.prepareStatement("SELECT g.groups_id, g.name FROM users_groups AS up, groups AS g WHERE up.groups_id = g.groups_id AND up.users_id = ?");
            stmt.setInt(1, id);
            ResultSet results = stmt.executeQuery();
            
            while (results.next())
            {
                Groups p = new Groups(results.getInt(1), results.getString(2));
                groups_.add(p);
            }
        } catch (SQLException e)
        {
            System.err.println("UsersGroups: " + e.getMessage());
        }
    }
    
    public ArrayList<Groups> getGroups() {
        return groups_;
    }
}
