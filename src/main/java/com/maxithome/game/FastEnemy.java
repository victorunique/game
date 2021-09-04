package com.maxithome.game;

import java.awt.*;

public class FastEnemy extends GameObject{
  private Handler handler;

  public FastEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);

    this.handler = handler;
    velX = 2;
    velY = 9;
  }

  @Override
  public void tick() {
    x += velX;
    y += velY;

    if(0 >= x || x >= Game.WIDTH - 16) velX *= -1;
    if(0 >= y || y >= Game.HEIGHT - 32) velY *= -1;

    handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16, 16, 0.1f, handler));
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.CYAN);
    g.fillRect(x, y, 16, 16);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 16, 16);
  }
}
