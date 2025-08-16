package main;

import entities.Entity;
import objects.ui.OBJ_Mana;
import objects.ui.OBJ_heart;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank, mana_full, mana_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;

    public UI(GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        //CREATE HUD OBJECT
        Entity heart = new OBJ_heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        Entity mana = new OBJ_Mana(gp);
        mana_full = mana.image;
        mana_blank = mana.image2;
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //PLAY STATE
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        //CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }
    }

    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;


        //DRAW MAX LIFE
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //DRAW CURRENT LIFE
        while(i<gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;

            if(i< gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

        //DRAW MAX MANA
        x = gp.tileSize/2-16;
        y = (int) (gp.tileSize*1.5);
        i = 0;
        while(i<gp.player.maxMana){
            g2.drawImage(mana_blank, x, y, null);
            i++;
            x += 36;
        }

        //DRAW MANA
        x = gp.tileSize/2-16;
        y = (int) (gp.tileSize*1.5);
        i = 0;
        while (i<gp.player.mana){
            g2.drawImage(mana_full, x, y, null);
            i++;
            x+=36;
        }

    }

    public void drawTitleScreen(){

        g2.setColor(new Color(56, 0, 56));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 75f));
        String text = "What You Make of It";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*3;

        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        //MAIN
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //PLAYER IMAGE

        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48));

        text ="New Game";
        x = getXForCenteredText(text);
        y+= gp.tileSize*3;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text ="Load Game";
        x = getXForCenteredText(text);
        y+= gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text ="Quit Game";
        x = getXForCenteredText(text);
        y+= gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }

    }

    public void drawPauseScreen(){

        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y+=40;
        }
    }

    public void drawCharacterScreen(){

        // CREATE A FRAME
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        //NAMES
        g2.drawString("Level", textX, textY);
        textY+=lineHeight;
        g2.drawString("Life", textX, textY);
        textY+=lineHeight;
        g2.drawString("Strength", textX, textY);
        textY+=lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY+=lineHeight;
        g2.drawString("Attack", textX, textY);
        textY+=lineHeight;
        g2.drawString("Defense", textX, textY);
        textY+=lineHeight;
        g2.drawString("Exp", textX, textY);
        textY+=lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY+=lineHeight;
        g2.drawString("Coin", textX, textY);
        textY+=lineHeight;
        g2.drawString("Weapon", textX, textY);
        textY+=lineHeight;
        g2.drawString("Amulet", textX, textY);
        textY += lineHeight;

        //VALUES
        int tailX = (frameX + frameWidth) - 30;
        //RESET TEXTY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gp.player.life + "/" + gp.player.maxLife;
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-14, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentAmulet.down1, tailX - gp.tileSize, textY-14, null);
    }

    public void drawInventory(){

        //FRAME
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //DRAW PLAYERS INVENTORY
        for(int i = 0; i<gp.player.inventory.size(); i++){

            //EQUIP CURSOR
            if(gp.player.inventory.get(i) == gp.player.currentWeapon ||
                    gp.player.inventory.get(i) == gp.player.currentAmulet){
                g2.setColor(new Color(240, 240, 50));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);

            slotX += slotSize;

            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        //DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);


        //DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;

        //DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(14f));

        int itemIndex = getItemIndexOnSlot();

        if(itemIndex < gp.player.inventory.size()){

            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

            for(String line:gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 16;
            }
        }
    }

    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(5, 0, 5, 220);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, gp.tileSize/2, gp.tileSize/2);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, gp.tileSize/3, gp.tileSize/3);
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public int getXForAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
