package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainPanel extends Container {
    private Game game;
    private JPanel mainPanel;
    private JButton pauseButton;
    private JButton quitButton;
    private JButton restartButton;


    private boolean pause;

    public MainPanel (Game game){
        this.game = game;

        mainPanel = new JPanel();

        pauseButton = new JButton("Pause");
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

        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.restart();
            }
        });

        mainPanel.add(pauseButton);
        mainPanel.add(restartButton);
        mainPanel.add(quitButton);

        unFocus(false);
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void unFocus(boolean f) {
        mainPanel.setFocusable(f);
        pauseButton.setFocusable(f);
        quitButton.setFocusable(f);
        restartButton.setFocusable(f);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
