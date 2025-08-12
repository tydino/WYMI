package entities;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Clickles extends Entity{

    public NPC_Clickles(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 3;

        solidArea = new Rectangle();
        solidArea.x = 4 * gp.scale;
        solidArea.y = 13 * gp.scale;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 7 * gp.scale;
        solidArea.height = 2 * gp.scale;

        getImage();
    }

    public void getImage(){
        up1 = setup("clickles","up1");
        up2 = setup("clickles","up2");
        down1 = setup("clickles","down1");
        down2 = setup("clickles","down2");
        left1 = setup("clickles","left1");
        left2 = setup("clickles","left2");
        right1 = setup("clickles","right1");
        right2 = setup("clickles","right2");
    }

    public void setAction(){

        actionLockCount++;

        if(actionLockCount == 40) {//every 2 seconds
            Random random = new Random();
            int i = random.nextInt(100) + 1;//picks from 0-99 so +1 fixxes it

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCount = 0;
        }
    }
}
