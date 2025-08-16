package objects.swords;

import entities.Entity;
import main.GamePanel;

public class OBJ_Sword_grassBladed extends Entity {
    public OBJ_Sword_grassBladed(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Grass Bladed Sword";
        down1 = setup("objects", "swords", "grass_bladed_sword", 1, 1);
        attackValue = 1;
        description = "[" + name + "]\nThis is the worst thing to fight with.\nGood for beginners";
        attackArea.width = 36;
        attackArea.height = 36;
    }
}
