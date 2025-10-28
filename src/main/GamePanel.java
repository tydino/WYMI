package main;

import entities.Entity;
import entities.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
//on part https://youtu.be/H23OmhqLo3E?list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&t=55

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
    public Sound music = new Sound();
    public Sound sfx = new Sound();
    public CollisionCheck cChecker = new CollisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public Thread gameThread;

    //ENTITIES AND OBJECTS
    public Player player = new Player(this, keyH);
    public Entity[] obj = new Entity[50];//slots of objects that can be displayed at a time
    public Entity[] npc = new Entity[50];
    public Entity[] monster = new Entity[50];
    public InteractiveTile[] iTile = new InteractiveTile[50];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;

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
        aSetter.setMonster();
        aSetter.setInteractiveTile();

        gameState = titleState;
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
            for(int i = 0; i< monster.length; i++){
                if (monster[i] != null){
                    if(monster[i].alive && !monster[i].dying) {
                        monster[i].update();
                    }
                    if(!monster[i].alive) {
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
            }
            for(int i = 0; i< projectileList.size(); i++){
                if (projectileList.get(i) != null){
                    if(projectileList.get(i).alive) {
                        projectileList.get(i).update();
                    }
                    if(!projectileList.get(i).alive) {
                        projectileList.remove(i);
                    }
                }
            }
            for (InteractiveTile interactiveTile : iTile) {
                if (interactiveTile != null) {
                    interactiveTile.update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing for now
        }

    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;//drawn bottom to top on screen is top to bottom here.

        //DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }
        //other
        else {

            //TILE
            tileM.draw(g2);

            //INTERACTIVE TILE
            for (InteractiveTile interactiveTile : iTile) {
                if (interactiveTile != null) {
                    interactiveTile.draw(g2);
                }
            }

            //ADDS ENTITIES TO LIST
            entityList.add(player);

            for (Entity entity : npc) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            for (Entity entity : obj) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            for (Entity entity : monster) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }
            for (Entity entity : projectileList) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            //SORT
            entityList.sort(new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.WorldY, e2.WorldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for(int i = 0; i< entityList.size(); i++){
                entityList.get(i).draw(g2);
            }

            entityList.clear();

            //UI
            ui.draw(g2);

            if (keyH.checkDrawTime) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Draw Time: " + passed, 10, 400);
                System.out.println("Draw Time: " + passed);
            }

            g2.dispose();
        }
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
