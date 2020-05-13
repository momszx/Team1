package com.company;

import com.company.entity.Entity;
import com.company.entity.mob.Player;
import com.company.graphics.Sprite;
import com.company.graphics.SpriteSheet;
import com.company.inputs.KeyInput;
import com.company.state.PlayerState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends Canvas  implements Runnable,ActionListener{


    public static  final int WIDTH =450; //Ezekkel lehet szorakozni, nekem nagy dög monitorom van, szóval így jó :D
    public static  final int HEIGHT = 300; //same
    public static  final int SCALE =4;
    public static final String TITTLE="GAME";
    private Thread thread;
    private boolean running = false;
    private boolean showMainMenu = true;
    protected static Game game = new Game();

    private static BufferedImage[] levels;
    private static BufferedImage background;
    private static int level = 0;
    private static int levelsCompleted=0;

    public static boolean levelCreated=true;

    private static int stop = 0;
    public static int coins =0;
    public static int lives =1;
    public static int deathScreenTime =0;

    public static boolean showDeathScreen =true;
    public static boolean gameOver=false;
    public static boolean gameComplete=false;

    public static boolean playing = false;
    public static String character  = "Man";

    public static Handler handler;

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
    public static Sprite player2[];
    public static Sprite plant;
    public static Sprite star;
    public static Sprite particle[];
    public static Sprite dirt;

    public static Sound jump;
    public static Sound levelcomplet;
    public static Sound loasealife;
    public static Sound themesong;

    public static JButton levelButtons[] = new JButton[10];
    public static  JPanel jpanel = new JPanel();
    public static JFrame frame=new JFrame();
    public static JButton chooseLevelBtn=new JButton("Pályaválasztás");
    public static JButton chooseCharBtn=new JButton("Karakterválasztás");
    public static JButton settingsBtn=new JButton("Beállítások");
    public static JButton quitBtn=new JButton("Kilépés");
    public static Font RetroGame;
    private int buttonWidth = WIDTH*SCALE/2;
    private int buttonHeight = HEIGHT*SCALE/6;

    public Game(){
        Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

     private void init(){

         handler =new Handler();
        sheet = new SpriteSheet("/SpriteSheet.png");
        addKeyListener(new KeyInput());
        cam =new Camera();

        levels = new BufferedImage[10];

        dirt = new Sprite(sheet,1,3);
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
        player2 = new Sprite[6];
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
         for (int i=0;i<player2.length;i++)
         {
             player2[i] = new Sprite(sheet, i+1, 10);
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
                String levelpath = "/level"+(i+1)+".png";
                levels[i] = ImageIO.read(getClass().getResource(levelpath));
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
        }catch (IllegalStateException e){

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void render(){ //displaying on the screen
            try{

                if (!showMainMenu){
                    BufferStrategy bs = getBufferStrategy();
                    if(bs==null){
                        createBufferStrategy(3); //3 bufferstrategy will build up our screen
                        return;
                    }
                    Graphics g = bs.getDrawGraphics();
                    if (gameComplete){
                        g.setColor(Color.BLACK);
                        g.fillRect(0,0,getWidth(),getHeight());
                        g.setColor(Color.WHITE);
                        g.setFont(RetroGame.deriveFont(70F));
                        g.drawString("Game Completed!",getWidth()/2-350,getHeight()/2);
                        bs.show();
                        Restart();
                    }

                    if (!showDeathScreen) {
                        levelCreated=true;
                        g.drawImage(background,0,0,getWidth(),getHeight(),null);
                    }
                    if(showDeathScreen){
                        g.setColor(Color.BLACK);
                        g.fillRect(0,0,getWidth(),getHeight());
                        if (!gameOver){
                            g.setColor(Color.WHITE);
                            g.setFont(RetroGame);
                            if (character=="Man") g.drawImage(Game.player[0].getBufferedImage(),getWidth()/2-50,getHeight()/2,100,100,null);
                            else g.drawImage(Game.player2[0].getBufferedImage(),getWidth()/2-50,getHeight()/2,100,100,null);
                            g.drawString("x"+lives,getWidth()/2-100,getHeight()/2+70);
                        }else {
                            g.setColor(Color.WHITE);
                            g.setFont(RetroGame.deriveFont(70F));
                            g.drawString("Game Over",getWidth()/2-200,getHeight()/2);
                            bs.show();
                            Restart();
                        }
                    }
                    if (playing) g.translate(cam.getX(),cam.getY());
                    if (playing && !showDeathScreen) handler.render(g);
                    g.dispose();
                    bs.show();
                }else if (stop ==0){
                    stop =1;
                    mainMenu();
                }
            }catch (IllegalStateException e){

            }
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
        try{
            Connection con = DriverManager.getConnection("jdbc:h2:C:/Team1/2DGame/res/Database.db","AFPTEAM","password");
            Statement myStatement = con.createStatement();
            String SQL = "update levels set levelsCompleted=levelsCompleted+1";
            myStatement.executeUpdate(SQL);
            con.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        levelCreated=true;
        level++;
        System.out.println(level);
        System.out.println(levelsCompleted);
        handler.clearLevel();
        handler.createLevel(levels[level]);
        levelCreated=false;
    }

    public void tick(){
        handler.tick();
        for(Entity e:handler.entity){
            if (e.getId()==Id.player){
                if(!e.goingDownPipe)cam.tick(e);
            }
        }
        if(showDeathScreen&&!gameOver&&!gameComplete&&playing) deathScreenTime++;
        if (gameComplete){
            showDeathScreen =false;
            deathScreenTime =0;
            playing=false;
        }
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

    public void initMainButtons(){
        try{
            RetroGame = Font.createFont(Font.TRUETYPE_FONT, new File("res/RetroGaming.ttf")).deriveFont(40F);
        }
        catch (IOException | FontFormatException e){

        }

        buttonProperties(chooseLevelBtn);
        chooseLevelBtn.setBounds(WIDTH*SCALE/2-buttonWidth/2,100,buttonWidth,buttonHeight);
        chooseLevelBtn.addActionListener(this::startClicked);

        buttonProperties(chooseCharBtn);
        chooseCharBtn.setBounds(WIDTH*SCALE/2-buttonWidth/2,100+buttonHeight,buttonWidth,buttonHeight);
        chooseCharBtn.addActionListener(this::chooseCharClicked);

        buttonProperties(settingsBtn);
        settingsBtn.setBounds(WIDTH*SCALE/2-buttonWidth/2,100+buttonHeight*2,buttonWidth,buttonHeight);

        buttonProperties(quitBtn);
        quitBtn.setBounds(WIDTH*SCALE/2-buttonWidth/2,100+buttonHeight*3,buttonWidth,buttonHeight);
        quitBtn.addActionListener(this::quitClicked);
    }

    public void mainMenu(){
        JFrame jframe = new JFrame(TITTLE);
        frame=jframe;
        jpanel.setLayout(null);
        jpanel.setBackground(Color.GRAY);

        frame.setBounds(0,0,WIDTH*SCALE,HEIGHT*SCALE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainMenuButtons();
        frame.setVisible(true);

    }
    public void mainMenuButtons(){
        jpanel.add(chooseLevelBtn);
        jpanel.add(chooseCharBtn);
        jpanel.add(settingsBtn);
        jpanel.add(quitBtn);

        frame.add(jpanel);
    }

    public void buttonProperties(JButton button) {

        button.setBorder(null);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(RetroGame.deriveFont(60F));
        button.setForeground(Color.WHITE);

        button.addMouseListener(new MouseAdapter() {
            Color oldcolor = button.getForeground();
            public void mouseEntered(MouseEvent me) {
                oldcolor = button.getForeground();
                button.setForeground(Color.red);
            }
            public void mouseExited(MouseEvent me) {
                button.setForeground(oldcolor);
            }
            public void mousePressed(MouseEvent me) {button.setForeground(oldcolor); }
        });

        jpanel.add(button);
    }
    public void quitClicked(ActionEvent e){
        System.exit(1);
    }
    public void startClicked(ActionEvent e){
        chooseLevel();

    }
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    public void chooseCharClicked(ActionEvent e){
        jpanel.removeAll();
        jpanel.repaint();
        JButton char1Btn = new JButton();
        buttonProperties(char1Btn);
        char1Btn.setBounds(buttonWidth/2,HEIGHT*SCALE/2-buttonHeight,buttonWidth/4,buttonHeight);
        ImageIcon icon1 = new ImageIcon("res/c1right.png");
        ImageIcon icon2 = new ImageIcon("res/c2right.png");
        int offset = char1Btn.getInsets().left;
        char1Btn.setIcon(resizeIcon(icon1, char1Btn.getWidth() - offset, char1Btn.getHeight() - offset));
        char1Btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                character="Man";
                jpanel.removeAll();
                jpanel.repaint();
                frame.remove(jpanel);
                mainMenuButtons();
            }
        });

        JButton char2Btn = new JButton();
        buttonProperties(char2Btn);
        char2Btn.setBounds(WIDTH*SCALE-buttonWidth/2-buttonWidth/4,HEIGHT*SCALE/2-buttonHeight,buttonWidth/4,buttonHeight);
        char2Btn.setIcon(resizeIcon(icon2, char2Btn.getWidth() - offset, char2Btn.getHeight() - offset));
        char2Btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                character="Woman";
                jpanel.removeAll();
                jpanel.repaint();
                frame.remove(jpanel);
                mainMenuButtons();
            }
        });

        /*JButton backBtn = new JButton("Vissza");
        buttonProperties(backBtn);
        backBtn.setBounds(WIDTH*SCALE/2-buttonWidth/2,buttonHeight*4,buttonWidth,buttonHeight);
        backBtn.setFont(RetroGame.deriveFont(40F));
        backBtn.addActionListener(this::backClicked);*/

        frame.add(jpanel);
    }

    public void backClicked(ActionEvent e) {
        jpanel.removeAll();
        jpanel.repaint();
        frame.remove(jpanel);
        mainMenuButtons();
    }
    public void chooseLevel(){
        try{
            Connection con = DriverManager.getConnection("jdbc:h2:C:/Team1/2DGame/res/Database.db","AFPTEAM","password");
            Statement myStatement = con.createStatement();
            String SQL = "select * from levels";
            ResultSet rs = myStatement.executeQuery(SQL);
            rs.next();
            levelsCompleted=Integer.parseInt(rs.getString("levelsCompleted"));
            con.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        jpanel.removeAll();
        jpanel.repaint();
        frame.remove(jpanel);
        int y=0;
        for (int i=0;i<levelButtons.length;i++){
            levelButtons[i]= new JButton();
        }
        for (int i=0;i<levelButtons.length;i+=2){
            int chooselevel=i;
            buttonProperties(levelButtons[i]);
            buttonProperties(levelButtons[i+1]);
            levelButtons[i].setText("Level "+(i+1));
            levelButtons[i].setBounds(0,y,buttonWidth,buttonHeight);
            levelButtons[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    if (levelButtons[chooselevel].isEnabled()){
                        startGame(chooselevel);
                    }

                }
            });

            levelButtons[i+1].setText("Level "+(i+2));
            levelButtons[i+1].setBounds(buttonWidth,y,buttonWidth,buttonHeight);
            levelButtons[i+1].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    if (levelButtons[chooselevel+1].isEnabled()){
                        startGame(chooselevel+1);
                    }
                }
            });


            y+=200;
        }
        checkLevels();


        frame.add(jpanel);
    }
    public void checkLevels(){
        for (int i = 9;i>levelsCompleted;i--){
            levelButtons[i].setEnabled(false);
        }
    }
    public void startGame(int chooseLevel){
        level=chooseLevel;
        frame.setVisible(false);
        JFrame jframe = new JFrame(TITTLE);
        frame = jframe;
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showMainMenu=false;
        playing=true;
        frame.setVisible(true);
        game.start();
    }

    public void actionPerformed(ActionEvent e) {
    }

    public static void main (String[] args){
        game.initMainButtons();
        game.mainMenu();
    }
    public void Restart(){
        lives=1;
        handler.clearLevel();
        gameOver=false;
        playing=false;
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            System.out.println(e);
        }
        jpanel.removeAll();
        jpanel.repaint();
        frame.setVisible(false);
        showMainMenu=true;
        stop =0;
    }
}
