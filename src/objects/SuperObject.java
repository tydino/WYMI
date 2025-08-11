package objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp){

        int screenX = worldX - gp.player.WorldX + gp.player.screenX;
        int screenY = worldY - gp.player.WorldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.WorldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.WorldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.WorldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.WorldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
