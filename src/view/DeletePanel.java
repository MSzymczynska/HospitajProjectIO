/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
    
    public DeletePanel()
    {
        this.setLayout(new BorderLayout());
        this.setSize(1366, 768);
        usersModel = new DefaultListModel();
        usersModel.addElement("Jan");
        usersModel.addElement("Janusz");
        usersList = new JList(usersModel);
        usersPane = new JScrollPane(usersList);
        label = new JLabel("Usuñ u¿ytkownika");
        deleteUserButton = new JButton("Usuñ");
        
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
    }
}
