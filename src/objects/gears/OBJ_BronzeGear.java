package objects.gears;

import main.GamePanel;

public class OBJ_BronzeGear extends OBJ_GearBase{
    public OBJ_BronzeGear(GamePanel gp) {
        super(gp, 5, "Bronze Gear");
        down1 = setup("objects", "gear", "bronze", 1, 1);
    }
}
