package Jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represente l'historique des meilleurs scores
 */
public class MeilleurJoueur {

    private String nom;
    private int score;

    public static ArrayList<MeilleurJoueur> classement = new ArrayList<>();

    public MeilleurJoueur(String pNom, int pPoints){
        nom = pNom;
        score = pPoints;
    }

    public static void ajouterJoueur(String nom, int point){

        MeilleurJoueur j = new MeilleurJoueur(nom,point);
        classement.add(j);
        classement.sort((o1, o2) -> o2.score-o1.score);

        int taille = Math.min(classement.size(), 10);

        classement = new ArrayList<>(classement.subList(0,taille));

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
