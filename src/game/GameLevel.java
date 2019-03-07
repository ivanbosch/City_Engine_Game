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

    public void populate(Game game, int playerHealth) {
        spaceship = new Player(this , playerHealth, game);
        spaceship.setPosition(playerStartPosition());
        spaceship.addCollisionListener(new PlayerCollision(spaceship, game));
    }

    public abstract Vec2 playerStartPosition();

    public abstract boolean isCompleted();
}
