package Controlleur;

import Jeu.Carte.Carte;
import Jeu.Monde;

public class Controlleur {

    Monde monde;

    public String getDescriptionCarte(){
        Carte carte = monde.premiereCarte();

        if(carte == null) {

            //erreur

        }

        return carte.getDescriptif();
    }

    public void piocherCarte(){
        monde.piocherCarte();
    }

    public boolean verifierJeuFini(){
        return monde.getJeuFini();  //Si le jeu est fini, desactiver les controles et changer l'affichage
    }

    public void faireChoix(boolean pChoixDroit){
        monde.selectionnerChoix(pChoixDroit);
    }

    public String getTexteChoix(){
        return monde.getChoixSelectionne().getReponse();
    }

    public int getScore(){
        return monde.getScore();
    }




}
