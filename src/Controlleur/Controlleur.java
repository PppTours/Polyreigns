package Controlleur;

import Jeu.Carte.Carte;
import Jeu.Monde;

public class Controlleur {

    Monde monde;

    /**
     * Recupere le texte decrivant la situation de la carte
     * @return
     */
    public String getDescriptionCarte(){
        Carte carte = monde.premiereCarte();

        if(carte == null) {

            //erreur

        }

        return carte.getDescriptif();
    }

    /**
     * Fait piocher une carte
     */
    public void piocherCarte(){
        monde.piocherCarte();
    }

    /**
     * Fait verifier si le jeu est termine
     * @return true si le jeu est termine
     */
    public boolean verifierJeuFini(){
        return monde.getJeuFini();  //Si le jeu est fini, desactiver les controles et changer l'affichage
    }

    /**
     * Selectionne un choix
     * @param pChoixDroit true si choix droit, false pour le gauche
     */
    public void selectionnerChoix(boolean pChoixDroit){
        monde.selectionnerChoix(pChoixDroit);
    }

    /**
     * Recupere le texte associe au choix preselectionne
     * @return
     */
    public String getTexteChoix(){
        return monde.getChoixSelectionne().getReponse();
    }

    /**
     * Recupere le score du joueur
     * @return
     */
    public int getScore(){
        return monde.getScore();
    }

    /**
     * Recupere tous les effets associes au choix preselectionne
     * @return un int[4] compose de 4 elements : argent, energie, vie sociale, note
     */
    public int[] getEffetCarte(){
        int[] tab = new int[4];

        tab[0] = monde.getChoixSelectionne().getEffetArgent();
        tab[1] = monde.getChoixSelectionne().getEffetEnergie();
        tab[2] = monde.getChoixSelectionne().getEffetVieSociale();
        tab[3] = monde.getChoixSelectionne().getEffetNote();

        return tab;
    }

    /**
     * Recupere toutes les stats du joueur
     * @return un int[4] compose de 4 elements : argent, energie, vie sociale, note
     */
    public int[] getStatsJoueur(){
        int[] tab = new int[4];

        tab[0] = monde.getJoueur().getArgent();
        tab[1] = monde.getJoueur().getEnergie();
        tab[2] = monde.getJoueur().getVieSociale();
        tab[3] = monde.getJoueur().getNote();

        return tab;
    }




}
