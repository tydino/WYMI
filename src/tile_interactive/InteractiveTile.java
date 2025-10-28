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

    @Override
    public void update() {

    }
}
