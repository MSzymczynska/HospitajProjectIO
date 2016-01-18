/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.lang.Exception;
import java.sql.*;
import java.util.ArrayList;

public class Accounts {
    private final Connection database;
    private static Accounts instance_ = null;
    private Users auth_user_ = null;
    
    private Accounts(Connection db)
    {
        database = db;
    }
    
    public static Accounts getInstance() throws Exception
    {
        if (instance_ == null)
        {
            throw new java.lang.Exception("You must initialize Accounts first!");
        }
        return instance_;
    }
    
    public static Accounts getInstance(Connection conn) throws Exception
    {
        if (instance_ == null && conn != null)
        {
            instance_ = new Accounts(conn);
        } else if (conn == null)
        {
            throw new java.lang.Exception("You must set connection!");
        }
        return instance_;
    }
    
    public int authenticate(String username, String password)
    {
        try {
            PreparedStatement stmt = database.prepareStatement("SELECT users_id, username, admin FROM users WHERE username = ? AND password = PASSWORD(?) LIMIT 1");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet results = stmt.executeQuery();
            
            if (!results.next())
            {
                return 1; // brak rekordow
            } else
            {
                if (results.getString(2).compareTo(username) == 0)
                {
                    auth_user_ = new Users(results.getInt(1), results.getString(2), results.getBoolean(3));
                    return 0; // brak bledow
                }
            }
            
        } catch (SQLException e)
        {
            System.err.println("Accounts: " + e.getMessage());
            return 2; // blad sql
        }
        
        return 3;
    }
    
    public boolean can(String privilegePath)
    {
        Users auth_user = getAuthenticatedUser();
        if (auth_user == null)
        {
            return false;
        }
        
        if (isAdmin())
        {
            return true;
        }
        
        ArrayList<Groups> groups = auth_user.getGroups().getGroups();
        for (int i = 0; i < groups.size(); ++i)
        {
            if (groups.get(i).can(privilegePath))
            {
                return true;
            }
        }
        
        ArrayList<Privileges> privileges = auth_user.getPrivileges().privileges();
        for (int i = 0; i < groups.size(); ++i)
        {
            if (privileges.get(i).path().compareTo(privilegePath) == 0)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isAdmin()
    {
        return false;
    }
    
    public Users getAuthenticatedUser()
    {
        return auth_user_;
    }
}
