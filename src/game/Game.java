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

        //start on level1
        world = new Level1();
        world.populate(this);

        //make view
        view = new StartView(world, 500, 500);

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

    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }

    public void goNextLevel(int i) {
        world.stop();
        if (level == 1 && i == 2) {
            setLevel(i);

            world = new Level2();

            levelPopulation();
        }

        if (level == 1 && i == 3){
            setLevel(i);

            world = new Level3();

            levelPopulation();
        }

        if (level == 1 && i == 4){
            setLevel(i);

            world = new Level4();

            levelPopulation();
        }
    }

    public void goLevel1() {
        world.stop();

        setLevel(1);

        view.setWorld(world);

        keyboardInput.setPlayer(world.getPlayer());

        world.start();
    }

    public UserView getView() {
        return view;
    }

    public World getWorld() {
        return world;
    }

    public void setLevel(int i) {
        level = i;
    }

    public boolean isCurrentLevel2(World world) {
        return true;
    }

    public boolean isCurrentLevel3(World world) {
        return true;
    }

    public boolean isCurrentLevel4(World world) {
        return true;
    }

    public void levelPopulation() {
        world.populate(this);

        view.setWorld(world);

        world.setGravity(0);

        keyboardInput.setPlayer(world.getPlayer());

        world.start();
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }

}
