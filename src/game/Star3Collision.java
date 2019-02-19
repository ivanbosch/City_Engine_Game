package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class Star3Collision implements CollisionListener {
    private Star3 star3;

    public Star3Collision(Star3 star3) {
        this.star3 = star3;
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

    //When a star dies a different level starts

    public void collision () {
        star3.decreaseStarHealth();
        star3.star3Death();
        star3.setLinearVelocity(new Vec2(0,0));
        star3.setPosition(new Vec2(8,9));

    }
}
