package main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 12;
        eventRect.y = 12;
        eventRect.width = 24;
        eventRect.height = 24;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent(){
        //minus 1 to both x, and y for the column and rows. (starts at 0x0 and not 1x1)
        if (hit(12, 12, "any")){teleport(46, 11, gp.dialogueState);}
        if(hit(10, 24, "up")){fullhealing(gp.dialogueState);}
    }

    public boolean hit(int eventcol, int eventRow, String reqDirection){
        boolean hit = false;

        gp.player.solidArea.x = gp.player.WorldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.WorldY + gp.player.solidArea.y;
        eventRect.x = eventcol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damage(int gameState){

        gp.gameState = gameState;
        gp.ui.currentDialogue = "Miss fortune befalls you. \n It took some health!";
        gp.player.life -=1;
    }

    public void teleport(int x, int y, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You've been teleported!";
        gp.player.WorldX = gp.tileSize * x;
        gp.player.WorldY = gp.tileSize * y;
    }

    public void fullhealing(int gameState){

        if(gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You gain health.";
            gp.player.life = gp.player.maxLife;
        }
    }

}
