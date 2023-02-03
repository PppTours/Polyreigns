package Jeu.PaquetCarte;

import Jeu.Carte.Carte;

import java.util.ArrayList;

/**
 * Decrit un ensemble de cartes qui pourra etre ajoute
 * a la pioche. Par defaut, l'extension "base" est ajoutee
 * a la pioche. Les autres extensions pourront etre ajoutees
 * selon les choix de certaines cartes.
 * Il peut y avoir des cartes venant d'autres extensions. Ces cartes
 * seront les declencheurs: si on fait le bon choix avec une de ces cartes alors
 * que son extension a dejaIntegreePioche=false , alors on l'integre a la pioche et on passe
 * dejaIntegreePioche a true
 */
public class Extension {

    private boolean dejaIntegreePioche = false;

    private String nom;
    private ArrayList<Carte> cartes;
    private int points;     //Le decompte final sera l'addition des points dans
                    //chaque extension

    static ArrayList<Extension> extensionListe = new ArrayList<>();

    public Extension(String pNom){

        nom = pNom;
        extensionListe.add(this);
    }

    public static int getTotalScore(){

        int score = 0;

        for(Extension e : extensionListe)
            score += e.getPoints();

        return score;

    }

    public boolean isDejaIntegreePioche() {
        return dejaIntegreePioche;
    }

    public void setDejaIntegreePioche(boolean dejaIntegreePioche) {
        this.dejaIntegreePioche = dejaIntegreePioche;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
