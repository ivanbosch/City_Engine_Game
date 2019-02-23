package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class PlayerCollision implements CollisionListener {
    private Player spaceship;

    public PlayerCollision(Player spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public void collide(CollisionEvent e) {

        if (e.getOtherBody() instanceof Enemy) {
            collision();
        }
        else if (e.getOtherBody() instanceof EnemyBullet) {
            collision();
        }
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

    private void safeCollision () {
        spaceship.setAngularVelocity(0);
        spaceship.setAngle(0);
    }

    private void collision () {
        spaceship.decreaseHealth();
        spaceship.death();
        spaceship.setAngularVelocity(0);
        spaceship.setAngle(0);
    }
}
