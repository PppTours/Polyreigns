package Jeu.PaquetCarte;

import Jeu.Carte.Carte;

import java.util.ArrayList;

/**
 * Decrit l'etat de la pioche actuelle du jeu
 */
public class Pioche {

    ArrayList<Carte> pioche;

    ArrayList<Extension> extensionListe;

    public Pioche(){

        pioche = new ArrayList<>();
        extensionListe = new ArrayList<>();

    }

    public void verifierExtension(){

    }

    public void ajouterExtension(Extension pExtension){



    }


    public Carte premiereCarte(){
        if(pioche.size()>0)
            return pioche.get(0);

        //gerer le cas
        return null;
    }

    public Carte piocherCarte(){
        if(pioche.size()>0)
            return pioche.remove(0);

        return null;
    }





}
