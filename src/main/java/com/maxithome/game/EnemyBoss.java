package com.maxithome.game;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject{
  private Handler handler;
  private Random r = new Random(System.currentTimeMillis());

  private int timer = 80;
  private int timer2 = 50;

  public EnemyBoss(int x, int y, ID id, Handler handler) {
    super(x, y, id);

    this.handler = handler;
    velX = 0;
    velY = 2;
  }

  @Override
  public void tick() {
    x += velX;
    y += velY;

    if(timer <= 0) {
      velY = 0;
      timer2--;
    } else {
      timer--;
    }

    if(timer2 <= 0) {
      if(velX == 0) {
        velX = 2;
      }


      int spawn = r.nextInt(10);
      if(spawn == 0) {
        handler.addObject(new EnemyBossBullet(x + 48, y + 48, ID.BasicEnemy, handler));
      }
    }

    if(0 >= x || x >= Game.WIDTH - 96) velX *= -1;
    //if(0 >= y || y >= Game.HEIGHT - 128) velY *= -1;

    //handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96, 96, 0.1f, handler));
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(x, y, 96, 96);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 96, 96);
  }
}
