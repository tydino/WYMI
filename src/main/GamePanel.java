package main;

import entities.Entity;
import entities.Player;
import objects.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16;///oTS x XoTStile size
    public final int scale = 3;//s*oTS

    public final int tileSize = originalTileSize * scale; //48x48 tiles will be displayed
    public final int maxScreenCol = 16; // horizonatl/left right
    public final int maxScreenRow = 12; // vertical/up down
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    int FPS = 20;

    //SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler  keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound sfx = new Sound();
    public CollisionCheck cChecker = new CollisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Thread gameThread;

    //ENTITIES AND OBJECTS
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[15];//slots of objects that can be displayed at a time
    public Entity[] npc = new Entity[10];

    //GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){

        aSetter.setObject();
        aSetter.setNPC();

        playMusic(0);
        gameState = playState;
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }


        }

    }
    public void update(){

        if(gameState == playState) {
            //PLAYER
            player.update();
            //NPC
            for(int i = 0; i< npc.length; i++){
                if (npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing for now
        }

    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;//drawn bottom to top on screen is top to bottom here.

        //DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        //TILES
        tileM.draw(g2);

        //OBJECT
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //NPC
        for(int i =0; i<npc.length; i++){
            if (npc[i] != null){
                npc[i].draw(g2);
            }
        }

        //PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);

        if(keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    public void playMusic(int i){

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSFX(int i){
        sfx.setFile(i);
        sfx.play();
    }
}
