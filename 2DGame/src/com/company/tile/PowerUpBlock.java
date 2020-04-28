package com.company.tile;

import com.company.Game;
import com.company.Handler;
import com.company.Id;
import com.company.graphics.Sprite;
import com.company.powerup.Wine;

import java.awt.*;

public class PowerUpBlock extends Tile {

    private Sprite powerUp;
    private boolean poppedUp = false;
    private int spriteY = getY();
    private int type;

    public PowerUpBlock(int x, int y, int width, int height, boolean solid, Id id, Handler handler, Sprite powerUp, int type){
        super(x, y, width, height, solid, id, handler);
        this.powerUp = powerUp;
        this.type = type;
    }

    @Override
    public void render(Graphics g) {
        if(!poppedUp) g.drawImage(powerUp.getBufferedImage(), x, spriteY, width, height, null);
        if(!activated) g.drawImage(Game.powerUp.getBufferedImage(), x, y, width, height,null);
        else g.drawImage(Game.usedPowerUp.getBufferedImage(), x, y, width, height, null);
    }

    @Override
    public void tick() {
    if(activated && !poppedUp){
        spriteY--;
        if(spriteY <= y-height){
            handler.addEntity(new Wine(x, spriteY, width, height, Id.wine, handler, type));
            poppedUp=true;
        }
    }
    }
}
