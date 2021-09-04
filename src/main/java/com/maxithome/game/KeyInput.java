package com.maxithome.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
  private Handler handler;
  private boolean[] keyDown = new boolean[4];

  public KeyInput(Handler handler) {
    this.handler = handler;

    keyDown[0] = false;
    keyDown[1] = false;
    keyDown[2] = false;
    keyDown[3] = false;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();

    for(GameObject object : handler.objects) {
      if(object.id == ID.Player) {
        if(key == KeyEvent.VK_W) {
          object.setVelY(-5);
          keyDown[0] = true;
        }

        if(key == KeyEvent.VK_S) {
          object.setVelY(5);
          keyDown[1] = true;
        }

        if(key == KeyEvent.VK_D) {
          object.setVelX(5);
          keyDown[2] = true;
        }

        if(key == KeyEvent.VK_A) {
          object.setVelX(-5);
          keyDown[3] = true;
        }
      }
    }

    if(key == KeyEvent.VK_ESCAPE) {
      System.exit(0);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();

    for(GameObject object : handler.objects) {
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
