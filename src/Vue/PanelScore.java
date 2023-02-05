package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import Controlleur.Controlleur;


public class PanelScore extends JPanel implements ComponentListener {

    Controlleur controlleur;

    JLabel score;
    JLabel firstLetter;
    JLabel secondLetter;
    JLabel thirdLetter;

    public PanelScore() {
        controlleur = new Controlleur();

        /*score = new JLabel("Score : " + controlleur.getScore());
        score.setSize(50,100);
        score.setBorder(BorderFactory.createLineBorder(Color.black));
        add(score);*/

        secondLetter = new JLabel("A");
        secondLetter.setSize(50,100);
        secondLetter.setBorder(BorderFactory.createLineBorder(Color.black));
        add(secondLetter);

        /*firstLetter = new JLabel("A");
        firstLetter.setSize(50,100);
        firstLetter.setBorder(BorderFactory.createLineBorder(Color.black));
        add(firstLetter);

        thirdLetter = new JLabel("A");
        thirdLetter.setSize(50,100);
        thirdLetter.setBorder(BorderFactory.createLineBorder(Color.black));
        add(thirdLetter);*/

    }

    @Override
    public void componentResized(ComponentEvent e) {
        secondLetter.setLocation(getX() + getWidth()/2-score.getWidth()/2, getY()+getHeight()/2-score.getHeight()/2);
        System.out.println(getX());
        firstLetter.setLocation(secondLetter.getX()-firstLetter.getWidth()-10, secondLetter.getY());
        thirdLetter.setLocation(secondLetter.getX()+secondLetter.getWidth()+10, secondLetter.getY());
        score.setLocation(getX()+getWidth()/2-score.getWidth()/2, 0);

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
