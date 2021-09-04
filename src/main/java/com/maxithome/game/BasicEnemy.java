package com.maxithome.game;

import java.awt.*;

public class BasicEnemy extends GameObject{
  private Handler handler;

  public BasicEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);

    this.handler = handler;
    velX = 5;
    velY = 5;
  }

  @Override
  public void tick() {
    x += velX;
    y += velY;

    if(0 >= x || x >= Game.WIDTH - 16) velX *= -1;
    if(0 >= y || y >= Game.HEIGHT - 32) velY *= -1;

    handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.1f, handler));
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(x, y, 16, 16);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 16, 16);
  }
}
