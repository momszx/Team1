package com.company.entity.mob;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;
import com.company.tile.Tile;

import java.awt.*;

public class Player extends Entity {

    public Player(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);
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
        //for(Tile t:handler.tile){
        for (int i=0;i<handler.tile.size();i++){
            Tile t =handler.tile.get(i);
            if(!t.solid) break;
            if(t.getId()==Id.wall) {
                if (getBoundsTop().intersects(t.getBounds()) && t.getId() != Id.coin) {
                    setVelY(0);
                    if (jumping) {
                        jumping = false;
                        gravity = 0.0;
                        falling = true;
                    }
                }
            }
            if(t.getId()==Id.powerUp){
                if(getBoundsTop().intersects(t.getBounds())) t.activated=true;
            }

                if(getBoundsBottom().intersects(t.getBounds())&&t.getId()!=Id.coin) {
                    setVelY(0);
                    if(falling) falling = false;
                }else {
                    if(!falling && !jumping){
                        gravity = 0.0;
                        falling = true;
                    }
                }
                if(getBoundsLeft().intersects(t.getBounds())&&t.getId()!=Id.coin) {
                    setVelX(0);
                    x = t.getX()+t.width;
                }
                if(getBoundsRight().intersects(t.getBounds())&&t.getId()!=Id.coin) {
                    setVelX(0);
                    x = t.getX()-t.width;
                }
                if(getBounds().intersects(t.getBounds())&&t.getId()==Id.coin){
                    Game.coins++;
                    t.die();
                }
            }

        for (int i=0;i<handler.entity.size();i++){
            Entity e =handler.entity.get(i);
            if(e.getId()==Id.wine){
                if(getBounds().intersects(e.getBounds())){
                    int tpX = getX();
                    int tpY = getY();
                    width*=2;
                    height*=2;
                    setX(tpX-width);
                    setY(tpY-height);
                    e.die();
                }
            } else if(e.getId()==Id.snake) {
                if(getBoundsBottom().intersects(e.getBoundsTop())){
                    e.die();
                }
                else if(getBounds().intersects(e.getBounds())) {
                    diePlayer();
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

        if(velX!=0){
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
