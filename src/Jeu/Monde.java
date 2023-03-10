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

        resetScore();
    }

    /**
     * Reset le score de toute les extensions
     */
    public void resetScore(){
        Extension.resetScore();
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

        if(joueur.statHorsBorne()){

            String texteChoixDroite="Oups...", texteChoixGauche="AH!", texteCarte = null, image = null;

            if(joueur.getArgent()<=0) {
                texteCarte = "Vous n'avez plus d'argent et vous ne pouvez pas continuer votre vie d'etudiant. Vous " +
                        "avez ??t?? contraint de quitter Polytech et vous bossez maintenant ?? MacDo.";
                texteChoixDroite = "Heureusement que j'aime les frites";
                texteChoixGauche = "Ca a toujours ??t?? ma vocation";
                image = "carte/NoMoney.jpg";
            }
            if(joueur.getArgent()>=Joueur.maxStat) {
                texteCarte = "Vous ??tes tellement riche que vous avez quitt?? Polytech et investit toute votre" +
                        " fortune dans des NFT.";
                texteChoixDroite = "Je vais devenir riche";
                texteChoixGauche = "Au revoir la pl??be";
                image = "carte/TooMuchMoney.jpg";
            }
            if(joueur.getEnergie()<=0) {
                texteCarte = "Vous faites un burn out" +
                " et avait ??t?? intern?? dans un hopital psychiatrique :-)";
                texteChoixDroite = "Piti?? je ne veux plus coder";
                texteChoixGauche = "Enfin du repos";
                image = "carte/burnOUTE.jpg";
            }
            if(joueur.getEnergie()>=Joueur.maxStat) {
                texteCarte = "Vous avez tellement d'energie que vous ??tes devenu insuppportable a vivre. Vous ne restez plus" +
                        " en place. Tout le monde a en marre de vous. Une p??tition vous a fait exclure de Polytech.";
                texteChoixDroite = "Oupsi";
                texteChoixGauche = "AAAAAAAAAAHFEZUFHQPEFNR8G???RIS9I";
                image = "carte/TOOMUCHENERGY.jpg";
            }
            if(joueur.getVieSociale()<=0) {
                texteCarte = "Vous passez tellement " +
                        "inaper??u aupr??s de tout le monde que m??me Polytech a oubli?? votre existence, vous n'??tes plus inscrit " +
                        "en tant qu'??l??ve.";
                texteChoixDroite = "...";
                texteChoixGauche = "...";
                image = "carte/4everAlone.jpg";
            }
            if(joueur.getVieSociale()>=Joueur.maxStat) {
                texteCarte = "Vous ??tes tellement populaire que vous avez maintenant une foule de fans constamment ?? vos trousses." +
                        " L'un d'eux vous a poignard?? apr??s que vous ayez refus?? un selfie.";
                texteChoixDroite = "AAAARGH";
                texteChoixGauche = "*mort*";
                image = "carte/TooMuchFamous.jpg";
            }
            if(joueur.getNote()<=0) {
                texteCarte = "Vous avez lamentablement ??chou??." +
                        " Vous allez redoubler, si Polytech veut bien de vous, ce n'est pas s??r.";
                texteChoixDroite = "A l'ann??e prochaine!";
                texteChoixGauche = "Snif.";
                image = "carte/NoBrain.jpg";
            }
            if(joueur.getNote()>=Joueur.maxStat) {
                texteCarte = "Votre intelligence surhumaine a fini par attirer des scientifiques du monde entier." +
                        " Le Conseil Scientifique a exig?? que vous soyez diss??qu?? afin d'??tudier votre corps. Votre cerveau est" +
                        " actuellement dans un bocal et flotte aux c??t??s des cerveaux les plus brillants.";
                texteChoixDroite = "blblbl";
                texteChoixGauche = "...";
                image = "carte/BIGBRAIN.jpg";
            }

            Choix droiteFin = new Choix(texteChoixDroite,0,0,0,0,0);
            Choix gaucheFin = new Choix(texteChoixGauche,0,0,0,0,0);

            Carte carteFin = new Carte(Extension.getExtensionParNom("MainDeck"),texteCarte,
                    gaucheFin,droiteFin,1, image);

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
