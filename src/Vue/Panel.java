package Vue;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    int panelWidth = 1080;
    int panelHeight = 1080;

    JLabel scoreLabel;
    JLabel descriptifLabel;
    PanelStat panelStat;

    public Panel(){

        this.setLayout(null);

        scoreLabel = new JLabel("Score : 0000");
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setSize(150,50);
        scoreLabel.setLocation(1080,830);
        add(scoreLabel);

        System.out.println (panelWidth + " " + panelHeight + " " + scoreLabel.getWidth() + " " + scoreLabel.getHeight());

        descriptifLabel = new JLabel("Descriptif de la carte");
        descriptifLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        descriptifLabel.setSize(400,200);
        descriptifLabel.setLocation(100,400);
        add(descriptifLabel);

        panelStat = new PanelStat();
        panelStat.setLocation(100,0);
        add(panelStat);

    }

}
