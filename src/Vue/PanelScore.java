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

    Controlleur controlleur;

    JLabel score;
    RouletteTextField firstLetter;
    RouletteTextField secondLetter;
    RouletteTextField thirdLetter;

    int indiceSelection = 1;

    public PanelScore() {
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        setSize(300,200);

        controlleur = new Controlleur();

        score = new JLabel();
        add(score);

        secondLetter = new RouletteTextField();
        add(secondLetter);

        firstLetter = new RouletteTextField();
        add(firstLetter);

        thirdLetter = new RouletteTextField();
        add(thirdLetter);

        setVisible(false);

    }

    public void afficherScore(){

        score.setText("Score : " + controlleur.getScore());
        score.setSize(120,50);
        score.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
        score.setForeground(Color.white);
        score.setLocation(getWidth()/2-score.getWidth()/2, 0);

        secondLetter.setText("A");
        secondLetter.setSize(50,70);
        secondLetter.setLocation(getWidth()/2-secondLetter.getWidth()/2,getHeight()/2-secondLetter.getHeight()/2);

        firstLetter.setText("A");
        firstLetter.setSize(50,70);
        firstLetter.setLocation(secondLetter.getX()-firstLetter.getWidth()-10, secondLetter.getY());

        thirdLetter.setText("A");
        thirdLetter.setSize(50,70);
        thirdLetter.setLocation(secondLetter.getX()+secondLetter.getWidth()+10, secondLetter.getY());

        setVisible(true);
        firstLetter.selectionner();
    }

    public void majSelection(){

        firstLetter.deselectionner();
        secondLetter.deselectionner();
        thirdLetter.deselectionner();

        switch(indiceSelection){
            case 1 -> firstLetter.selectionner();
            case 2 -> secondLetter.selectionner();
            case 3 -> thirdLetter.selectionner();
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
        if(indiceSelection>3)
            indiceSelection = 3;
        majSelection();
    }

    public void decrementerIndiceSelection(){
        indiceSelection--;
        if(indiceSelection<1)
            indiceSelection = 1;
        majSelection();
    }

    public String getNom() {
        return firstLetter.getText()+secondLetter.getText()+thirdLetter.getText();
    }
}
