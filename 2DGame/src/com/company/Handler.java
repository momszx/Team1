package com.company;

import com.company.entity.Entity;
import com.company.entity.Flag;
import com.company.entity.mob.*;
import com.company.powerup.PowerStar;
import com.company.powerup.Wine;
import com.company.tile.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler {
    public LinkedList<Entity> entity =new LinkedList<Entity>();
    public LinkedList<Tile> tile =new LinkedList<Tile>();

    public void  render(Graphics g){
        for(int i=0; i < entity.size(); i++){
            Entity e = entity.get(i);
            if(Game.getVisibleArea()!=null && e.getBounds().intersects(Game.getVisibleArea()) && e.getId()==Id.plant) e.render(g);
        }
        for(int i=0; i < tile.size(); i++){
            Tile t = tile.get(i);
            if(Game.getVisibleArea()!=null && t.getBounds().intersects(Game.getVisibleArea())) t.render(g);
        }
        for(int i=0; i < entity.size(); i++){
            Entity e = entity.get(i);
            if(Game.getVisibleArea()!=null && e.getBounds().intersects(Game.getVisibleArea()) && e.getId()!=Id.plant) e.render(g);
        }

        g.drawImage(Game.coin.getBufferedImage(),Game.getVisibleArea().x+20,Game.getVisibleArea().y+20,75,75,null);
        g.setColor(Color.WHITE);
        g.setFont(Game.RetroGame);
        g.drawString("x"+Game.coins,Game.getVisibleArea().x+100,Game.getVisibleArea().y+75);
    }
    public void tick(){
        for(int i=0; i < entity.size(); i++){
            Entity e = entity.get(i);
            e.tick();
        }
        for(int i=0; i < tile.size(); i++){
            Tile t = tile.get(i);
            if(Game.getVisibleArea()!=null && t.getBounds().intersects(Game.getVisibleArea())) t.tick();
        }
    }
    public void  addEntity (Entity en){
        entity.add(en);
    }
    public void removeEntity (Entity en){
        entity.remove(en);
    }
    public void addTile (Tile ti){
        tile.add(ti);
    }
    public void removeTile (Tile ti){
        tile.remove(ti);
    }

    public void createLevel(BufferedImage level){
        int width = level.getWidth();
        int height = level.getHeight();

        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                int pixel = level.getRGB(x, y);

                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 0 && green == 0 && blue == 0) addTile(new Wall(x*64,y*64, 64,64,true, Id.wall, this));
                if(red == 70 && green == 30 && blue == 10) addTile(new Dirt(x*64,y*64, 64,64,true, Id.dirt, this));
                if(red == 0 && green == 0 && blue == 255) addEntity(new Player(x*64, y*64, 64, 64, Id.player, this));
                if(red == 255 && green == 0 && blue == 0) addEntity(new Wine(x*64,y*64, 64,64, Id.wine, this,0));
                if(red == 0 && green == 255 && blue == 0) addEntity(new Snake(x*64,y*64, 64,64, Id.snake, this));
                if(red == 100 && green == 100 && blue == 100) addEntity(new Turtle(x*64,y*64, 64,64, Id.turtle, this));
                if(red == 255 && green == 100 && blue == 50) addEntity(new TowerBoss(x*64,y*64, 64,64, Id.towerBoss, this,1));
                if(red == 0 && green == 240 && blue == 255) addTile(new PowerUpBlock(x*64,y*64,64,64,true,Id.powerUp,this, Game.lifeWine,1));
                if(red == 255 && green==255 && blue==0) addEntity(new Coin(x*64,y*64,64,64,Id.coin,this));
                if(red == 0 && (green > 123 && green < 129)&& blue == 0) addTile(new Pipe(x*64, y*64, 64, 64*3, true, Id.pipe, this, 128-green));
                if(red == 255 && green == 0 && blue == 255) addEntity(new Flag(x*64, y*64, 63, 64*5,  Id.flag, this));
                if(red == 255 && green == 100 && blue == 100) addEntity(new Plant(x*64, y*64, 64, 64, Id.plant, this));
                if(red == 100 && green == 100 && blue == 255) addEntity(new PowerStar(x*64, y*64, 50, 50, Id.poweStar, this));



            }
        }
    }
    public  void  clearLevel(){
        entity.clear();
        tile.clear();
    }
}
