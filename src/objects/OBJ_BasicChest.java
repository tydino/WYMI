package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BasicChest extends SuperObject{

    GamePanel gp;

    public OBJ_BasicChest(GamePanel gp){
        this.gp = gp;

        name = "Basic Chest";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/basic_chest.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
