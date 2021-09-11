package com.maxithome.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {
  private Handler handler;
  private HUD hud;
  private Game game;

  private int B1 = 100;
  private int B2 = 100;
  private int B3 = 100;

  public Shop(Handler handler, HUD hud, Game game) {
    this.handler = handler;
    this.hud = hud;
    this.game = game;
  }

  public void render(Graphics g) {
    g.setColor(Color.white);
    g.setFont(new Font("arial", 0, 48));
    g.drawString("SHOP", Game.WIDTH / 2 - 100, 50);

    // Box-1
    g.setFont(new Font("arial", 0, 12));
    g.drawString("Upgrade Health", 110, 120);
    g.drawString("Cost: " + B1 , 110, 140);
    g.drawRect(100, 100, 100, 80);

    // Box-2
    g.drawString("Upgrade Speed", 260, 120);
    g.drawString("Cost: " + B2 , 260, 140);
    g.drawRect(250, 100, 100, 80);

    // Box-3
    g.drawString("Refill Health", 410, 120);
    g.drawString("Cost: " + B1 , 410, 140);
    g.drawRect(400, 100, 100, 80);

    g.drawString("SCORE: " + hud.getScore(), Game.WIDTH / 2 - 50, 300);
    g.drawString("Press Space to go back", Game.WIDTH / 2 - 50, 330);
  }

  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();

    if(game.gameState == Game.STATE.Shop) {
      // Box-1
      if (mx >= 100 && mx <= 200 && my >= 100 && my <= 180) {
        if (hud.getScore() >= B1) {
          hud.setScore(hud.getScore() - B1);
          B1 += 100;
          hud.bounds += 20;
          hud.HEALTH = 100 + (hud.bounds / 2);
        }
      }

      // Box-2
      if (mx >= 250 && mx <= 350 && my >= 100 && my <= 180) {
        if (hud.getScore() >= B2) {
          hud.setScore(hud.getScore() - B2);
          B2 += 100;
          handler.spd++;
        }
      }

      // Box-3
      if (mx >= 400 && mx <= 500 && my >= 100 && my <= 180) {
        if (hud.getScore() >= B3) {
          hud.setScore(hud.getScore() - B3);
          hud.HEALTH = 100 + (hud.bounds / 2);
        }
      }
    }
  }
}
