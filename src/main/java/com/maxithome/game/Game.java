package com.maxithome.game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

  public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
  private Thread thread;
  private boolean running = false;
  private Handler handler;
  private Random r;
  private HUD hud;
  private Spawn spawner;
  private Menu menu;

  public static boolean paused = false;
  public int diff = 0;    // 0 = Normal; 1 = Hard;

  public enum STATE {
    Menu,
    Select,
    Help,
    Game,
    End,
  }

  public STATE gameState = STATE.Menu;

  public Game() {
    handler = new Handler();
    hud = new HUD();
    menu = new Menu(this, handler, hud);
    this.addKeyListener(new KeyInput(handler, this));
    this.addMouseListener(menu);

    r = new Random(System.currentTimeMillis());
    new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);


    spawner = new Spawn(handler, hud, this);


    //if(gameState == STATE.Game) {
    //  handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
    //  handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
    //}
    if(gameState != STATE.Game) {
      for (int i = 0; i < 10; ++i) {
        handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
      }
    }
  }

  public synchronized void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public synchronized void stop() {
    try{
      thread.join();
      running = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while(running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while(delta >= 1) {
        tick();
        delta--;
      }

      if(running) {
        render();
      }

      frames++;

      if(System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        System.out.println("FPS: " + frames);
        frames = 0;
      }
    }

    stop();
  }

  private void tick() {
    if(gameState == STATE.Game) {

      if(!paused) {
        this.hud.tick();
        this.spawner.tick();
        this.handler.tick();

        if(HUD.HEALTH <= 0) {
          HUD.HEALTH = 100;
          gameState = STATE.End;
          handler.clearEnemies(true);
          for (int i = 0; i < 10; ++i) {
            handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
          }
        }
      }

    } else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
      this.menu.tick();
      this.handler.tick();
    }
  }

  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null) {
      this.createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();

    g.setColor(Color.black);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    this.handler.render(g);

    if(paused) {
      g.setColor(Color.white);
      g.drawString("PAUSED", 100, 100);
    }

    if(gameState == STATE.Game) {
      this.hud.render(g);
    } else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
      this.menu.render(g);
    }

    g.dispose();
    bs.show();
  }

  public static int clamp(int var, int min, int max) {
    if(var >= max) {
      return max;
    } else if(var <= min) {
      return min;
    } else {
      return var;
    }
  }

  public static void main(String[] args) {
    new Game();
  }
}
