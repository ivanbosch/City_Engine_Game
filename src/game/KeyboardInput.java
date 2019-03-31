package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyboardInput extends KeyAdapter {
    private static final float WALKING_SPEED = 9.0f;
    private Player player;
    private static SoundClip playerShoot;


    public KeyboardInput(Player player) {
        this.player = player;
    }

    //shooting music is only loaded once
    static {
        try {
            playerShoot = new SoundClip("data/SpaceMusicPack/fx/playerShoot.wav");
        }
        catch (UnsupportedAudioFileException| IOException| LineUnavailableException e) {
            System.out.println(e);
        }
    }

    @Override
    //Key presses to walk and move the player
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

    //method for when the body moves changes the images and speed of the body
    private void moveBody(float speedX, float speedY) {
        player.stopIdleImage();
        player.setLinearVelocity(new Vec2(speedX, speedY)); // move
        player.removeAllImages();
        player.addImage(new BodyImage("data/Spaceship_Assets/Spaceship3.png",2));
    }

    @Override
    //on key releases stop movements on certain axis
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
        } else if (code == KeyEvent.VK_M && player.getHealth() != 0) { //shoot on key release so it can be hold and its not game breaking
            Bullet bullet = new Bullet(player.getWorld());
            playerShoot.play();
            playerShoot.setVolume(0.2);
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