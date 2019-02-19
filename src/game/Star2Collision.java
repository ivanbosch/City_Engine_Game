package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class Star2Collision implements CollisionListener {
    private Star2 star2;

    public Star2Collision(Star2 star2) {
        this.star2 = star2;
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
        star2.decreaseStarHealth();
        star2.star2Death();
        star2.setLinearVelocity(new Vec2(0,0));
        star2.setPosition(new Vec2(0,9));
    }
}
