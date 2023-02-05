package Vue;

import Outil.FichierManager;

import javax.swing.*;
import java.awt.*;

public class PanelChoix extends JPanel {

    JTextArea textArea;
    JLabel image;

    public PanelChoix(String pImage){

        setBackground(new Color(0,0,0,0));

        Font f = new Font(Font.SANS_SERIF, Font.BOLD,15);

        image = new JLabel();
        image.setIcon(new ImageIcon(FichierManager.chargerImage(pImage)));

        setSize(160,200);

        textArea = new JTextArea();
        textArea.setBackground(new Color(0,0,0,0));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(f);

        add(image);
        add(textArea);

    }

    public void changerTexte(String pText){
        textArea.setText(pText);
    }

}
