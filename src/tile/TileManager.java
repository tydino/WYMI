package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];


    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[8];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("maps/world01.txt");
    }

    public void getTileImage() {
        //blank tile with collision
        tile[0] = new Tile();
        tile[0].collision = true;

        //setup files
        setup(1, "grass", false);
        setup(2, "wall", true);
        setup(3, "water", true);
        setup(4, "dirt_path", false);
        setup(5, "dirt", false);
        setup(6, "sand", false);
        setup(7, "smalltree_grass", true);
    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/"+ imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePass){
        try{
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePass);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col] [row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }

            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.WorldX + gp.player.screenX;
            int screenY = worldY - gp.player.WorldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.WorldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.WorldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.WorldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.WorldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
