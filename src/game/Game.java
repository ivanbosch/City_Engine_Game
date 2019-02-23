package game;

import city.cs.engine.*;

import javax.swing.JFrame;

public class Game {

    private GameLevel world;

    private UserView view;

    private int level;

    private KeyboardInput keyboardInput;
    public Game() {

        // make the world
        level = 1;

        world = new Level2();
        world.populate(this);

        // make EnemiesCollision view
        view = new StartView(world, 500, 500);

        // uncomment this to draw EnemiesCollision 1-metre grid over the view
        // view.setGridResolution(1);

        // display the view in EnemiesCollision frame
        final JFrame frame = new JFrame("Stardust Crusaders");

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        frame.requestFocus();

        keyboardInput = new KeyboardInput(world.getPlayer());
        frame.addKeyListener(keyboardInput);
        // uncomment this to make EnemiesCollision debugging view
        //JFrame debugView = new DebugViewer(world, 500, 500);

        world.setGravity(0);

        // start!
        world.start();
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }

    public void goNextLevel() {
        if (level == 3) {
            System.exit(0);
        } else {
            level++;

            world = new Level2();

            world.populate(this);

            keyboardInput.setPlayer(world.getPlayer());
        }
    }

    public UserView getView() {
        return view;
    }

    public World getWorld() {
        return world;
    }
}
