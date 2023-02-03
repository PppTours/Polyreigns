package Vue;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Fenetre extends JFrame {

    Panel panel;

    int width = 600;
    int height = 700;

    public Fenetre(){

        setSize(600,700);
        setContentPane(panel = new Panel());

        setVisible(true);
        setUndecorated(true);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                width = e.getComponent().getWidth();
                height = e.getComponent().getHeight();

               // panel.maj();
            }
            @Override
            public void componentMoved(ComponentEvent e) {
                System.out.println("Moved to " + e.getComponent().getLocation());
            }
        });

    }

}
