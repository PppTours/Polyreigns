package Vue;

import Controlleur.Controlleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel {

    PanelStat panelStat;
    JLabel cardPicture;
    JLabel descriptifTextArea;
    JLabel scoreTextfield;

    Controlleur controlleur;

    public Panel(){

        controlleur = new Controlleur();

        setBackground(Color.yellow);
        setLayout(null);

        scoreTextfield = new JLabel("Score : 0000");
        scoreTextfield.setSize(100,50);
        add(scoreTextfield);

        JLabel cardPicture = new JLabel("Carte");
        cardPicture.setSize(350,450);
        cardPicture.setIcon(new ImageIcon("\\src\\Pictures\\carte.jpg"));
        cardPicture.setBackground(Color.BLACK);
        add(cardPicture);

        descriptifTextArea = new JLabel("Descriptif de la carte");
        descriptifTextArea.setSize(300,100);
        add(descriptifTextArea);

        panelStat = new PanelStat(controlleur);
        add(panelStat);


        //Gestion des inputs
        setFocusable(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

                }

                /*if(e.getKeyChar()=='q'){

                }*/
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }
 public void maj(){
     scoreTextfield.setLocation((getWidth()-scoreTextfield.getWidth()),getHeight()-scoreTextfield.getHeight());
     System.out.println(scoreTextfield.getLocation());
     descriptifTextArea.setLocation((getWidth()-descriptifTextArea.getWidth())/2,(getHeight()-descriptifTextArea.getHeight())/2);
     panelStat.setLocation((getWidth()-panelStat.getWidth())/2,0);

 }
}
