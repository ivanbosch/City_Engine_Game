package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class EnemyBullet extends DynamicBody {
    private static final Shape bulletShape = new CircleShape(0.2f);
    private static final BodyImage bulletImage = new BodyImage("data/stars/star_orange01.png");

    public EnemyBullet (World world, Enemy enemy) {
        super(world, bulletShape);
        addImage(bulletImage);
        Vec2 position = enemy.getPosition();
        this.setPosition(new Vec2(position.x,position.y - 1.5f));
        this.setBullet(true);
        this.setLinearVelocity(new Vec2(0,-20));
        this.addCollisionListener(new BulletCollision());
    }
}
