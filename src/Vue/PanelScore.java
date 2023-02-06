package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Controlleur.Controlleur;
import Outil.RouletteTextField;


public class PanelScore extends JPanel {

    JLabel score;
    RouletteTextField firstLetter;
    RouletteTextField secondLetter;
    RouletteTextField thirdLetter;

    JLabel recommencer;
    JLabel quitter;

    int indiceSelection = 1;

    public PanelScore() {
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        setSize(300,300);

        score = new JLabel();
        add(score);

        secondLetter = new RouletteTextField();
        add(secondLetter);

        firstLetter = new RouletteTextField();
        add(firstLetter);

        thirdLetter = new RouletteTextField();
        add(thirdLetter);

        recommencer = new JLabel();
        recommencer.setText("Recommencer");
        add(recommencer);

        quitter = new JLabel();
        quitter.setText("Quitter");
        add(quitter);

        setVisible(false);

    }

    public void afficherScore(int pScore){

        Font f = new Font(Font.SANS_SERIF, Font.BOLD,20);

        score.setText("Score : " + pScore);
        score.setSize(120,50);
        score.setFont(f);
        score.setForeground(Color.white);
        score.setLocation(getWidth()/2-score.getWidth()/2, 0);

        secondLetter.setText("A");
        secondLetter.setSize(50,70);
        secondLetter.setLocation(getWidth()/2-secondLetter.getWidth()/2,getHeight()/2-secondLetter.getHeight());

        firstLetter.setText("A");
        firstLetter.setSize(50,70);
        firstLetter.setLocation(secondLetter.getX()-firstLetter.getWidth()-10, secondLetter.getY());

        thirdLetter.setText("A");
        thirdLetter.setSize(50,70);
        thirdLetter.setLocation(secondLetter.getX()+secondLetter.getWidth()+10, secondLetter.getY());

        recommencer.setFont(f);
        quitter.setFont(f);

        recommencer.setSize(150,30);
        recommencer.setForeground(Color.white);
        recommencer.setLocation(getWidth()/2-recommencer.getWidth()/2,secondLetter.getY()+100);

        quitter.setSize(150,30);
        quitter.setForeground(Color.white);
        quitter.setLocation(getWidth()/2-quitter.getWidth()/2,secondLetter.getY()+150);

        setVisible(true);
        firstLetter.selectionner();
    }

    public void majSelection(){

        firstLetter.deselectionner();
        secondLetter.deselectionner();
        thirdLetter.deselectionner();

        quitter.setForeground(Color.white);
        recommencer.setForeground(Color.white);

        switch(indiceSelection){
            case 1 -> firstLetter.selectionner();
            case 2 -> secondLetter.selectionner();
            case 3 -> thirdLetter.selectionner();
            case 4 -> recommencer.setForeground(Color.green);
            case 5 -> quitter.setForeground(Color.green);
        }
    }

    public void incrementeLettre(){

        switch(indiceSelection){
            case 1 -> firstLetter.incremeter();
            case 2 -> secondLetter.incremeter();
            case 3 -> thirdLetter.incremeter();
        }

    }

    public void decrementeLettre(){

        switch(indiceSelection){
            case 1 -> firstLetter.decrementer();
            case 2 -> secondLetter.decrementer();
            case 3 -> thirdLetter.decrementer();
        }

    }

    public void incremeterIndiceSelection(){
        indiceSelection++;
        if(indiceSelection>5)
            indiceSelection = 5;
        majSelection();
    }

    public void decrementerIndiceSelection(){
        indiceSelection--;
        if(indiceSelection<0)
            indiceSelection = 0;
        majSelection();
    }

    public int getSelection(){
        return indiceSelection;
    }

    public String getNom() {
        return firstLetter.getText()+secondLetter.getText()+thirdLetter.getText();
    }
}
