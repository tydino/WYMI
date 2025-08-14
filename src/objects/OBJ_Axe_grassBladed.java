package objects;

import entities.Entity;
import main.GamePanel;

public class OBJ_Axe_grassBladed extends Entity {
    public OBJ_Axe_grassBladed(GamePanel gp) {
        super(gp);

        type = type_axe;
        name = "Grass Bladed Axe";
        down1 = setup("objects", "axes", "grass_bladed_axe", 1, 1);
        attackValue = 2;
        description = "[" + name + "]\nThis is the worst axe to do anything with.";
        attackArea.width = 30;
        attackArea.height = 30;
    }
}
