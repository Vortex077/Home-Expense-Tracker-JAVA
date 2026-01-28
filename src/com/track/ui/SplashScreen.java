package com.track.ui;


import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

public class SplashScreen extends JWindow {
    JProgressBar progressBar;
    //Constructor
    SplashScreen(){
        setSize(700,500);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK); 

        ImageIcon image = new ImageIcon("images/expense_logo02.png");
        JLabel lbl = new JLabel(image);
        getContentPane().add(lbl , BorderLayout.CENTER);
        progressBar = new JProgressBar(1, 100);
        progressBar.setStringPainted(true);
        this.getContentPane().add(progressBar,BorderLayout.SOUTH);
        loadProgress();
    }
    Timer timer;
    void loadProgress(){
        timer = new Timer(30, e->{
            int val = progressBar.getValue();
            if(val < 100){
                progressBar.setValue(val + 1);
            }else{
                timer.stop();
                dispose(); //current screen dispose
                Login login = new Login();
                login.setVisible(true);
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.setVisible(true); //to show screen
    }
}
