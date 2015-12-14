/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.ArrayList;

/**
 *
 * @author pbugara
 */
public class UsersPrivileges {
    private ArrayList<Privileges> privileges_;
    
    public boolean can(String privilegePath)
    {
        for(Privileges p : privileges_)
        {
            if(p.exists(privilegePath))
                return true;
            
        }
        return false;
    }
    
    public ArrayList<Privileges> privileges()
    {
        return privileges_;
    }
    
    public void add(int user, String privilege)
    {
        
    }
    
    public void remove(int user, String privilegePath)
    {
        
    }
}
