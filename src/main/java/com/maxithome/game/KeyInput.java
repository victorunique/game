package com.maxithome.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
  private Handler handler;
  private boolean[] keyDown = new boolean[4];

  private Game game;

  public KeyInput(Handler handler, Game game) {
    this.handler = handler;
    this.game = game;

    keyDown[0] = false;
    keyDown[1] = false;
    keyDown[2] = false;
    keyDown[3] = false;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();

    for(int i = 0; i < handler.objects.size(); ++i) {
      GameObject object = handler.objects.get(i);
      if(object.id == ID.Player) {
        if(key == KeyEvent.VK_W) {
          object.setVelY(-handler.spd);
          keyDown[0] = true;
        }

        if(key == KeyEvent.VK_S) {
          object.setVelY(handler.spd);
          keyDown[1] = true;
        }

        if(key == KeyEvent.VK_D) {
          object.setVelX(handler.spd);
          keyDown[2] = true;
        }

        if(key == KeyEvent.VK_A) {
          object.setVelX(-handler.spd);
          keyDown[3] = true;
        }
      }
    }

    if(key == KeyEvent.VK_P) {
      if(game.gameState == Game.STATE.Game) {
        Game.paused = !Game.paused;
      }
    }

    if(key == KeyEvent.VK_ESCAPE) {
      System.exit(0);
    }

    if(key == KeyEvent.VK_SPACE) {
      if(game.gameState == Game.STATE.Game) {
        game.gameState = Game.STATE.Shop;
      } else if(game.gameState == Game.STATE.Shop){
        game.gameState = Game.STATE.Game;
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();

    for(int i = 0; i < handler.objects.size(); ++i) {
      GameObject object = handler.objects.get(i);
      if(object.id == ID.Player) {
        if(key == KeyEvent.VK_W) keyDown[0] = false;
        if(key == KeyEvent.VK_S) keyDown[1] = false;
        if(key == KeyEvent.VK_D) keyDown[2] = false;
        if(key == KeyEvent.VK_A) keyDown[3] = false;

        // vertical movement
        if(!keyDown[0] && !keyDown[1]) object.setVelY(0);

        // horizontal movement
        if(!keyDown[2] && !keyDown[3]) object.setVelX(0);
      }
    }
  }
}
