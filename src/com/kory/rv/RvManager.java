package com.kory.rv;

import com.kory.user.medecin.MedecinManager;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

public class RvManager {
    private static final RvRepository repository = new RvRepository();
    private static int idMedecin;
    public static boolean isRvProgrammable(String specialite) {
        idMedecin = MedecinManager.getDisponibleMedecin(specialite);
        return idMedecin != 0;
    }
    public static String giveRvToPatient(int idPatient, Date dateRv, String specialite) {
        Rv rv = new Rv(dateRv, idMedecin, idPatient, specialite);
        insert(rv);
        return "Vous avez rendez-vous le: " + dateRv.toString();
    }

    public static void insert(Rv rv) {
        try {
            repository.save(rv);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static List<Rv> findAll() throws SQLException {
        return repository.findAll();
    }
}
