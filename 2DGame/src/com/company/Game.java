package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas  implements Runnable{

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
    private Thread thread;
    private boolean running = false;

    private synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this,"Thread"); //Thread will be the name of this thread.
        thread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime(); //current time in nanoseconds
        long timer = System.currentTimeMillis(); //current time in milliseconds
        double delta = 0.0;
        double ns = 1000000000.0/60.0; //nanosecond
        int frames = 0;
        int ticks = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta>=1){
                tick();
                ticks++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis()-timer > 1000){
                timer += 1000;
                System.out.println(frames + "frames per seconds, " + ticks + " update per seconds.");
                frames = 0;
                ticks = 0;
            }
        }
        stop();
    }

    private synchronized void stop(){
        if(!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void render(){ //displaying on the screen
        BufferStrategy bs = getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3); //3 bufferstrategy will build up our screen
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.MAGENTA);
        g.fillRect(0,0,getWidth(), getHeight());
        g.dispose();
        bs.show();
    }

    public void tick(){ //update

    }

    public static void main (String[] args){
        Game game = new Game();
        JFrame frame = new JFrame(TITTLE);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.start();
    }

}
