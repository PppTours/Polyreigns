package Vue;

import Controlleur.Controlleur;
import Outil.FichierManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelStat extends JPanel {

    Image fond;
    Image devant;

    ArrayList<Image> slotImageEffet = new ArrayList<>();
    ArrayList<Image> slotImageStats = new ArrayList<>();

    Controlleur controlleur;

    public PanelStat(Controlleur pControlleur){

        controlleur = pControlleur;

        fond = FichierManager.chargerImage("fond.png");
        devant = FichierManager.chargerImage("devant.png");

        for(int i=0; i<4; i++){
            slotImageEffet.add(FichierManager.chargerImage("effetGrand.png"));
        }

        for(int i=0; i<4; i++){
            slotImageStats.add(FichierManager.chargerImage("fondStat.png"));
        }


        setBackground(new Color(0,0,0,0));

        setSize(500,150);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fond, 0, 0, null);

        int[] stats = controlleur.getStatsJoueur();

        int increment = 93;
        for(int i=0; i<slotImageStats.size(); i++){

            int max = controlleur.getMaxStat();

            int hauteur = (int) (stats[i] * 74 / max);      //74 c'est la taille de l'image

            Image image = slotImageStats.get(i);
            g.drawImage(image, increment, 71+74-hauteur,74,hauteur,null);
            increment+=80;
        }

        g.drawImage(devant, 0, 0, null);

        int[] effet = controlleur.getEffetCarte();

        increment = 120;
        for(int i=0; i<slotImageEffet.size(); i++){
            if(effet[i]!=0){
                Image image = slotImageEffet.get(i);
                g.drawImage(image, increment, 47,null);
            }
            increment+=80;
        }

    }



}
