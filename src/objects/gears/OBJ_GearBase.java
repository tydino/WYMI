package objects.gears;

import entities.Entity;
import main.GamePanel;

public class OBJ_GearBase extends Entity {

    public OBJ_GearBase(GamePanel gp, int value, String name) {
        super(gp);

        type = type_pickupOnly;
        this.value = value;
        this.name = name;
    }

    public void use(Entity entity){

        gp.playSFX(15);
        gp.player.coin += value;
    }
}
