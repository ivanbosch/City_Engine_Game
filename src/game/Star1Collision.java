package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class Star1Collision implements CollisionListener {
    private Star1 star1;

    public Star1Collision (Star1 star1) {
        this.star1 = star1;
    }

    @Override
    //Collision against player and player's bullets
    public void collide (CollisionEvent e) {
        if (e.getOtherBody() instanceof Bullet) {
            star1.decreaseStarHealth();
            collision();
            e.getOtherBody().destroy();
        } else if (e.getOtherBody() instanceof Player) {
            star1.setHealth(0);
            collision();
        }
    }

    public void collision () {
        star1.setLinearVelocity(new Vec2(0,0));
        star1.setPosition(new Vec2(-8,9));
        star1.star1Death();
    }
}
