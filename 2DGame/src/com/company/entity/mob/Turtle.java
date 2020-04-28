package com.company.entity.mob;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;

import java.awt.*;

public class Turtle extends Entity {
    public Turtle(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);
    }

    public void render(Graphics g) {
        g.drawImage(Game.turtle.getBufferedImage(), x, y, width, height, null);
    }

    public void tick() {

    }
}
