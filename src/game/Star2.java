package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Star2 extends DynamicBody {

    private int health;
    private static final Shape circleShape = new CircleShape(3);
    private static final BodyImage starImage = new BodyImage("data/stars/star_orange01.png", 9);
    private Game game;

    //Star object when it's destroy it initialises a different level, acts as level selector
    public Star2(World world, int health, int x, int y, Game game) {
        super(world, circleShape);
        this.addImage(starImage);
        this.health = health;
        this.setPosition(new Vec2(x,y));
        this.game = game;
    }

    public void decreaseStarHealth() {
        health--;
    }

    //When the object is destroy starts new level
    public void star2Death() {
        if (health == 0) {
            this.destroy();
            game.goNextLevel(3);
        }
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
