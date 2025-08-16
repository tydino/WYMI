package entities;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    public GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2,
            attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3, image4, image5;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public String[] dialogues = new String[50];

    // STATE
    public int WorldX, WorldY;
    public  String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;

    //COUNTER
    public int spriteCounter =0;
    public int actionLockCount = 0;
    public int invincibleCount = 0;
    public int shotAvailableCount = 0;
    int dyingCount = 0;
    int hpBarCount = 0;

    //CHARACTER ATTRIBUTES
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentAmulet;
    public Projectile projectile;

    //ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;

    //TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_amulet = 5;
    public final int type_consumable = 6;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){}
    public void damageReaction(){}
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void use(Entity entity){}

    public void update(){

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);


        if(this.type == type_monster && contactPlayer){
            if(!gp.player.invincible){
                gp.playSFX(3);

                int damage = attack - gp.player.defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.player.life -= damage;
                gp.player.invincible = true;
            }
        }

        //IF COLLISION IS FALSE, PLAYER CAN MOVE
        if (!collisionOn){
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

        if(invincible){
            invincibleCount++;
            if(invincibleCount > 20){
                invincible = false;
                invincibleCount = 0;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = WorldX - gp.player.WorldX + gp.player.screenX;
        int screenY = WorldY - gp.player.WorldY + gp.player.screenY;

        if (WorldX + gp.tileSize > gp.player.WorldX - gp.player.screenX &&
                WorldX - gp.tileSize < gp.player.WorldX + gp.player.screenX &&
                WorldY + gp.tileSize > gp.player.WorldY - gp.player.screenY &&
                WorldY - gp.tileSize < gp.player.WorldY + gp.player.screenY) {

            switch(direction){
                case "up":
                    if(spriteNum == 1) {image = up1;}
                    if(spriteNum == 2){image = up2;}
                    break;
                case "down":
                    if(spriteNum == 1) {image = down1;}
                    if(spriteNum == 2){image = down2;}
                    break;
                case "left":
                    if(spriteNum == 1) {image = left1;}
                    if(spriteNum == 2){image = left2;}
                    break;
                case "right":
                    if(spriteNum == 1) {image = right1;}
                    if(spriteNum == 2){image = right2;}
                    break;
            }

            //Monster HP bar
            if(type == type_monster && hpBarOn) {

                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;

                g2.setColor(new Color(69, 7, 56));
                g2.fillRect(screenX - 2, screenY - 19, gp.tileSize+4, 14);

                g2.setColor(new Color(255, 5, 30));
                g2.fillRect(screenX, screenY - 16, (int)hpBarValue, 8);

                hpBarCount++;

                if(hpBarCount>200){
                    hpBarCount = 0;
                    hpBarOn=false;
                }
            }

            if(invincible){
                hpBarOn = true;
                hpBarCount = 0;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
            if(dying){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            //reset alpha
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public void dyingAnimation(Graphics2D g2){
        dyingCount++;

        if(dyingCount<=5){
            changeAlpha(g2,0f);
        }
        if(dyingCount>5 && dyingCount<=10){
            changeAlpha(g2, 1f);
        }
        if(dyingCount>10 && dyingCount<=15){
            changeAlpha(g2,0f);
        }
        if(dyingCount>15 && dyingCount<=20){
            changeAlpha(g2, 0.75f);
        }
        if(dyingCount>20 && dyingCount<=25){
            changeAlpha(g2,0f);
        }
        if(dyingCount>25 && dyingCount<=30){
            changeAlpha(g2, 0.5f);
        }
        if(dyingCount>30 && dyingCount<=35){
            changeAlpha(g2,0f);
        }
        if(dyingCount>35 && dyingCount<=40){
            changeAlpha(g2, 0.25f);
        }
        if(dyingCount > 40){
            dying = false;
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String directory, String imagePath, String imageName, int width, int height){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(directory + "/" + imagePath + "/" + imageName + ".png"));
            image = uTool.scaleImage(image, width*gp.tileSize, height*gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
