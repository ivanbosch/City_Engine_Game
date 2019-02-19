package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;

import java.util.List;

public class MovementListener implements StepListener {
    private List<Enemy> enemies;
    private World world;
    private Player spaceship;
    private EnemyBullet enemyBullet;

    public MovementListener (World world, List<Enemy> enemies, Player spaceship) {
        this.world = world;
        this.enemies = enemies;
        this.spaceship = spaceship;
    }

    @Override
    public void preStep (StepEvent e) {
    }

    @Override
    public void postStep (StepEvent e) {
        for (Enemy enemy : enemies) {
            if (enemy.getPosition().x > 8) {
                enemy.startWalking(enemy.getWalkingSpeed());
            } else if (enemy.getPosition().x < -8) {
                enemy.startWalking(-enemy.getWalkingSpeed());
            }

            float enemyPosition = enemy.getPosition().x;
            float spaceshipPosition = spaceship.getPosition().x;

            if ((enemyPosition + 1) > spaceshipPosition && (enemyPosition + 0.9f) < spaceshipPosition) {
                enemyBullet = new EnemyBullet(world, enemy);
            } else if ((enemyPosition - 1) < spaceshipPosition && (enemyPosition - 0.9f) > spaceshipPosition) {
                enemyBullet = new EnemyBullet(world, enemy);
            }
        }
    }
}