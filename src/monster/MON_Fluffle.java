package monster;

import entities.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Fluffle extends Entity {
    public MON_Fluffle(GamePanel gp) {
        super(gp);

        type = 2;
        name = "Fluffle";
        speed = 3;
        maxLife = 2;
        life = maxLife;

        solidArea.x = gp.scale*3;
        solidArea.y = gp.scale*10;
        solidArea.width = gp.scale*10;
        solidArea.height = gp.scale*5;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        up1 = setup("npc", "fluffle", "fluffle0");
        up2 = setup("npc", "fluffle", "fluffle1");
        down1 = setup("npc", "fluffle", "fluffle0");
        down2 = setup("npc", "fluffle", "fluffle1");
        left1 = setup("npc", "fluffle", "fluffle0");
        left2 = setup("npc", "fluffle", "fluffle1");
        right1 = setup("npc", "fluffle", "fluffle0");
        right2 = setup("npc", "fluffle", "fluffle1");
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
