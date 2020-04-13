package com.company.entity.mob;

import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;

import java.awt.*;

public class Snake extends Entity {
    public Snake(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g){

    }

    public void tick() {
        x+=velX;
        y+=velY;
    }
}
