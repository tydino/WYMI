package objects;

import entities.Entity;
import main.GamePanel;

public class OBJ_BasicKey extends Entity {

    public OBJ_BasicKey(GamePanel gp, String name){
        super(gp);

        this.name = name;
        down1 = setup("objects","basics","basic_key", 1, 1);
        description = "[" + name + "]\nHow do you even have this?\nThis item is for testing purposes only.";

    }
}
