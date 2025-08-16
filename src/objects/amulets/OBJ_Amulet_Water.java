package objects.amulets;

import entities.Entity;
import main.GamePanel;

public class OBJ_Amulet_Water extends Entity {
    public OBJ_Amulet_Water(GamePanel gp) {
        super(gp);

        type = type_amulet;
        name = "Water Bound Amulet";
        down1 = setup("objects", "amulets", "water_bound_amulet", 1, 1);
        defenseValue = 1;
        description = "[" + name + "]\nThis is the worst thing to defend with.\nGood for beginners";
    }
}
