package com.maxithome.game;

import java.awt.*;

public class SmartEnemy extends GameObject{
  private Handler handler;
  private GameObject player;

  public SmartEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;

    for(GameObject object : handler.objects) {
      if(object.id == ID.Player) {
        this.player = object;
      }
    }
  }

  @Override
  public void tick() {
    x += velX;
    y += velY;

    float diffX = x - player.getX() - 8;
    float diffY = y - player.getY() - 8;
    float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
    velX = (int) Math.round((-1.0/distance) * diffX);
    velY = (int) Math.round((-1.0/distance) * diffY);

    handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.1f, handler));
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.green);
    g.fillRect(x, y, 16, 16);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 16, 16);
  }
}
