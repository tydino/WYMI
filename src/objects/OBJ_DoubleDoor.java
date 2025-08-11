package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DoubleDoor extends SuperObject{
    public OBJ_DoubleDoor() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/double_door.png"));

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
