package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_heart extends SuperObject{

    GamePanel gp;

    public OBJ_heart(GamePanel gp){
        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/heart/heart_0.png"));
            image2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/heart/heart_1.png"));
            image3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/heart/heart_2.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
