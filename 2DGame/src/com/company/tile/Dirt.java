package com.company.tile;

import com.company.Game;
import com.company.Handler;
import com.company.Id;

import java.awt.*;

public class Dirt extends Tile {
    public Dirt(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) { g.drawImage(Game.dirt.getBufferedImage(), x, y, width, height, null);

    }

    public void tick() {

    }
}
