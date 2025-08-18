package objects.vials;

import entities.Entity;
import main.GamePanel;

public class OBJ_NumNum_Vial extends Entity {

    int value = 1;

    public OBJ_NumNum_Vial(GamePanel gp, int value){
        super(gp);

        this.value = value;

        type = type_consumable;
        this.name = "Num Num Vial";
        down1 = setup("objects","vials","numnum_vial", 1, 1);
        description = "[" + name + "]\nSmells delicious!\nGives " + this.value + " amount of health!";

    }

    public void use(Entity entity){

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "\n your life has been recovered by " + value + ".";
        entity.life += value;
        gp.playSFX(10);
    }
}
