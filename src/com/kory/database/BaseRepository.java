package com.kory.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseRepository<T> {
    static Connection getConnexion(){
        return MySqlAccess.getConnexion();
    }
    void save(T personne) throws SQLException;
    void update(T personne) throws SQLException;
    void delete(int id) throws SQLException;
    List<T> findAll() throws SQLException;
    T findById(int id) throws SQLException;
    static void closeConnection(Connection connection, Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
