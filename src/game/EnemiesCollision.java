package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class EnemiesCollision implements CollisionListener {
    private Enemy enemy;
    private Game game;

    public EnemiesCollision(Enemy enemy, Game game) {
        this.enemy = enemy;
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            enemy.decreaseHealth();
        } else if (e.getOtherBody() instanceof Bullet) {
            enemy.decreaseHealth();
//            if (enemy.getHealth() == 0 && game.isCurrentLevel2(enemy.getWorld())) {
//                enemy.destroy();
//                Level2 level2 = (Level2) enemy.getWorld();
//                level2.getEnemies().remove(enemy);
//            }

//            if (enemy.getHealth() == 0 && game.isCurrentLevel3(enemy.getWorld())) {
//                enemy.destroy();
//                Level3 level3 = (Level3) enemy.getWorld();
//                level3.getEnemies().remove(enemy);
//            }
            if (enemy.getHealth() == 0 && game.isCurrentLevel4(enemy.getWorld())) {
                enemy.destroy();
                Level4 level4 = (Level4) enemy.getWorld();
                level4.getEnemies().remove(enemy);
            }
            e.getOtherBody().destroy();
        } else if (e.getOtherBody() instanceof EnemyBullet) {
            enemy.setLinearVelocity(new Vec2(enemy.getWalkingSpeed(), 0));
        }
    }
}
