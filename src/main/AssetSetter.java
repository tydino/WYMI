package main;

import entities.NPC_Clickles;
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

        gp.npc[1] = new NPC_Clickles(gp);
        gp.npc[1].WorldX = gp.tileSize*16;
        gp.npc[1].WorldY = gp.tileSize*10;

        gp.npc[2] = new NPC_Clickles(gp);
        gp.npc[2].WorldX = gp.tileSize*16;
        gp.npc[2].WorldY = gp.tileSize*10;

    }
}
