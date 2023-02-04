package Vue;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    PanelStat panelStat;
    JLabel cardPicture;
    JLabel descriptifTextArea;
    JLabel scoreTextfield;

    public Panel(){

        setBackground(Color.yellow);
        setLayout(null);

        scoreTextfield = new JLabel("Score : 0000");
        scoreTextfield.setSize(100,50);
        add(scoreTextfield);

        JLabel cardPicture = new JLabel("Carte");
        cardPicture.setSize(350,450);
        cardPicture.setIcon(new ImageIcon("\\src\\Pictures\\carte.jpg"));
        add(cardPicture);

        descriptifTextArea = new JLabel("Descriptif de la carte");
        descriptifTextArea.setSize(300,100);
        add(descriptifTextArea);

        panelStat = new PanelStat();
        add(panelStat);

    }
 public void maj(){
     scoreTextfield.setLocation((getWidth()-scoreTextfield.getWidth()),getHeight()-scoreTextfield.getHeight());
     System.out.println(scoreTextfield.getLocation());
     descriptifTextArea.setLocation((getWidth()-descriptifTextArea.getWidth())/2,(getHeight()-descriptifTextArea.getHeight())/2);
     panelStat.setLocation((getWidth()-panelStat.getWidth())/2,0);

 }
}
