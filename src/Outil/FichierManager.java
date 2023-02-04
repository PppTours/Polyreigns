package Outil;

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

    public static void importerCarte(){

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("game/Extensions/MainDeck.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray cartes = (JSONArray) obj;

            Extension main = new Extension("MainDeck",-1);

            for(Object o : cartes){

                JSONObject object = (JSONObject) o;

                Choix choixDroite = new Choix();
                Choix choixGauche = new Choix();

            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }


    }

    public Image chargerImage(String pChemin){

        return null;
    }

}
