/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import projekt.Users;

/**
 *
 * @author piotr
 */
public class DeletePanel extends JPanel {
    private JScrollPane usersPane;
    private DefaultListModel usersModel; 
    private JList usersList;
    private JButton deleteUserButton;
    private JLabel label;
    private JPanel labelPanel, usersPanel;
    private ArrayList<Users> users_;
    
    public DeletePanel()
    {
        this.setLayout(new BorderLayout());
        this.setSize(1366, 768);
        usersModel = new DefaultListModel();
        users_ = Users.getUsers();
        for(Users u : users_) {
            usersModel.addElement(u.username());
        }
        usersList = new JList(usersModel);
        usersPane = new JScrollPane(usersList);
        label = new JLabel("Usuń użytkownika");
        deleteUserButton = new JButton("Usuń");
        
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
        
        labelPanel = new JPanel(new BorderLayout());
        labelPanel.setSize(new Dimension(this.getWidth()/5, this.getHeight()/5));
        labelPanel.setPreferredSize(new Dimension(this.getWidth()/5, this.getHeight()/5));
        labelPanel.add(label, BorderLayout.CENTER);
        usersPanel = new JPanel(new BorderLayout());
        usersPanel.setSize(new Dimension(this.getWidth(), this.getHeight()/3));
        usersPanel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()/3));
        usersPanel.add(usersPane, BorderLayout.NORTH);
        usersPanel.add(deleteUserButton, BorderLayout.CENTER);
        
        this.add(labelPanel, BorderLayout.NORTH);
        this.add(usersPanel, BorderLayout.CENTER);
        
        deleteUserButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = usersList.getSelectedIndex();
                Users u = users_.get(index);
                users_.remove(index);
                usersModel.removeElementAt(index);
                usersList.setModel(usersModel);
                Users.removeUser(u);
            } 
        });
    }
}
