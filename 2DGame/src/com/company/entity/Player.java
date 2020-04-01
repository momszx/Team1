package com.company.entity;

import com.company.Handler;
import com.company.Id;
import com.company.tile.Tile;

import java.awt.*;

public class Player extends Entity{


    public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x,y,width,height);
    }


    public void tick() {
        x+=velX;
        y+=velY;
        if(x<=0){
            x=0;
        }
        if(y<=0){
            x=0;
        }
        if(x+width>=1080) {
            x =1080-width;
        }
        if(x+height>=771) {
            x =771-height;
        }
        for(Tile t:handler.tile){
            if(!t.solid) break;
            if(t.getId()==Id.wall){
                if(getBoundsTop().intersects(t.getBounds())) {
                    setVelY(0);
                    y = t.getY()+t.height;
                }
                if(getBoundsBottom().intersects(t.getBounds())) {
                    setVelY(0);
                    y = t.getY()-t.height;
                }
                if(getBoundsLeft().intersects(t.getBounds())) {
                    setVelX(0);
                    x = t.getX()+t.width;
                }
                if(getBoundsRight().intersects(t.getBounds())) {
                    setVelX(0);
                    x = t.getX()-t.width;
                }
            }
        }
    }
}
