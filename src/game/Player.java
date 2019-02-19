package game;

import city.cs.engine.*;

import java.util.Timer;


public class Player extends DynamicBody implements IdleImageInterface {

    private int health;
    private static final Shape playerShape = new PolygonShape(0.8f, 0.6f, 0, 0.8f, -0.8f, 0.6f, -0.7f, -0.5f, 0.7f, -0.5f);
    private static final BodyImage playerImage = new BodyImage("data/Spaceship_Assets/Spaceship1.png",2);
    private static final float angularVelocity = 0;
    private Timer timer;
    private IdleImageTask idleImageTask;

    public Player(GameWorld world, int health){
        super(world, playerShape);
        this.addImage(playerImage);
        this.health = health;
        this.timer = world.getTimer();
        this.startIdleImage();
        this.setAngularVelocity(angularVelocity);
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
        }
    }
}
