package objects;

import entities.Entity;
import main.GamePanel;

public class OBJ_heart extends Entity {

    public OBJ_heart(GamePanel gp){
        super(gp);

        name = "Heart";
        image = setup("player", "heart", "heart_0");
        image2 = setup("player", "heart", "heart_1");
        image3 = setup("player", "heart", "heart_2");
    }
}
