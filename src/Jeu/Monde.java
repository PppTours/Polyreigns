package Jeu;

import Jeu.Carte.Carte;
import Jeu.Carte.Choix;
import Jeu.PaquetCarte.Extension;
import Jeu.PaquetCarte.Pioche;

/**
 * Regroupe les principaux objets utilises pour le jeu
 */
public class Monde {

    Joueur joueur;
    Pioche pioche;

    Choix choixSelectionne;

    public Monde(){

        joueur = new Joueur();
        pioche = new Pioche();

    }

    public Carte premiereCarte(){
        return pioche.premiereCarte();
    }


    public void piocherCarte(){

        Carte carte = pioche.piocherCarte();
        carte.getExtension().setPoints(carte.getExtension().getPoints() + choixSelectionne.getEffetPoint());

        if(choixSelectionne.isActiveExtension()){   //Si le choix est liee a l'ajout d'une extension
            if(carte.getExtension().isDejaIntegreePioche()==false){     //On regarde si l'extension n'est pas deja presente
                pioche.ajouterExtension(carte.getExtension());
                carte.getExtension().setDejaIntegreePioche(true);
            }
        }

        joueur.setArgent(joueur.getArgent()+choixSelectionne.getEffetArgent());
        joueur.setNote(joueur.getNote()+choixSelectionne.getEffetNote());
        joueur.setEnergie(joueur.getEnergie()+choixSelectionne.getEffetEnergie());
        joueur.setVieSociale(joueur.getVieSociale()+choixSelectionne.getEffetVieSociale());

    }

    public void selectionnerChoix(boolean pChoixDroit){

        Carte carte = pioche.premiereCarte();

        if(pChoixDroit)
            choixSelectionne = carte.getChoixDroite();
        else
            choixSelectionne = carte.getChoixGauche();

    }

    public int getScore(){
        return Extension.getTotalScore();
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Pioche getPioche() {
        return pioche;
    }

    public void setPioche(Pioche pioche) {
        this.pioche = pioche;
    }

    public Choix getChoixSelectionne() {
        return choixSelectionne;
    }

    public void setChoixSelectionne(Choix choixSelectionne) {
        this.choixSelectionne = choixSelectionne;
    }
}
