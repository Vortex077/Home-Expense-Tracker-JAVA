package com.track.dao;

import java.sql.*;

public class CategoryDAO {

    public int getCategoryId(String name) {

        try (Connection con = DB.createConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT id FROM categories WHERE name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt(1);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}

