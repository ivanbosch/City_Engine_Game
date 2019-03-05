package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends Container {
    private JPanel controlPanel;
    private JButton pauseButton;
    private JButton quitButton;
    private JButton restartButton;
    private Game game;

    private boolean pause;

    public ControlPanel(Game game) {
        this.game = game;
        controlPanel.setFocusable(false);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pause == false) {
                    game.getWorld().stop();
                    setPause(true);
                } else {
                    game.getWorld().start();
                    setPause(false);
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.restart();
            }
        });
    }

    public JPanel getMainPanel() {
        return controlPanel;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
