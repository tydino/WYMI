package entities;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 4 * gp.scale;
        solidArea.y = 13 * gp.scale;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 7 * gp.scale;
        solidArea.height = 2 * gp.scale;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        WorldX=gp.tileSize * 23;
        WorldY=gp.tileSize * 21;
        speed=3;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/up1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/up2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/down1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/down2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECKS OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false){
                switch(direction){
                    case "up":
                        WorldY -= speed;
                        break;
                    case "down":
                        WorldY += speed;
                        break;
                    case "left":
                        WorldX -= speed;
                        break;
                    case "right":
                        WorldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 5) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int i){
        if(i != 999){

            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Basic Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key:"+hasKey);
                    break;
                case "Door":
                    if(hasKey>0){
                        gp.playSFX(2);
                        gp.obj[i] = null;
                        hasKey--;
                        System.out.println("Door went bye bye.");
                    }
                    System.out.println("Key:"+hasKey);
                    break;
                case "Test":
                    gp.playSFX(1);
                    speed = speed * 2;
                    gp.obj[i] = null;
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
