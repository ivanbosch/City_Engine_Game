package game;

import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

public class Level1 extends GameLevel {
    private Star1 star1;
    private Star2 star2;
    private Star3 star3;
    private static final int ENEMIES_KILLED = 0;
    private SoundClip winMusic;


    @Override
    public void populate(Game game) {


        game.playInitialMusic();

        //initialisation of stars

        if (game.getLevel2WasCompleted() == false) {
            star1 = new Star1(this, 5, -8, 9, game);
            star1.addCollisionListener(new Star1Collision(star1));
        } else {

        }
        //after broken go to level 1

        if (game.getLevel3WasCompleted() == false) {
            star2 = new Star2(this, 5, 0, 9, game);
            star2.addCollisionListener(new Star2Collision(star2));
        } else {

        }

        //after broken go to level 2

        if (game.getLevel4WasCompleted() == false) {
            star3 = new Star3(this, 5, 8, 9, game);
            star3.addCollisionListener(new Star3Collision(star3));
        } else {

        }

        //after broken go to level 3

        if (game.getLevel4WasCompleted() == true && game.getLevel3WasCompleted() == true && game.getLevel2WasCompleted() == true) {
            game.playWinningMusic();
        }
    }

    @Override
    public Vec2 playerStartPosition() {
        return new Vec2(0,-5);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCount() >= ENEMIES_KILLED;
    }

}



