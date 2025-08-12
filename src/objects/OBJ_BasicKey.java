package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BasicKey extends SuperObject{

    GamePanel gp;

    public OBJ_BasicKey(GamePanel gp){
        this.gp = gp;

        name = "Basic Key";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/basic_key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
