package game;

import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


public class Level1 extends GameLevel {
    private Player spaceship;
    private Star1 star1;
    private Star2 star2;
    private Star3 star3;
    private List<Enemy> enemies = new ArrayList<>();
    private Timer timer;
    private static final int ENEMIES_KILLED = 0;

    public Level1() {
        super();

        //initialise timer in the gameWorld which is controlled by TimerTasks
        this.timer = new Timer();

        //initialisation of the player
        spaceship = new Player(this, 3);
        spaceship.setPosition(startPosition());
        spaceship.addCollisionListener(new PlayerCollision(spaceship));

        //initialisation of stars

        star1 = new Star1(this,5,-8,9);
        star1.addCollisionListener(new Star1Collision(star1));
        //after broken go to level 1, it happens in the collision listener?

        star2 = new Star2(this,5,0,9);
        star2.addCollisionListener(new Star2Collision(star2));
        //after broken go to level 2

        star3 = new Star3(this,5,8,9);
        star3.addCollisionListener(new Star3Collision(star3));
        //after broken go to level 3

        /*
        for (int i = 1; i <4; i++) {
            Enemy enemy = new Enemy(this, 3);
            enemy.setPosition(new Vec2(i*3-9, 6+i*2));
            //we do get player because we dont declare a new player here
            enemy.move(new Vec2(getPlayer().getPosition().x, getPlayer().getPosition().y));
            enemy.addCollisionListener(new EnemiesCollision(enemy));
            enemies.add(enemy);
        }
        this.addStepListener(new MovementListener(this,enemies,spaceship));
        */

    }

    public Player getPlayer() {
        return spaceship;
    }

    public Timer getTimer() {
        return timer;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0,0);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCount() >= ENEMIES_KILLED;
    }
}



