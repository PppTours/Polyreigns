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
        choix1.setVisible(false);
        add(choix1);

        choix2 = new JLabel("Choix 2");
        choix2.setSize(200,100);
        choix2.setBorder(BorderFactory.createLineBorder(Color.black));
        choix2.setVisible(false);
        add(choix2);

        panelStat = new PanelStat(controlleur);
        add(panelStat);


        //Gestion des inputs
        setFocusable(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                int choix = 0;

                if (e.getKeyChar() == 'd') {
                    previewChoix1();
                    choix = 1;
                } else if (e.getKeyChar() == 'q') {
                    previewChoix2();
                    choix = 2;
                } else if (e.getKeyChar() == KeyEvent.VK_ENTER){
                    controlleur.selectionnerChoix(choix);
                    maj();
                    choix1.setVisible(false);
                    choix2.setVisible(false);
                }

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
        cardPicture.setLocation((getWidth()-cardPicture.getWidth())/2,(getHeight()-cardPicture.getHeight())/2);
        descriptifTextArea.setLocation((getWidth()-descriptifTextArea.getWidth())/2,cardPicture.getY()+cardPicture.getHeight());
        choix1.setLocation(cardPicture.getX()-choix1.getWidth(), cardPicture.getY()+(cardPicture.getHeight()-choix1.getHeight())/2);
        choix2.setLocation(cardPicture.getX()+cardPicture.getWidth(), cardPicture.getY()+(cardPicture.getHeight()-choix2.getHeight())/2);
        panelStat.setLocation((getWidth()-panelStat.getWidth())/2,0);

    }

    public void previewChoix1(){
        maj();
        cardPicture.setLocation(cardPicture.getX() + 40, cardPicture.getY());
        choix1.setLocation(choix1.getX() + 20, choix1.getY());
        choix1.setVisible(true);
        choix2.setVisible(false);
    }
    public void previewChoix2(){
        maj();
        cardPicture.setLocation(cardPicture.getX() - 40, cardPicture.getY());
        choix2.setLocation(choix2.getX() - 20, choix2.getY());
        choix1.setVisible(false);
        choix2.setVisible(true);
    }
}
