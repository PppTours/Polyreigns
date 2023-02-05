package Jeu.PaquetCarte;

import Jeu.Carte.Carte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Decrit l'etat de la pioche actuelle du jeu
 */
public class Pioche {

    ArrayList<Carte> pioche;

    ArrayList<Extension> extensionListe;

    /**
     * Constructeur d'une pioche, ajoute les cartes de l'extension 'base'
     */
    public Pioche(){

        pioche = new ArrayList<>();
        extensionListe = new ArrayList<>();

        ajouterExtension(Extension.getExtensionParNom("MainDeck"));

    }


    /**
     * Ajoute une extension dans la pioche. L'ajoute dans l'intervalle approprie
     * @param pExtension l'extension a ajouter
     */
    public void ajouterExtension(Extension pExtension){

        if(pExtension == null){
            //gerer erreur
            return;
        }

        //On garde la premiere carte de cote car elle est deja affichee
        Carte premiere = null;
        if(pioche.size()>0)
             premiere = pioche.remove(0);

        ArrayList<Carte> aAjouter = new ArrayList<>();

        if(pExtension.getIntervallePresence() == -1){   //Jeu de base, pas de dispersion

            pExtension.setDejaIntegreePioche(true);

            for(Carte c : pExtension.getCartes()){
                for(int i=0; i<c.getNombreExemplaire(); i++)
                    aAjouter.add(c);
            }

            pioche.addAll(aAjouter);
            Collections.shuffle(pioche);
            Collections.shuffle(pioche);

        }else{      //Utiliser IntervallePresence pour disperser les cartes

            for(Carte c : pExtension.getCartes()){
                for(int i=0; i<c.getNombreExemplaire(); i++)
                    aAjouter.add(c);
            }

            int taille = pExtension.getIntervallePresence()-aAjouter.size();

            aAjouter.addAll(pioche.subList(0, taille));
            pioche = new ArrayList<>(pioche.subList(taille,pioche.size()));

            Collections.shuffle(aAjouter);
            Collections.shuffle(aAjouter);

            pioche.addAll(0,aAjouter);
        }

        //Les cartes pouvant declencher des extensions doivent etre au debut
        ArrayList<Carte> carteDeclencheuse = new ArrayList<>();

        for(int i = 0; i<pioche.size(); i++){

            Carte c = pioche.get(i);

            //Si la carte represente une extension non integree, c'est que c'est une carte declencheuse
            if(c.getExtension().isDejaIntegreePioche()==false && c.getExtension()!=pExtension){
                carteDeclencheuse.add(pioche.remove(i));
                i--;
            }
        }

        carteDeclencheuse.addAll(pioche.subList(0, 30));
        pioche = new ArrayList<>(pioche.subList(30,pioche.size()));
        Collections.shuffle(carteDeclencheuse);
        pioche.addAll(0,carteDeclencheuse);

        if(premiere!=null)
            pioche.add(0,premiere);

    }

    /**
     * Voir la premiere carte de la pioche
     * @return la carte
     */
    public Carte premiereCarte(){
        if(pioche.size()>0)
            return pioche.get(0);

        //gerer le cas
        return null;
    }

    /**
     * Enleve la premiere carte de la pioche. Si la carte est chainee, met sur le dessus
     * de la pioche la carte liee
     * @return la carte qui a ete retiree
     */
    public Carte piocherCarte(){
        if(pioche.size()>0){
            Carte piochee = pioche.remove(0);
            if(piochee.getSuivante()!=null)
                pioche.add(0, piochee.getSuivante());
            return piochee;
        }

        return null;
    }

    /**
     * Vide la pioche
     */
    public void viderPioche(){
        pioche.clear();
    }

    /**
     * Ajoute une carte au debut de la pioche
     * @param carte la carte a ajouter
     */
    public void ajouterCarteDebut(Carte carte){
        pioche.add(0,carte);
    }


}
