package Jeu.Carte;

import Jeu.PaquetCarte.Extension;

/**
 * Decrit une carte du jeu
 */
public class Carte {

    private String descriptif;
    private Choix choixGauche;
    private Choix choixDroite;

    //parfois des cartes doivent etre a la suite. Permet ici de les chainer
    Carte suivante;

    Extension extension;    //Extension d'origine


    public Carte(Extension pExtension, String pDescriptif, Choix pChoixGauche, Choix pChoixDroite){
        extension = pExtension;
        descriptif = pDescriptif;
        choixDroite = pChoixDroite;
        choixGauche = pChoixGauche;
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
