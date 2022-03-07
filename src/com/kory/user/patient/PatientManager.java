package com.kory.user.patient;

import java.sql.SQLException;
import java.util.List;

public class PatientManager {
    private static final PatientRepository repository = new PatientRepository();
    public static void insert(Patient patient) {
        try {
            repository.save(patient);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static int createDossierPatient() throws SQLException {
        return repository.createDossierPatient();
    }

    public static List<Patient> findAll() throws SQLException {
        return repository.findAll();
    }

    public static Patient findById(int id) throws SQLException {
        return repository.findById(id);
    }

}
