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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas  implements Runnable{

    public static  final int WIDTH =450; //Ezekkel lehet szorakozni, nekem nagy dög monitorom van, szóval így jó :D
    public static  final int HEIGHT = 300; //same
    public static  final int SCALE =4;
    public static final String TITTLE="GAME";
    public static Font RetroGame;
    private Thread thread;
    private boolean running = false;

    private static BufferedImage[] levels;
    private static BufferedImage background;
    private static int level = 0;
    private static String levelPaths = "/level.png";

    public static int coins =0;
    public static int lives =1;
    public static int deathScreenTime =0;

    public static boolean showDeathScreen =true;
    public static boolean gameOver=false;

    public static boolean playing = false;

    public static Handler handler;
    public static Launcher launcher;
    public static MouseInput mouse;

    public static SpriteSheet sheet;
    public Camera cam;
    public static Sprite grass;

    public static Sprite powerUp;
    public static Sprite lifeWine;
    public static Sprite usedPowerUp;

    public static Sprite wine;
    public static Sprite coin;
    public static Sprite turtle[];
    public static Sprite snake[];
    public static Sprite towerBoss[];
    public static Sprite flag[];
    public static Sprite player[];
    public static Sprite plant;
    public static Sprite star;
    public static Sprite particle[];

    public static Sound jump;
    public static Sound levelcomplet;
    public static Sound loasealife;
    public static Sound themesong;

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

        levels = new BufferedImage[2];

        grass = new Sprite(sheet,1,1);
        powerUp=new Sprite(sheet, 3, 1);
        lifeWine= new Sprite(sheet,6,1);
        usedPowerUp = new Sprite(sheet, 4,1);
        wine= new Sprite(sheet,2,1);
        coin=new Sprite(sheet,5,1);
        plant = new Sprite(sheet,1,12);
        star = new Sprite(sheet,7,1);

        turtle = new Sprite[3];
        snake = new Sprite[8];
        towerBoss = new Sprite[10];
        flag = new Sprite[3];
        player = new Sprite[6];
        particle = new Sprite[6];

         for (int i=0;i<particle.length;i++)
         {
             particle[i] = new Sprite(sheet, i+1, 11);
         }

        for (int i=0;i<turtle.length;i++)
        {
            turtle[i] = new Sprite(sheet, i+1, 13);
        }

        for (int i=0;i<player.length;i++)
        {
           player[i] = new Sprite(sheet, i+1, 16);
        }

        for (int i=0;i<snake.length;i++)
        {
            snake[i] = new Sprite(sheet, i+1, 15);
        }

        for (int i=0;i<towerBoss.length;i++)
        {
            towerBoss[i] = new Sprite(sheet, i+1, 14);
        }

        for (int i=0;i<flag.length;i++)
        {
            flag[i] = new Sprite(sheet, i+1, 2);
        }

        try {
            background = ImageIO.read(getClass().getResource("/GameBackGround.png"));
            for (int i=0;i<levels.length;i++){
                String asd = "/level"+(i+1)+".png";
                levels[i] = ImageIO.read(getClass().getResource(asd));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        jump =new Sound("/jump.mp3");
        levelcomplet =new Sound("/levelComplete.mp3");
        loasealife =new Sound("/lifeLose.wav");
        themesong =new Sound("/themesong.wav");
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
        try{
            RetroGame = Font.createFont(Font.TRUETYPE_FONT, new File("res/RetroGaming.ttf")).deriveFont(40F);
        }
        catch (IOException | FontFormatException e){

        }
        BufferStrategy bs = getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3); //3 bufferstrategy will build up our screen
            return;
        }
        Graphics g = bs.getDrawGraphics();

        if (!showDeathScreen) {
            g.drawImage(background,0,0,getWidth(),getHeight(),null);
        }
        if(showDeathScreen){
            g.setColor(Color.BLACK);
            g.fillRect(0,0,getWidth(),getHeight());
            if (!gameOver){
                g.setColor(Color.WHITE);
                g.setFont(RetroGame);
                g.drawImage(Game.player[0].getBufferedImage(),getWidth()/2-50,getHeight()/2,100,100,null);
                g.drawString("x"+lives,getWidth()/2-100,getHeight()/2+70);
            }else {
                g.setColor(Color.WHITE);
                g.setFont(RetroGame);
                g.drawString("Game Over",getWidth()/2-150,getHeight()/2);
            }
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

    public static Rectangle getVisibleArea(){
        for (int i =0;i<handler.entity.size();i++){
            Entity e = handler.entity.get(i);
            if(e.getId()==Id.player) return new Rectangle(e.getX()-(getFrameWidth()/2-5),e.getY()-(getFrameHeight()/2-5),getFrameWidth()+10,getFrameHeight()+10);
        }
        return null;
    }

    public static void switchLevel(){
        level++;

        handler.clearLevel();
        handler.createLevel(levels[level]);
    }

    public void tick(){
        handler.tick();
        for(Entity e:handler.entity){
            if (e.getId()==Id.player){
                if(!e.goingDownPipe)cam.tick(e);
            }
        }
        if(showDeathScreen&&!gameOver&&playing) deathScreenTime++;
        if(deathScreenTime>=180){
            if (!gameOver){
                showDeathScreen =false;
                deathScreenTime =0;
                handler.clearLevel();
                handler.createLevel(levels[level]);
                themesong.play();
                coins=0;
            }
            else if(gameOver){
                showDeathScreen =false;
                deathScreenTime =0;
                playing=false;
                gameOver=false;
            }
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
