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

    //enemy collision management against the Player and its bullets
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            enemy.decreaseHealth();
        } else if (e.getOtherBody() instanceof Bullet) {
            enemy.decreaseHealth();
            enemy.setLinearVelocity(new Vec2(enemy.getWalkingSpeed(), 0));

            //when an enemy health is 0 destroy it and take it out of the enemies list
            if (enemy.getHealth() == 0) {
                enemy.destroy();
                game.getWorld().getPlayer().addOne();
                if (game.getLevel() == 2) {
                    Level2 level2 = (Level2) enemy.getWorld();
                    level2.getEnemies().remove(enemy);
                    if (level2.getEnemies().isEmpty()) {
                        game.goLevel1();
                        System.out.println("level finished");
                    }
                }
                else if (game.getLevel() == 3) {
                    Level3 level3 = (Level3) enemy.getWorld();
                    level3.getEnemies().remove(enemy);
                    if (level3.getEnemies().isEmpty()) {
                        game.goLevel1();
                        System.out.println("level finished");
                    }
                }
                else if (game.getLevel() == 4) {
                    Level4 level4 = (Level4) enemy.getWorld();
                    level4.getEnemies().remove(enemy);
                    if (level4.getEnemies().isEmpty()) {
                        game.goLevel1();
                        System.out.println("level finished");
                    }
                }
            }

            e.getOtherBody().destroy();
        } else if (e.getOtherBody() instanceof EnemyBullet) {
            enemy.setLinearVelocity(new Vec2(enemy.getWalkingSpeed(), 0));
        }
    }
}
