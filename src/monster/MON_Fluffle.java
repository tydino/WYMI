package monster;

import entities.Entity;
import main.GamePanel;
import objects.gears.OBJ_BronzeGear;
import objects.ui.OBJ_Mana;
import objects.ui.OBJ_heart;

import java.util.Random;

public class MON_Fluffle extends Entity {

    public MON_Fluffle(GamePanel gp) {
        super(gp);

        type = type_monster;
        name = "Fluffle";
        speed = 3;
        maxLife = 10;
        life = maxLife;
        attack = 4;
        defense = 0;
        exp = 1;

        solidArea.x = gp.scale*3;
        solidArea.y = gp.scale*10;
        solidArea.width = gp.scale*10;
        solidArea.height = gp.scale*5;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        up1 = setup("npc", "fluffle", "fluffle0", 1, 1);
        up2 = setup("npc", "fluffle", "fluffle1", 1, 1);
        down1 = setup("npc", "fluffle", "fluffle0", 1, 1);
        down2 = setup("npc", "fluffle", "fluffle1", 1, 1);
        left1 = setup("npc", "fluffle", "fluffle0", 1, 1);
        left2 = setup("npc", "fluffle", "fluffle1", 1, 1);
        right1 = setup("npc", "fluffle", "fluffle0", 1, 1);
        right2 = setup("npc", "fluffle", "fluffle1", 1, 1);
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

    public void damageReaction(){

        actionLockCount = 0;
        direction = gp.player.direction;
    }

    public void checkDrop(){

        //CAST A DICE
        int i = new Random().nextInt(4)+1;

        //SET THE DROP
        if(i == 1){
            dropItem(new OBJ_heart(gp, 2));
        }else if(i == 2){
            dropItem(new OBJ_Mana(gp, 1));
        }else if(i == 3){
            dropItem(new OBJ_BronzeGear(gp));
        }
    }
}
