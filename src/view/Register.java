/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import projekt.Groups;
import projekt.Privileges;
import projekt.Users;

/**
 *
 * @author pbugara
 */
public class Register extends JPanel {
    private JLabel titleLabel, nameLabel, surnameLabel, passLabell, rePassLabel;
    private JLabel phoneLabel, emailLabel, salaryLabel, managerLabel, departmentLabel;
    private JLabel groupsLabel, chGroupsLabel, privilegesLabel, chPrivilegesLabel;
    private JTextField nameText, surnameText, passText, rePassText;
    private JTextField phoneText, emailText;
    private JFormattedTextField salaryText;
    private JPanel dataPanel, privilegesPanel, titlePanel, buttonPanel;
    private JCheckBox userBox, managerBox, adminBox;
    private JCheckBox closeSysBox, changePrivilegeBox, addUserBox, removeUserBox;
    private JButton registerButton;
    private JList groupsList, privilegesList, chGroupsList, chPrivilegesList;
    private DefaultListModel listModel, privilegesModel, chPrivilegesModel, chGroupsModel;
    private JScrollPane groupsPane, privilegesPane, chGroupsPane, chPrivilegesPane; 
    private GridBagConstraints gbc = new GridBagConstraints();
    private ArrayList<JCheckBox> adminBoxList, managerBoxList, userBoxList;
    private ArrayList<Groups> groups_;
    private ArrayList<Privileges> privileges_;
    
    
    public Register() {        
        this.setSize(1366, 768);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        
        titleLabel = new JLabel("Dodaj nowego użytkownika");
        nameLabel = new JLabel("Imie");
        surnameLabel = new JLabel("Nazwisko");
        passLabell = new JLabel("Hasło");
        rePassLabel = new JLabel("Powtórz hasło");
        phoneLabel = new JLabel("Telefon");
        emailLabel = new JLabel("Email");
        salaryLabel = new JLabel("Płaca");
        managerLabel = new JLabel("Manager");
        departmentLabel = new JLabel("Oddział");
        groupsLabel = new JLabel("Dostępne grupy");
        chGroupsLabel = new JLabel("Wybrane grupy");
        privilegesLabel = new JLabel("Dostępne uprawnienia");
        chPrivilegesLabel = new JLabel("Wybrane uprawnienia");
        nameText = new JTextField(20);
        surnameText = new JTextField(20);
        passText = new JPasswordField(20);
        rePassText = new JPasswordField(20);
        phoneText = new JTextField(20);
        emailText = new JTextField(20);
        salaryText = new JFormattedTextField();
        adminBox = new JCheckBox("Admin", false);
        managerBox = new JCheckBox("Manager", false);
        userBox = new JCheckBox("Użytkownik", false);
        changePrivilegeBox = new JCheckBox("Zmiana uprawnień", false);
        closeSysBox = new JCheckBox("Wyłączanie system", false);
        addUserBox = new JCheckBox("Dodawanie użytkownika", false);
        removeUserBox = new JCheckBox("Usuwanie użytkownika", false);
        registerButton = new JButton("Zarejestruj");
        
        
        dataPanel = new JPanel(new GridBagLayout());
        titlePanel = new JPanel(new GridBagLayout());
        privilegesPanel = new JPanel(new GridBagLayout());
        
        titlePanel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()/10));
        dataPanel.setPreferredSize(new Dimension(this.getWidth()*3/7, this.getHeight()*2/5));
        privilegesPanel.setPreferredSize(new Dimension(this.getWidth()*4/7, this.getHeight()*2/5));
        dataPanel.setSize(new Dimension(this.getWidth()*3/7, this.getHeight()*2/5));
        privilegesPanel.setSize(new Dimension(this.getWidth()*4/7, this.getHeight()*2/5));
        
        
        titleLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        titlePanel.add(titleLabel, gbc);
        //gbc.gridheight = 10;
        // name
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 1;
        gbc.gridy = 2;        
        dataPanel.add(nameLabel, gbc);        
        gbc.gridx = 2;
        gbc.gridy = 2;        
        dataPanel.add(nameText, gbc);
        
        // surname
        gbc.gridx = 1;
        gbc.gridy = 3;        
        dataPanel.add(surnameLabel, gbc);        
        gbc.gridx = 2;
        gbc.gridy = 3;        
        dataPanel.add(surnameText, gbc);
        
        // pass
        gbc.gridx = 1;
        gbc.gridy = 4;        
        dataPanel.add(passLabell, gbc);        
        gbc.gridx = 2;
        gbc.gridy = 4;        
        dataPanel.add(passText, gbc);
        
        // repass
        gbc.gridx = 1;
        gbc.gridy = 5;        
        dataPanel.add(rePassLabel, gbc);        
        gbc.gridx = 2;
        gbc.gridy = 5;        
        dataPanel.add(rePassText, gbc);
        
        // phone
        gbc.gridx = 1;
        gbc.gridy = 6;        
        dataPanel.add(phoneLabel, gbc);        
        gbc.gridx = 2;
        gbc.gridy = 6;        
        dataPanel.add(phoneText, gbc);
        
        // email
        gbc.gridx = 1;
        gbc.gridy = 7;        
        dataPanel.add(emailLabel, gbc);        
        gbc.gridx = 2;
        gbc.gridy = 7;        
        dataPanel.add(emailText, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        privilegesPanel.add(groupsLabel, gbc);
        listModel = new DefaultListModel();
        groups_ = Groups.getGroups();
        for(Groups g : groups_) {
            listModel.addElement(g.getName());
        }
        groupsList = new JList(listModel);
        groupsList.setVisibleRowCount(5);
        groupsPane = new JScrollPane(groupsList);
        groupsPane.setName("Grupy");
        groupsPane.setPreferredSize(new Dimension(privilegesPanel.getWidth()*1/6, privilegesPanel.getHeight()*2/5));       
        groupsPane.setSize(new Dimension(privilegesPanel.getWidth()*1/6, privilegesPanel.getHeight()*2/5));        
        gbc.gridx = 1;
        gbc.gridy = 1;
        privilegesPanel.add(groupsPane, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        privilegesPanel.add(chGroupsLabel, gbc);
        chGroupsModel = new DefaultListModel();       
        chGroupsList = new JList(chGroupsModel);
        chGroupsList.setVisibleRowCount(5);
        chGroupsList.setSize(100,100);
        chGroupsPane = new JScrollPane(chGroupsList);
        chGroupsPane.setName("Wybrane grupy");
        chGroupsPane.setPreferredSize(new Dimension(privilegesPanel.getWidth()*1/6, privilegesPanel.getHeight()*2/5));       
        chGroupsPane.setSize(new Dimension(privilegesPanel.getWidth()*1/6, privilegesPanel.getHeight()*2/5));
        gbc.gridx = 2;
        gbc.gridy = 1;        
        privilegesPanel.add(chGroupsPane, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        privileges_ = Privileges.getPrivileges();
        privilegesPanel.add(privilegesLabel, gbc);
        privilegesModel = new DefaultListModel();
        for(Privileges p : privileges_) {
            privilegesModel.addElement(p.path());
        }
        privilegesList = new JList(privilegesModel);
        privilegesList.setVisibleRowCount(5);
        privilegesPane = new JScrollPane( privilegesList);
        privilegesPane.setPreferredSize(new Dimension(privilegesPanel.getWidth()*1/6, privilegesPanel.getHeight()*2/5));       
        privilegesPane.setSize(new Dimension(privilegesPanel.getWidth()*1/6, privilegesPanel.getHeight()*2/5));
        privilegesPane.setName("Uprawnienia");
        gbc.gridx = 1;
        gbc.gridy = 3;
        privilegesPanel.add(privilegesPane, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        privilegesPanel.add(chPrivilegesLabel, gbc);
        chPrivilegesModel = new DefaultListModel();
        chPrivilegesList = new JList(chPrivilegesModel);
        chPrivilegesList.setVisibleRowCount(5);
        chPrivilegesPane = new JScrollPane(chPrivilegesList);
        chPrivilegesPane.setName("Wybrane uprawnienia");
        chPrivilegesPane.setPreferredSize(new Dimension(privilegesPanel.getWidth()*1/6, privilegesPanel.getHeight()*2/5));       
        chPrivilegesPane.setSize(new Dimension(privilegesPanel.getWidth()*1/6, privilegesPanel.getHeight()*2/5));
        gbc.gridx = 2;
        gbc.gridy = 3;
        privilegesPanel.add(chPrivilegesPane, gbc);
        
        groupsList.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent evt) {
                 JList list = (JList)evt.getSource();
                 if (evt.getClickCount() == 2) {
                   //  listModel.removeAllElements();
                     Object o = groupsList.getSelectedValue();
                     chGroupsModel.addElement(o);
                     listModel.removeElement(o);
                 }
             }
        });
        privilegesList.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent evt) {
                 JList list = (JList)evt.getSource();
                 if (evt.getClickCount() == 2) {
                   //  listModel.removeAllElements();
                     Object o = privilegesList.getSelectedValue();
                     chPrivilegesModel.addElement(o);
                     privilegesModel.removeElement(o);
                 }
             }
        });
        chGroupsList.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent evt) {
                 JList list = (JList)evt.getSource();
                 if (evt.getClickCount() == 2) {
                   //  listModel.removeAllElements();
                     Object o = chGroupsList.getSelectedValue();
                     listModel.addElement(o);
                     chGroupsModel.removeElement(o);
                 }
             }
        });
        chPrivilegesList.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent evt) {
                 JList list = (JList)evt.getSource();
                 if (evt.getClickCount() == 2) {
                   //  listModel.removeAllElements();
                     Object o = chPrivilegesList.getSelectedValue();
                     privilegesModel.addElement(o);
                     chPrivilegesModel.removeElement(o);
                 }
             }
        });
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(dataPanel, BorderLayout.WEST);
        this.add(privilegesPanel, BorderLayout.CENTER);
        this.add(registerButton, BorderLayout.SOUTH);
    }
    
    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name, surname;
        String pass, repass;
        String phone;
        String email;
        name = nameText.getText();
        surname = surnameText.getText();
        pass = passText.getText();
        repass = rePassText.getText();
        phone = phoneText.getText();
        email = emailText.getText();
        ArrayList<Groups> groups = new ArrayList<>();
        for(int i=0; i<chGroupsModel.getSize(); i++) {
            for(Groups g : groups_)
            {
                if(g.getName().equals(chGroupsModel.getElementAt(i))) {                    
                    groups.add(g);
                    break;
                }
            }
        }
        ArrayList<Privileges> privileges = new ArrayList<>();
        for(int i=0; i<chPrivilegesModel.getSize(); i++) {
            for(Privileges p : privileges_)
            {
                if(p.path().equals(chPrivilegesModel.getElementAt(i))) {                    
                    privileges.add(p);
                    break;
                }
            }
        }
        
        Users.addUser(name, surname, pass, repass, phone, email, groups, privileges);
    }
}
