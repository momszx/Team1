package com.company;

import com.company.entity.Entity;

public class Camera {
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public  int x;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public  int y;

    public void tick(Entity player){
        setX(-player.getX() + Game.WIDTH/2);
        setY(-player.getY() + Game.HEIGHT/2);
    }
}
