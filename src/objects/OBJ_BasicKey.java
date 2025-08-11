package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BasicKey extends SuperObject{

    public OBJ_BasicKey(){
        name = "Basic Key";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/basic_key.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
