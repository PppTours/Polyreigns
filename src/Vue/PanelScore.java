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
        setBackground(Color.blue);

        score = new JLabel();
        add(score);

        secondLetter = new JLabel();
        add(secondLetter);

        firstLetter = new JLabel();
        add(firstLetter);

        thirdLetter = new JLabel();
        add(thirdLetter);

    }

    public void afficherScore(){
        score.setText("Score : " + controlleur.getScore());
        score.setSize(120,50);
        score.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

        firstLetter.setText("A");
        firstLetter.setSize(50,100);
        firstLetter.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

        secondLetter.setText("A");
        secondLetter.setSize(50,100);
        secondLetter.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

        thirdLetter.setText("A");
        thirdLetter.setSize(50,100);
        thirdLetter.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

        setVisible(true);
        System.out.println("score : "+controlleur.getScore());

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
