package com.kory.user.admin;

import com.kory.user.Personne;

import java.util.Date;

public class Administrateur extends Personne {
    private String userName;
    private final String password;
    private Date dateInscription;

    //Constructeur
    public Administrateur(int id, String nom, String prenom, Date date_naissance,
                          String telephone, String adresse, String email, String sexe,
                          String userName, String password, Date dateInscription) {
        super(id, nom, prenom, date_naissance, telephone, adresse, email, sexe);
        this.userName = userName;
        this.password = password;
        this.dateInscription = dateInscription;
    }
    
    //Mutateurs
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Date getDateInscription() {
        return dateInscription;
    }
    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
    public String getPassword() { return password; }
}
