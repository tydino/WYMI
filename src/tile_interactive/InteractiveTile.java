package tile_interactive;

import entities.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {


    GamePanel gp;
    public boolean destrucatable = false;

    public InteractiveTile(GamePanel gp, int col, int row){
        super(gp);
        this.gp = gp;

        this.WorldX = gp.tileSize * col;
        this.WorldY = gp.tileSize * row;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public void playSE(){}
    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;
        return tile;
    }

    @Override
    public void update() {
        if(invincible){
            invincibleCount++;
            if(invincibleCount > 10){
                invincible = false;
                invincibleCount = 0;
            }
        }
    }
}
