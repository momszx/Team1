package com.company.powerup;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;

import java.awt.*;

public class Wine extends Entity {
    public Wine(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        g.drawImage(Game.wine.getBufferedImage(),x,y,width,height,null);
    }

    public void tick() {
        x+=velX;
        y+=velY;
    }
}
