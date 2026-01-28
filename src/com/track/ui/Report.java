package com.track.ui;

import javax.swing.*;

import com.track.dao.DB;

import java.awt.*;
import java.sql.*;

public class Report extends JFrame {
    public Report(int userId) {
        setTitle("Monthly Report");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        try {
            Connection con = DB.createConnection();

            ResultSet total = con.createStatement().executeQuery(
                "SELECT SUM(amount) FROM expenses WHERE user_id="+userId);
            total.next();
            area.append("Total Expense: ₹ " + total.getDouble(1) + "\n\n");

            ResultSet cat = con.createStatement().executeQuery(
                "SELECT c.name, SUM(e.amount) FROM expenses e " +
                "JOIN categories c ON e.category_id=c.id WHERE user_id="+userId+" GROUP BY c.name");

            while(cat.next()){
                area.append(cat.getString(1) + " : ₹ " + cat.getDouble(2) + "\n");
            }

        } catch(Exception e){ e.printStackTrace(); }

        add(new JScrollPane(area), BorderLayout.CENTER);
        setVisible(true);
    }
}
