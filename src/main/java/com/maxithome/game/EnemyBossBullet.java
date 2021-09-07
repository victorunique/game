package com.maxithome.game;

import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject{
  private Handler handler;
  private Random r = new Random(System.currentTimeMillis());

  public EnemyBossBullet(int x, int y, ID id, Handler handler) {
    super(x, y, id);

    this.handler = handler;
    velX = r.nextInt(10) - 5;
    velY = 5;
  }

  @Override
  public void tick() {
    x += velX;
    y += velY;

    //if(0 >= x || x >= Game.WIDTH - 16) velX *= -1;
    //if(0 >= y || y >= Game.HEIGHT - 32) velY *= -1;

    if(y >= Game.HEIGHT) {
      handler.removeObject(this);
    }

    handler.addObject(new Trail(x, y, ID.Trail, Color.gray, 16, 16, 0.1f, handler));
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.gray);
    g.fillRect(x, y, 16, 16);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 16, 16);
  }
}
