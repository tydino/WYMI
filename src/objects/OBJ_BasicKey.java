package objects;

import entities.Entity;
import main.GamePanel;

public class OBJ_BasicKey extends Entity {

    public OBJ_BasicKey(GamePanel gp){
        super(gp);

        name = "Basic Key";
        down1 = setup("objects","basics","basic_key", 1, 1);
    }
}
