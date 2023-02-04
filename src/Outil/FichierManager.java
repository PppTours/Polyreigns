package Outil;

import Jeu.Carte.Carte;
import Jeu.Carte.Choix;
import Jeu.MeilleurJoueur;
import Jeu.PaquetCarte.Extension;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.*;

/**
 * Manipule les fichiers associes au jeu
 */
public class FichierManager {

    public static void exporterMeilleurJoueur(){

        try(FileWriter fileWriter = new FileWriter("game/scores.txt")) {

            for(MeilleurJoueur a : MeilleurJoueur.classement){
                fileWriter.write(a.getNom()+";"+a.getScore()+"\r\n");
            }

        } catch (IOException e) {

        }

    }

    public static void importerMeilleurJoueur(){

        try(FileReader fileReader = new FileReader("game/scores.txt")) {

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

    public static void importerTouteLesCartes(){

        String nomDeckPrincipal = "MainDeck";

        File f = new File("game/Extension");

        String[] chemins = f.list();

        if(chemins==null)
            return;

        for(String chemin : chemins){
            importerExtension(chemin);
        }

        Extension.initialiseExtension();

    }

    private static void importerExtension(String nomFichier){

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("game/Extension/"+nomFichier)) {
            JSONObject obj = (JSONObject) jsonParser.parse(reader);

            JSONArray cartes = (JSONArray) obj.get("cartes");

            Extension extension = new Extension(nomFichier.substring(0,nomFichier.indexOf('.')),-1);

            for(Object o : cartes){

                JSONObject object = (JSONObject) o;


                // CHOIX 1

                JSONObject choix = (JSONObject) object.get("choix1");

                boolean declencheurchoix = false;
                Object objectDenc = choix.get("declencheur");
                if(objectDenc!=null){
                    declencheurchoix = ((Long)object.get("occurence"))>0;
                }

                JSONArray arrayChoix = (JSONArray)choix.get("stats");

                int[] statschoix = new int[arrayChoix.size()];
                for(int i=0; i<arrayChoix.size(); i++){
                    statschoix[i] = Integer.parseInt(arrayChoix.get(i).toString());
                }
                String reponsechoix = (String) choix.get("text");

                Choix choixDroite = new Choix(reponsechoix,statschoix[0],statschoix[1],statschoix[2],statschoix[3],1);
                choixDroite.setActiveExtension(declencheurchoix);


                // CHOIX 2

                choix = (JSONObject) object.get("choix2");

                declencheurchoix = false;
                objectDenc = choix.get("declencheur");
                if(objectDenc!=null){
                    declencheurchoix = ((Long)object.get("occurence"))>0;
                }

                arrayChoix = (JSONArray)choix.get("stats");

                statschoix = new int[arrayChoix.size()];
                for(int i=0; i<arrayChoix.size(); i++){
                    statschoix[i] = Integer.parseInt(arrayChoix.get(i).toString());
                }
                reponsechoix = (String) choix.get("text");

                Choix choixGauche = new Choix(reponsechoix,statschoix[0],statschoix[1],statschoix[2],statschoix[3],1);
                choixGauche.setActiveExtension(declencheurchoix);


                int occurence = ((Long)object.get("occurence")).intValue();
                String description = (String) object.get("descriptif");

                extension.getCartes().add(new Carte(extension,description,choixDroite,choixGauche,occurence));
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }

    public Image chargerImage(String pChemin){

        return null;
    }

}
