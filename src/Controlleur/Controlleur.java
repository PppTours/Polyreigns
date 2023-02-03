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

    public String getTexteChoix(){
        return monde.getChoixSelectionne().getReponse();
    }

    public int getScore(){
        return monde.getScore();
    }




}
