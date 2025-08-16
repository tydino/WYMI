package objects.amulets;

import entities.Entity;
import main.GamePanel;

public class OBJ_Amulet_Wood extends Entity {
    public OBJ_Amulet_Wood(GamePanel gp) {
        super(gp);

        type = type_amulet;
        name = "Wooden Amulet";
        down1 = setup("objects", "amulets", "wooden_amulet", 1, 1);
        defenseValue = 1;
        description = "[" + name + "]\nThis is wood, very good wood.\nnot better then the water bound amulet,\nbut it is a nice gift!";
    }
}
