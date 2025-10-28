package tile_interactive;

import entities.Entity;
import main.GamePanel;

public class IT_DryTree extends InteractiveTile{

    GamePanel gp;

    public IT_DryTree(GamePanel gp, int col, int row){
        super(gp, col, row);
        this.gp = gp;

        down1 = setup("tiles", "interactable", "smalltree_dry", 1, 1);
        destrucatable = true;
    }

    @Override
    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = entity.currentWeapon.type == type_axe;

        return isCorrectItem;
    }

    @Override
    public void playSE(){
        gp.playSFX(16);
    }

    @Override
    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = new IT_Trunk(gp, WorldX/gp.tileSize, WorldY/gp.tileSize);
        return tile;
    }
}
