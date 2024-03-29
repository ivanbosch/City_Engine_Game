package game;

import city.cs.engine.BodyImage;

import java.util.TimerTask;

public class IdleImageTask extends TimerTask {

    //initialisation of images
    private static final BodyImage image1 = new BodyImage("data/Spaceship_Assets/Spaceship1.png",2);
    private static final BodyImage image2 = new BodyImage("data/Spaceship_Assets/Spaceship2.png",2);
    private Player player;

    private boolean isImage1;

    public IdleImageTask(Player player) {
        this.player = player;
    }

    //on every timer tick change the image from one to another
    public void run() {
        if (this.isImage1) {
            this.setImage(image1);
            this.isImage1 = false;
        } else {
            this.setImage(image2);
            this.isImage1 = true;
        }
    }

    //image setter
    private void setImage(BodyImage image) {
        player.removeAllImages();
        player.addImage(image);
    }
}
