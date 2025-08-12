package main;

import entities.NPC_Clickles;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){//in world map -1 from where the actual tile is in both x and y

    }

    public void setNPC(){

        gp.npc[0] = new NPC_Clickles(gp);
        gp.npc[0].WorldX = gp.tileSize*16;
        gp.npc[0].WorldY = gp.tileSize*10;

    }
}
