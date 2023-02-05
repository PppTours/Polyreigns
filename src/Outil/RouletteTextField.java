package Outil;

import javax.swing.*;
import java.awt.*;

public class RouletteTextField extends JTextField {

    int lettreCourante;

    char debut = 'A';
    char fin = 'Z';

    public RouletteTextField(){
        setEditable(false);
        Font f = new Font(Font.SANS_SERIF, Font.BOLD,20);
        setFont(f);
        setText(String.valueOf(debut));
        lettreCourante = (int)debut;
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
