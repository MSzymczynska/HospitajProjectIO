/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.sql.*;
import java.util.ArrayList;

public class Users {
    private int id_;
    private String username_;
    private String password_;
    private UsersGroups groups_;
    private UsersPrivileges privileges_;
    private boolean is_admin_;
    
    public Users(int id, String username, boolean is_admin)
    {
        id_ = id;
        username_ = username;
        is_admin_ = is_admin;
        privileges_ = new UsersPrivileges(id_);
        groups_ = new UsersGroups(id_);
    }
    
    public String username()
    {
        return username_;
    }
    
    public UsersGroups getGroups()
    {
        return groups_;
    }
    
    public UsersPrivileges getPrivileges()
    {
        return privileges_;
    }
    
    public static void addUser(String name, String pass, ArrayList<Groups> groups, 
            ArrayList<Privileges> privileges)
    {
        
    }
    
    public static ArrayList<Users> getUsers() {
        ArrayList<Users>users = new ArrayList<Users>();
        try {
            Connection conn = DbManager.getInstance().connection();
            PreparedStatement stmt = conn.prepareStatement("SELECT users_id, username, password, admin FROM users");
            java.sql.ResultSet results = stmt.executeQuery();
            
            while (results.next())
            {
                Users u = new Users(results.getInt(1), results.getString(2), results.getBoolean(4));
                users.add(u);
            }
            
            return users;
        } catch (SQLException e)
        {
            System.err.println("Users: " + e.getMessage());
        }
        return users;
    }
    
    public static void removeUser(Users u) {
        
    }
}
