package Jeu.Carte;

import Jeu.PaquetCarte.Extension;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Decrit une carte du jeu
 */
public class Carte implements Serializable {

    private int nombreExemplaire;    //Decrit le nombre d'exemplaire present dans son extension

    private String descriptif;
    private Choix choixGauche;
    private Choix choixDroite;

    //parfois des cartes doivent etre a la suite. Permet ici de les chainer
    Carte suivante;

    Extension extension;    //Extension d'origine

    public Carte(Extension pExtension, String pDescriptif, Choix pChoixGauche, Choix pChoixDroite, int pNbExemplaire){
        extension = pExtension;
        descriptif = pDescriptif;
        choixDroite = pChoixDroite;
        choixGauche = pChoixGauche;
        nombreExemplaire = pNbExemplaire;
    }

    public int getNombreExemplaire() {
        return nombreExemplaire;
    }

    public void setNombreExemplaire(int nombreExemplaire) {
        this.nombreExemplaire = nombreExemplaire;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public Choix getChoixGauche() {
        return choixGauche;
    }

    public void setChoixGauche(Choix choixGauche) {
        this.choixGauche = choixGauche;
    }

    public Choix getChoixDroite() {
        return choixDroite;
    }

    public void setChoixDroite(Choix choixDroite) {
        this.choixDroite = choixDroite;
    }

    public Carte getSuivante() {
        return suivante;
    }

    public void setSuivante(Carte suivante) {
        this.suivante = suivante;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }
}
