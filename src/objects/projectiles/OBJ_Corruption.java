package objects.projectiles;

import entities.Projectile;
import main.GamePanel;

public class OBJ_Corruption extends Projectile {

    public OBJ_Corruption(GamePanel gp, int attack, int useCost){
        super(gp);

        name = "Corruption";
        speed = 6;
        maxLife = 40;
        life = maxLife;
        this.attack = attack;
        this.useCost = useCost;
        alive = false;
        getImage();
    }
    public void getImage(){
        up1 = setup("projectiles", "corruption", "corruption0", 1, 1);
        up2 = setup("projectiles", "corruption", "corruption1", 1, 1);
        left1 = setup("projectiles", "corruption", "corruption2", 1, 1);
        left2 = setup("projectiles", "corruption", "corruption3", 1, 1);
        down1 = setup("projectiles", "corruption", "corruption4", 1, 1);
        down2 = setup("projectiles", "corruption", "corruption5", 1, 1);
        right1 = setup("projectiles", "corruption", "corruption6", 1, 1);
        right2 = setup("projectiles", "corruption", "corruption7", 1, 1);
    }
}
