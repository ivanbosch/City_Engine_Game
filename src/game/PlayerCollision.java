package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class PlayerCollision implements CollisionListener {
    private Player spaceship;
    private Game game;

    public PlayerCollision(Player spaceship, Game game) {
        this.spaceship = spaceship;
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {

        // collision against an enemy
        if (e.getOtherBody() instanceof Enemy) {
            collision();
        }
        //collision against enemy bullet
        else if (e.getOtherBody() instanceof EnemyBullet) {
            collision();
            System.out.println(game.getData().getHealth());
        }
        //Collision against stars
        else if (e.getOtherBody() instanceof Star1) {
            safeCollision();
        }
        else if (e.getOtherBody() instanceof Star2) {
            safeCollision();
        }
        else if (e.getOtherBody() instanceof Star3) {
            safeCollision();
        }
    }

    //Collision that doesnt affect player's health
    private void safeCollision () {
        spaceship.setAngularVelocity(0);
        spaceship.setAngle(0);
    }

    //Collision that affects player's health
    private void collision () {
        spaceship.decreaseHealth();
        spaceship.death();
        spaceship.setAngularVelocity(0);
        spaceship.setAngle(0);
    }
}
