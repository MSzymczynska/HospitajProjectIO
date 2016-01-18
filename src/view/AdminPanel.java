/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author piotr
 */
public class AdminPanel extends JPanel {
    JTabbedPane pane;
    JPanel addUserPanel, deleteUserPanel, editUserPanel;
    
    public AdminPanel()
    {
    	setVisible(true);
        this.setLayout(new BorderLayout());
        pane = new JTabbedPane();
        addUserPanel = new Register();
        deleteUserPanel = new DeletePanel();
        editUserPanel = new EditPanel();
        pane.addTab("Dodaj użytkownika", addUserPanel);
        pane.addTab("Usuń użytkownika", deleteUserPanel);
        pane.addTab("Edytuj użytkownika", editUserPanel);
        this.add(pane, BorderLayout.NORTH);
    }
}
