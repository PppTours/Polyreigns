package Jeu.PaquetCarte;

import Jeu.Carte.Carte;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Decrit l'etat de la pioche actuelle du jeu
 */
public class Pioche {

    ArrayList<Carte> pioche;

    ArrayList<Extension> extensionListe;

    public Pioche(){

        pioche = new ArrayList<>();
        extensionListe = new ArrayList<>();

        ajouterExtension(Extension.getExtensionParNom("base"));

    }

    /**
     * Il faut faire en sorte que toute les cartes permettant de débloquer une extension
     * soit dans les 20-30 premières.
     *
     */

    public void ajouterExtension(Extension pExtension){

        if(pExtension == null){
            //gerer erreur
            return;
        }

        //On garde la premiere carte de cote car elle est deja affichee
        //On doit aussi verifier si cette carte n'est pas liee a des suivantes
        ArrayList<Carte> aGarder = new ArrayList<>();

        ArrayList<Carte> aAjouter = new ArrayList<>();

        if(pExtension.getIntervallePresence() == -1){   //Jeu de base, pas de dispersion

            for(Carte c : pExtension.getCartes()){
                for(int i=0; i<c.getNombreExemplaire(); i++)
                    aAjouter.add(c);
            }

            pioche.addAll(aAjouter);
            Collections.shuffle(aAjouter);

        }else{      //Utiliser InterVallePresence pour disperser les cartes

            aAjouter.addAll(pioche.subList(0, pExtension.getIntervallePresence()));
            pioche.removeAll(aAjouter);

            for(Carte c : pExtension.getCartes()){
                for(int i=0; i<c.getNombreExemplaire(); i++)
                    aAjouter.add(c);
            }

            Collections.shuffle(aAjouter);

            pioche.addAll(0,aAjouter);

        }

        pioche.addAll(0,aGarder);

    }

    public Carte premiereCarte(){
        if(pioche.size()>0)
            return pioche.get(0);

        //gerer le cas
        return null;
    }

    public Carte piocherCarte(){
        if(pioche.size()>0){
            Carte piochee = pioche.remove(0);
            if(piochee.getSuivante()!=null)
                pioche.add(0, piochee.getSuivante());
            return piochee;
        }

        return null;
    }


}
