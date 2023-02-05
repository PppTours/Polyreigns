package Vue;

import Controlleur.Controlleur;
import Outil.EffetEcriture;
import Outil.FichierManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel {

    PanelStat panelStat;
    JLabel cardPicture;
    JLabel fondCarte;
    JTextArea descriptifTextArea;
    JLabel scoreTextfield;
    PanelChoix choix1;
    PanelChoix choix2;

    Controlleur controlleur;

    Image fond;

    EffetEcriture effetEcriture;

    public Panel(){

        controlleur = new Controlleur();

        setBackground(Color.GRAY);
        setLayout(null);

        Font f = new Font(Font.SANS_SERIF, Font.BOLD,20);

        choix1 = new PanelChoix("flechegauche.png");
        add(choix1);

        choix2 = new PanelChoix("flechedroite.png");
        add(choix2);

        scoreTextfield = new JLabel("Score : 0");
        scoreTextfield.setSize(120,50);
        scoreTextfield.setFont(f);
        add(scoreTextfield);

        cardPicture = new JLabel();
        cardPicture.setSize(335,335);
        cardPicture.setIcon(new ImageIcon("game/image/carte.png"));
        add(cardPicture);

        fondCarte = new JLabel();
        fondCarte.setSize(350,350);
        fondCarte.setIcon(new ImageIcon("game/image/fondcarte.png"));
        add(fondCarte);

        descriptifTextArea = new JTextArea("Descriptif de la carte");
        descriptifTextArea.setSize(450,150);
        descriptifTextArea.setEditable(false);
        descriptifTextArea.setLineWrap(true);
        descriptifTextArea.setWrapStyleWord(true);
        descriptifTextArea.setForeground(Color.black);
        descriptifTextArea.setBackground(Color.gray);

        descriptifTextArea.setFont(f);

        add(descriptifTextArea);

        panelStat = new PanelStat(controlleur);
        add(panelStat);


        //Gestion des inputs
        setFocusable(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (e.getKeyChar() == KeyEvent.VK_ENTER){
                    if(controlleur.piocherCarte()){
                        panelStat.repaint();
                        scoreTextfield.setText("Score : "+controlleur.getScore());
                        effetEcriture.stopEffet();
                        effetEcriture = new EffetEcriture(descriptifTextArea,controlleur.getDescriptionCarte());
                        effetEcriture.start();
                        majTexte();
                    }
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyChar() == 'q') {
                    controlleur.selectionnerChoix(1);
                    previewChoix1();
                    panelStat.repaint();
                } else if (e.getKeyChar() == 'd') {
                    controlleur.selectionnerChoix(2);
                    previewChoix2();
                    panelStat.repaint();
                }

                repaint();

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == 'q' || e.getKeyChar() == 'd') {
                    maj();
                    panelStat.repaint();
                    controlleur.selectionnerChoix(0);
                    choix1.setVisible(true);
                    choix2.setVisible(true);
                }
            }
        });

        effetEcriture = new EffetEcriture(descriptifTextArea,controlleur.getDescriptionCarte());
        effetEcriture.start();

        majTexte();

    }
    public void maj(){
        scoreTextfield.setLocation((getWidth()-scoreTextfield.getWidth()),getHeight()-scoreTextfield.getHeight());
        cardPicture.setLocation((getWidth()-cardPicture.getWidth())/2,(getHeight()-cardPicture.getHeight())/2);
        fondCarte.setLocation((getWidth()-fondCarte.getWidth())/2,(getHeight()-fondCarte.getHeight())/2);
        descriptifTextArea.setLocation((getWidth()-descriptifTextArea.getWidth())/2,fondCarte.getY()+fondCarte.getHeight()+10);

        choix1.setLocation((fondCarte.getX()-choix1.getWidth() - 10), fondCarte.getY()+50+(fondCarte.getHeight()-choix1.getHeight())/2);
        choix2.setLocation((fondCarte.getX()+fondCarte.getWidth() + 10), fondCarte.getY()+50+(fondCarte.getHeight()-choix2.getHeight())/2);

        panelStat.setLocation((getWidth()-panelStat.getWidth())/2,0);
    }

    public void majTexte(){
        choix1.changerTexte(controlleur.getTexteChoixGauche());
        choix2.changerTexte(controlleur.getTexteChoixDroite());

    }


    public void previewChoix1(){
        maj();
        cardPicture.setLocation(cardPicture.getX() - 40, cardPicture.getY());
        choix1.setLocation(choix1.getX() - 40, choix1.getY());
        choix1.setVisible(true);
        choix2.setVisible(false);
        panelStat.repaint();
    }
    public void previewChoix2(){
        maj();
        cardPicture.setLocation(cardPicture.getX() + 40, cardPicture.getY());
        choix2.setLocation(choix2.getX() + 40, choix2.getY());
        choix1.setVisible(false);
        choix2.setVisible(true);
        panelStat.repaint();
    }

}
