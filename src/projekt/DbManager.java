/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.sql.*;

/**
 *
 * @author kacper
 */
public class DbManager {    
    static private DbManager instance_ = null;
    private java.sql.Connection connection;
    
    private DbManager() throws SQLException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + DbConfig.server + "/" + DbConfig.dbname, DbConfig.username, DbConfig.password);
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    public static DbManager getInstance() throws SQLException
    {
        if (instance_ == null)
        {
            instance_ = new DbManager();
        }
        return instance_;
    }
    
    public java.sql.Connection connection()
    {
        return connection;
    }
}
