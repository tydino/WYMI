package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Test extends SuperObject{

    GamePanel gp;

    public OBJ_Test(GamePanel gp){
        this.gp = gp;

        name = "Test";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/test_prize.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
