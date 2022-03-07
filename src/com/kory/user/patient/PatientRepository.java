package com.kory.user.patient;

import com.kory.DateHelper;
import com.kory.database.BaseRepository;
import com.kory.user.medecin.Medecin;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientRepository implements BaseRepository<Patient> {
    @Override
    public void save(Patient patient) throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        String sql = """
                INSERT INTO patient(nom, prenom, date_naissance, telephone, adresse, email, sexe,num_dossier)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        try {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setDate(3, DateHelper.toSqlDate(patient.getDate_naissance()));
            stmt.setString(4, patient.getTelephone());
            stmt.setString(5, patient.getAdresse());
            stmt.setString(6, patient.getEmail());
            stmt.setString(7, patient.getSexe());
            stmt.setInt(8, patient.getNumDossier());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Patient inscris avec succes!!");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            BaseRepository.closeConnection(connection, stmt);
        }
    }

    @Override
    public void update(Patient patient) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public List<Patient> findAll() throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        String sql = "SELECT * FROM patient";
        PreparedStatement stmt = connection.prepareStatement(sql);
        List<Patient> patients = new ArrayList<>();
        try {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                patients.add(generatePatientFromResult(result));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return patients;
    }

    @Override
    public Patient findById(int id) throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        String sql = "SELECT * FROM patient WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        Patient patient = null;
        try {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                patient = generatePatientFromResult(result);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            BaseRepository.closeConnection(connection, stmt);
        }
        return patient;
    }
    public int createDossierPatient() throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        int numDossier = 0;
        String sql = """
                INSERT INTO dossier_patient(date_consultation, prescription)
                VALUES(?, ?)
                """;
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            stmt.setDate(1, DateHelper.toSqlDate(DateHelper.currentDate()));
            stmt.setString(2, null);
            stmt.executeUpdate();
            ResultSet result = stmt.getGeneratedKeys();
            if (result.next()) {
                numDossier = result.getInt(1);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            BaseRepository.closeConnection(connection, stmt);
        }
        return numDossier;
    }
    private static Patient generatePatientFromResult(ResultSet result) {
        Patient patient = null;
        try {
            int id = result.getInt("id");
            String nom =  result.getString("nom");
            String prenom = result.getString("prenom");
            Date date_naissance = result.getDate("date_naissance");
            String telephone = result.getString("telephone");
            String adresse = result.getString("adresse");
            String email = result.getString("email");
            String sexe = result.getString("sexe");
            int num_dossier = result.getInt("num_dossier");
            patient = new Patient(id, nom, prenom, date_naissance, telephone, adresse, email, sexe, num_dossier);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return patient;
    }
}
