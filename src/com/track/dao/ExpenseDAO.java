package com.track.dao;

import com.track.dto.ExpenseDTO;
import java.sql.*;
import java.util.ArrayList;

public class ExpenseDAO {

    // 1. Add new expense
    public boolean addExpense(ExpenseDTO exp) {

        try (Connection con = DB.createConnection()) {

            String sql = "INSERT INTO expenses(user_id, category_id, amount, description, expense_date) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, exp.getUserId());
            ps.setInt(2, exp.getCategoryId());
            ps.setDouble(3, exp.getAmount());
            ps.setString(4, exp.getDescription());
            ps.setString(5, exp.getDate());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Get all expenses for a user
    public ArrayList<ExpenseDTO> getUserExpenses(int userId) {

        ArrayList<ExpenseDTO> list = new ArrayList<>();

        try (Connection con = DB.createConnection()) {

            String sql = "SELECT e.id, e.amount, e.description, e.expense_date, c.name, e.category_id " +
                         "FROM expenses e JOIN categories c ON e.category_id=c.id WHERE e.user_id=? ORDER BY e.expense_date DESC";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ExpenseDTO exp = new ExpenseDTO();
                exp.setId(rs.getInt("id"));
                exp.setAmount(rs.getDouble("amount"));
                exp.setDescription(rs.getString("description"));
                exp.setDate(rs.getString("expense_date"));
                exp.setCategoryName(rs.getString("name"));
                exp.setCategoryId(rs.getInt("category_id"));
                list.add(exp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 3. Get monthly total
    public double getMonthlyTotal(int userId, int month, int year) {

        double total = 0;

        try (Connection con = DB.createConnection()) {

            String sql = "SELECT SUM(amount) FROM expenses WHERE user_id=? AND MONTH(expense_date)=? AND YEAR(expense_date)=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, month);
            ps.setInt(3, year);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    // 4. Category-wise totals
    public ResultSet getCategoryTotals(int userId, int month, int year) {

        try {
            Connection con = DB.createConnection();

            String sql = "SELECT c.name, SUM(e.amount) " +
                         "FROM expenses e JOIN categories c ON e.category_id=c.id " +
                         "WHERE e.user_id=? AND MONTH(e.expense_date)=? AND YEAR(e.expense_date)=? " +
                         "GROUP BY c.name";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, month);
            ps.setInt(3, year);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

