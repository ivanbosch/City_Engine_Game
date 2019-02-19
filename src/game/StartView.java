package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class StartView extends UserView {
    private Image background;

    public StartView(World world, int width, int height) {
        super(world,width,height);
        background = new ImageIcon("data/nebula/stars.png").getImage();
    }

    @Override
    protected void paintBackground (Graphics2D g){
        g.drawImage(background,0,0,this);
    }
}
