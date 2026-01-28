package com.track.ui;

import com.track.dao.CategoryDAO;
import com.track.dao.DB;
import com.track.dao.ExpenseDAO;
import com.track.dto.ExpenseDTO;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.*;


public class AddExpense extends JFrame  {
     public AddExpense(int userId) {
        setTitle("Add Expense");
        setSize(400, 350);
        setLocationRelativeTo(null);

        JLabel amtLbl = new JLabel("Amount");
        JLabel catLbl = new JLabel("Category");
        JLabel dateLbl = new JLabel("Date (YYYY-MM-DD)");
        JLabel descLbl = new JLabel("Description");

        JTextField amtTxt = new JTextField();
        JComboBox<String> catBox = new JComboBox<>();
        JTextField dateTxt = new JTextField();
        JTextField descTxt = new JTextField();

        JButton saveBtn = new JButton("Save");

        setLayout(new GridLayout(5,2,10,10));
        add(amtLbl); add(amtTxt);
        add(catLbl); add(catBox);
        add(dateLbl); add(dateTxt);
        add(descLbl); add(descTxt);
        add(new JLabel()); add(saveBtn);

        // Load categories
        try {
            Connection con = DB.createConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT name FROM categories");
            while(rs.next()){
                catBox.addItem(rs.getString(1));
            }
        } catch(Exception e){ e.printStackTrace(); }

        saveBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amtTxt.getText());
                String cat = catBox.getSelectedItem().toString();
                String date = dateTxt.getText();
                String desc = descTxt.getText();

                CategoryDAO catDao = new CategoryDAO();
                int catId = catDao.getCategoryId(cat);

                ExpenseDTO exp = new ExpenseDTO();
                exp.setUserId(userId);
                exp.setCategoryId(catId);
                exp.setAmount(amt);
                exp.setDescription(desc);
                exp.setDate(date);

                ExpenseDAO dao = new ExpenseDAO();
                boolean success = dao.addExpense(exp);

                if(success){
                    JOptionPane.showMessageDialog(this, "Expense Added!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add expense");
                }

            } catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Invalid Data");
            }
        });

        setVisible(true);
    }


}
