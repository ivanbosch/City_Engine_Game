package game;

import org.jbox2d.common.Vec2;
import java.util.ArrayList;
import java.util.List;

public class Level1 extends GameWorld {
    private List<Enemy> enemies = new ArrayList<>();
    private boolean compleated;

    public Level1() {
        super();


// no need to create a new object because we want the one from GameWorl
// there is only one player object that goes around the levels and always keep the same attributes

//        spaceship.setPosition(new Vec2(0,0));
//        spaceship.addCollisionListener(new PlayerCollision(spaceship));

//enemies are not created in game world so we create them
        for (int i = 1; i <4; i++) {
            Enemy enemy = new Enemy(this, 3);
            enemy.setPosition(new Vec2(i*3-9, 6+i*2));
            //we do get player because we dont declare a new player here
            enemy.move(new Vec2(getPlayer().getPosition().x, getPlayer().getPosition().y));
            enemy.addCollisionListener(new EnemiesCollision(enemy));
            enemies.add(enemy);
        }


        if (compleated == true) {
            // go back to GameWorld
        }

    }
}
