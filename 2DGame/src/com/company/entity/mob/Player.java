package com.company.entity.mob;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;
import com.company.tile.Tile;

import java.awt.*;

public class Player extends Entity {

    private int frame = 0;
    private int frameDelay = 0; //update idő a frame váltások közt

    private boolean animate = false;

    public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        if (facing ==0){
            g.drawImage(Game.player[frame+3].getBufferedImage(), x, y, width, height, null);
        } else if(facing ==1){
            g.drawImage(Game.player[frame].getBufferedImage(), x, y, width, height, null);
        }
    }


    public void tick() {
        x+=velX;
        y+=velY;

        if(y+height>=771) {
            y =771-height; //Jobb oldalt leesés
        }

        if (velX!=0) animate = true;
        else animate = false;

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

        if(animate){
            frameDelay ++;
            if (frameDelay>=3) {
                frame++;
                if (frame >= 3){
                    frame = 0;
                }
                frameDelay=0;
            }
        }

    }
}
