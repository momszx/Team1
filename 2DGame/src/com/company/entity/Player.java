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
            y=0;
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
                    if(jumping){
                        jumping = false;
                        gravity = 0.0;
                        falling = true;
                    }
                }
                if(getBoundsBottom().intersects(t.getBounds())) {
                    setVelY(0);
                    if(falling) falling = false;
                }else {
                    if(!falling && !jumping){
                        gravity = 0.0;
                        falling = true;
                    }
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
        for (int i=0;i<handler.entity.size();i++){
            Entity e =handler.entity.get(i);
            if(e.getId()==Id.wine){
                if(getBounds().intersects(e.getBounds())){
                    width*=2;
                    height*=2;
                    e.die();
                }
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
