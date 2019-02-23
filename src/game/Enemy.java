package game;
import city.cs.engine.*;
import java.util.Timer;

public class Enemy extends Walker {
    private int health;
    private static final Shape enemyShape = new PolygonShape(0.8f, 0.4f, -0.8f, 0.4f, -0.7f, -0.4f, 0, -0.7f, 0.7f, -0.4f);
    private static final BodyImage enemyImage = new BodyImage("data/Spaceship_Assets/enemy.png",2);

    private static float WALKING_SPEED = -5;

    public Enemy(World world, int health){
        super(world, enemyShape);
        addImage(enemyImage);
        this.health = health;
        this.startWalking(WALKING_SPEED);
    }


    public static float getWalkingSpeed() {
        return WALKING_SPEED;
    }

    public static void setWalkingSpeed(float walkingSpeed) {
        WALKING_SPEED = walkingSpeed;
    }
}


