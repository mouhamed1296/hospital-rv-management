package com.kory.user.admin;

import com.kory.database.BaseRepository;

import java.sql.*;
import java.time.ZoneId;
import java.util.List;

public class AdminRepository implements BaseRepository<Administrateur> {
    @Override
    public void save(Administrateur admin) throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        String sql = """
            INSERT INTO admin(nom, prenom, date_naissance, telephone, adresse, email, sexe, username, password, date_ins) 
            VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        try {
            stmt.setString(1, admin.getNom());
            stmt.setString(2, admin.getPrenom());
            stmt.setDate(3,
                    Date.valueOf(admin.getDate_naissance().toInstant()
                            .atZone(ZoneId.of("Africa/Dakar")).toLocalDate())
            );
            stmt.setString(4, admin.getTelephone());
            stmt.setString(5, admin.getAdresse());
            stmt.setString(6, admin.getEmail());
            stmt.setString(7, admin.getSexe());
            stmt.setString(8, admin.getUserName());
            stmt.setString(9, admin.getPassword());
            stmt.setDate(10, Date.valueOf(admin.getDateInscription().toInstant()
                    .atZone(ZoneId.of("Africa/Dakar")).toLocalDate()));
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Admin inscris avec succes");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
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

    @Override
    public void update(Administrateur personne) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public List<Administrateur> findAll() throws SQLException {
        return null;
    }

    @Override
    public Administrateur findById(int id) throws SQLException {
        return null;
    }

    public Administrateur findByUsername(String username) throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        String sql = """
                SELECT * From admin WHERE username = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        Administrateur admin = null;
        try {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                admin = generateAdminFromResult(result);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
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
        return admin;
    }

    private static Administrateur generateAdminFromResult(ResultSet result) {
        Administrateur admin = null;
        try {
            int id = result.getInt("id");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            Date date_naissance = result.getDate("date_naissance");
            String telephone = result.getString("telephone");
            String addresse = result.getString("adresse");
            String email = result.getString("email");
            String sexe = result.getString("sexe");
            String username = result.getString("username");
            String password = result.getString("password");
            Date date_ins = result.getDate("date_ins");
            admin = new Administrateur(id, nom, prenom, date_naissance, telephone, addresse, email,
                    sexe, username, password, date_ins);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return admin;
    }
}
