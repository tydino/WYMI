package objects;

import entities.Entity;
import main.GamePanel;

public class OBJ_Amulet_Water extends Entity {
    public OBJ_Amulet_Water(GamePanel gp) {
        super(gp);

        name = "Water Bound Amulet";
        down1 = setup("objects", "amulets", "water_bound_amulet", 1, 1);
        defenseValue = 1;
    }
}
