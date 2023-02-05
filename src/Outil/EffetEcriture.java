package Outil;

import javax.swing.*;

public class EffetEcriture extends Thread{

    private int lettreParSeconde = 60;

    String texteAEcrire;
    JTextArea textArea;

    private boolean stop = false;

    public EffetEcriture(JTextArea pTextArea, String pTexte){
        texteAEcrire = pTexte;
        textArea = pTextArea;
    }

    @Override
    public void run(){

        for(int i=0; i<=texteAEcrire.length(); i++){

            if(stop)
                break;

            try {
                textArea.setText(texteAEcrire.substring(0,i));
                sleep(1000/lettreParSeconde);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public void stopEffet(){
        stop = true;
        texteAEcrire = "";
    }

}
