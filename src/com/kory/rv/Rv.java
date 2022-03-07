package com.kory.rv;

import java.util.Date;

public class Rv {
    private final Date dateRv;
    private final int idMedecin;
    private final int idPatient;
    private final String specialite;

    public Rv(Date date, int idMedecin, int idPatient, String specialite) {
        this.dateRv = date;
        this.idMedecin = idMedecin;
        this.idPatient = idPatient;
        this.specialite =specialite;
    }

    public Date getDateRv() {
        return this.dateRv;
    }

    public int getIdMedecin() {
        return this.idMedecin;
    }

    public int getIdPatient() {
        return this.idPatient;
    }

    public String getSpecialite() {
        return this.specialite;
    }
}
