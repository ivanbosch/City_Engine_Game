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
            Enemy enemy = new Enemy(this, 3);
            enemy.setPosition(new Vec2(i*3-9, 6+i*2));
            //we do get player because we dont declare a new player here
            enemy.move(new Vec2(getPlayer().getPosition().x, getPlayer().getPosition().y));
            enemy.addCollisionListener(new EnemiesCollision(enemy));
            enemies.add(enemy);
        }
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
