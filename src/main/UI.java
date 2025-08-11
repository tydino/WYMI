package main;

import objects.OBJ_BasicKey;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_40 = new Font("Arial", Font.BOLD, 80);
        OBJ_BasicKey key = new OBJ_BasicKey();
        keyImage = key.image;
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        if (gameFinished == true){

            g2.setFont(arial_80B);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You got to the chest!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        }else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 70);

            //MESSAGE
            if (messageOn) {

                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                messageCounter++;

                if (messageCounter > 40) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
