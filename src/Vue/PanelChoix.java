package Vue;

import Outil.FichierManager;

import javax.swing.*;
import java.awt.*;

public class PanelChoix extends JPanel {

    JTextArea textArea;
    JLabel image;

    public PanelChoix(String pImage){

        setBackground(new Color(255,255,255,100));
        setOpaque(false);

        Font f = new Font(Font.SANS_SERIF, Font.BOLD,18);

        image = new JLabel();
        image.setSize(180,50);
        image.setIcon(new ImageIcon(FichierManager.chargerImage(pImage)));

        setSize(180,200);

        textArea = new JTextArea();
        textArea.setOpaque(false); // background of parent will be painted first

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(f);
        textArea.setSize(180,150);
        textArea.setBorder(BorderFactory.createCompoundBorder(
                textArea.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        add(image);
        add(textArea);

    }

    public void changerTexte(String pText){
        textArea.setText(pText);
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }


}
