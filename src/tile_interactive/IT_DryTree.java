package tile_interactive;

import main.GamePanel;

public class IT_DryTree extends InteractiveTile{

    GamePanel gp;

    public IT_DryTree(GamePanel gp, int col, int row){
        super(gp, col, row);
        this.gp = gp;

        down1 = setup("tiles", "interactable", "smalltree_dry", 1, 1);
        destrucatable = true;
    }
}
