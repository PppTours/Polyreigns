package Vue;

import Controlleur.Controlleur;
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
    JLabel choix1;
    JLabel choix2;

    Controlleur controlleur;

    Image fond;

    public Panel(){

        controlleur = new Controlleur();

        setBackground(Color.GRAY);
        setLayout(null);

        //fond = FichierManager.chargerImage();

        choix1 = new JLabel();
        choix1.setSize(200,100);
        choix1.setBorder(BorderFactory.createLineBorder(Color.black));
        choix1.setIcon(new ImageIcon("game/image/flechegauche.png"));
        add(choix1);

        choix2 = new JLabel();
        choix2.setSize(200,100);
        choix2.setBorder(BorderFactory.createLineBorder(Color.black));
        choix2.setIcon(new ImageIcon("game/image/flechedroite.png"));
        add(choix2);

        scoreTextfield = new JLabel("Score : 0000");
        scoreTextfield.setSize(100,50);
        scoreTextfield.setBorder(BorderFactory.createLineBorder(Color.black));
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
        descriptifTextArea.setSize(300,100);
        descriptifTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
        descriptifTextArea.setEditable(false);
        descriptifTextArea.setLineWrap(true);
        add(descriptifTextArea);

        panelStat = new PanelStat(controlleur);
        add(panelStat);


        //Gestion des inputs
        setFocusable(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (e.getKeyChar() == KeyEvent.VK_ENTER){
                    controlleur.piocherCarte();
                    maj();
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyChar() == 'q') {
                    controlleur.selectionnerChoix(1);
                    previewChoix1();
                } else if (e.getKeyChar() == 'd') {
                    controlleur.selectionnerChoix(2);
                    previewChoix2();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == 'q' || e.getKeyChar() == 'd') {
                    maj();
                }
            }
        });

    }
    public void maj(){
        scoreTextfield.setLocation((getWidth()-scoreTextfield.getWidth()),getHeight()-scoreTextfield.getHeight());
        cardPicture.setLocation((getWidth()-cardPicture.getWidth())/2,(getHeight()-cardPicture.getHeight())/2);
        fondCarte.setLocation((getWidth()-fondCarte.getWidth())/2,(getHeight()-fondCarte.getHeight())/2);
        descriptifTextArea.setLocation((getWidth()-descriptifTextArea.getWidth())/2,fondCarte.getY()+fondCarte.getHeight());
        descriptifTextArea.setText(controlleur.getDescriptionCarte());
        choix1.setLocation((int) (fondCarte.getX()-choix1.getWidth() - 10), fondCarte.getY()+(fondCarte.getHeight()-choix1.getHeight())/2);
        controlleur.selectionnerChoix(1);
        choix1.setText(controlleur.getTexteChoix());
        choix1.setVisible(true);
        choix2.setLocation((int) (fondCarte.getX()+fondCarte.getWidth() + 10), fondCarte.getY()+(fondCarte.getHeight()-choix2.getHeight())/2);
        controlleur.selectionnerChoix(2);
        choix2.setText(controlleur.getTexteChoix());
        choix2.setVisible(true);
        panelStat.setLocation((getWidth()-panelStat.getWidth())/2,0);

    }

    public void previewChoix1(){
        maj();
        cardPicture.setLocation(cardPicture.getX() - 40, cardPicture.getY());
        choix1.setLocation(choix1.getX() - 40, choix1.getY());
        choix1.setVisible(true);
        choix2.setVisible(false);
    }
    public void previewChoix2(){
        maj();
        cardPicture.setLocation(cardPicture.getX() + 40, cardPicture.getY());
        choix2.setLocation(choix2.getX() + 40, choix2.getY());
        choix1.setVisible(false);
        choix2.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fond, 0, 0, null);
    }
}
