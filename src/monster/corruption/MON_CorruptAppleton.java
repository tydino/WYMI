package monster.corruption;

import entities.Entity;
import main.GamePanel;
import objects.projectiles.OBJ_Corruption;

import java.util.Random;

public class MON_CorruptAppleton extends Entity {

    public MON_CorruptAppleton(GamePanel gp){
        super(gp);

        type = type_monster;
        name = "Corrupt Appleton";
        speed = 3;
        maxLife = 20;
        life = maxLife;
        attack = 10;
        defense = 2;
        exp = 4;
        projectile = new OBJ_Corruption(gp, attack, 1);

        solidArea.x = gp.scale*3;
        solidArea.y = gp.scale*10;
        solidArea.width = gp.scale*8;
        solidArea.height = gp.scale*5;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("npc", "corrupt/appleton", "appleton6", 1, 1);
        up2 = setup("npc", "corrupt/appleton", "appleton7", 1, 1);
        down1 = setup("npc", "corrupt/appleton", "appleton0", 1, 1);
        down2 = setup("npc", "corrupt/appleton", "appleton1", 1, 1);
        left1 = setup("npc", "corrupt/appleton", "appleton4", 1, 1);
        left2 = setup("npc", "corrupt/appleton", "appleton5", 1, 1);
        right1 = setup("npc", "corrupt/appleton", "appleton2", 1, 1);
        right2 = setup("npc", "corrupt/appleton", "appleton3", 1, 1);
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

        int i = new Random().nextInt(100)+1;
        if(i > 99 && !projectile.alive && shotAvailableCount == 10){
            projectile.set(WorldX, WorldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCount = 0;
            gp.playSFX(14);
        }
    }

    public void damageReaction(){

        actionLockCount = 0;
        switch (gp.player.direction) {
            case "up": direction = "down"; break;
            case "down": direction = "up"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;

        }
    }
}
