package com.track.dao;

import com.track.dto.UserDTO;
import java.sql.*;

public class UserDAO {

    public UserDTO login(String username, String password) {

        UserDTO user = null;

        try (Connection con = DB.createConnection()) {

            String sql = "SELECT id, username FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserDTO();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;   // null = login failed
    }
}
