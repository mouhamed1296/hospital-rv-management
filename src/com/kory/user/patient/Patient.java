package com.kory.user.patient;

import com.kory.user.Personne;

import java.util.Date;

public class Patient extends Personne { //definition de l'heritage avec le mot clé extends
    private final int numDossier;

    //construteur
    public Patient(int id, String nom, String prenom, Date date_naissance, String telephone,
                   String adresse, String email, String sexe, int numDossier) {
        super(id, nom, prenom, date_naissance, telephone, adresse, email, sexe);
        this.numDossier = numDossier;
    }
   
   //getteur d'attribut numDossier
    public int getNumDossier() {
        return numDossier;
    }
}
