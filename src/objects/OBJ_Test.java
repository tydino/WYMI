package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Test extends SuperObject{
    public OBJ_Test(){
        name = "Test";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/test_prize.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
