package Vue;

import Controlleur.Controlleur;
import Outil.EffetEcriture;
import Outil.FichierManager;
import Outil.MP3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel {

    PanelStat panelStat;
    PanelScore panelScore;
    JLabel cardPicture;
    JLabel fondCarte;
    JTextArea descriptifTextArea;
    JLabel scoreTextfield;
    PanelChoix choix1;
    PanelChoix choix2;

    Controlleur controlleur;

    Image fond;

    EffetEcriture effetEcriture;

    boolean jeuTermine = false;

    boolean enterPresse = false;

    MP3 mp3;

    public Panel( ){

        mp3 = new MP3(FichierManager.getLienMusique("music.mp3"));
        mp3.play();

        controlleur = new Controlleur();

        setBackground(Color.GRAY.brighter());
        setLayout(null);

        fond = FichierManager.chargerImage("fondecran.png");

        panelScore = new PanelScore();
        add(panelScore);

        choix1 = new PanelChoix("flechegauche.png");
        add(choix1);

        choix2 = new PanelChoix("flechedroite.png");
        add(choix2);

        Font fscore = new Font(Font.SANS_SERIF, Font.BOLD,32);

        scoreTextfield = new JLabel("Score : 0");
        scoreTextfield.setSize(190,80);
        scoreTextfield.setFont(fscore);
        add(scoreTextfield);

        cardPicture = new JLabel();
        cardPicture.setSize(335,335);
        Image i = FichierManager.chargerImage("carteTest2.png");
        cardPicture.setIcon(new ImageIcon(i));
        add(cardPicture);

        fondCarte = new JLabel();
        fondCarte.setSize(350,350);
        i = FichierManager.chargerImage("fondcarte.png");
        fondCarte.setIcon(new ImageIcon(i));
        //add(fondCarte);

        descriptifTextArea = new JTextArea("Descriptif de la carte");
        descriptifTextArea.setSize(450,150);
        descriptifTextArea.setEditable(false);
        descriptifTextArea.setLineWrap(true);
        descriptifTextArea.setWrapStyleWord(true);
        descriptifTextArea.setForeground(Color.black);
        descriptifTextArea.setBackground(Color.gray.brighter().brighter());
        descriptifTextArea.setBorder(BorderFactory.createCompoundBorder(
                descriptifTextArea.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        Font f = new Font(Font.SANS_SERIF, Font.BOLD,20);

        descriptifTextArea.setFont(f);

        add(descriptifTextArea);

        panelStat = new PanelStat(controlleur);
        add(panelStat);


        //Gestion des inputs
        setFocusable(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                if(e.getKeyChar() == KeyEvent.VK_ESCAPE)
                    System.exit(0);

                if(jeuTermine==false){
                    if (e.getKeyChar() == 'r' && enterPresse == false){
                        if(controlleur.piocherCarte()) {

                            enterPresse = true;

                            panelStat.repaint();
                            scoreTextfield.setText("Score : " + controlleur.getScore());
                            effetEcriture.stopEffet();
                            effetEcriture = new EffetEcriture(descriptifTextArea, controlleur.getDescriptionCarte());
                            effetEcriture.start();
                            jeuTermine = controlleur.verifierJeuFini();

                            majTexte();

                            if (jeuTermine)
                                panelScore.afficherScore(controlleur.getScore());
                        }
                    }
                }else {
                    if (e.getKeyChar() == 'r') {
                        controlleur.donnerNomJoueur(panelScore.getNom());

                        int selection = panelScore.getSelection();

                        if(selection == 5){
                            System.exit(0);
                        }
                        if(selection == 4){

                            remove(panelScore);
                            panelScore = new PanelScore();
                            add(panelScore,0);

                            jeuTermine = false;
                            controlleur = new Controlleur();

                            effetEcriture = new EffetEcriture(descriptifTextArea,controlleur.getDescriptionCarte());
                            effetEcriture.start();

                            panelStat.setControlleur(controlleur);
                            panelStat.repaint();

                            maj();
                            majTexte();

                            choix1.setVisible(true);
                            choix2.setVisible(true);
                        }
                    }
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if(jeuTermine==false){
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        controlleur.selectionnerChoix(1);
                        previewChoix1();
                        panelStat.repaint();
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        controlleur.selectionnerChoix(2);
                        previewChoix2();
                        panelStat.repaint();
                    }
                }else {
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        panelScore.incremeterIndiceSelection();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        panelScore.decrementerIndiceSelection();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        panelScore.incrementeLettre();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        panelScore.decrementeLettre();
                    }
                }

                repaint();

            }

            @Override
            public void keyReleased(KeyEvent e) {

                if(jeuTermine==false) {

                    if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
                        maj();
                        panelStat.repaint();
                        controlleur.selectionnerChoix(0);
                        choix1.setVisible(true);
                        choix2.setVisible(true);
                    }
                    if (e.getKeyChar() == 'r')
                        enterPresse = false;
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
        panelScore.setLocation((getWidth()-panelScore.getWidth())/2,(getHeight()-panelScore.getHeight())/2);
    }

    public void majTexte(){

        if(jeuTermine==false){
            choix1.changerTexte(controlleur.getTexteChoixGauche());
            choix2.changerTexte(controlleur.getTexteChoixDroite());
        }else{
            choix1.setVisible(false);
            choix2.setVisible(false);
        }

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fond, 0, 0,getWidth(),getHeight(), null);
    }

}
