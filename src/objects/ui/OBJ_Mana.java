package objects.ui;

import entities.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity {
    public OBJ_Mana(GamePanel gp) {
        super(gp);

        name = "Mana";
        image = setup("objects", "mana", "0", 1, 1);
        image2 = setup("objects", "mana", "blank", 1, 1);
    }
}
