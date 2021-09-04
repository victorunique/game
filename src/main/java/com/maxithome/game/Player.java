package com.maxithome.game;

import java.awt.*;

public class Player extends GameObject{
  private Handler handler;

  public Player(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
  }

  @Override
  public void tick() {
    x += velX;
    y += velY;

    x = Game.clamp(x, 0, Game.WIDTH - 32);
    y = Game.clamp(y, 0, Game.HEIGHT - 64);

    collision();
  }

  private void collision() {
    for(GameObject object : handler.objects) {
      if(object.getId() == ID.BasicEnemy || object.getId() == ID.FastEnemy) {
        if(getBounds().intersects(object.getBounds())) {
          HUD.HEALTH -= 2;
        }
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(x, y, 32, 32);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32);
  }
}
