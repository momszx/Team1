package com.company.entity.mob;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;

import java.awt.*;
import java.util.Random;

public class Plant extends Entity {

    private Random rnd;
    private int wait;
    private boolean insidePipe;
    private boolean moving;
    private int pixelsTravelled;


    public Plant(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);

        insidePipe=true;
        moving=false;
        rnd = new Random();
    }

    public void render(Graphics g) {
        g.drawImage(Game.plant.getBufferedImage(),getX(),getY(),width,64,null);
    }

    public void tick() {
        y+=velY;
        if(!moving) wait+=rnd.nextInt(6);
        if (wait > 180){
            if (insidePipe){
                insidePipe=false;
            }
            else{
                insidePipe=true;
            }

            moving=true;
            wait=0;
        }

        if (moving){
            if (insidePipe){
                setVelY(-3);
            }
            else{
                setVelY(3);
            }

            pixelsTravelled+=velY;
            if (pixelsTravelled>=getHeight()+10 || pixelsTravelled<=-getHeight()-10){
                pixelsTravelled=0;
                moving=false;

                setVelY(0);
            }
        }
    }
}
