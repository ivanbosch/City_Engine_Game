package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MainPanel extends Container {
    private Game game;
    private JPanel mainPanel;
    private JButton pauseButton;
    private JButton quitButton;
    private JButton restartButton;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel errorMessage;


    private boolean pause;

    //Main Panel constructor buttons are initialised here and their action on press.
    public MainPanel (Game game){
        this.game = game;

        mainPanel = new JPanel();

        mainPanel.setBackground(Color.black);

        errorMessage = new JLabel("");

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

        restartButton = new JButton("Back to Galaxy");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.restart();
            }
        });

        saveButton = new JButton("Save Game");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    game.getWorld().save("data/save.txt", "user", game.getLevel(), game.getData().getScore(), game.getLevel2WasCompleted(), game.getLevel3WasCompleted(), game.getLevel4WasCompleted());
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        loadButton = new JButton("Load File");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    game.getWorld().loadFile("data/save.txt", game);
                }
                catch (GameLoadException ex){
                    errorMessage.setText(ex.getReason());
                }
            }
        });

        paintButton(pauseButton);
        paintButton(restartButton);
        paintButton(quitButton);
        paintButton(saveButton);
        paintButton(loadButton);
        errorMessage.setBackground(Color.black);
        errorMessage.setForeground(Color.lightGray);
        errorMessage.setFocusable(false);
        mainPanel.add(errorMessage);

        mainPanel.setFocusable(false);
    }

    //style the button and add it to the panel
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
