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
public class GroupsPrivileges {
    private ArrayList<Privileges> privileges_;
    
    public boolean can(String privilegePath)
    {
        return true;
    }
    
    public ArrayList<Privileges> privileges()
    {
        return privileges_;
    }
    
    public void add(int group, String privilegePath)
    {
        
    }
    
    public void remove(int group, String privilegePath)
    {
        
    }
}
