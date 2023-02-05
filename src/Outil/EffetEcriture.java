package Outil;

import javax.swing.*;

public class EffetEcriture extends Thread{

    private int lettreParSeconde = 50;

    String texteAEcrire;
    JTextArea textArea;

    public EffetEcriture(JTextArea pTextArea, String pTexte){
        texteAEcrire = pTexte;
        textArea = pTextArea;
    }

    @Override
    public void run(){

        for(int i=0; i<=texteAEcrire.length(); i++){

            try {
                sleep(1000/lettreParSeconde);
                textArea.setText(texteAEcrire.substring(0,i));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
