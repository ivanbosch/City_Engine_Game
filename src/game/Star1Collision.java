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
    public void collide (CollisionEvent e) {
        if (e.getOtherBody() instanceof Bullet) {
            collision();
            e.getOtherBody().destroy();
        } else if (e.getOtherBody() instanceof Player) {
            collision();
        }
    }

    public void collision () {
        star1.decreaseStarHealth();
        star1.star1Death();
        star1.setLinearVelocity(new Vec2(0,0));
        star1.setPosition(new Vec2(-8,9));
    }
}
