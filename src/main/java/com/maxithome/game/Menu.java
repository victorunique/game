package com.maxithome.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
  private Game game;
  private Handler handler;
  private Random r = new Random(System.currentTimeMillis());

  public Menu(Game game, Handler handler) {
    this.game = game;
    this.handler = handler;
  }

  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();

    if(game.gameState == Game.STATE.Menu) {
      // Play button
      if(mouseOver(mx, my, 210, 150, 200, 64)) {
        game.gameState = Game.STATE.Game;
        handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
      }

      // Help button
      if(mouseOver(mx, my, 210, 250, 200, 64)) {
        game.gameState = Game.STATE.Help;
      }

      // Quit button
      if(mouseOver(mx, my, 210, 350, 200, 64)) {
        System.exit(0);
      }
    }

    // Back button for Help
    if(game.gameState == Game.STATE.Help) {
      if(mouseOver(mx, my, 210, 350, 200, 64)) {
        game.gameState = Game.STATE.Menu;
      }
    }
  }

  public void mouseReleased(MouseEvent e) {

  }

  private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
    if(mx > x && mx < x + width && my > y && my < y + height) {
      return true;
    }

    return false;
  }

  public void tick() {

  }

  public void render(Graphics g) {
    if(game.gameState == Game.STATE.Menu) {
      Font fnt = new Font("arial", 1, 50);
      Font fnt2 = new Font("arial", 1, 30);

      g.setFont(fnt);
      g.setColor(Color.white);
      g.drawString("Menu", 240, 70);

      g.setFont(fnt2);
      g.drawRect(210, 150, 200, 64);
      g.drawString("Play", 270, 190);

      g.drawRect(210, 250, 200, 64);
      g.drawString("Help", 270, 290);

      g.drawRect(210, 350, 200, 64);
      g.drawString("Quit", 270, 390);
    } else if(game.gameState == Game.STATE.Help) {
      Font fnt = new Font("arial", 1, 50);
      Font fnt2 = new Font("arial", 1, 30);
      Font fnt3 = new Font("arial", 1, 20);

      g.setFont(fnt);
      g.setColor(Color.white);
      g.drawString("Help", 240, 70);

      g.setFont(fnt3);
      g.drawString("Use WASD keys to move player and dodge enemies", 50, 200);

      g.setFont(fnt2);
      g.drawRect(210, 350, 200, 64);
      g.drawString("Back", 270, 390);
    }
  }
}
