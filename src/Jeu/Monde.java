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

    boolean jeuFini;

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

        verifierJeuFini();

    }

    public void selectionnerChoix(boolean pChoixDroit){

        Carte carte = pioche.premiereCarte();

        if(pChoixDroit)
            choixSelectionne = carte.getChoixDroite();
        else
            choixSelectionne = carte.getChoixGauche();

    }

    public void verifierJeuFini(){

        //Verifier si le jeu est fini
        if(pioche.premiereCarte()==null){
            jeuFini = false;
            return;
        }


        if(joueur.getArgent()==0){
            Choix droiteFin = new Choix("Oups...",0,0,0,0,0);
            Choix gaucheFin = new Choix("AH!",0,0,0,0,0);

            Carte carteFin = new Carte(Extension.getExtensionParNom("base"),"Vous n'avez plus d'argent" +
                    "et vous ne pouvez pas continuer votre vie d'etudiant, vous allez devoir trouver un travail a macdo.",
                    gaucheFin,droiteFin,1);

            pioche.ajouterCarteDebut(carteFin);
        }
        if(joueur.getNote()==0){
            Choix droiteFin = new Choix("Oups...",0,0,0,0,0);
            Choix gaucheFin = new Choix("AH!",0,0,0,0,0);

            Carte carteFin = new Carte(Extension.getExtensionParNom("base"),"eztqrysthdjhg",
                    gaucheFin,droiteFin,1);

            pioche.ajouterCarteDebut(carteFin);
        }
        if(joueur.getEnergie()==0){
            Choix droiteFin = new Choix("Oups...",0,0,0,0,0);
            Choix gaucheFin = new Choix("AH!",0,0,0,0,0);

            Carte carteFin = new Carte(Extension.getExtensionParNom("base"),"aezrrgtrhyj",
                    gaucheFin,droiteFin,1);

            pioche.ajouterCarteDebut(carteFin);
        }
        if(joueur.getVieSociale()==0){
            Choix droiteFin = new Choix("Oups...",0,0,0,0,0);
            Choix gaucheFin = new Choix("AH!",0,0,0,0,0);

            Carte carteFin = new Carte(Extension.getExtensionParNom("base"),"Vefrgthyj",
                    gaucheFin,droiteFin,1);

            pioche.ajouterCarteDebut(carteFin);
        }

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

    public boolean getJeuFini() {
        return jeuFini;
    }
}
