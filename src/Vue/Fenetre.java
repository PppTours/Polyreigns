package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Fenetre extends JFrame {

    Panel panel;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;
    FlowLayout flowLayout;

    public Fenetre(){

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        setSize(width, height);

        panel = new Panel();
        panel.setSize(height, height);
        add(panel);

        getContentPane().setBackground(Color.green);
        setUndecorated(true);
        setVisible(true);
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
        panel.maj();
    }
}
