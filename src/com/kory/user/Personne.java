package com.kory.user;

import java.util.Date;


 public class Personne {
     //Attributs de la classe personne
    private int id ;
    private String nom;
    private String prenom ;
    private Date date_naissance ;
    private String telephone ;
    private String adresse;
    private String email ;
    private String sexe;

     //Constructeurs de la classe personne
     public Personne(int id, String nom, String prenom, Date date_naissance, String telephone,
                     String adresse, String email, String sexe) {
         this.id = id;
         this.nom = nom;
         this.prenom = prenom;
         this.date_naissance = date_naissance;
         this.telephone = telephone;
         this.adresse = adresse;
         this.email = email;
         this.sexe = sexe;

     }

     public Personne(int id) {
         this.id = id;
     }

    //Mutateurs des attributs de la classe
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
}