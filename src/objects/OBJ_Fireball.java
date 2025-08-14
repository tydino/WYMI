package objects;

import entities.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile {

    public OBJ_Fireball(GamePanel gp) {
        super(gp);

        name = "Fireball";
        speed = 6;
        maxLife = 40;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        up1 = setup("projectiles", "fireball", "fireball0", 1, 1);
        up2 = setup("projectiles", "fireball", "fireball1", 1, 1);
        left1 = setup("projectiles", "fireball", "fireball2", 1, 1);
        left2 = setup("projectiles", "fireball", "fireball3", 1, 1);
        down1 = setup("projectiles", "fireball", "fireball4", 1, 1);
        down2 = setup("projectiles", "fireball", "fireball5", 1, 1);
        right1 = setup("projectiles", "fireball", "fireball6", 1, 1);
        right2 = setup("projectiles", "fireball", "fireball7", 1, 1);
    }
}
