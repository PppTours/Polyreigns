package Outil;

import Jeu.Carte.Carte;
import Jeu.Carte.Choix;
import Jeu.MeilleurJoueur;
import Jeu.PaquetCarte.Extension;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Manipule les fichiers associes au jeu
 */
public class FichierManager {

    static boolean modeDev = true;

    /**
     * Exporte les meilleurs joueurs dans game/scores.txt
     */
    public static void exporterMeilleurJoueur(){

        String chemin = "scores.txt";
        if(modeDev)
            chemin = "game/"+chemin;

        try(FileWriter fileWriter = new FileWriter(chemin)) {

            for(MeilleurJoueur a : MeilleurJoueur.classement){
                fileWriter.write(a.getNom()+";"+a.getScore()+"\r\n");
            }

        } catch (IOException e) {

        }

    }

    /**
     * Importe les meilleurs joueurs depuis game/scores.txt
     */
    public static void importerMeilleurJoueur(){

        String chemin = "scores.txt";
        if(modeDev)
            chemin = "game/"+chemin;

        try(FileReader fileReader = new FileReader(chemin)) {

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(";");
                String nom = split[0];
                int score = Integer.parseInt(split[1]);
                MeilleurJoueur.classement.add(new MeilleurJoueur(nom, score));
            }

            bufferedReader.close();

        } catch (IOException e) {

        }

    }

    /**
     * Importe toutes les cartes et extensions, localisées dans game/Extension/
     */
    public static void importerTouteLesCartes(){

        String chemin = "Extension";
        if(modeDev)
            chemin = "game/"+chemin;

        File f = new File(chemin);

        String[] chemins = f.list();

        if(chemins==null)
            return;

        for(String c : chemins){
            importerExtension(c);
        }

        Extension.initialiseExtension();

    }

    /**
     * Importe toute les cartes d'une extension
     * @param nomFichier le nom du fichier json de l'extension
     */
    private static void importerExtension(String nomFichier){

        String chemin = "Extension/";
        if(modeDev)
            chemin = "game/"+chemin;

        String nomDeckPrincipal = "MainDeck";

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(chemin+nomFichier, StandardCharsets.UTF_8)) {
            JSONObject obj = (JSONObject) jsonParser.parse(reader);

            int points = ((Long)obj.get("points")).intValue();
            JSONArray cartes = (JSONArray) obj.get("cartes");

            String nomExtension = nomFichier.substring(0,nomFichier.indexOf('.'));

            int intervalle = 40;
            if(nomExtension.equals(nomDeckPrincipal))
                intervalle = -1;

            Extension extension = new Extension(nomExtension,intervalle);

            for(Object o : cartes){

                JSONObject object = (JSONObject) o;


                // CHOIX 1

                JSONObject choix = (JSONObject) object.get("choix1");

                boolean declencheurchoix = false;
                Object objectDenc = choix.get("declencheur");
                if(objectDenc!=null){
                    declencheurchoix = (Long)(objectDenc)>0;
                }

                JSONArray arrayChoix = (JSONArray)choix.get("stats");

                int[] statschoix = new int[arrayChoix.size()];
                for(int i=0; i<arrayChoix.size(); i++){
                    statschoix[i] = Integer.parseInt(arrayChoix.get(i).toString());
                }
                String reponsechoix = (String) choix.get("text");

                Choix choixDroite = new Choix(reponsechoix,statschoix[0],statschoix[1],statschoix[2],statschoix[3],points);
                choixDroite.setActiveExtension(declencheurchoix);


                // CHOIX 2

                choix = (JSONObject) object.get("choix2");

                declencheurchoix = false;
                objectDenc = choix.get("declencheur");
                if(objectDenc!=null){
                    declencheurchoix = (Long)(objectDenc)>0;
                }

                arrayChoix = (JSONArray)choix.get("stats");

                statschoix = new int[arrayChoix.size()];
                for(int i=0; i<arrayChoix.size(); i++){
                    statschoix[i] = Integer.parseInt(arrayChoix.get(i).toString());
                }
                reponsechoix = (String) choix.get("text");

                Choix choixGauche = new Choix(reponsechoix,statschoix[0],statschoix[1],statschoix[2],statschoix[3],5);
                choixGauche.setActiveExtension(declencheurchoix);


                int occurence = ((Long)object.get("occurence")).intValue();
                String description = (String) object.get("descriptif");
                String image = (String) object.get("image");


                Choix permutation = choixDroite;
                if(Math.random()>0.5){
                    choixDroite = choixGauche;
                    choixGauche = permutation;
                }

                Carte c = new Carte(extension,description,choixGauche,choixDroite,occurence,"carte/"+image);

                extension.getCartes().add(c);
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Charge une image presente dans le dossier game/image/
     * @param pNom le nom de l'image
     * @return
     */
    public static Image chargerImage(String pNom){

        String chemin = "image/";
        if(modeDev)
            chemin = "game/"+chemin;

        Image image = null;
        try {
            image = ImageIO.read(new File(chemin+pNom));
        } catch (IOException e) {
            System.out.println("Image non trouvee: "+chemin+pNom);
            throw new RuntimeException(e);
        }
        return image;
    }

    public static String getLienMusique(String nomFichier){
        String chemin = nomFichier;
        if(modeDev)
            chemin = "game/"+chemin;
        return chemin;
    }

}
