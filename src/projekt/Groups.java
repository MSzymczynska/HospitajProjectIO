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
public class Groups {
    private UsersGroups users_;
    private GroupsPrivileges groups_;
    private int id_;
    private String name_;
    
    public Groups(String name)
    {
        
    }
    
    public Groups(int id)
    {
        
    }
    
    public String getName()
    {
        return name_;
    }
    
    public GroupsPrivileges getPrivileges()
    {
        return groups_;
    }
    
    public UsersGroups getUsers()
    {
        return users_;
    }
    
    public void addPrivilege(String privilegePath)
    {
        
    }
    
    public void removePrivilege(String privilegePath)
    {
        
    }
}

