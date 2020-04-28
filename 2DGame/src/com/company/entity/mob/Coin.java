package com.company.entity.mob;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;
import com.company.tile.Tile;

import java.awt.*;

public class Coin extends Entity {

    public Coin(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);
    }

    public void render(Graphics g)
    {
        g.drawImage(Game.coin.getBufferedImage(),x,y,width,height,null);
    }

    public void tick() {

    }
}
