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
public class Accounts {
    private static Accounts instance_;
    
    public static Accounts getInstance()
    {
        return instance_;
    }
    
    public int authenticate(String username, String password)
    {
        return 1;
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
