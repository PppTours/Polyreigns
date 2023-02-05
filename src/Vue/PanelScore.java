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
        setLayout(null);
        setBackground(Color.yellow);
        setSize(300,200);

        controlleur = new Controlleur();

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
        score.setLocation(getWidth()/2-score.getWidth()/2, 0);

        secondLetter.setText("A");
        secondLetter.setSize(50,100);
        secondLetter.setLocation(getWidth()/2-secondLetter.getWidth()/2,getHeight()/2-secondLetter.getHeight()/2);
        secondLetter.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

        firstLetter.setText("A");
        firstLetter.setSize(50,100);
        firstLetter.setLocation(secondLetter.getX()-firstLetter.getWidth()-10, secondLetter.getY());
        firstLetter.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

        thirdLetter.setText("A");
        thirdLetter.setSize(50,100);
        thirdLetter.setLocation(secondLetter.getX()+secondLetter.getWidth()+10, secondLetter.getY());
        thirdLetter.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

        setVisible(true);
        System.out.println("score : "+controlleur.getScore());

    }

    @Override
    public void componentResized(ComponentEvent e) {

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
