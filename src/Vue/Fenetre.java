package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Fenetre extends JFrame {

    Panel panel;

    int width = 600;
    int height = 700;

    FlowLayout flowLayout;

    public Fenetre(){

        setLayout(null);
        add(panel = new Panel());

        getContentPane().setBackground(Color.BLACK);

        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        setUndecorated(true);
        setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        device.setFullScreenWindow(this);
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                width = e.getComponent().getWidth();
                height = e.getComponent().getHeight();
                panel.setLocation((getWidth()-panel.getWidth())/2,0);

                // panel.maj();
            }
            @Override
            public void componentMoved(ComponentEvent e) {
                System.out.println("Moved to " + e.getComponent().getLocation());
            }
        });

        panel.setLocation((getWidth()-panel.getWidth())/2,0);

    }

}
