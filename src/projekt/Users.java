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
    
    public int id()
    {
        return id_;
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
    
    public static boolean addUser(String name, String pass, ArrayList<Groups> groups, ArrayList<Privileges> privileges)
    {
        try {
            Connection conn = DbManager.getInstance().connection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password, admin) VALUES (?, PASSWORD(?), 0)");
            stmt.setString(1, name);
            stmt.setString(2, pass);
            
            stmt.execute();
            ResultSet keys = stmt.getGeneratedKeys();
            int id = 0;
            if (keys.next())
            {
                id = keys.getInt(1);
            }
            
            addUserPrivileges(id, privileges);
            addUserGroups(id, groups);
            return true;
        } catch (SQLException e)
        {
            System.err.println("Users::addUser: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean addUserGroups(int id, ArrayList<Groups> groups)
    {
        try {
            Connection conn = DbManager.getInstance().connection();
            for (int i = 0; i < groups.size(); ++i)
            {
                PreparedStatement stmt = conn.prepareStatement("INSERT IGNORE INTO users_groups SET users_id = ?, groups_id = ?");
                stmt.setInt(1, id);
                stmt.setInt(2, groups.get(i).id());
                stmt.execute();
            }
            return true;
        } catch (SQLException e)
        {
            System.err.println("Users::addUserGroups: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean addUserPrivileges(int id, ArrayList<Privileges> privileges)
    {
        try {
            Connection conn = DbManager.getInstance().connection();
            for (int i = 0; i < privileges.size(); ++i)
            {
                PreparedStatement stmt = conn.prepareStatement("INSERT IGNORE INTO users_privileges SET users_id = ?, privileges_id = ?");
                stmt.setInt(1, id);
                stmt.setInt(2, privileges.get(i).id());
                stmt.execute();
            }
            return true;
        } catch (SQLException e)
        {
            System.err.println("Users::addUserPrivileges: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean updateUser(String name, String pass)
    {
        return true;
    }
    
    public static ArrayList<Users> getUsers() {
        ArrayList<Users>  users = new ArrayList<Users>();
        try {
            Connection conn = DbManager.getInstance().connection();
            PreparedStatement stmt = conn.prepareStatement("SELECT users_id, username, admin FROM users");
            java.sql.ResultSet results = stmt.executeQuery();
            
            while (results.next())
            {
                Users u = new Users(results.getInt(1), results.getString(2), results.getBoolean(3));
                users.add(u);
            }
            
            return users;
        } catch (SQLException e)
        {
            System.err.println("Users: " + e.getMessage());
        }
        return users;
    }
    
    public static boolean removeUser(Users user) {
        try {
            Connection conn = DbManager.getInstance().connection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE users_id = ?");
            stmt.setInt(1,user.id());
            
            stmt.execute();
            return true;
        } catch (SQLException e)
        {
            System.err.println("Users::removeUser: " + e.getMessage());
            return false;
        }
    }
}
