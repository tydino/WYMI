package objects.ui;

import entities.Entity;
import main.GamePanel;

public class OBJ_heart extends Entity {

    public OBJ_heart(GamePanel gp){
        super(gp);

        name = "Heart";
        image = setup("player", "heart", "heart_0", 1, 1);
        image2 = setup("player", "heart", "heart_1", 1, 1);
        image3 = setup("player", "heart", "heart_2", 1, 1);
    }

    public OBJ_heart(GamePanel gp, int Value){
        super(gp);

        name = "Heart";
        type = type_pickupOnly;
        down1 = setup("player", "heart", "heart_0", 1, 1);
        value = Value;
    }

    public void use(Entity entity){
        gp.playSFX(10);
        entity.life += value;
    }
}
