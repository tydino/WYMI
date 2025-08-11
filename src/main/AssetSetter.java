package main;

import objects.OBJ_BasicChest;
import objects.OBJ_BasicKey;
import objects.OBJ_DoubleDoor;
import objects.OBJ_Test;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){//in world map -1 from where the actual tile is in both x and y
        gp.obj[0] = new OBJ_BasicKey();
        gp.obj[0].worldX = 19 * gp.tileSize;
        gp.obj[0].worldY = 11 * gp.tileSize;//repeat these 3 for each object.

        gp.obj[1] = new OBJ_DoubleDoor();
        gp.obj[1].worldX = 19 * gp.tileSize;
        gp.obj[1].worldY = 8 * gp.tileSize;

        gp.obj[2] = new OBJ_BasicChest();
        gp.obj[2].worldX = 19 *gp.tileSize;
        gp.obj[2].worldY = 5 *gp.tileSize;

        gp.obj[3] = new OBJ_Test();
        gp.obj[3].worldX = 19 *gp.tileSize;
        gp.obj[3].worldY = 3 *gp.tileSize;

    }
}
