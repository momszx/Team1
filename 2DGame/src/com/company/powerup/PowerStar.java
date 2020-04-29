package com.company.powerup;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;
import com.company.tile.Tile;

import java.awt.*;
import java.util.Random;

public class PowerStar extends Entity {
    private Random rnd;

    public PowerStar(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);

        rnd = new Random();
        int dir =rnd.nextInt(2);

        switch (dir){
            case 0:
                setVelX(-4);
                break;
            case 1:
                setVelX(4);
                break;
        }

        falling=true;
        gravity=0.2;
    }

    public void render(Graphics g) {
        g.drawImage(Game.star.getBufferedImage(),getX(),getY(),width,height,null);
    }

    public void tick() {
        x+=velX;
        y+=velY;

        for(int i=0;i<handler.tile.size();i++){
            Tile t = handler.tile.get(i);

            if (t.isSolid()){
                if (getBoundsBottom().intersects(t.getBounds())){
                    falling=false;
                    jumping=true;
                    gravity=6;
                }
                if (getBoundsLeft().intersects(t.getBounds())) setVelX(2);
                if (getBoundsRight().intersects(t.getBounds())) setVelX(-2);
            }
        }

        if(jumping) {
            gravity -= 0.2;
            setVelY((int) -gravity);
            if (gravity <= 0.0) {
                jumping = false;
                falling = true;
            }
        }
        if(falling) {
            gravity +=0.2;
            setVelY((int)gravity);
        }
    }
}
