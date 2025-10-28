package tile_interactive;

import main.GamePanel;

public class IT_Trunk extends InteractiveTile{

    GamePanel gp;

    public IT_Trunk(GamePanel gp, int col, int row){
        super(gp, col, row);
        this.gp = gp;

        down1 = setup("tiles", "interactable", "smalltree_trunk", 1, 1);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
