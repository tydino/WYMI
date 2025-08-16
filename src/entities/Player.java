package entities;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import objects.OBJ_Amulet_Water;
import objects.OBJ_Fireball;
import objects.OBJ_Sword_grassBladed;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public int inventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);

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
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultValues(){
        WorldX=gp.tileSize * 33;
        WorldY=gp.tileSize * 25;
        speed=6;
        direction = "down";

        //PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_grassBladed(gp);
        currentAmulet = new OBJ_Amulet_Water(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setItems(){

        inventory.add(currentWeapon);
        inventory.add(currentAmulet);
    }

    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense(){
        return defense = dexterity * currentAmulet.defenseValue;
    }

    public void getPlayerImage(){
        up1 = setup("", "player","up1", 1, 1);
        up2 = setup("", "player","up2", 1, 1);
        down1 = setup("", "player","down1",1 , 1);
        down2 = setup("", "player","down2",1 , 1);
        left1 = setup("", "player","left1", 1, 1);
        left2 = setup("", "player","left2", 1, 1);
        right1 = setup("", "player","right1", 1, 1);
        right2 = setup("", "player","right2", 1, 1);
    }

    public void getPlayerAttackImage(){
        attackUp1 = setup("", "player", "up_attack0", 1, 2);
        attackUp2 = setup("", "player", "up_attack1", 1, 2);
        attackDown1 = setup("", "player", "down_attack0", 1, 2);
        attackDown2 = setup("", "player", "down_attack1", 1, 2);
        attackLeft1 = setup("", "player", "left_attack0", 2, 1);
        attackLeft2 = setup("", "player", "left_attack1", 2, 1);
        attackRight1 = setup("", "player", "right_attack0", 2, 1);
        attackRight2 = setup("", "player", "right_attack1", 2, 1);
    }

    @Override
    public BufferedImage setup(String directory, String imagePath, String imageName, int width, int height){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream( imagePath + "/" + imageName + ".png"));
            image = uTool.scaleImage(image, width*gp.tileSize, height*gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if (attacking){
            attacking();
        }
        else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {

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

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //CHECK EVENT
            gp.eHandler.checkEvent();

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyH.enterPressed){
                switch(direction){
                    case "up": WorldY -= speed;break;
                    case "down": WorldY += speed;break;
                    case "left": WorldX -= speed;break;
                    case "right": WorldX += speed;break;
                }
            }

            if(keyH.enterPressed && !attackCanceled){
                gp.playSFX(2);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;

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

        if(gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCount == 10){

            //SET DEFAILT COORDINATES AND DIRECTIONS
            projectile.set(WorldX, WorldY, direction, true, this);

            //ADD IT TO THE LIST!
            gp.projectileList.add(projectile);

            shotAvailableCount = 0;

            gp.playSFX(11);
        }

        //This needs to be outside of key if statement
        if(invincible){
            invincibleCount++;
            if(invincibleCount > 20){
                invincible = false;
                invincibleCount = 0;
            }
        }

        if(shotAvailableCount < 10){
            shotAvailableCount++;
        }

    }

    public void attacking(){

        spriteCounter++;

        if(spriteCounter<=5){
            spriteNum = 1;
        }
        if(spriteCounter>5 && spriteCounter <=15){
            spriteNum = 2;

            //Saves current world x world y and area
            int currentWorldX = WorldX;
            int currentWorldY = WorldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjusts player to check
            switch (direction){
                case "up": WorldY -= attackArea.height; break;
                case "down": WorldY += attackArea.height; break;
                case "left": WorldX -= attackArea.width; break;
                case "right": WorldX += attackArea.width; break;
            }

            //attack area becomes solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //checks monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            //after checking reset everything
            WorldX = currentWorldX;
            WorldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter>15){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i){
        if(i != -1){

            String text;

            if(inventory.size() != inventorySize){

                inventory.add(gp.obj[i]);
                gp.playSFX(8);
                text = "You Got a " + gp.obj[i].name + "!";
            }
            else{
                text = "Sorry you cannot pick that up right now, \nyour inventory is full.";
            }

            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = text;
            gp.obj[i] = null;
        }
    }

    public void interactNPC(int i){
        if(gp.keyH.enterPressed){

            if(i != -1) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
                gp.keyH.enterPressed = false;
            }
        }

    }

    public void contactMonster(int i){

        if(i != -1 && !gp.monster[i].invincible){
            if(!invincible) {
                gp.playSFX(3);

                int damage = gp.monster[i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }

                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack){
        if(i != -1) {

            if(!gp.monster[i].invincible){

                int damage = attack - gp.monster[i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.monster[i].life -= damage;
                gp.monster[i].damageReaction();
                gp.monster[i].invincible = true;


                if(gp.monster[i].life <= 0){
                    gp.playSFX(monsterSound(i, true));
                    gp.monster[i].dying = true;
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }else{
                    gp.playSFX(monsterSound(i, false));
                }
            }
        }
    }

    public void checkLevelUp(){

        if(exp >= nextLevelExp){

            exp -= nextLevelExp;
            level++;
            nextLevelExp = nextLevelExp *2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSFX(6);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are now level " + level + "\n You feel a shift in your body \n and are now stronger!";
        }
    }

    public void selectItem(){

        int itemIndex = gp.ui.getItemIndexOnSlot();

        if(itemIndex < inventory.size()){

            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
                gp.playSFX(9);
            }
            if(selectedItem.type == type_amulet){

                currentAmulet = selectedItem;
                defense = getDefense();
                gp.playSFX(9);
            }
            if(selectedItem.type == type_consumable){

                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public int monsterSound(int index, boolean dead){
        int monsterSound = -1;
        Entity Monster = gp.monster[index];

        if(Objects.equals(Monster.name, "Fluffle")){
            if(!dead){
                return 5;
            }else{
                return 4;
            }
        }
        if(Objects.equals(Monster.name, "Corrupt Appleton")){
            if(!dead){
                return 13;
            }else{
                return 12;
            }
        }

        return monsterSound;
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction){
            case "up":
                if(!attacking)
                {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }else{
                    tempScreenY = screenY-gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if(!attacking) {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }else{
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if(!attacking) {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }else{
                    tempScreenX = screenX-gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if(!attacking) {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }else{
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                break;
        }

        if(invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
