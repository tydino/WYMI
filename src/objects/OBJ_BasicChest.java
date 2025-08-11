package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BasicChest extends SuperObject{
    public OBJ_BasicChest(){
        name = "Basic Chest";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/basic_chest.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
