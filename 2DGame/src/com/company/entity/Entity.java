package com.company.entity;

import com.company.Handler;
import com.company.Id;

import java.awt.*;

public abstract class Entity {
    public int x;
    public int y;
    public int width, height;
    public boolean solid;

    public int velX; //velocity
    public int velY;

    public Id id;

    public Handler handler;

    public Entity(int x, int y, int width, int height, boolean solid, Id id,Handler handler){
        this.setX(x);
        this.setY(y);
        this.width=width;
        this.height=height;
        this.solid=solid;
        this.id=id;
        this.handler=handler;
    }

    public abstract void render(Graphics g);

    public abstract void tick();

    public void die(){
        handler.removeEntity(this);
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

    public boolean isSolid() {
        return solid;
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
}
