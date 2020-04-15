package com.company;

import com.company.entity.Entity;
import com.company.entity.Player;
import com.company.tile.Tile;
import com.company.tile.Wall;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler {
    public LinkedList<Entity> entity =new LinkedList<Entity>();
    public LinkedList<Tile> tile =new LinkedList<Tile>();

    public void  render(Graphics g){
        for(int i=0; i < entity.size(); i++){
            Entity e = entity.get(i);
            e.render(g);
        }
        for(int i=0; i < tile.size(); i++){
            Tile t = tile.get(i);
            t.render(g);
        }
    }
    public void tick(){
        for(int i=0; i < entity.size(); i++){
            Entity e = entity.get(i);
            e.tick();
        }
        for(int i=0; i < tile.size(); i++){
            Tile t = tile.get(i);
            t.tick();
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
                if (red == 0 && green == 0 && blue == 255) addEntity(new Player(x*64, y*64, 64, 64, false, Id.player, this));
            }
        }
    }
}
