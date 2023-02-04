package Vue;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    JTextField scoreTextfield;
    JTextArea descriptifTextArea;

    PanelStat panelStat;

    public Panel(){

        setBackground(Color.yellow);
        setSize(1080,1080);
        setLayout(null);

        scoreTextfield = new JTextField("Score : 0000");
        scoreTextfield.setLocation(500,500);
        add(scoreTextfield);

        descriptifTextArea = new JTextArea("Descriptif de la carte");
        descriptifTextArea.setSize(400,200);
        descriptifTextArea.setLocation(100,400);
        add(descriptifTextArea);

        panelStat = new PanelStat();
        panelStat.setLocation(100,0);
        add(panelStat);

    }

}
