package objects.basics;

import entities.Entity;
import main.GamePanel;

public class OBJ_BasicChest extends Entity {

    public OBJ_BasicChest(GamePanel gp){
        super(gp);

        name = "Basic Chest";
        down1 = setup("objects", "basics", "basic_chest", 1, 1);
    }
}
