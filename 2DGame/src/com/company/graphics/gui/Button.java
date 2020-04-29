package com.company.graphics.gui;

import com.company.Game;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Button {

    public int x,y;
    public int width,height;

    public String label;

    public Button(int x, int y, int width, int height, String label) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
    }

    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(Game.RetroGame);
        FontMetrics fm = g.getFontMetrics();
        int stringX = (getWidth() - fm.stringWidth(getLabel())) /2;
        int stringY = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) /2);
        g.drawString(getLabel(),getX()+stringX,getY()+stringY);
    }

    public void mouseClickedEvent(){
        if (getLabel().toLowerCase().contains("pálya")) Game.playing=true;
        else if (getLabel().toLowerCase().contains("karakter")) System.exit(0);
        else if (getLabel().toLowerCase().contains("beállítás")) System.exit(0);
        else if (getLabel().toLowerCase().contains("kilépés")) System.exit(0);
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
