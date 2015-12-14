/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

/**
 *
 * @author pbugara
 */
public class Users {
    private UsersGroups groups_;
    private UsersPrivileges privileges_;
    
    public Users(String username)
    {
    
    }
    
    public Users(int id)
    {
        
    }
    
    public String username()
    {
        return "username";
    }
    
    public void username(String name)
    {
        
    }
    
    public void password(String pass)
    {
        
    }
    
    public boolean checkPassowrd(String pass)
    {
        return true;
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
