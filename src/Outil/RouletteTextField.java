package Outil;

import javax.swing.*;
import java.awt.*;

public class RouletteTextField extends JTextField {

    int lettreCourante;

    char debut = 'A';
    char fin = 'Z';

    public RouletteTextField(){
        setHorizontalAlignment(JTextField.CENTER);
        setEditable(false);
        Font f = new Font(Font.SANS_SERIF, Font.BOLD,40);
        setFont(f);
        setText(String.valueOf(debut));
        lettreCourante = (int)debut;
        setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
    }

    public void selectionner(){
        setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
    }

    public void deselectionner(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
    }

    public void incremeter(){
        lettreCourante = lettreCourante+1;
        if(lettreCourante>(int)fin)
            lettreCourante = (int)debut;
        setText((char)lettreCourante+"");
    }

    public void decrementer(){
        lettreCourante = lettreCourante-1;
        if(lettreCourante<(int)debut)
            lettreCourante = (int)fin;
        setText((char)lettreCourante+"");
    }

}
