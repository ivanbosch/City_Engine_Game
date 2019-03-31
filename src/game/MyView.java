package game;

import city.cs.engine.UserView;
import javax.swing.*;
import java.awt.*;

public class MyView extends UserView {
    private Image background;
    private Image backgroundLevel2;
    private Image backgroundLevel3;
    private Image backgroundLevel4;
    private Image healthIcon;
    private Game game;
    final Font font = new Font("TimesRoman",Font.PLAIN,30);

    public MyView(GameLevel world, int width, int height, Game game) {
        super(world, width,height);
        background = new ImageIcon("data/nebula/stars.png").getImage();
        backgroundLevel2 = new ImageIcon("data/nebula/nebula01.png").getImage();
        backgroundLevel3 = new ImageIcon("data/nebula/nebula04.png").getImage();
        backgroundLevel4 = new ImageIcon("data/nebula/nebula05.png").getImage();
        healthIcon = new ImageIcon("data/Spaceship_Assets/Spaceship1.png").getImage();
        this.game = game;
    }

    @Override
    //Manage different backgrounds on different levels
    protected void paintBackground (Graphics2D g){
        if (game.getLevel() == 1) {
            g.drawImage(background,0,0, 500, 500, this);
        }
        else if (game.getLevel() == 2) {
            g.drawImage(backgroundLevel2,0, 0,500,500,this);
        }
        else if (game.getLevel() == 3) {
            g.drawImage(backgroundLevel3,0,0,500,500,this);
        }
        else if (game.getLevel() == 4) {
            g.drawImage(backgroundLevel4,0,0,500,500,this);
        }
    }

    @Override
    protected void paintForeground (Graphics2D g) {
        //Players data
        g.setColor(Color.red);
        g.drawString("Level Score: " + game.getWorld().getPlayer().getCount(), 10,20);
        g.drawString("Total Score: " + game.getData().getScore(), 10, 32);
        //Player instructions on how to play the game
        g.drawString("Move with WASD", 405 , 438);
        g.drawString("Shoot with M", 420 , 450);

        //If winning music is being played display winning message
        if (game.isWinMusic() == true) {
            g.setFont(font);
            g.setColor(Color.lightGray);
            g.drawString("YOU WON", getWidth()/2-70,getHeight()/2);
        }

        //Display players health with small icons
        for (int i = 0; i < game.getData().getHealth(); i++) {
            g.drawImage(healthIcon,10*i,440, 30,30,this);
        }
    }
}
