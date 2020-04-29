package com.company.entity.mob;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;
import com.company.state.BossState;
import com.company.state.PlayerState;
import com.company.state.TurtleState;
import com.company.tile.Tile;

import java.awt.*;
import java.util.Random;

public class Turtle extends Entity {
    private Random rnd = new Random();

    private int shellCount;

    public Turtle(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);

        int dir =rnd.nextInt(2);

        switch (dir){
            case 0:
                setVelX(-2);
                facing = 0;
                break;
            case 1:
                setVelX(2);
                facing = 1;
                break;
        }
        turtleState = TurtleState.WALKING;
    }

    public void render(Graphics g) {
        if(turtleState==TurtleState.WALKING){
            if (facing == 0){
                g.drawImage(Game.turtle[1].getBufferedImage(), x, y, width, height, null);
            } else if(facing == 1){
                g.drawImage(Game.turtle[0].getBufferedImage(), x, y, width, height, null);
            }
        }
        if(turtleState==TurtleState.SHELL || turtleState==TurtleState.SPINNING){
            g.drawImage(Game.turtle[2].getBufferedImage(), x, y, width, height, null);
        }
    }

    public void tick() {
        x+=velX;
        y+=velY;
        if (turtleState==TurtleState.SHELL){
            setVelX(0);
            shellCount++;

            if (shellCount>=300){
                shellCount=0;
                turtleState=TurtleState.WALKING;
            }
            if (turtleState==TurtleState.WALKING||turtleState==TurtleState.SPINNING){
                shellCount=0;
                if (velX==0){
                    int dir =rnd.nextInt(2);

                    switch (dir){
                        case 0:
                            setVelX(-2);
                            facing = 0;
                            break;
                        case 1:
                            setVelX(2);
                            facing = 1;
                            break;
                    }
                }
            }
        }

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
                    if(turtleState==TurtleState.SPINNING) setVelX(10);
                    else setVelX(2);
                    facing = 1;
                }
                if(getBoundsRight().intersects(t.getBounds())){
                    if(turtleState==TurtleState.SPINNING) setVelX(-10);
                    else setVelX(-2);
                    facing = 0;
                }
            }
        }

        for (int i=0;i<handler.entity.size();i++){
            Entity e =handler.entity.get(i);
            if (e.getId()==Id.snake && turtleState==TurtleState.SPINNING){
                if (getBounds().intersects(e.getBounds())) e.die();
            }
        }

        if(falling){
            gravity+=0.1;
            setVelY((int)gravity);
        }


    }
}
