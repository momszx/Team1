package com.company.tile;

import com.company.Game;
import com.company.Handler;
import com.company.Id;

import java.awt.*;

public class Flag extends Tile {
    public Flag(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        g.drawImage(Game.flag[1].getBufferedImage(),getX(),getY(),width,64,null);
        g.drawImage(Game.flag[2].getBufferedImage(),getX(),getY()+64,width,64,null);
        g.drawImage(Game.flag[2].getBufferedImage(),getX(),getY()+128,width,64,null);
        g.drawImage(Game.flag[2].getBufferedImage(),getX(),getY()+192,width,64,null);
        g.drawImage(Game.flag[0].getBufferedImage(),getX(),getY()+height-64,width,64,null);

    }

    public void tick() {

    }
}
