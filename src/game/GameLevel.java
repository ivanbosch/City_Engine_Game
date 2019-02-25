package game;

import city.cs.engine.World;

import org.jbox2d.common.Vec2;

import java.util.Timer;

public abstract class GameLevel extends World {
    private Player spaceship;
    private Timer timer;

    public GameLevel() {
        this.timer = new Timer();
    }

    public Player getPlayer() {
        return spaceship;
    }

    public Timer getTimer() {
        return timer;
    }

    public void populate(Game game) {
        spaceship = new Player(this , 3);
        spaceship.setPosition(playerStartPosition());
        spaceship.addCollisionListener(new PlayerCollision(spaceship));
    }

    public abstract Vec2 playerStartPosition();

    public abstract Vec2 enemyStartPosition();

    public abstract boolean isCompleted();

}
