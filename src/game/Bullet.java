package game;

import city.cs.engine.*;

public class Bullet extends DynamicBody {

    private static final Shape bulletShape = new CircleShape(0.5f);
    private static final BodyImage bulletImage = new BodyImage("data/stars/star_yellow01.png",1);

    //Player bullet
    public Bullet (World world) {
        super(world, bulletShape);
        addImage(bulletImage);
        this.setBullet(true);
    }
}
