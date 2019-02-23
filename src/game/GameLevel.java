package game;

import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public abstract class GameLevel extends World {
    private Player spaceship;

    public Player getPlayer() {
        return spaceship;
    }

    public void populate(Game game) {
        spaceship = new Player(this , 3);
        spaceship.setPosition(startPosition());

    }

    public abstract Vec2 startPosition();

    public abstract boolean isCompleted();
}
