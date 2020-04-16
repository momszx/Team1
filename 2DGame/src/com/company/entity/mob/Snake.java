package com.company.entity.mob;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;
import com.company.tile.Tile;

import java.awt.*;

public class Snake extends Entity {

    public Snake(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g){
        if (facing == 0){
            g.drawImage(Game.snake[4+frame].getBufferedImage(), x, y, width, height, null);
        } else if(facing == 1){
            g.drawImage(Game.snake[frame].getBufferedImage(), x, y, width, height, null);
        }
    }

    public void tick() {
        x+=velX;
        y+=velY;

        for (int i=0;i<handler.tile.size();i++){
            Tile t =handler.tile.get(i);
            if(t.isSolid()){
                if(getBoundsBottom().intersects(t.getBounds())){
                    setVelY(0);
                    if(falling) falling =false;
                }
                else if(!falling){
                    falling=true;
                    gravity=0.8;
                }
                if(getBoundsLeft().intersects(t.getBounds())){
                    setVelX(2);
                }
                if(getBoundsRight().intersects(t.getBounds())){
                    setVelX(-2);
                }
            }
        }
        if(falling){
            gravity+=0.1;
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
