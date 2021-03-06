package com.company.tile;

import com.company.Handler;
import com.company.Id;

import java.awt.*;

public abstract class Tile {
    public int x;
    public int y;
    public int width, height;
    public boolean solid;
    public boolean activated = false;
    public int facing;

    public int velX; //velocity
    public int velY;

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

    public Id id;

    public Handler handler;

    public Tile(int x, int y, int width, int height, boolean solid, Id id,Handler handler){
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
        handler.removeTile(this);
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

    public Rectangle getBounds(){
        return new Rectangle(getX(), getY()-2 ,width+10, height);
    }
}
