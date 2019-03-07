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

        mainPanel.setBackground(Color.black);
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

        paintButton(pauseButton);
        paintButton(restartButton);
        paintButton(quitButton);

        mainPanel.setFocusable(false);
    }

    public void paintButton(JButton button) {
        button.setBackground(Color.black);
        button.setBorderPainted(false);
        button.setForeground(Color.lightGray);
        button.setFocusable(false);
        mainPanel.add(button);
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
