package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class EnemiesCollision implements CollisionListener {
    private Enemy enemy;

    public EnemiesCollision(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            enemy.destroy();
        }

        else if (e.getOtherBody() instanceof Bullet) {
            enemy.destroy();
            e.getOtherBody().destroy();
        }
    }
}
