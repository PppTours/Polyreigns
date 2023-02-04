package Outil;

import Jeu.MeilleurJoueur;

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

    }

    public Image chargerImage(String pChemin){

        return null;
    }

}
