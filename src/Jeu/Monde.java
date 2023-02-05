package Jeu;

import Jeu.Carte.Carte;
import Jeu.Carte.Choix;
import Jeu.PaquetCarte.Extension;
import Jeu.PaquetCarte.Pioche;
import Outil.FichierManager;

/**
 * Regroupe les principaux objets utilises pour le jeu
 */
public class Monde {

    Joueur joueur;
    Pioche pioche;

    Choix choixSelectionne;

    boolean jeuFini;

    /**
     * Constructeur de monde, instancie les acteurs
     */
    public Monde(){

        joueur = new Joueur();
        pioche = new Pioche();
    }

    /**
     * Voir la premiere carte de la pioche
     * @return la premiere carte
     */
    public Carte premiereCarte(){
        return pioche.premiereCarte();
    }

    /**
     * Enleve la premiere carte de la pioche. Verifie si cela cause l'ajout d'une extension.
     * Met a jour les stats du joueur et verifie si le jeu est fini
     */
    public boolean piocherCarte(){

        if(choixSelectionne==null || jeuFini)
            return false;

        Carte carte = pioche.piocherCarte();

        if(carte == null){
            jeuFini = true;
            return false;
        }

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

        Carte prochaine = pioche.premiereCarte();

        if(prochaine!=null){
            if(choixSelectionne==carte.getChoixDroite()){
                choixSelectionne = prochaine.getChoixDroite();
            }
            if(choixSelectionne==carte.getChoixGauche())
                choixSelectionne = prochaine.getChoixGauche();
        }

        return true;

    }

    /**
     * Preselectionne un choix pour une carte
     * @param choix==2 si c'est le choix droite, ==1 pour le gauche
     */
    public void selectionnerChoix(int choix){

        Carte carte = pioche.premiereCarte();

        if(carte == null)
            return;

        if(choix==1)
            choixSelectionne = carte.getChoixGauche();
        else if(choix==2)
            choixSelectionne = carte.getChoixDroite();
        else if(choix==0)
            choixSelectionne = null;

    }

    /**
     * Verifie si le jeu est termine.
     * Si la pioche est vide, alors le jeu est fini.
     * Sinon, si l'une des stats du joueur est <= 0, on vide la pioche
     * et on rajoute une carte custom representant la cause de la fin du joueur.
     * Le joueur pioche cette derniere carte, et arrive donc sur la fin normale.
     */
    public void verifierJeuFini(){

        //Verifier si le jeu est fini
        if(pioche.premiereCarte()==null){
            jeuFini = true;
            return;
        }

        if(joueur.getArgent()<=0){
            Choix droiteFin = new Choix("Oups...",0,0,0,0,0);
            Choix gaucheFin = new Choix("AH!",0,0,0,0,0);

            Carte carteFin = new Carte(Extension.getExtensionParNom("MainDeck"),"Vous n'avez plus d'argent " +
                    "et vous ne pouvez pas continuer votre vie d'etudiant, vous allez devoir trouver un travail a MacDo.",
                    gaucheFin,droiteFin,1);

            pioche.viderPioche();
            pioche.ajouterCarteDebut(carteFin);
        }
        if(joueur.getNote()<=0){
            Choix droiteFin = new Choix("Oups...",0,0,0,0,0);
            Choix gaucheFin = new Choix("AH!",0,0,0,0,0);

            Carte carteFin = new Carte(Extension.getExtensionParNom("MainDeck"),"Vous avez lamentablement échoué." +
                    " Vous allez redoubler, si Polytech veut bien de vous, ce n'est pas sûr.",
                    gaucheFin,droiteFin,1);

            pioche.viderPioche();
            pioche.ajouterCarteDebut(carteFin);
        }
        if(joueur.getEnergie()<=0){
            Choix droiteFin = new Choix("Oups...",0,0,0,0,0);
            Choix gaucheFin = new Choix("AH!",0,0,0,0,0);

            Carte carteFin = new Carte(Extension.getExtensionParNom("MainDeck"),"Vous faites un burn out" +
                    " et avait été interné dans un hopital psychiatrique :-)",
                    gaucheFin,droiteFin,1);

            pioche.viderPioche();
            pioche.ajouterCarteDebut(carteFin);
        }
        if(joueur.getVieSociale()<=0){
            Choix droiteFin = new Choix("Oups...",0,0,0,0,0);
            Choix gaucheFin = new Choix("AH!",0,0,0,0,0);

            Carte carteFin = new Carte(Extension.getExtensionParNom("MainDeck"),"Vous passez tellement " +
                    "inaperçu auprès de tout le monde que même Polytech a oublié votre existence, vous n'êtes plus inscrit " +
                    "en tant qu'élève.",
                    gaucheFin,droiteFin,1);

            pioche.viderPioche();
            pioche.ajouterCarteDebut(carteFin);
        }

    }

    /**
     * Ajoute le joueur a la liste des meilleurs joueurs, la classe MeilleurJoueur
     * se chargera de savoir si il est retenu parmis les meilleurs ou non
     * @param nom
     */
    public void enregistrerJoueur(String nom){
        MeilleurJoueur.ajouterJoueur(nom, Extension.getTotalScore());
        FichierManager.exporterMeilleurJoueur();
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
