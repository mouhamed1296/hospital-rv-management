package com.kory.ui;

import com.kory.DateHelper;
import com.kory.rv.Rv;
import com.kory.rv.RvRepository;
import com.kory.user.Personne;
import com.kory.user.admin.AdminManager;
import com.kory.user.medecin.Medecin;
import com.kory.user.medecin.MedecinManager;
import com.kory.user.patient.Patient;
import com.kory.user.patient.PatientManager;
import com.kory.rv.RvManager;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    public static void MenuPrincipal() throws IllegalStateException {
        System.out.println("********** Bienvenue à l'Hopital **********");
        System.out.println("1. Menu Administrateur");
        System.out.println("2. Menu Medecin");
        System.out.println("3. Quitter");
        short choix = scanner.nextShort();
        switch (choix) {
            case 1 -> MenuAdmin();
            case 2 -> MenuMedecin();
            case 3 -> Quitter();
            default -> throw new IllegalStateException("Unexpected choice" + choix);
        }
    }

    protected static void MenuAdmin() throws IllegalStateException {
        System.out.println("***** Birnvenue sur le Menu Administrateur *****");
        System.out.println("1. Se Connecter");
        System.out.println("2. Retour au Menu Principal");
        short choix = scanner.nextShort();
        switch (choix) {
            case 1 -> adminLogin();
            case 2 -> MenuPrincipal();
            default -> throw new IllegalStateException("Unexpected choice " + choix);
        }
    }
    protected static void MenuMedecin() {
        System.out.println("**** Menu Medecin ****");
        System.out.println("1. Afficher liste rendez-vous");
        System.out.println("2. Retour");
        short choice = scanner.nextShort();
        switch (choice) {
            case 1 -> showRvList();
            case 2 -> MenuPrincipal();
            default -> throw new IllegalStateException("Not handled choice " + choice);
        }
    }

    private static void showRvList() {
        try {
            List<Rv> rvList = RvManager.findAll();
            List<Rv> medRv = new ArrayList<>();
            System.out.println("Entrez votre id medecin");
            int id = scanner.nextInt();
            for (Rv rv: rvList) {
                if (rv.getIdMedecin() == id) {
                    medRv.add(rv);
                }
            }
            for (Rv rv : medRv) {
                System.out.println("Date rendez-vous: " + rv.getDateRv().toString());
                Patient patient = PatientManager.findById(rv.getIdPatient());
                System.out.println("Nom patient: " + patient.getNom());
                System.out.println("Prenom patient: " + patient.getPrenom());
                System.out.println("Email patient: " + patient.getEmail());
                System.out.println("Numero dossier: " + patient.getNumDossier());
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            MenuPrincipal();
        }
    }

    protected static void adminLogin() {
        System.out.println("Username: ");
        scanner.nextLine();
        String username = scanner.nextLine();
        System.out.println("Mot de passe: ");
        String password = scanner.nextLine();
        if (AdminManager.authenticate(username, password)) {
            AdminDashboard();
        } else {
            System.out.println("Username ou mot de passe incorrect!!");
            MenuAdmin();
        }
    }

    protected static void AdminDashboard() throws IllegalStateException {
        System.out.println("**** Tableau de bord administrateur ****");
        System.out.println("1. Ajouter Medecin");
        System.out.println("2. Ajouter Patient");
        System.out.println("3. Donner rendez-vous au patient");
        System.out.println("4. Afficher liste patient");
        System.out.println("5. Afficher liste Medecin");
        System.out.println("6. Rechercher Patient");
        System.out.println("7. Se Déconnecter");
        short choix = scanner.nextShort();
        switch (choix) {
            case 1 -> addMedecinUI();
            case 2 -> addPatientUI();
            case 3 -> RendezVousUI();
            case 4 -> showPatientListUI();
            case 5 -> showMedecinListUI();
            case 6 -> searchPatientUI();
            case 7 -> MenuAdmin();
            default -> throw new IllegalStateException("Unexpected choice: " + choix);
        }
    }

    protected static void searchPatientUI() {
        System.out.println("Entrez l'identifiant du patient à rechercher");
        int id = scanner.nextInt();
        Patient patient;
        try {
            patient = PatientManager.findById(id);
            if (patient != null) {
                showPatientUI(patient);
            } else {
                System.out.println("No patient found with id " + id);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            AdminDashboard();
        }
    }

    /*private static void showDossierPatient(int id) {

    }*/

    protected static void showMedecinListUI() {
        try {
            List<Medecin> medecins = MedecinManager.findAll();
            System.out.println("\t\t ** Listes des Médecins ** \t\t");
            System.out.println("---------------------------------------------");
            System.out.print("|\t Nom \t|");
            System.out.print("|\t Prenom \t|");
            System.out.println("|\t Spécialité\t|");
            System.out.println("---------------------------------------------");
            for (Medecin medecin: medecins) {
                System.out.print("|\t " + medecin.getNom() +  " \t|");
                System.out.print("|\t " + medecin.getPrenom() +  " \t|");
                String specialite = medecin.getspecialite();
                if (specialite.equals("CHIRURGIEN")) {
                    System.out.println("|\t " + medecin.getspecialite() +  " |");
                } else {
                    System.out.println("|\t " + medecin.getspecialite() + " \t|");
                }
                System.out.println("---------------------------------------------");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            AdminDashboard();
        }
    }

    protected static void showPatientUI(Patient patient) {
        showPersonnalInfoUI(patient);
        System.out.println("Numero Dossier: " + patient.getNumDossier());
    }

    protected static void showPatientListUI() {
        try {
            List<Patient> patients  = PatientManager.findAll();
            System.out.println("\t\t ** Listes des Patients ** \t\t");
            System.out.println("---------------------------------------------");
            System.out.print("|\t Nom \t|");
            System.out.print("|\t Prenom \t|");
            System.out.println("|\t Num_Dossier \t|");
            System.out.println("---------------------------------------------");
            for (Patient patient: patients) {
                System.out.print("|\t " + patient.getNom() +  " \t|");
                System.out.print("|\t " + patient.getPrenom() +  " \t|");
                System.out.println("|\t " + patient.getNumDossier() + " \t|");
                System.out.println("---------------------------------------------");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            System.out.println("1. Rechercher un patient");
            System.out.println("PS: Vous pouvez entrez tout autre caractère pour retourner au tableau de bord");
            short choice = scanner.nextShort();
            if (choice == 1) {
                searchPatientUI();
            } else {
                AdminDashboard();
            }
        }
    }

    protected static void RendezVousUI() {
        System.out.println("***** Menu RV *****");
        System.out.println("1. Donner rendez-vous");
        System.out.println("2: Retour");
        short choix = scanner.nextShort();
        switch (choix) {
            case 1 -> DonnerRv();
            case 2 -> AdminDashboard();
        }
    }

    private static void DonnerRv() {
        System.out.println("Entrez la specialite concerné");
        scanner.nextLine();
        String specialite = scanner.nextLine();
        if (RvManager.isRvProgrammable(specialite)) {
            System.out.println("Entrez l'identifiant du patient");
            int id = scanner.nextInt();
            System.out.println("--Date du rendez-vous--");
            System.out.print("Veuillez saisir le jour: ");
            short jour  = scanner.nextShort();
            System.out.print("Veuillez saisir le mois: ");
            short mois = scanner.nextShort();
            System.out.print("Veuillez saisir l'année: ");
            int annee = scanner.nextInt();
            Date rv_Date = DateHelper.createDate(jour, mois, annee);
            String rv = RvManager.giveRvToPatient(id, rv_Date, specialite);
            System.out.println(rv);
        } else {
            System.out.println("Aucun medecin n'est disponible pour ce rendez-vous");
        }
        RendezVousUI();
    }

    protected static void addPatientUI() {
        Personne pInf = personalInfoUi("patient");
        try {
            int numDossier = PatientManager.createDossierPatient();
            Patient patient =  new Patient(1, pInf.getNom(), pInf.getPrenom(), pInf.getDate_naissance(), pInf.getTelephone(),
                    pInf.getAdresse(), pInf.getEmail(), pInf.getSexe(), numDossier);
            PatientManager.insert(patient);
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    protected static void addMedecinUI() throws IllegalStateException {
        Personne pInf = personalInfoUi("medecin");
        System.out.println("Entrez la specialite du medecin");
        scanner.nextLine();
        String specialite = scanner.nextLine();
        Medecin medecin = new Medecin(1, pInf.getNom(), pInf.getPrenom(), pInf.getDate_naissance(), pInf.getTelephone(),
                pInf.getAdresse(), pInf.getEmail(), pInf.getSexe(), specialite, true, DateHelper.currentDate());
        try {
            MedecinManager.insert(medecin);
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            MenuAdmin();
        }
    }

    private static Personne personalInfoUi(String personStatus) {
        System.out.println("Entrez le nom du " + personStatus);
        scanner.nextLine();
        String nom = scanner.nextLine();
        System.out.println("Entrez le prénom du " + personStatus);
        String prenom = scanner.nextLine();
        System.out.println("Date de naissance: ");
        System.out.println("Entrez le jour:");
        short jour = scanner.nextShort();
        System.out.println("Entrez le mois:");
        short mois = scanner.nextShort();
        System.out.println("Entrez l'annee:");
        int annee = scanner.nextShort();
        Date date_naissance = DateHelper.createDate(jour, mois, annee);
        System.out.println("Entrez le numero de telephone du " + personStatus);
        scanner.nextLine();
        String tel = scanner.nextLine();
        System.out.println("Entrez l'adresse du " + personStatus);
        String addr = scanner.nextLine();
        System.out.println("Entrez l'adresse email du " + personStatus);
        String email = scanner.nextLine();
        System.out.println("Choisissez le sexe du " + personStatus);
        System.out.println("\t1: M");
        System.out.println("\t2: F");
        String sexe;
        short choix_sexe = scanner.nextShort();
        sexe = switch (choix_sexe) {
            case 1 -> "M";
            case 2 -> "F";
            default -> throw new IllegalStateException("Unexpected sexe: " + mois);
        };
        return new Personne(0, nom, prenom, date_naissance, tel, addr, email, sexe);
    }

    private static void showPersonnalInfoUI(Patient patient) {
        System.out.print("Nom: " + patient.getNom() + "\t");
        System.out.println("Prenom: " + patient.getPrenom());
        System.out.println("Date de naissance: " + DateHelper.toLocalZoneDate(patient.getDate_naissance()).toString());
        System.out.println("Telephone: " + patient.getTelephone());
        System.out.println("Email: " + patient.getEmail());
        System.out.println("Sexe: " + patient.getSexe());
    }

    protected static void Quitter() throws IllegalStateException {
        System.out.println("Voulez vous vraiment quitter ?");
        System.out.println("1: Oui");
        System.out.println("2: Non");
        short choix = scanner.nextShort();
        switch (choix) {
            case 1 -> System.exit(0);
            case 2 -> MenuPrincipal();
            default -> throw new IllegalStateException("Unexpected choice " + choix);
        }
    }
}
