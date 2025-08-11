package main;

import entities.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16;///oTS x XoTStile size
    final int scale = 3;//s*oTS

    public final int tileSize = originalTileSize * scale; //48x48 tiles will be displayed
    public final int maxScreenCol = 16; // horizonatl/left right
    public final int maxScreenRow = 12; // vertical/up down
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 20;

    public TileManager tileM = new TileManager(this);
    public KeyHandler  keyH = new KeyHandler();
    public Thread gameThread;
    public Player player = new Player(this, keyH);

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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

        player.update();

    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;//drawn bottom to top on screen is top to bottom here.

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}
