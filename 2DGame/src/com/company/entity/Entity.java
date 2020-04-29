package com.company.entity;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.entity.mob.Turtle;
import com.company.state.BossState;
import com.company.state.TurtleState;

import java.awt.*;

public abstract class Entity {
    public int x;
    public int y;
    public int width, height;
    public int facing = 0;
    public int hp;
    public int phaseTime;

    public int frame = 0;
    public int frameDelay = 0; //update idő a frame váltások közt

    public boolean goingDownPipe = false;

    public int type;
    public int getType() {
        return type;
    }

    public boolean attackable = false;
    public boolean jumping = false;
    public boolean falling = true;
    public double gravity = 0.0;

    public int velX; //velocity
    public int velY;

    public Id id;
    public BossState bossState;
    public TurtleState turtleState;
    public Handler handler;

    public Entity(int x, int y, int width, int height, Id id,Handler handler){
        this.setX(x);
        this.setY(y);
        this.width=width;
        this.height=height;
        this.id=id;
        this.handler=handler;
    }

    public abstract void render(Graphics g);

    public abstract void tick();

    public void die(){
        handler.removeEntity(this);
        if (getId()==Id.player){
            Game.themesong.stop();
            Game.lives--;
            Game.showDeathScreen=true;
            if (Game.lives<=0){
                Game.gameOver=true;
            }
            Game.loasealife.play();
        }

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Rectangle getBounds(){
        return new Rectangle(getX(), getY() ,width, height);
    }

    public Rectangle getBoundsTop(){
        return new Rectangle(x+10, y, width-20, 5);
    }

    public Rectangle getBoundsBottom(){
        return new Rectangle(getX()+10, getY()+width-5, width-20, 5);
    }

    public Rectangle getBoundsLeft(){
        return new Rectangle(getX(), getY()+10, 5, height-20);
    }

    public Rectangle getBoundsRight(){
        return new Rectangle(getX()+width-5, getY()+10, 5, height-20);
    }
}
