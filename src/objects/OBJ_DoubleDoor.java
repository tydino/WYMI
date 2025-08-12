package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DoubleDoor extends SuperObject{

    GamePanel gp;

    public OBJ_DoubleDoor(GamePanel gp) {
        this.gp = gp;

        name = "Door";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/double_door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
