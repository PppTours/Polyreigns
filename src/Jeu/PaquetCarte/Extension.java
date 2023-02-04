package Jeu.PaquetCarte;

import Jeu.Carte.Carte;

import java.util.ArrayList;

/**
 * Decrit un ensemble de cartes qui pourra etre ajoute
 * a la pioche. Par defaut, l'extension "base" est ajoutee
 * a la pioche. Les autres extensions pourront etre ajoutees
 * selon les choix de certaines cartes.
 * Il peut y avoir des cartes venant d'autres extensions. Ces cartes
 * seront les declencheurs: si on fait le bon choix avec une de ces cartes alors
 * que son extension a dejaIntegreePioche=false , alors on l'integre a la pioche et on passe
 * dejaIntegreePioche a true
 */
public class Extension {

    int intervallePresence;     //lors de l'ajout de l'extension, elle sera eparpillee parmis les x prochaine cartes
    private boolean dejaIntegreePioche = false;

    private String nom;
    private ArrayList<Carte> cartes;
    private int points;     //Le decompte final sera l'addition des points dans
                    //chaque extension

    static ArrayList<Extension> extensionListe = new ArrayList<>();

    /**
     * Constructeur d'une extension
     * @param pNom son nom
     * @param pIntervallePresence l'intervalle de cartes dans la pioche
     *                            ou cette extension devra etre dispersee
     */
    public Extension(String pNom, int pIntervallePresence){

        intervallePresence = pIntervallePresence;

        nom = pNom;
        extensionListe.add(this);
    }

    /**
     * Cherche une extension via son nom
     * @param pNom le nom a chercher
     * @return l'extension trouvee
     */
    public static Extension getExtensionParNom(String pNom){
        for(Extension s : extensionListe){
            if(s.nom.equals(pNom))
                return s;
        }

        return null;
    }

    /**
     * Recupere le score de toute les extensions, qui est donc le score total
     * Le score est stocke par extension pour permettre l'activation de certaines
     * choses apres avoir atteint un score dans une extension precise, comme
     * l'ajout d'une autre extension par exemple
     * @return
     */
    public static int getTotalScore(){
        int score = 0;

        for(Extension e : extensionListe)
            score += e.getPoints();

        return score;
    }

    public int getIntervallePresence() {
        return intervallePresence;
    }

    public void setIntervallePresence(int intervallePresence) {
        this.intervallePresence = intervallePresence;
    }

    public boolean isDejaIntegreePioche() {
        return dejaIntegreePioche;
    }

    public void setDejaIntegreePioche(boolean dejaIntegreePioche) {
        this.dejaIntegreePioche = dejaIntegreePioche;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
