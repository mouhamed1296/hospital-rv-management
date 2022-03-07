package com.kory.user.medecin;

import com.kory.user.Personne;

import java.util.Date;

public class Medecin extends Personne {
    private String specialite;
    private boolean disponibilite;
    private Date dateInscription;

    //constructeur
    public Medecin(int id, String nom, String prenom, Date date_naissance, String telephone, String adresse,
                   String email, String sexe, String specialite, boolean disponibilite, Date dateInscription) {
        super(id, nom, prenom, date_naissance, telephone, adresse, email, sexe);
        this.specialite = specialite;
        this.disponibilite = disponibilite;
        this.dateInscription = dateInscription;
    }

    //Mutateus des attributs
    public String getspecialite() {
        return specialite;
    }
    public void setspecialite(String specialite) {
        this.specialite = specialite;
    }
    public Date getDateInscription() {
        return dateInscription;
    }
    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public boolean isDisponible() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
}
