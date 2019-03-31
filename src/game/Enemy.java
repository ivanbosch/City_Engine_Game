package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.Timer;

public class Enemy extends Walker {
    private int health;
    private static final Shape enemyShape = new PolygonShape(0.8f, 0.4f, -0.8f, 0.4f, -0.7f, -0.4f, 0, -0.7f, 0.7f, -0.4f);
    private static final BodyImage enemyImage = new BodyImage("data/Spaceship_Assets/enemy.png",2);

    private static float WALKING_SPEED = -5;

    //enemy characteristics
    public Enemy(World world, int health){
        super(world, enemyShape);
        addImage(enemyImage);
        this.health = health;
        this.startWalking(WALKING_SPEED);
    }


    //enemy moving speed
    public static float getWalkingSpeed() {
        return WALKING_SPEED;
    }

    public static void setWalkingSpeed(float walkingSpeed) {
        WALKING_SPEED = walkingSpeed;
    }

    //enemy health goes down on collisions
    public void decreaseHealth() {
        health--;
        this.setLinearVelocity(new Vec2(WALKING_SPEED,0));
        System.out.println(this.getHealth());
    }

    public int getHealth() {
        return health;
    }
}


