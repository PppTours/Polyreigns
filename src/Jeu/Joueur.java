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

        argent = 25;
        energie = 25;
        vieSociale = 25;
        note = 25;
    }

    /**
     * Verifie si l'une des stats du joueurs est en dehors d'une borne
     * @return true si c'est le cas
     */
    public boolean statHorsBorne(){
        return argent > maxStat || argent <= 0 || energie > maxStat || energie <= 0 || vieSociale > maxStat || vieSociale <= 0
                || note > maxStat || note <= 0;

    }


    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = Math.min(argent, maxStat);
    }

    public int getEnergie() {
        return energie;
    }

    public void setEnergie(int energie) {
        this.energie = Math.min(energie, maxStat);
    }

    public int getVieSociale() {
        return vieSociale;
    }

    public void setVieSociale(int vieSociale) {
        this.vieSociale = Math.min(vieSociale, maxStat);
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = Math.min(note, maxStat);
    }

    public int getMaxStat(){
        return maxStat;
    }
}
