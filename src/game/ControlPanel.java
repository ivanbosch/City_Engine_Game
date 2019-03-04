package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel {
    private JButton pauseButton;
    private JPanel panel1;
    private JButton quitButton;
    private JButton restartButton;

    public ControlPanel() {
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel getMainPanel() {
        return panel1;
    }
}
