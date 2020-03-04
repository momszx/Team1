package com.company;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;

public class Game extends Canvas {

    public static  final int WIDTH =200;
    public static  final int HEIGHT = WIDTH/14*10;
    public static  final int SCALE =200;
    public static final String TITTLE="GAME";


    public Game(){
    Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
    setPreferredSize(size);
    setMaximumSize(size);
    setMinimumSize(size);
    }

    public static void main (String[] args){
        Game game = new Game();
        JFrame frame = new Frame(TITTLE);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
