package game;

import javax.swing.*;
import java.awt.*;

public class WorldView extends JPanel {
    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        paintBackground(g2);

        paintForeground(g2);
    }

    protected void paintBackground(Graphics2D g){

    }
    protected void paintForeground(Graphics2D g) {

    }
}
