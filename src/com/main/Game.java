package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.main.GameObjects.Player;
import com.main.GameStates.HUD;
import com.main.GameStates.Menu;
import com.main.GameStates.STATE;
import com.main.GameStates.Settings;
import com.main.GameStates.Shop;
import com.main.gfx.Background;
import com.main.gfx.BufferedImageLoader;
import com.main.gfx.Window;

public class Game extends Canvas implements Runnable {

  private static final long serialVersionUID = 6621720780426957385L;

  public static int WIDTH = 1280, HEIGHT = 720;

  private Thread thread;
  private boolean running = false;

  private Random r;
  private Handler handler;
  private HUD hud;
  private Spawn spawner;
  private Menu menu;
  private Color col;
  private Shop shop;
  private Player player;
  private Settings settings;
  private Background bg;

  // private int s1 = 0;
  public int s2 = 0;

  public static STATE gameState = STATE.Loading;
  public static boolean paused = false;
  public static int diff;
  public static int diff2;

  public static BufferedImage spritesheet;
  public static BufferedImage entity001;
  public static BufferedImage bg1;
  public static BufferedImage bg2;
  public static BufferedImage bg3;
  public static BufferedImage bg4;
  public static BufferedImage bg5;
  public static BufferedImage bg6;

  public Game() {
    init();
    handler.addParticles(r);
    AudioPlayer.load();

    gameState = STATE.Menu;
    handler.StartMusic();

    handler.rNumEntity001(r);
    handler.doesSpawnEntity001(r);
  }

  public void init() {
    handler = new Handler();
    hud = new HUD(handler);
    shop = new Shop(handler, hud);
    r = new Random();
    spawner = new Spawn(handler, hud, this, player);
    menu = new Menu(this, handler, hud, shop);
    settings = new Settings(handler, this, shop, hud);
    BufferedImageLoader loader = new BufferedImageLoader();

    spritesheet = loader.loadImage("/sprite_sheet.png");
    entity001 = loader.loadImage("/Entity001.png");
    bg = new Background();

    this.addKeyListener(new KeyInput(handler, this, hud, shop, settings));
    this.addMouseListener(menu);
    this.addMouseListener(shop);
    this.addMouseListener(settings);

    new Window(WIDTH, HEIGHT, "BLoCky AwOId", this);
  }

  public synchronized void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public synchronized void stop() {
    try {
      thread.join();
      running = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;

    while (running) {
      boolean shouldRender = false;
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;

      while (delta >= 1) {
        tick();
        delta--;
        shouldRender = true;
      }

      if (shouldRender) {
        render();
        frames++;
      }

      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        System.out.println("FPS: " + frames);
        frames = 0;
      }
    }
    stop();
  }

  private void tick() {
    bg.tick();

    if (gameState == STATE.Settings) {
      handler.tick();
      settings.tick();
    }

    if (gameState == STATE.Shop || gameState == STATE.Shop2) {
      hud.tick();
    }

    if (gameState == STATE.Respawn) {
      paused = true;
    }

    if (gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.SelectMode
        || gameState == STATE.SelectMode2 || gameState == STATE.Loading || gameState == STATE.Help
        || gameState == STATE.Help2 || gameState == STATE.Respawn) {
      if (!paused) {
        menu.tick();
        if (Settings.EnableParticles)
          handler.tick();
      }
    }

    if (gameState == STATE.Game || gameState == STATE.Game2) {
      if (!paused) {
        handler.tick();
        hud.tick();
        spawner.tick();

        if (HUD.HEALTH <= 0 || HUD.HEALTH2 <= 0) {
          if (handler.getCash() >= (shop.respawn)) {
            gameState = STATE.Respawn;
          } else {
            gameState = STATE.GameOver;
          }
        }
      }
    }

    if (handler.getCash() < 0) {
      handler.setCash(0);
    }
  }

  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();

    g.setColor(Color.black);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    // g.fillRect(0, 0, getWidth(), getHeight());

    bg.render(g);

    if (gameState == STATE.Settings) {
      if (Settings.EnableParticles) {
        handler.render(g);
      }
      settings.render(g);
    }

    if (gameState == STATE.Shop || gameState == STATE.Shop2) {
      shop.render(g);
    }

    if (gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.SelectMode
        || gameState == STATE.SelectMode2 || gameState == STATE.Loading || gameState == STATE.Help
        || gameState == STATE.Help2 || gameState == STATE.Respawn) {
      if (Settings.EnableParticles) {
        handler.render(g);
      }
      menu.render(g);
    }

    if (gameState == STATE.Game || gameState == STATE.Game2) {
      handler.render(g);
      if (paused) {
        g.setColor(col);
        g.drawString("PAUSED", 150, 150);
      }
      hud.render(g);
    }
    // g.setColor(Color.WHITE);
    // g.drawLine(Game.WIDTH / 2, 0, Game.WIDTH / 2, Game.WIDTH);
    g.dispose();
    bs.show();
  }

  public static float clamp(float var, float min, float max) {
    if (var >= max)
      return var = max;
    else if (var <= min)
      return var = min;
    else
      return var;
  }

  public static void main(String[] args) {
    new Game();
  }

  public BufferedImage getSpriteSheet() {
    return spritesheet;
  }
}
