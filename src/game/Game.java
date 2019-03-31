package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author Ivan, Bosch, ivan.bosch@city.ac.uk
 * @version 28
 * @since 1
 */
public class Game {

    //DECLARATIONS OF ALL THE DIFFERENT DATA USED
    /**
     * Declaring a world
     */
    private GameLevel world;
    /**
     * Declaring a view
     */
    private UserView view;
    /**
     * Declaring level which will change according to the level in which the player is
     */
    private int level;
    /**
     * boolean is this level completed
     */
    private boolean level2WasCompleted;
    /**
     * boolean is this level completed
     */
    private boolean level3WasCompleted;
    /**
     * boolean is this level completed
     */
    private boolean level4WasCompleted;
    /**
     * A keyboardInput object so that the player is able to move
     */
    private KeyboardInput keyboardInput;
    /**
     * Declaring a music for battle scenes
     */
    private SoundClip battleMusic;
    /**
     * Declaring a start of the game music
     */
    private SoundClip gameMusic;
    /**
     * Declaring a loading music
     */
    private SoundClip loadingMusic;
    /**
     * Declaring a end game music
     */
    private SoundClip winMusic;
    /**
     * Boolean is battle music being played
     */
    private boolean isBattleMusic;
    /**
     * Boolean is win music being played
     */
    private boolean isWinMusic;
    /**
     * Boolean is loading music being played
     */
    private boolean isLoadingMusic;
    /**
     * A PlayerData class object in which the player data is stored
     */
    private PlayerData data;

    /**
     * Starting state of the game
     * <p>
     *     How the game starts, the world, frame and view are initialized, the player keyboard inputs are linked into
     *     the frame and also the mainPanel buttons are as well. The world gravity is set to 0 since the game happens in space.
     * </p>
     */
    public Game() {
        // make the world

        level = 1;

        //start on level1
        world = new Level1();
        world.populate(this,3);

        data = new PlayerData(world.getPlayer());
        data.setHealth(3);

        //make view
        view = new MyView(world, 500, 500, this);

        // display the view in EnemiesCollision frame
        final JFrame frame = new JFrame("Stardust Crusaders");

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.add(view);
        frame.setResizable(false);
        frame.pack();

        MainPanel buttons = new MainPanel(this);
        frame.add(buttons.getMainPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);

        keyboardInput = new KeyboardInput(world.getPlayer());
        frame.addKeyListener(keyboardInput);

        frame.requestFocus();

        world.setGravity(0);

        // uncomment this to make EnemiesCollision debugging view
        //JFrame debugView = new DebugViewer(world, 500, 500);

        // start!
        world.start();
    }

