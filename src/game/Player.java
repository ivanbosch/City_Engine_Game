package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Timer;


public class Player extends DynamicBody implements IdleImageInterface {

    private int health;
    private static final Shape playerShape = new PolygonShape(0.8f, 0.6f, 0, 0.8f, -0.8f, 0.6f, -0.7f, -0.5f, 0.7f, -0.5f);
    private static final BodyImage playerImage = new BodyImage("data/Spaceship_Assets/Spaceship1.png",2);
    private static final float angularVelocity = 0;
    private Timer timer;
    private IdleImageTask idleImageTask;
    private static SoundClip deathScream;
    private int killCount;
    private Game game;

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

    static {
        try {
            deathScream = new SoundClip("data/SpaceMusicPack/fx/scream.wav");
        } catch (UnsupportedAudioFileException| IOException| LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public void startIdleImage() {
        this.idleImageTask = new IdleImageTask(this);
        this.timer.schedule(this.idleImageTask, 0, 1000);
    }

    public void stopIdleImage() {
        this.idleImageTask.cancel();
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth () {
        health--;
        System.out.println("Focus improve, you still have " + health + " lives");
    }

    public void death () {
        if (health == 0) {
            this.destroy();
            deathScream.play();
            game.goLevel1();
        }
    }

    public int getCount() {
        return 100*killCount;
    }

    public void addOne() {
        killCount++;
    }
}
