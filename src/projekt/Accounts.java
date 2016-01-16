/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.lang.Exception;
import java.sql.*;

public class Accounts {
    private final Connection database;
    private static Accounts instance_ = null;
    
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
            PreparedStatement stmt = database.prepareStatement("SELECT username FROM users WHERE username = ? AND password = PASSWORD(?) LIMIT 1");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet results = stmt.executeQuery();
            
            if (!results.next())
            {
                return 1; // brak rekordow
            } else
            {
                if (results.getString(1).compareTo(username) == 0 && !results.next())
                {
                    return 0; // brak bledow
                } else
                {
                    return 2; // pobrano za duzo rekordow!
                }
            }
            
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            return 3; // blad sql
        }
        
    }
    
    public boolean can(String privilegePath)
    {
        return true;
    }
    
    public boolean isAdmin()
    {
     return true;   
    }
    
    public Users getAuthenticatedUser()
    {
        return null;
    }
}
