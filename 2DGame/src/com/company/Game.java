package com.company;

import com.company.entity.Entity;
import com.company.entity.mob.Player;
import com.company.graphics.Sprite;
import com.company.graphics.SpriteSheet;
import com.company.graphics.gui.Launcher;
import com.company.graphics.gui.Button;
import com.company.inputs.KeyInput;
import com.company.inputs.MouseInput;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas  implements Runnable{

    public static  final int WIDTH =450; //Ezekkel lehet szorakozni, nekem nagy dög monitorom van, szóval így jó :D
    public static  final int HEIGHT = 300; //same
    public static  final int SCALE =4;
    public static final String TITTLE="GAME";
    private Thread thread;
    private boolean running = false;

    private BufferedImage image;

    public static int coins =0;
    public static int lives =5;
    public static int deathScreenTime =0;

    public static boolean showDeathScreen =true;

    public static boolean playing = false;

    public static Handler handler;
    public static Launcher launcher;
    public static MouseInput mouse;

    public static SpriteSheet sheet;
    public Camera cam;
    public static Sprite grass;
    public static Sprite wine;
    public static Sprite coin;
    public static Sprite snake[] = new Sprite[8];

    public static Sprite player[] = new Sprite[6];

    public Game(){
    Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
    setPreferredSize(size);
    setMaximumSize(size);
    setMinimumSize(size);
    }

     private void init(){
        handler =new Handler();
        sheet = new SpriteSheet("/SpriteSheet.png");
        launcher = new Launcher();
        mouse = new MouseInput();
        addKeyListener(new KeyInput());
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        cam =new Camera();
        grass = new Sprite(sheet,1,1);
        wine= new Sprite(sheet,2,1);
        coin=new Sprite(sheet,5,1);


        for (int i=0;i<player.length;i++)
        {
           player[i] = new Sprite(sheet, i+1, 16);
        }

         for (int i=0;i<snake.length;i++)
         {
             snake[i] = new Sprite(sheet, i+1, 15);
         }

         try {
             image = ImageIO.read(getClass().getResource("/level.png"));
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    private synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this,"Thread"); //Thread will be the name of this thread.
        thread.start();
    }

    @Override
    public void run() {
        init();
        requestFocus();
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
        if (!showDeathScreen) {
            g.drawImage(Game.coin.getBufferedImage(),20,20,75,75,null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier",Font.BOLD,20));
            g.drawString("x"+coin,100,95);
        }
        if(showDeathScreen){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dourier",Font.BOLD,50));
            g.drawImage(Game.player[0].getBufferedImage(),500,300,100,100,null);
            g.drawString("x"+lives,610,380);
        }
        if (playing) g.translate(cam.getX(),cam.getY());
        if (playing&&!showDeathScreen) handler.render(g);
        else if (!playing) launcher.render(g);
        g.dispose();
        bs.show();
    }

    public static int getFrameWidth(){
        return WIDTH*SCALE;
    }

    public static int getFrameHeight(){
        return HEIGHT*SCALE;
    }

    public void tick(){
        handler.tick();
        for(Entity e:handler.entity){
            if (e.getId()==Id.player){
                cam.tick(e);
            }
        }
        if(showDeathScreen) deathScreenTime++;
        if(deathScreenTime>=180){
            showDeathScreen =false;
            deathScreenTime =0;
            handler.clearLevel();
            handler.createLevel(image);
        }
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
