package com.maxithome.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
  private Game game;
  private Handler handler;
  private HUD hud;
  private Random r = new Random(System.currentTimeMillis());

  public Menu(Game game, Handler handler, HUD hud) {
    this.game = game;
    this.handler = handler;
    this.hud = hud;
  }

  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();

    if(game.gameState == Game.STATE.Menu) {
      // Play button
      if(mouseOver(mx, my, 210, 150, 200, 64)) {
        //game.gameState = Game.STATE.Game;
        //handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
        //handler.clearEnemies();
        //handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
        game.gameState = Game.STATE.Select;
        return;
      }

      // Help button
      if(mouseOver(mx, my, 210, 250, 200, 64)) {
        game.gameState = Game.STATE.Help;
        return;
      }

      // Quit button
      if(mouseOver(mx, my, 210, 350, 200, 64)) {
        System.exit(0);
      }
    }

    if(game.gameState == Game.STATE.Select) {
      // Normal button
      if(mouseOver(mx, my, 210, 150, 200, 64)) {
        game.gameState = Game.STATE.Game;
        handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
        handler.clearEnemies();
        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
        game.diff = 0;
        return;
      }

      // Hard button
      if(mouseOver(mx, my, 210, 250, 200, 64)) {
        game.gameState = Game.STATE.Game;
        handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
        handler.clearEnemies();
        handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
        game.diff = 1;
        return;
      }

      // Back button
      if(mouseOver(mx, my, 210, 350, 200, 64)) {
        game.gameState = Game.STATE.Menu;
        return;
      }
    }

    // Back button for Help
    if(game.gameState == Game.STATE.Help) {
      if(mouseOver(mx, my, 210, 350, 200, 64)) {
        game.gameState = Game.STATE.Menu;
        return;
      }
    }

    // Try Again button for Game Over
    if(game.gameState == Game.STATE.End) {
      if(mouseOver(mx, my, 210, 350, 200, 64)) {
        game.gameState = Game.STATE.Menu;
        hud.setScore(0);
        hud.setLevel(1);
        return;
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
      g.drawString("Wave", 240, 70);

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
    } else if(game.gameState == Game.STATE.End) {
      Font fnt = new Font("arial", 1, 50);
      Font fnt2 = new Font("arial", 1, 30);
      Font fnt3 = new Font("arial", 1, 20);

      g.setFont(fnt);
      g.setColor(Color.white);
      g.drawString("Game Over", 180, 70);

      g.setFont(fnt3);
      g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);

      g.setFont(fnt2);
      g.drawRect(210, 350, 200, 64);
      g.drawString("Try Again", 245, 390);
    } else if(game.gameState == Game.STATE.Select) {
      Font fnt = new Font("arial", 1, 50);
      Font fnt2 = new Font("arial", 1, 30);

      g.setFont(fnt);
      g.setColor(Color.white);
      g.drawString("SELECT DIFFICULTY", 70, 70);

      g.setFont(fnt2);
      g.drawRect(210, 150, 200, 64);
      g.drawString("Normal", 270, 190);

      g.drawRect(210, 250, 200, 64);
      g.drawString("Hard", 270, 290);

      g.drawRect(210, 350, 200, 64);
      g.drawString("Back", 270, 390);
    }
  }
}
