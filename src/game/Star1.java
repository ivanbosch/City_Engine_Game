package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Star1 extends DynamicBody {

    private int health;
    private static final Shape circleShape = new CircleShape(3);
    private static final BodyImage starImage = new BodyImage("data/stars/star_blue01.png", 9);

    public Star1(GameWorld world, int health, int x, int y) {
        super(world, circleShape);
        this.addImage(starImage);
        this.health = health;
        this.setPosition(new Vec2(x,y));
    }

    public void decreaseStarHealth() {
        health--;
    }

    public void star1Death() {
        if (health == 0) {
            this.destroy();
            Level1 level1 = new Level1();
        }

    }
}
