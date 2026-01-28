package com.track.ui;

import javax.swing.*;
import javax.swing.table.*;
import com.track.dao.DB;

import java.sql.*;

public class ViewExpense extends JFrame {
    public ViewExpense(int userId) {
        setTitle("Your Expenses");
        setSize(600, 400);
        setLocationRelativeTo(null);

        String[] cols = {"Date", "Category", "Amount", "Description"};
        DefaultTableModel model = new DefaultTableModel(cols,0);    
        JTable table = new JTable(model);

        try {
            Connection con = DB.createConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT e.expense_date, c.name, e.amount, e.description " +
                "FROM expenses e JOIN categories c ON e.category_id=c.id WHERE user_id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4)
                });
            }
        } catch(Exception e){ e.printStackTrace(); }

        add(new JScrollPane(table));
        setVisible(true);
    }
}
