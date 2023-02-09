package Jeu;

/**
 * Decrit le joueur et ses stats
 */
public class Joueur {

    private int argent;
    private int energie;
    private int vieSociale;
    private int note;

    public final static int maxStat = 60;

    public Joueur(){

        argent = 30;
        energie = 30;
        vieSociale = 30;
        note = 30;
    }

    /**
     * Verifie si l'une des stats du joueur est en dehors d'une borne
     * @return true si c'est le cas
     */
    public boolean statHorsBorne(){
        return argent >= maxStat || argent <= 0 || energie >= maxStat || energie <= 0 || vieSociale >= maxStat || vieSociale <= 0
                || note >= maxStat || note <= 0;

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

    public int getMaxStat(){
        return maxStat;
    }
}
