package Jeu;

/**
 * Decrit le joueur et ses stats
 */
public class Joueur {

    private int argent;
    private int energie;
    private int vieSociale;
    private int note;

    public Joueur(){

        argent = 50;
        energie = 50;
        vieSociale = 50;
        note = 50;
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public int getEnergie() {
        return energie;
    }

    public void setEnergie(int energie) {
        this.energie = energie;
    }

    public int getVieSociale() {
        return vieSociale;
    }

    public void setVieSociale(int vieSociale) {
        this.vieSociale = vieSociale;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
