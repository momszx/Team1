package com.company.inputs;

import com.company.Game;
import com.company.Id;
import com.company.entity.Entity;
import com.company.tile.Tile;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < Game.handler.entity.size(); i++) {
            Entity en = Game.handler.entity.get(i);
            if (en.getId() == Id.player) {
                if(en.goingDownPipe) return;
                switch (key) {
                    case KeyEvent.VK_W:
                        if (!en.jumping && en.gravity==0.2) {
                            en.jumping = true;
                            en.gravity = 10.0;
                            Game.jump.play();
                        }
                        break;
                    case KeyEvent.VK_S:
                        for (int j = 0; j < Game.handler.tile.size(); j++){
                            Tile t = Game.handler.tile.get(j);
                            if(t.getId()==Id.pipe){
                                if(en.getBoundsBottom().intersects(t.getBounds())){
                                    if(!en.goingDownPipe) en.goingDownPipe = true;
                                }
                            }
                        }
                        break;
                    case KeyEvent.VK_A:
                        en.setVelX(-5);
                        en.facing = 0;
                        break;
                    case KeyEvent.VK_D:
                        en.setVelX(5);
                        en.facing = 1;
                        break;
                }
            }
        }
    }

        public void keyReleased (KeyEvent e){
            int keya = e.getKeyCode();
            for (int i = 0; i < Game.handler.entity.size(); i++) {
                Entity en = Game.handler.entity.get(i);
                if (en.getId() == Id.player) {
                    switch (keya) {
                        case KeyEvent.VK_W:
                            en.setVelY(0);
                            break;
                        case KeyEvent.VK_A:
                            en.setVelX(0);
                            break;
                        case KeyEvent.VK_D:
                            en.setVelX(0);
                            break;
                    }
                }
            }
        }
    }
