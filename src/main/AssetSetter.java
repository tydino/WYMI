package main;

import entities.NPC_Clickles;
import monster.MON_Fluffle;
import objects.OBJ_DoubleDoor;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){//in world map -1 from where the actual tile is in both x and y

        gp.obj[0] = new OBJ_DoubleDoor(gp);
        gp.obj[0].WorldX = gp.tileSize*18;
        gp.obj[0].WorldY = gp.tileSize*18;
    }

    public void setNPC(){

        gp.npc[0] = new NPC_Clickles(gp);
        gp.npc[0].WorldX = gp.tileSize*16;
        gp.npc[0].WorldY = gp.tileSize*10;
    }

    public void setMonster() {

        int i = 0;
        gp.monster[i] = new MON_Fluffle(gp);
        gp.monster[i].WorldX = gp.tileSize * 32;
        gp.monster[i].WorldY = gp.tileSize * 13;
        i++;
        gp.monster[i] = new MON_Fluffle(gp);
        gp.monster[i].WorldX = gp.tileSize * 28;
        gp.monster[i].WorldY = gp.tileSize * 17;
        i++;
        gp.monster[i] = new MON_Fluffle(gp);
        gp.monster[i].WorldX = gp.tileSize * 32;
        gp.monster[i].WorldY = gp.tileSize * 17;
        i++;
        gp.monster[i] = new MON_Fluffle(gp);
        gp.monster[i].WorldX = gp.tileSize * 27;
        gp.monster[i].WorldY = gp.tileSize * 10;
        i++;
        gp.monster[i] = new MON_Fluffle(gp);
        gp.monster[i].WorldX = gp.tileSize * 27;
        gp.monster[i].WorldY = gp.tileSize * 36;
        i++;
        gp.monster[i] = new MON_Fluffle(gp);
        gp.monster[i].WorldX = gp.tileSize * 25;
        gp.monster[i].WorldY = gp.tileSize * 36;
        i++;
        gp.monster[i] = new MON_Fluffle(gp);
        gp.monster[i].WorldX = gp.tileSize * 27;
        gp.monster[i].WorldY = gp.tileSize * 11;
        i++;
    }
}
