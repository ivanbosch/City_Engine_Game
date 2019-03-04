package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class MyView extends UserView {
    private Image background;
    private Image backgroundLevel2;
    private Image backgroundLevel3;
    private Image backgroundLevel4;
    private Game game;
    final Font font = new Font("TimesRoman",Font.PLAIN,30);

    public MyView(GameLevel world, int width, int height, Game game) {
        super(world, width,height);
        background = new ImageIcon("data/nebula/stars.png").getImage();
        backgroundLevel2 = new ImageIcon("data/nebula/nebula01.png").getImage();
        backgroundLevel3 = new ImageIcon("data/nebula/nebula04.png").getImage();
        backgroundLevel4 = new ImageIcon("data/nebula/nebula05.png").getImage();
        this.game = game;
    }

    @Override
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
        g.setColor(Color.red);
        g.drawString("Lives: " + game.getWorld().getPlayer().getHealth(), 10,20);
        if (game.isWinMusic() == true) {
            g.setFont(font);
            g.setColor(Color.lightGray);
            g.drawString("YOU WON", getWidth()/2,getHeight()/2);
        }
    }
}
