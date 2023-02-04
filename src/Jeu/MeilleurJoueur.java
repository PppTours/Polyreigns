package Jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represente l'historique des meilleurs scores
 */
public class MeilleurJoueur {

    String nom;
    int score;

    static ArrayList<MeilleurJoueur> classement = new ArrayList<>();

    private MeilleurJoueur(String pNom, int pPoints){
        nom = pNom;
        score = pPoints;
    }

    public static void ajouterJoueur(String nom, int point){

        MeilleurJoueur j = new MeilleurJoueur(nom,point);
        classement.add(j);
        classement.sort(Comparator.comparingInt(o -> o.score));
        classement = (ArrayList<MeilleurJoueur>) classement.subList(0,10);

    }



}
