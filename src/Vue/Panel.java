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
    JLabel choix1;
    JLabel choix2;

    Controlleur controlleur;

    public Panel(){

        controlleur = new Controlleur();

        setBackground(Color.yellow);
        setLayout(null);

        scoreTextfield = new JLabel("Score : 0000");
        scoreTextfield.setSize(100,50);
        scoreTextfield.setBorder(BorderFactory.createLineBorder(Color.black));
        add(scoreTextfield);

        cardPicture = new JLabel("Carte");
        cardPicture.setSize(350,350);
        cardPicture.setIcon(new ImageIcon("\\src\\Pictures\\carte.jpg"));
        cardPicture.setBorder(BorderFactory.createLineBorder(Color.black));
        cardPicture.setBackground(Color.BLACK);
        add(cardPicture);

        descriptifTextArea = new JLabel("Descriptif de la carte");
        descriptifTextArea.setSize(300,100);
        descriptifTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
        add(descriptifTextArea);

        choix1 = new JLabel("Choix 1");
        choix1.setSize(200,100);
        choix1.setBorder(BorderFactory.createLineBorder(Color.black));
        add(choix1);

        choix2 = new JLabel("Choix 2");
        choix2.setSize(200,100);
        choix2.setBorder(BorderFactory.createLineBorder(Color.black));
        add(choix2);

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
        cardPicture.setLocation((getWidth()-cardPicture.getWidth())/2,(getHeight()-cardPicture.getHeight())/2);
        descriptifTextArea.setLocation((getWidth()-descriptifTextArea.getWidth())/2,cardPicture.getY()+cardPicture.getHeight());
        choix1.setLocation(cardPicture.getX()-choix1.getWidth(), cardPicture.getY()+(cardPicture.getHeight()-choix1.getHeight())/2);
        choix2.setLocation(cardPicture.getX()+cardPicture.getWidth(), cardPicture.getY()+(cardPicture.getHeight()-choix2.getHeight())/2);
        panelStat.setLocation((getWidth()-panelStat.getWidth())/2,0);

    }

    public void choix1(){
        cardPicture.setLocation(cardPicture.getX() + 40, cardPicture.getY());
        choix1.setLocation(choix1.getX() + 20, choix1.getY());
    }
    public void choix2(){
        cardPicture.setLocation(cardPicture.getX() - 40, cardPicture.getY());
        choix2.setLocation(choix2.getX() - 20, choix2.getY());
    }
}
