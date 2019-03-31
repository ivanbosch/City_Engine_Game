package game;

import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level2 extends GameLevel {
    private List<Enemy> enemies = new ArrayList<>();
    private static final int ENEMIES_KILLED = 3;

    @Override
    public void populate(Game game, int playerHealth) {
        super.populate(game, playerHealth);

        game.playBattleMusic();

        //make enemies with health
        for (int i = 1; i <4; i++) {
            Enemy enemy = new Enemy(this, 1);
            enemy.setPosition(new Vec2(i*-3, 8+(i*2)));
            //we do get player because we dont declare a new player here
            enemy.move(new Vec2(getPlayer().getPosition().x, getPlayer().getPosition().y));
            enemy.addCollisionListener(new EnemiesCollision(enemy, game));
            enemies.add(enemy);
        }
        //link step listener to this enemies
        this.addStepListener(new MovementListener(this,enemies,getPlayer()));
    }

    @Override
    public Vec2 playerStartPosition() {
        return new Vec2(0,-5);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCount() >= ENEMIES_KILLED;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

}
