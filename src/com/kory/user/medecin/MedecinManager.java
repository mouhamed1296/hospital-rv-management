package com.kory.user.medecin;

import java.sql.SQLException;
import java.util.List;

public class MedecinManager {
    private static final MedecinRepository repository = new MedecinRepository();

    /**This method allow you to add new Medecin into the Database
     * @param medecin the Medecin that you want to add
     * @throws SQLException exception thrown when an error occured in the database insertion processus
     */
    public static void insert(Medecin medecin) throws SQLException {
        repository.save(medecin);
    }

    /**This method allow you to get a retrieve a medecin from the database using his id
     * @param id The id of the Medecin you are searching for
     * @return A Medecin object or null depending on whether the infos where found or not
     * @throws SQLException when an sql error occur during the operation
     */
    public static Medecin findById(int id) throws SQLException {
        return repository.findById(id);
    }

    /**This method allow you to retrieve all Medecin from the Database
     * @return A List of all the Medecin found in the database
     * @throws SQLException when an sql error occur during the operation
     */
    public static List<Medecin> findAll() throws SQLException {
        return repository.findAll();
    }

    public static int getDisponibleMedecin(String specialite) {
        int idMedecin = 0;
        try {
            List<Medecin> medecins = repository.findBySpecialite(specialite);
            for (Medecin medecin : medecins) {
                if(medecin.isDisponible()) {
                    idMedecin = medecin.getId();
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idMedecin;
    }
}
