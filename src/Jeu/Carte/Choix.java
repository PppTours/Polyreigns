package Jeu.Carte;

/**
 * Decrit un choix possible d'une carte
 */
public class Choix {

    String reponse;
    int effetArgent;
    int effetVieSociale;
    int effetNote;
    int effetEnergie;

    int effetPoint;

    boolean activeExtension = false;    //Si vrai, on integrera a la pioche l'extension de la carte

    public Choix(String pReponse, int pArgent, int pVieSociale, int pEnergie, int pNote, int pPoint){
        reponse = pReponse;
        effetArgent = pArgent;
        effetVieSociale = pVieSociale;
        effetNote = pNote;
        effetEnergie = pEnergie;
        effetPoint = pPoint;

    }

    public boolean isActiveExtension() {
        return activeExtension;
    }

    public void setActiveExtension(boolean activeExtension) {
        this.activeExtension = activeExtension;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getEffetEnergie() {
        return effetEnergie;
    }

    public void setEffetEnergie(int effetEnergie) {
        this.effetEnergie = effetEnergie;
    }

    public int getEffetArgent() {
        return effetArgent;
    }

    public void setEffetArgent(int effetArgent) {
        this.effetArgent = effetArgent;
    }

    public int getEffetVieSociale() {
        return effetVieSociale;
    }

    public void setEffetVieSociale(int effetVieSociale) {
        this.effetVieSociale = effetVieSociale;
    }

    public int getEffetNote() {
        return effetNote;
    }

    public void setEffetNote(int effetNote) {
        this.effetNote = effetNote;
    }

    public int getEffetPoint() {
        return effetPoint;
    }

    public void setEffetPoint(int effetPoint) {
        this.effetPoint = effetPoint;
    }
}
