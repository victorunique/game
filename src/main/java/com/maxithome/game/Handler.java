package com.maxithome.game;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
  LinkedList<GameObject> objects = new LinkedList<>();

  public int spd = 5;

  public void tick() {
    for(int i = 0; i < objects.size(); ++i) {
      GameObject obj = objects.get(i);
      obj.tick();
    }
  }

  public void render(Graphics g) {
    for(int i = 0; i < objects.size(); ++i) {
      GameObject obj = objects.get(i);
      obj.render(g);
    }
  }

  public void addObject(GameObject object) {
    this.objects.add(object);
  }

  public void removeObject(GameObject object) {
    this.objects.remove(object);
  }

  public void clearEnemies(boolean isEnd) {
    for(int i = 0; i < objects.size(); ++i) {
      GameObject obj = objects.get(i);
      if(isEnd) {
        removeObject(obj);
        i--;
      } else if(obj.getId() != ID.Player) {
        removeObject(obj);
        i--;
      }
    }
  }

  public void clearEnemies() {
    this.clearEnemies(false);
  }
}
