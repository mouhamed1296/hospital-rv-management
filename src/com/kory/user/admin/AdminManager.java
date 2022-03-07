package com.kory.user.admin;

import java.sql.SQLException;

public class AdminManager {
    public static boolean authenticate(String username, String password) {
        AdminRepository repository = new AdminRepository();
        Administrateur admin = null;
        try {
            admin = repository.findByUsername(username);
        } catch (SQLException se) {
            return false;
        }
        if (admin == null) {
            return false;
        }
        return (username.equals(admin.getUserName()) && password.equals(admin.getPassword()));
    }
}
