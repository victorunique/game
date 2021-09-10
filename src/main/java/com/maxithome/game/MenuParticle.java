package com.maxithome.game;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject{
  private Handler handler;
  private Random r = new Random();
  private Color col;

  public MenuParticle(int x, int y, ID id, Handler handler) {
    super(x, y, id);

    this.handler = handler;

    velX = r.nextInt(14) - 7;
    if(0 == velX) {
      velX = 1;
    }

    velY = r.nextInt(14) - 7;
    if(0 == velY) {
      velY = 1;
    }

    col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
  }

  @Override
  public void tick() {
    x += velX;
    y += velY;

    if(0 >= x || x >= Game.WIDTH - 16) velX *= -1;
    if(0 >= y || y >= Game.HEIGHT - 32) velY *= -1;

    handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.1f, handler));
  }

  @Override
  public void render(Graphics g) {
    g.setColor(col);
    g.fillRect(x, y, 16, 16);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 16, 16);
  }
}
