package com.track.ui;

import java.awt.*;
import javax.swing.*;

public class Dashboard extends JFrame{

    public Dashboard(int userId, String username) {
        
        setTitle("Home Expense Tracker");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome, " + username, JLabel.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcome, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4,1,10,10));

        JButton addBtn = new JButton("Add Expense");
        JButton viewBtn = new JButton("View Expenses");
        JButton reportBtn = new JButton("Monthly Report");
        JButton logoutBtn = new JButton("Logout");

        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(reportBtn);
        panel.add(logoutBtn);

        add(panel, BorderLayout.CENTER);

        addBtn.addActionListener(e -> new AddExpense(userId));
        viewBtn.addActionListener(e -> new ViewExpense(userId));
        reportBtn.addActionListener(e -> new Report(userId));
        logoutBtn.addActionListener(e -> {
            dispose();
            new Login();
        });

        setVisible(true);
    }

}
