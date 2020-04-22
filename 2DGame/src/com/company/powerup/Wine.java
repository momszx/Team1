package com.company.powerup;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;
import com.company.tile.Tile;

import java.awt.*;
import java.util.Random;

public class Wine extends Entity {

    private Random rnd =new Random();

    public Wine(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);

        int dir =rnd.nextInt(2);

        switch (dir){
            case 0:
                setVelX(-2);
                break;
            case 1:
                setVelX(2);
                break;
        }
    }

    public void render(Graphics g) {
        g.drawImage(Game.wine.getBufferedImage(),x,y,width,height,null);
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
    }
}
