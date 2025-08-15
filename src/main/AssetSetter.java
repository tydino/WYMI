package main;

import entities.NPC_Clickles;
import monster.MON_CorruptAppleton;
import monster.MON_Fluffle;
import objects.OBJ_Axe_grassBladed;
import objects.OBJ_BasicKey;
import objects.OBJ_DoubleDoor;
import objects.OBJ_NumNum_Vial;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject() {//in world map -1 from where the actual tile is in both x and y

        int i = 0;
        gp.obj[i] = new OBJ_DoubleDoor(gp);
        gp.obj[i].WorldX = gp.tileSize * 18;
        gp.obj[i].WorldY = gp.tileSize * 18;
        i++;
        gp.obj[i] = new OBJ_BasicKey(gp, "Door Key");
        gp.obj[i].WorldX = gp.tileSize * 18;
        gp.obj[i].WorldY = gp.tileSize * 16;
        i++;
        gp.obj[i] = new OBJ_NumNum_Vial(gp, 4);
        gp.obj[i].WorldX = gp.tileSize * 35;
        gp.obj[i].WorldY = gp.tileSize * 17;
        i++;
        gp.obj[i] = new OBJ_Axe_grassBladed(gp);
        gp.obj[i].WorldX = gp.tileSize * 15;
        gp.obj[i].WorldY = gp.tileSize * 16;
        i++;
    }

    public void setNPC(){

        int i = 0;
        gp.npc[i] = new NPC_Clickles(gp);
        gp.npc[i].WorldX = gp.tileSize*16;
        gp.npc[i].WorldY = gp.tileSize*10;
        i++;
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
        gp.monster[i] = new MON_CorruptAppleton(gp);
        gp.monster[i].WorldX = gp.tileSize * 22;
        gp.monster[i].WorldY = gp.tileSize * 19;
        i++;
        gp.monster[i] = new MON_CorruptAppleton(gp);
        gp.monster[i].WorldX = gp.tileSize * 22;
        gp.monster[i].WorldY = gp.tileSize * 21;
        i++;
    }
}
