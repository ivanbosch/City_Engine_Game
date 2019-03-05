package game;

import city.cs.engine.*;

import javax.sound.sampled.Control;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game {

    private GameLevel world;
    private UserView view;
    private int level;
    private boolean level2WasCompleted;
    private boolean level3WasCompleted;
    private boolean level4WasCompleted;
    private KeyboardInput keyboardInput;
    private SoundClip battleMusic;
    private SoundClip gameMusic;
    private SoundClip loadingMusic;
    private SoundClip winMusic;
    private boolean isBattleMusic;
    private boolean isWinMusic;

    private boolean isLoadingMusic;

    public Game() {

        // make the world
        level = 1;

        //start on level1
        world = new Level1();
        world.populate(this);

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
//
//        ControlPanel buttons = new ControlPanel(this);
//        frame.add(buttons.getMainPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);

        frame.requestFocus();

        keyboardInput = new KeyboardInput(world.getPlayer());
        frame.addKeyListener(keyboardInput);



        world.setGravity(0);

        // uncomment this to make EnemiesCollision debugging view
        //JFrame debugView = new DebugViewer(world, 500, 500);

        // start!
        world.start();
    }

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

    public void goLevel1() {

        if(world.getPlayer().getHealth() != 0) {
            if (level == 2) {
                setLevel2WasCompleted(true);
            } else if (level == 3) {
                setLevel3WasCompleted(true);
            } else if (level == 4) {
                setLevel4WasCompleted(true);
            }
        }

        world.stop();

        setLevel(1);

        world = new Level1();

        levelPopulation();
    }

    public UserView getView() {
        return view;
    }

    public GameLevel getWorld() {
        return world;
    }

    public void setLevel(int i) {
        level = i;
    }

    public int getLevel() {
        return level;
    }

    public void levelPopulation() {
        world.populate(this);

        view.setWorld(world);

        world.setGravity(0);

        keyboardInput.setPlayer(world.getPlayer());

        world.start();
    }

    public boolean getLevel2WasCompleted() {
        return level2WasCompleted;
    }

    public void setLevel2WasCompleted(boolean level2WasCompleted) {
        this.level2WasCompleted = level2WasCompleted;
    }

    public boolean getLevel3WasCompleted() {
        return level3WasCompleted;
    }

    public void setLevel3WasCompleted(boolean level3WasCompleted) {
        this.level3WasCompleted = level3WasCompleted;
    }

    public boolean getLevel4WasCompleted() {
        return level4WasCompleted;
    }

    public void setLevel4WasCompleted(boolean level4WasCompleted) {
        this.level4WasCompleted = level4WasCompleted;
    }

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

    public void setBattleMusic(boolean battleMusic) {
        isBattleMusic = battleMusic;
    }

    public void setLoadingMusic(boolean loadingMusic) {
        isLoadingMusic = loadingMusic;
    }

    public void setWinMusic (boolean winMusic) {
        isWinMusic = winMusic;
    }

    public boolean isWinMusic() {
        return isWinMusic;
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }

}
