package entities.npc;

import entities.Entity;
import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Clickles extends Entity {

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
        setDialogue();
    }

    public void getImage(){
        up1 = setup("npc", "clickles","up1", 1, 1);
        up2 = setup("npc", "clickles","up2", 1, 1);
        down1 = setup("npc", "clickles","down1", 1, 1);
        down2 = setup("npc", "clickles","down2", 1, 1);
        left1 = setup("npc", "clickles","left1", 1, 1);
        left2 = setup("npc", "clickles","left2", 1, 1);
        right1 = setup("npc", "clickles","right1", 1, 1);
        right2 = setup("npc", "clickles","right2", 1, 1);
    }

    public void setDialogue(){

        dialogues[0] = "Hello! Aren't you THE new guy?";
        dialogues[1] = "Do you know why you are here?";
        dialogues[2] = "It appears to me \nthat you are a silent type.";
        dialogues[3] = "well, bye now i dont want to \nkeep you here too long!";
        dialogues[4] = "...";
        dialogues[5] = "...";
        dialogues[6] = "...";
        dialogues[7] = "...";
        dialogues[8] = "Please stop bugging me.";
        dialogues[9] = "...";
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

    public void speak(){

        //can go without this, but as a reminder untill i do it, you can add character specific stuff here.

        super.speak();

    }
}
