package com.kory.user.medecin;

import com.kory.DateHelper;
import com.kory.database.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedecinRepository implements BaseRepository<Medecin> {

    @Override
    public void save(Medecin medecin) throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        String sql = """
            INSERT INTO medecin(nom, prenom, date_naissance, telephone, adresse, email, sexe, specialite, date_ins, disponibilite)
            VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        try {
            stmt.setString(1, medecin.getNom());
            stmt.setString(2, medecin.getPrenom());
            stmt.setDate(3, DateHelper.toSqlDate(medecin.getDate_naissance()));
            stmt.setString(4, medecin.getTelephone());
            stmt.setString(5, medecin.getAdresse());
            stmt.setString(6, medecin.getEmail());
            stmt.setString(7, medecin.getSexe());
            stmt.setString(8, medecin.getspecialite());
            stmt.setDate(9, DateHelper.toSqlDate(medecin.getDateInscription()));
            stmt.setBoolean(10, medecin.isDisponible());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Medecin inscris avec succes");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            BaseRepository.closeConnection(connection, stmt);
        }
    }

    @Override
    public void update(Medecin medecin) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public List<Medecin> findAll() throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        String sql = "SELECT * FROM medecin";
        Statement stmt = connection.createStatement();
        List<Medecin> medecins = new ArrayList<>();
        try {
            ResultSet results = stmt.executeQuery(sql);
            while (results.next()) {
                medecins.add(generateMedecinFromResults(results));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            BaseRepository.closeConnection(connection, stmt);
        }
        return medecins;
    }

    @Override
    public Medecin findById(int id) throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        String sql = "SELECT * FROM medecins WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        Medecin medecin = null;
        try {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                medecin = generateMedecinFromResults(result);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            BaseRepository.closeConnection(connection, stmt);
        }
        return medecin;
    }
    public List<Medecin> findBySpecialite(String specialite) throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        String sql = "SELECT * FROM medecin WHERE specialite = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        List<Medecin> medecins = new ArrayList<>();
        try {
            stmt.setString(1, specialite);
            ResultSet result  = stmt.executeQuery();
            while (result.next()) {
                medecins.add(generateMedecinFromResults(result));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            BaseRepository.closeConnection(connection, stmt);
        }
        return medecins;
    }
    private static Medecin generateMedecinFromResults(ResultSet result) {
        Medecin medecin = null;
        try {
            int id = result.getInt("id");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            Date date_naissance = result.getDate("date_naissance");
            String telephone = result.getString("telephone");
            String addresse = result.getString("adresse");
            String email = result.getString("email");
            String sexe = result.getString("sexe");
            String specialite = result.getString("specialite");
            Date date_ins = result.getDate("date_ins");
            boolean disponibilite = result.getBoolean("disponibilite");
            medecin = new Medecin(id, nom, prenom, date_naissance, telephone, addresse, email,
                    sexe, specialite, disponibilite, date_ins);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return medecin;
    }
}
