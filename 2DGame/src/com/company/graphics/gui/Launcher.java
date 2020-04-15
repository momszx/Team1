package com.company.graphics.gui;

import com.company.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Launcher {
    public Button[] buttons;

    public Launcher(){
        buttons = new Button[4];
        buttons[0] = new Button(Game.getFrameWidth()/2-250,50,500,100,"Pályaválasztás");
        buttons[1] = new Button(Game.getFrameWidth()/2-250,170,500,100,"Karakterválasztás");
        buttons[2] = new Button(Game.getFrameWidth()/2-250,290,500,100,"Beállítások");
        buttons[3] = new Button(Game.getFrameWidth()/2-250,410,500,100,"Kilépés");
    }

    public void render(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(0,0, Game.getFrameWidth(),Game.getFrameHeight());

        for (int i=0;i<buttons.length;i++){
            buttons[i].render(g);
        }
    }
}
