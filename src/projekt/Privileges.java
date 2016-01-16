/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

public class Privileges {
    private int id_;
    private String path_;
    
    Privileges(int id, String path)
    {
        id_ = id;
        path_ = path;
    }
    
    public String path()
    {
        return path_;
    }
}
