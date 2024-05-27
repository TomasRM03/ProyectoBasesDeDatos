package com.uncuyo.dbapp.dao;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author tomas
 */
public class RoleDAO {
    String url = "jdbc:postgresql://localhost:5432/uncuyo";

    public RoleDAO() {
    }
    
    public boolean login(String name, String password){
        try {
            Connection conn = DriverManager.getConnection(url, name, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
