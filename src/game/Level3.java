package game;

import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

public class Level3 extends GameLevel {
    private List<Enemy> enemies = new ArrayList<>();
    private static final int ENEMIES_KILLED = 5;

    @Override
    public void populate(Game game, int playerHealth) {
        super.populate(game, playerHealth);

        game.playBattleMusic();

        //make enemies
        for (int i = 1; i <6; i++) {
            Enemy enemy = new Enemy(this, 3);
            enemy.setPosition(new Vec2(-8+(3*i), 4+(i*2)));
            //we do get player because we dont declare a new player here
            enemy.move(new Vec2(getPlayer().getPosition().x, getPlayer().getPosition().y));
            enemy.addCollisionListener(new EnemiesCollision(enemy, game));
            enemies.add(enemy);
        }
        this.addStepListener(new MovementListener(this,enemies,getPlayer()));
    }

    @Override
    public Vec2 playerStartPosition() {
        return new Vec2(0,-5);
    }

    public boolean isCompleted() {
        return getPlayer().getCount() >= ENEMIES_KILLED;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
