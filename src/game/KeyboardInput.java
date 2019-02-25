package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {
    private static final float WALKING_SPEED = 9.0f;
    private Player player;


    public KeyboardInput(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            this.moveBody(0, WALKING_SPEED); //move forward
        } else if (code == KeyEvent.VK_S) {
            this.moveBody(0,-WALKING_SPEED); //move backwards
        } else if (code == KeyEvent.VK_A) {
            this.moveBody(-WALKING_SPEED,0); //move left
        } else if (code == KeyEvent.VK_D) {
            this.moveBody(WALKING_SPEED,0); //move right
        }
    }

    private void moveBody(float speedX, float speedY) {
        player.stopIdleImage();
        player.setLinearVelocity(new Vec2(speedX, speedY)); // move
        player.removeAllImages();
        player.addImage(new BodyImage("data/Spaceship_Assets/Spaceship3.png",2));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_D) {
            this.stopBody(0,player.getLinearVelocity().y);
        } else if (code == KeyEvent.VK_A) {
            this.stopBody(0,player.getLinearVelocity().y);
        } else if (code == KeyEvent.VK_W) {
            this.stopBody(player.getLinearVelocity().x,0);
        } else if (code == KeyEvent.VK_S) {
            this.stopBody(player.getLinearVelocity().x,0);
        } else if (code == KeyEvent.VK_M) {
            Bullet bullet = new Bullet(player.getWorld());
            bullet.setPosition(new Vec2(player.getPosition().x,player.getPosition().y+1.5f));
            bullet.setLinearVelocity(new Vec2(0,20));
        }

    }

    //Stop moving method used in key releases to stop the body
    private void stopBody(float stopX, float stopY) {
        player.setLinearVelocity(new Vec2(stopX,stopY));
        player.startIdleImage();
        player.removeAllImages();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}