package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Timer;

/**
 * @author Ivan, Bosch, ivan.bosch@city.ac.uk
 * @version 28
 * @since version 2
 */
public class Player extends DynamicBody implements IdleImageInterface {

    /**
     * The player health
     */
    private int health;
    /**
     * The shape of the player's body
     */
    private static final Shape playerShape = new PolygonShape(0.8f, 0.6f, 0, 0.8f, -0.8f, 0.6f, -0.7f, -0.5f, 0.7f, -0.5f);
    /**
     * The image set to the player's body
     */
    private static final BodyImage playerImage = new BodyImage("data/Spaceship_Assets/Spaceship1.png",2);
    /**
     * Set final angular velocity so the player doesnt rotate on collision
     */
    private static final float angularVelocity = 0;
    /**
     * Declares a timer
     */
    private Timer timer;
    /**
     * Declares a timerTask a action that happens according to a timer
     */
    private IdleImageTask idleImageTask;
    /**
     * Declares a SoundClip effect that happens every time the player dies
     */
    private static SoundClip deathScream;
    /**
     * Counter for every enemy kill of the player
     */
    private int killCount;
    /**
     * Declares a game
     */
    private Game game;

    /**
     * Initialization of the player.
     * <p>
     *     The player is initialised in a world, with a set amount of health and in a game. A image is added to the body
     *     and a timer is linked to the player so that it changes images while in idle. A player score its started at this time.
     * </p>
     * @param world A player exists in a world meaning a level and moves throughout the levels.
     * @param health The player has a certain amount of health that changes around according to different collisions.
     * @param game The player is created in a game, this is used to managed the sound fx and the moving through levels.
     */
    public Player(GameLevel world, int health, Game game){
        super(world, playerShape);
        this.addImage(playerImage);
        this.game = game;
        this.health = health;
        this.timer = world.getTimer();
        this.startIdleImage();
        this.setAngularVelocity(angularVelocity);
        killCount = 0;
    }
    //Death sound its only loaded once in the game
    static {
        try {
            deathScream = new SoundClip("data/SpaceMusicPack/fx/scream.wav");
        } catch (UnsupportedAudioFileException| IOException| LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Start idle animation.
     * <p>
     *     The idle animation is changed with a timer and rotates from one picture to another to add a more pleasing aesthetic.
     * </p>
     */
    public void startIdleImage() {
        this.idleImageTask = new IdleImageTask(this);
        this.timer.schedule(this.idleImageTask, 0, 1000);
    }

    /**
     * Stop idle image
     * <p>
     *     Stop the idle image, this method is usually called when the player is moving.
     * </p>
     */
    public void stopIdleImage() {
        this.idleImageTask.cancel();
    }

    /**
     * Health getter
     * @return The health of the player
     */
    public int getHealth() {
        return health;
    }

    /**
     * Decrease the player health by 1
     * <p>
     *     Decreasing the player health its usually called when the player suffers a collision against another body.
     * </p>
     */
    public void decreaseHealth () {
        health--;
        game.getData().setHealth(game.getData().getHealth()-1);
        System.out.println("Focus improve, you still have " + game.getData().getHealth() + " lives");
    }

    /**
     * Kill the player
     * <p>
     *     When a player gets to 0 health dies and goes back to level 1 with a penalisation in his points, they get reset
     *     and a death sound fx happens.
     * </p>
     */
    public void death () {
        if (game.getData().getHealth() == 0) {
            this.destroy();
            deathScream.play();
            game.goLevel1();
        }
    }

    /**
     * getter for the Player points
     * @return the amount of kills * 100 since every kill is 100 points
     */
    public int getCount() {
        return 100*killCount;
    }

    /**
     * Player kills + 1
     */
    public void addOne() {
        killCount++;
    }
}
