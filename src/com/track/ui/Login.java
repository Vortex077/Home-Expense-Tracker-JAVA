package com.track.ui;

import com.track.dao.UserDAO;
import com.track.dto.UserDTO;
import java.awt.Font;
import javax.swing.*;

public class Login extends JFrame {
    JTextField username;
    JPasswordField password;
    
    public Login(){
        setTitle("User Login");
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        //JLabel logintxt = new JLabel("User Login");
        JLabel userlbl = new JLabel("User Name");
        userlbl.setFont(new Font("Arial", Font.BOLD, 14));
        userlbl.setBounds(20, 50, 120, 50); //setting bounds
        this.getContentPane().add(userlbl);
        username = new JTextField();
        username.setBounds(170, 50, 170, 50);
        this.getContentPane().add(username);
        JLabel passwordlbl = new JLabel("Password");
        passwordlbl.setBounds(20, 130, 70, 50);
        this.getContentPane().add(passwordlbl);
        password = new JPasswordField();
        password.setBounds(170, 140, 170, 50);
        this.getContentPane().add(password);
        JButton loginBt = new JButton("Login");
        loginBt.setBounds(150, 250, 150, 50);
        this.getContentPane().add(loginBt);
        getContentPane().setLayout(null); //default borderlayout set to null 
        loginBt.addActionListener(e->{
            doLogin();
        });
    }


    void doLogin(){
        String uname = username.getText();
        String pwd = new String(password.getPassword());

        UserDAO userDAO = new UserDAO();
        UserDTO user = userDAO.login(uname, pwd);

        if(user != null){
            JOptionPane.showMessageDialog(this, "Welcome " + user.getUsername());
            new Dashboard(user.getId(), user.getUsername());
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
    }


    // public static void main(String[] args) {
    //     Login login = new Login();
    //     login.setVisible(true);
    // }
}
