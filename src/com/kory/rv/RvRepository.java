package com.kory.rv;

import com.kory.DateHelper;
import com.kory.database.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RvRepository implements BaseRepository<Rv> {
    public void save(Rv rv) throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        String sql = """
                INSERT INTO rv(specialite, date_rv, id_patient, id_medecin)
                VALUES(?, ?, ?, ?)
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        try {
            stmt.setString(1, rv.getSpecialite());
            stmt.setDate(2, DateHelper.toSqlDate(rv.getDateRv()));
            stmt.setInt(3, rv.getIdPatient());
            stmt.setInt(4, rv.getIdMedecin());
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            BaseRepository.closeConnection(connection, stmt);
        }
    }

    @Override
    public void update(Rv personne) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public List<Rv> findAll() throws SQLException {
        Connection connection = BaseRepository.getConnexion();
        String sql = "SELECT * FROM rv";
        PreparedStatement stmt = connection.prepareStatement(sql);
        List<Rv> rvList = new ArrayList<>();
        try {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                String specialite = result.getString("specialite");
                Date date_rv = result.getDate("date_rv");
                int id_patient = result.getInt("id_patient");
                int id_medecin = result.getInt("id_medecin");
                rvList.add(new Rv(date_rv, id_medecin, id_patient, specialite));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return rvList;
    }

    @Override
    public Rv findById(int id) throws SQLException {
        return null;
    }
}