    /**
     * Proceed to next level
     * <p>
     *     Player goes to next level the level is populated here and the level is set.
     * </p>
     * @param i the level that the player proceeds to
     */
    // go to next level if the requirement are met in each different level
    public void goNextLevel(int i) {
        world.stop();

        gameMusic.stop();

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

    /**
     * Go back to the level selector (level 1).
     *<p>
     *     This method will return you to the first level from levels 2, 3 and 4. If the player health is 0 it will reset
     *     the score and the level will not be set as completed meaning its going to appear in level 1. Else if player health is above 0
     *     it will keep health as it is and set the level as completed and transfer all the points to total score.
     *</p>
     */
    //Go back to level 1 in case of death or level completion
    public void goLevel1() {

        if(data.getHealth() > 0) {
            if (level == 2) {
                setLevel2WasCompleted(true);
                data.setScore(data.getScore() + world.getPlayer().getCount());
                data.setHealth(world.getPlayer().getHealth());
            } else if (level == 3) {
                setLevel3WasCompleted(true);
                data.setScore(data.getScore() + world.getPlayer().getCount());
                data.setHealth(world.getPlayer().getHealth());
            } else if (level == 4) {
                setLevel4WasCompleted(true);
                data.setScore(data.getScore() + world.getPlayer().getCount());
                data.setHealth(world.getPlayer().getHealth());
            }
        } else {
            data.setScore(0);
            data.setHealth(3);
        }

        world.stop();

        setLevel(1);

        world = new Level1();

        levelPopulation();

    }

    /**
     * Go back to level 1
     * <p>
     *     This method is usually used to restart a player progression on the level it will allow the user to go back to level 1
     *     by clicking on a button.
     * </p>
     */
    //Go back to level 1 if the player wants to
    public void restart() {
        if (level != 1) {
            data.setHealth(3);

            world.stop();

            setLevel(1);

            world = new Level1();

            levelPopulation();
        } else {
            getWorld().getPlayer().setPosition(new Vec2(0,0));
        }
    }

    /**
     * Return GameLevel type object world
     * @return world
     */
    //ALL SETTERS AND GETTERS USED TO MANAGE DIFFERENT DATA FROM HERE ONWARDS AND SOME BOOLEANS
    public GameLevel getWorld() {
        return world;
    }

    public void setWorld(GameLevel world) {
        this.world = world;
    }

    /**
     * setLevel to a particular int
     * @param i set level to int i
     */
    public void setLevel(int i) {
        level = i;
    }

    /**
     * Get int type level
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Level population usually used when populating a new world in order to avoid code duplication
     */
    public void levelPopulation() {
        world.populate(this, data.getHealth());

        view.setWorld(world);

        world.setGravity(0);

        keyboardInput.setPlayer(world.getPlayer());

        world.start();
    }

    /**
     * Get boolean type was level 2 completed
     * @return boolean to know the state of completion of a level
     */
    public boolean getLevel2WasCompleted() {
        return level2WasCompleted;
    }

    /**
     * Set boolean type to change the completion of level 2
     * @param level2WasCompleted boolean that checks if the level was completed
     */
    public void setLevel2WasCompleted(boolean level2WasCompleted) {
        this.level2WasCompleted = level2WasCompleted;
    }

    /**
     * Get boolean type was level 2 completed
     * @return boolean to know the state of completion of a level
     */
    public boolean getLevel3WasCompleted() {
        return level3WasCompleted;
    }

    /**
     * Set boolean type to change the completion of level 3
     * @param level3WasCompleted boolean that checks if level 3 was completed
     */
    public void setLevel3WasCompleted(boolean level3WasCompleted) {
        this.level3WasCompleted = level3WasCompleted;
    }

    /**
     * Get boolean type was level 2 completed
     * @return boolean to know the state of completion of a level
     */
    public boolean getLevel4WasCompleted() {
        return level4WasCompleted;
    }

    /**
     * Set boolean type to change the completion of level 4
     * @param level4WasCompleted boolean that checks if level 4 was completed
     */
    public void setLevel4WasCompleted(boolean level4WasCompleted) {
        this.level4WasCompleted = level4WasCompleted;
    }

    /**
     * Method is used to play the battle music and set the boolean isBattleMusic true if the loading music is playing it will stop it
     */
    public void playBattleMusic() {
        if (isLoadingMusic == true) {
            loadingMusic.stop();
            setLoadingMusic(false);
        }
        try {
            battleMusic = new SoundClip("data/SpaceMusicPack/battle.wav");
            battleMusic.loop();
            battleMusic.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
        }
        setBattleMusic(true);

    }

    /**
     * Method is used to play the music at the first level.
     * <p>
     *     The first time the game is launched it will play a song and when it comes back from a different level it will play another.
     * </p>
     */
    public void playInitialMusic(){
        if (isBattleMusic == false) {
            try {
                gameMusic = new SoundClip("data/SpaceMusicPack/in-the-wreckage.wav");
                gameMusic.loop();
                gameMusic.setVolume(0.4);
            } catch (UnsupportedAudioFileException| IOException| LineUnavailableException e) {
                System.out.println(e);
            }
        } else {
            battleMusic.stop();
            setLoadingMusic(true);
            try {
                loadingMusic = new SoundClip("data/SpaceMusicPack/loading.wav");
                loadingMusic.loop();
            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Method is used to play music when all levels are completed
     * <p>
     *     When all levels are completed and there are no stars in the first level this music will be played.
     * </p>
     */
    public void playWinningMusic() {
            loadingMusic.stop();
            setWinMusic(true);
            try {
                winMusic = new SoundClip("data/SpaceMusicPack/meet-the-princess.wav");
                winMusic.loop();
                winMusic.setVolume(0.3);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }
    }

    /**
     * Set boolean for when the specified music is being played can be set to true
     * @param battleMusic boolean that checks if the battle music is on
     */
    public void setBattleMusic(boolean battleMusic) {
        isBattleMusic = battleMusic;
    }

    /**
     * Set boolean for when the specified music is being played can be set to true
     * @param loadingMusic boolean that checks if the loading music is on
     */
    public void setLoadingMusic(boolean loadingMusic) {
        isLoadingMusic = loadingMusic;
    }

    /**
     * Set boolean for when the specified music is being played can be set to true
     * @param winMusic boolean that checks if win music is on
     */
    public void setWinMusic (boolean winMusic) {
        isWinMusic = winMusic;
    }

    /**
     * Getter to check id the win music is being played
     * @return isWinMusic boolean either true or false
     */
    public boolean isWinMusic() {
        return isWinMusic;
    }

    /**
     * Getter for the PlayerData type data so that the different information stored can be moved from level to level and modified.
     * @return player data such as health or score
     */
    public PlayerData getData() {
        return data;
    }

    /** Run the game.
     * @param args args
     * */
    //Execution of the game
    public static void main(String[] args) {

        new Game();
    }

}
