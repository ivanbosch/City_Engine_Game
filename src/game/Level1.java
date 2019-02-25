package game;

import org.jbox2d.common.Vec2;

import java.util.Timer;


public class Level1 extends GameLevel {
    private Star1 star1;
    private Star2 star2;
    private Star3 star3;
    private static final int ENEMIES_KILLED = 0;

    @Override
    public void populate(Game game) {
        super.populate(game);

        //initialise timer in the gameWorld which is controlled by TimerTasks

        //initialisation of stars

        star1 = new Star1(this,5,-8,9, game);
        star1.addCollisionListener(new Star1Collision(star1));
        //after broken go to level 1, it happens in the collision listener?

        star2 = new Star2(this,5,0,9, game);
        star2.addCollisionListener(new Star2Collision(star2));
        //after broken go to level 2

        star3 = new Star3(this,5,8,9, game);
        star3.addCollisionListener(new Star3Collision(star3));
        //after broken go to level 3

    }


    @Override
    public Vec2 playerStartPosition() {
        return new Vec2(0,0);
    }

    @Override
    public Vec2 enemyStartPosition() {
        return new Vec2(3-9,6-12);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCount() >= ENEMIES_KILLED;
    }
}



