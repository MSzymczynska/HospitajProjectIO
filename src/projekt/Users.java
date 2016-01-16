/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.sql.*;

public class Users {
    private int id_;
    private String username_;
    private UsersGroups groups_;
    private UsersPrivileges privileges_;
    private boolean is_admin_;
    
    public Users(int id, String username, boolean is_admin)
    {
        id_ = id;
        username_ = username;
        is_admin_ = is_admin;
        
        privileges_ = new UsersPrivileges(id_);
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
}
