package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler extends MouseAdapter {

    private static final float RADIUS = 0.5f;

    private static final Shape projectileShape
            = new CircleShape(RADIUS);

    private static final BodyImage projectileImage
            = new BodyImage("data/stars/star_white01.png", 2*RADIUS);
    private static final float PROJECTILE_VELOCITY = 8;

    private WorldView view;
    private Walker player;

    public MouseHandler(WorldView view) {
        this.view = view;
    }

    /**
     * Create EnemiesCollision bowling ball at the current mouse position.
     * @param e event object containing the mouse position
     */
    public void mousePressed(MouseEvent e) {

    }
}
