package game;

import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

public class Level2 extends GameLevel {
    private List<Enemy> enemies = new ArrayList<>();
    private static final int ENEMIES_KILLED = 3;

    @Override
    public void populate(Game game) {
        super.populate(game);

        //make enemies
        for (int i = 1; i <4; i++) {
            Enemy enemy = new Enemy(this, 4);
            enemy.setPosition(enemyStartPosition());
            //we do get player because we dont declare a new player here
            enemy.move(new Vec2(getPlayer().getPosition().x, getPlayer().getPosition().y));
            enemy.addCollisionListener(new EnemiesCollision(enemy, game));
            enemies.add(enemy);
        }
        this.addStepListener(new MovementListener(this,enemies,getPlayer()));
    }

    @Override
    public Vec2 playerStartPosition() {
        return new Vec2(0,0);
    }

    @Override
    public Vec2 enemyStartPosition() {
        return new Vec2(3-9,0-8);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCount() >= ENEMIES_KILLED;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
