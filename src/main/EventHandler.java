package main;

public class EventHandler {

    GamePanel gp;
    EventRect[][] eventRect;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col<gp.maxWorldCol && row < gp.maxWorldRow){

            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 12;
            eventRect[col][row].y = 12;
            eventRect[col][row].width = 24;
            eventRect[col][row].height = 24;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col=0;
                row++;
            }
        }
    }

    public void checkEvent(){
        //minus 1 to both x, and y for the column and rows. (starts at 0x0 and not 1x1)
        if (hit(12, 12, "any")){teleport(12, 12, 46, 11, gp.dialogueState, false);}
        if (hit(44, 15, "any")){damage(44, 15, gp.dialogueState, true);}
        if (hit(10, 24, "up")){fullhealing(10, 24, gp.dialogueState, false);}
    }

    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;

        gp.player.solidArea.x = gp.player.WorldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.WorldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damage(int col, int row, int gameState, boolean oneTime){

        gp.gameState = gameState;
        gp.ui.currentDialogue = "Miss fortune befalls you. \n It took some health!";
        gp.player.life -=1;
        eventRect[col][row].eventDone = oneTime;
    }

    public void teleport(int col, int row, int x, int y, int gameState, boolean oneTime){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You've been teleported!";
        gp.player.WorldX = gp.tileSize * x;
        gp.player.WorldY = gp.tileSize * y;
        eventRect[col][row].eventDone = oneTime;
    }

    public void fullhealing(int col, int row, int gameState, boolean oneTime){

        if(gp.keyH.enterPressed){
            gp.player.attackCanceled = true;
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You gain health. \nAnd all creatures previously knocked out \nhave reawakened";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            eventRect[col][row].eventDone = oneTime;
            gp.aSetter.setMonster();
        }
    }

}
