package Jeu.PaquetCarte;

import Jeu.Carte.Carte;

import java.util.ArrayList;
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
        Carte premiere = pioche.remove(0);

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
            pioche = (ArrayList<Carte>) pioche.subList(pExtension.getIntervallePresence(),pioche.size());

            for(Carte c : pExtension.getCartes()){
                for(int i=0; i<c.getNombreExemplaire(); i++)
                    aAjouter.add(c);
            }

            Collections.shuffle(aAjouter);

            pioche.addAll(0,aAjouter);

        }

        pioche.add(0,premiere);

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

    public void viderPioche(){
        pioche.clear();
    }

    public void ajouterCarteDebut(Carte carte){
        pioche.add(0,carte);
    }


}
