package objects;

import entities.Entity;
import main.GamePanel;

public class OBJ_DoubleDoor extends Entity {

    public OBJ_DoubleDoor(GamePanel gp) {
        super(gp);

        name = "Door";
        down1 = setup("objects", "basics", "double_door", 1, 1);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 32;
        solidArea.width = 48;
        solidArea.height = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
