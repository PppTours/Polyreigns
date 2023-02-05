import Outil.FichierManager;
import Vue.Fenetre;

public class Main {
    public static void main(String[] args) {
        FichierManager.importerTouteLesCartes();
        FichierManager.importerMeilleurJoueur();

        Fenetre f = new Fenetre();
    }
}