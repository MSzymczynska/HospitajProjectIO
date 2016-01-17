/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pbugara
 */
public class Groups {
    private UsersGroups users_;
    private GroupsPrivileges groups_;
    private int id_;
    private String name_;
    
    
    public Groups(int id, String name) {
        id_ = id;
        name_ = name;
    }
    
    public Groups(String name) {
        this.name_ = name;
    }
    
    public int id()
    {
        return id_;
    }
    
    public String getName()
    {
        return name_;
    }
    
    public void setName(String name) {
        this.name_ = name;
    }
    
    public GroupsPrivileges getPrivileges()
    {
        return groups_;
    }
    
    public UsersGroups getUsers()
    {
        return users_;
    }
    
    public boolean can(String privilegePath)
    {
        return groups_.can(privilegePath);
    }
    
    public void addPrivilege(String privilegePath)
    {
        
    }
    
    public void removePrivilege(String privilegePath)
    {
        
    }
    
    public static ArrayList<Groups> getGroups() {
        ArrayList<Groups>groups_ = new ArrayList<Groups>();
        try {
            Connection conn = DbManager.getInstance().connection();
            PreparedStatement stmt = conn.prepareStatement("SELECT groups_id, name FROM groups");
            java.sql.ResultSet results = stmt.executeQuery();
            
            while (results.next())
            {
                Groups g = new Groups(results.getInt(1), results.getString(2));
                groups_.add(g);
            }
            
            return groups_;
        } catch (SQLException e)
        {
            System.err.println("Groups:: " + e.getMessage());
        }
        return null;
    }
}

