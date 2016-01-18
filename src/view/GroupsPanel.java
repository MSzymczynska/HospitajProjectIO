/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import projekt.Groups;

/**
 *
 * @author piotr
 */
public class GroupsPanel extends JPanel {
    JTabbedPane pane;
    JPanel addGroupPanel, deleteGroupPanel, editGroupPanel;
    private static DefaultListModel groupsModel;
    private static ArrayList<Groups> groups_;
    public GroupsPanel()
    {
    	setVisible(true);
        this.setLayout(new BorderLayout());
        model();
        pane = new JTabbedPane();
        addGroupPanel = new AddGroupPanel();
        deleteGroupPanel = new DeleteGroupPanel();
        editGroupPanel = new EditGroupPanel();
        pane.addTab("Dodaj grupe", addGroupPanel);
        pane.addTab("Usun grupe", deleteGroupPanel);
        pane.addTab("Edytuj grupe", editGroupPanel);
        this.add(pane, BorderLayout.NORTH);        
    }
    
    public void model() {
        groupsModel = new DefaultListModel();
        groups_ = Groups.getGroups();
        for(Groups g : groups_) {
            groupsModel.addElement(g.getName());            
        }
    }
    
    public static DefaultListModel getGroupsModel() {
        return groupsModel;
    }
    
    public static void removeGroup(int index) {
        Groups g = groups_.get(index);
        groups_.remove(index);
     //   Groups.removeGroup(g);
        groupsModel.removeElementAt(index);
    }
    
    public static void addGroup(Groups group) {
        //Groups.addGroup(group);
        groups_.add(group);
        groupsModel.addElement(group.getName());
    }
    
    public static void updateGroup(int index, String name) {
        Groups g = groups_.get(index);
     //   Groups.update(g, name);
        g.setName(name);
        groups_.set(index, g);
        groupsModel.set(index, name);
    }
}
