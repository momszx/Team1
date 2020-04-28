package com.company.entity.mob;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.Entity;
import com.company.state.BossState;
import com.company.tile.Tile;

import java.awt.*;
import java.util.Random;

public class TowerBoss extends Entity {

    public int jumpTime=0;
    public boolean addJumpTime=false;
    private Random random;

    public TowerBoss(int x, int y, int width, int height, Id id, Handler handler, int hp) {
        super(x, y, width, height, id, handler);
        this.hp=hp;
        bossState= BossState.IDLE;
        random=new Random();
    }

    public void render(Graphics g) {
        if (bossState==BossState.RUNNING || bossState==BossState.JUMPING){
            if (facing == 0){
                g.drawImage(Game.towerBoss[4+frame].getBufferedImage(), x, y, width, height, null);
            } else if(facing == 1){
                g.drawImage(Game.towerBoss[frame].getBufferedImage(), x, y, width, height, null);
            }
        }
        else if (bossState==BossState.RECOVERING || bossState==BossState.IDLE || bossState==BossState.SPINNING){
            if (facing == 0){
                g.drawImage(Game.towerBoss[9].getBufferedImage(), x, y, width, height, null);
            } else if(facing == 1){
                g.drawImage(Game.towerBoss[8].getBufferedImage(), x, y, width, height, null);
            }
        }
        else g.drawImage(Game.towerBoss[1].getBufferedImage(), x, y, width, height, null);
    }

    public void tick() {
        x+=velX;
        y+=velY;
        if (hp<=0) die();

        phaseTime++;



        if ((phaseTime>=180 && bossState==BossState.IDLE)|(phaseTime>=600 && bossState!=BossState.SPINNING)) chooseState();

        if (bossState==BossState.RECOVERING && phaseTime>=180){
            bossState=BossState.SPINNING;
            phaseTime=0;
        }

        if (phaseTime>=360 && bossState==BossState.SPINNING){
            phaseTime=0;
            bossState=BossState.IDLE;
        }

        if (bossState==BossState.IDLE || bossState==BossState.RECOVERING){
            setVelX(0);
            setVelY(0);
        }

        if (bossState==BossState.JUMPING || bossState==BossState.RUNNING) attackable=true;
        else attackable=false;

        if (bossState!=BossState.JUMPING){
            addJumpTime=false;
            jumpTime=0;
        }

        if (addJumpTime==true){
            jumpTime++;
            if (jumpTime>=30){
                addJumpTime=false;
                jumpTime=0;
            }
            if (!jumping && !falling){
                jumping=true;
                gravity=10;
            }
        }


        for (int i=0;i<handler.tile.size();i++){
            Tile t =handler.tile.get(i);
            if(!t.solid) break;
            if(t.getId()==Id.wall) {
                if (getBoundsTop().intersects(t.getBounds())) {
                    setVelY(0);
                    if (jumping) {
                        jumping = false;
                        gravity = 0.8;
                        falling = true;
                    }
                }
            }
            if(getBoundsBottom().intersects(t.getBounds())) {
                setVelY(0);
                if(falling){
                    falling = false;
                    addJumpTime=true;
                }
            }
            if(getBoundsLeft().intersects(t.getBounds())) {
                facing = 1;
                setVelX(0);
                if (bossState==BossState.RUNNING){
                    setVelX(4);
                }
                x = t.getX()+t.width;
            }
            if(getBoundsRight().intersects(t.getBounds())) {
                setVelX(0);
                facing = 0;
                if (bossState==BossState.RUNNING){
                    setVelX(-4);
                }
                x = t.getX()-t.width;
            }
        }

        for (int i=0;i<handler.entity.size();i++) {
            Entity e = handler.entity.get(i);
            if (e.getId()==Id.player){
                if (bossState==BossState.JUMPING){
                    if (jumping||falling){
                        if(getX()>=e.getX()-4 && getX()<=e.getX()+4) setVelX(0);
                        else if (e.getX()<getX()){
                            facing=0;
                            setVelX(-2);
                        }
                        else if (e.getX()>getX()){
                            facing=1;
                            setVelX(2);
                        }
                    }
                    else setVelX(0);
                }
                else if(bossState==BossState.SPINNING){
                   if (e.getX()<getX()){
                        facing=0;
                        setVelX(-2);
                    }
                    else if (e.getX()>getX()){
                        facing=1;
                        setVelX(2);
                    }
                }
            }
        }

        if(jumping) {
            gravity -= 0.2;
            setVelY((int) -gravity);
            if (gravity <= 0.5) {
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

    public void chooseState(){
        int nextPhase = random.nextInt(2);
        if (nextPhase==0){
            bossState=BossState.RUNNING;
            int direction = random.nextInt(2);
            if (direction==0) setVelX(-4);
            else setVelX(4);
        }
        else if (nextPhase==1){
            bossState=BossState.JUMPING;
            jumping=true;
            gravity=8.0;
        }
        phaseTime=0;
    }
}
