package com.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.main.GameObjects.BasicEnemy;
import com.main.GameObjects.BigEnemy;
import com.main.GameObjects.Entity001;
import com.main.GameObjects.FastEnemy;
import com.main.GameObjects.GameObject;
import com.main.GameObjects.HugeEnemy;
import com.main.GameObjects.ID;
import com.main.GameObjects.MenuParticle;
import com.main.GameObjects.Player;
import com.main.GameObjects.Player2;
import com.main.GameObjects.RollerEnemy;
import com.main.GameObjects.SmartEnemy;
import com.main.GameStates.HUD;
import com.main.GameStates.Menu;
import com.main.GameStates.STATE;
import com.main.GameStates.Settings;
import com.main.GameStates.Shop;

public class Handler {

  public LinkedList<GameObject> object = new LinkedList<GameObject>();

  public int shift = 0;
  public int shiftSet = 4;
  public int speed = 5;
  public int scoreKeep = 0;
  public int totalScore = 0;
  public int HealthUpg = 0;
  public int Cash = 0;
  public int NmrOfplayers = 1;
  public int enemyAmount = 3;
  public int scoreKeepE = 0;

  public int nextRound = 0;
  public int nextLevel = 0;
  public int whichEnemy = 0;
  public int oldwhichEnemy = 0;

  public int decreaseEnemySpawning = 0;

  public int doesSpawnEntity001 = 0;
  public int rNumEntity001 = 0;
  public boolean DoNotSpawn = false;
  public boolean Entity001Exit = false;

  public boolean justPressed = false;

  private int s = 1;

  public void tick() {
    for (int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);

      tempObject.tick();
    }

    if (Game.gameState == STATE.Game) {
      Cash++;
    }

    if (Game.gameState == STATE.Game2) {
      if (s > 15) {
        Cash++;
        s = 0;
      }
    }
  }

  public void render(Graphics g) {
    try {
      for (GameObject tempObject : object) {
        tempObject.render(g);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.toString());
    }
  }

  public void clearEnemys() {
    for (int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);

      if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2) {
        object.clear();
      }
    }
  }

  public void startGame(Game game, Random r) {
    rNumEntity001(r);
    doesSpawnEntity001(r);

    if (Game.gameState == STATE.Game) {
      if (Game.diff == 0) {
        for (int i = 0; i < 6; i++)
          addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, this, game));
      }

      if (Game.diff == 1) {
        for (int i = 0; i < 9; i++)
          addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, this, game));
      }
    }

    if (Game.gameState == STATE.Game2) {
      if (Game.diff2 == 0) {
        for (int i = 0; i < 6; i++)
          addObject(new BasicEnemy(r.nextInt(Game.WIDTH + 100) + 500, r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, this,
              game));
      }

      if (Game.diff2 == 1) {
        for (int i = 0; i < 12; i++)
          addObject(new BasicEnemy(r.nextInt(Game.WIDTH + 100) + 500, r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, this,
              game));
      }
    }
  }

  public void addPlayer(Game game) {
    if (Game.gameState == STATE.Game) {
      if (Game.diff == 0) {
        if (NmrOfplayers == 2) {
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
          clearEnemys();
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
          addObject(new Player2(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player2, this, game));
        } else {
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
          clearEnemys();
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
        }
      }
      if (Game.diff == 1) {
        if (NmrOfplayers == 2) {
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
          clearEnemys();
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
          addObject(new Player2(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player2, this, game));
        } else {
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
          clearEnemys();
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
        }
      }

      if (Game.diff == 2) {
        if (NmrOfplayers == 2) {
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
          clearEnemys();
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
          addObject(new Player2(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player2, this, game));
        } else {
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
          clearEnemys();
          addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, this, game));
        }
      }
    }

    if (Game.gameState == STATE.Game2) {
      if (Game.diff2 == 0) {
        addObject(new Player(30, Game.HEIGHT / 2 - 32, ID.Player, this, game));
        clearEnemys();
        addObject(new Player(30, Game.HEIGHT / 2 - 32, ID.Player, this, game));
      }

      if (Game.diff2 == 1) {
        addObject(new Player(30, Game.HEIGHT / 2 - 32, ID.Player, this, game));
        clearEnemys();
        addObject(new Player(30, Game.HEIGHT / 2 - 32, ID.Player, this, game));
      }
    }
  }

  public void resetUpg(Shop shop, HUD hud, Menu menu) {

    shop.lowerPrices = 0;
    shop.B1 = 500;
    shop.B2 = 500;
    shop.B3 = 750;
    shop.B4 = 250000;
    shop.B5 = 1000;
    menu.s1 = 0;
    shiftSet = 2;
    speed = 5;
    HUD.HEALTH = 200;
    HUD.HEALTH2 = 200;
    totalScore = 0;
    scoreKeep = 0;
    HealthUpg = 0;
    setCash(0);
    hud.setLevel(1);
    hud.setRound(1);
    Game.paused = false;
  }

  public void StartMusic() {
    StopMusic();

    if (Game.gameState == STATE.Menu) {
      if (Settings.EnableMainMenuMusic)
        AudioPlayer.playMusic("MainMenu");
    }

    if (Game.gameState == STATE.Game) {
      if (Settings.EnableGameMusic) {
        if (Game.diff == 0) {
          AudioPlayer.playMusic("GameMusic");
        }
        if (Game.diff == 1) {
          AudioPlayer.playMusic("GameMusic_E");
        }
      }
    }

    if (Game.gameState == STATE.Game2) {
      if (Settings.EnableGameMusic) {
        if (Game.diff2 == 0) {
          AudioPlayer.playMusic("GameMusic");
        }
        if (Game.diff2 == 1) {
          AudioPlayer.playMusic("GameMusic_E");
        }
      }
    }
  }

  public void StopMusic() {
    AudioPlayer.stopMusic();
  }

  public void SpawnAmount(Random r, HUD hud, Game game) {
    if (Game.gameState == STATE.Game2) {
      for (int i = 0; i < enemyAmount(hud, game); i++) {
        addObject(new BasicEnemy(r.nextInt(Game.WIDTH) + 800, r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, this, game));
      }
      if (scoreKeep == scoreKeepE) {
        for (int i = 0; i < enemyAmount(hud, game) + 2; i++) {
          addObject(
              new BasicEnemy(r.nextInt(Game.WIDTH) + 800, r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, this, game));
        }
      }
    }

    if (Game.gameState == STATE.Game) {

      if (rNumEntity001 == doesSpawnEntity001 && !DoNotSpawn) {
        StopMusic();
        clearEnemys();
        addObject(new Entity001(Game.WIDTH / 2 - 32, 0, ID.Entity001, this, game));
        DoNotSpawn = true;
      }

      if (!DoNotSpawn) {
        for (int i = 0; i < enemyAmount(hud, game); i++) {

          whichEnemy(r);

          if (whichEnemy == 1) {
            addObject(
                new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.BasicEnemy, this, game));
          }

          if (whichEnemy == 2) {
            addObject(
                new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.SmartEnemy, this, game));
          }

          if (whichEnemy == 3) {
            addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.FastEnemy, this, game));
          }

          if (whichEnemy == 4) {
            addObject(new BigEnemy(r.nextInt(Game.WIDTH - 35), r.nextInt(Game.HEIGHT - 35), ID.BigEnemy, this, game));
          }

          if (whichEnemy == 5) {
            addObject(new HugeEnemy(r.nextInt(Game.WIDTH - 70), r.nextInt(Game.HEIGHT - 70), ID.HugeEnemy, this, game));
          }

          if (whichEnemy == 6) {
            addObject(new RollerEnemy(r.nextInt(Game.WIDTH - 20), 0, ID.RollerEnemy, this, game));
          }
        }
      }
    }
  }

  public int whichEnemy(Random r) {
    double i = r.nextInt(6) + 1;
    whichEnemy = (int) i;
    return whichEnemy;
  }

  public int doesSpawnEntity001(Random r) {
    double i = r.nextInt(100) + 1;
    doesSpawnEntity001 = (int) i;
    return doesSpawnEntity001;
  }

  public int rNumEntity001(Random r) {
    double i = r.nextInt(100) + 1;
    rNumEntity001 = (int) i;
    return rNumEntity001;
  }

  public int enemyAmount(HUD hud, Game game) {
    if (Game.gameState == STATE.Game2) {
      if (Game.diff2 == 0) {
        int i = hud.getRound() + 2;
        enemyAmount = i * 2;
      }
      if (Game.diff2 == 1) {
        int i = hud.getRound() + 4;
        enemyAmount = i * 2;
      }
    }

    if (Game.gameState == STATE.Game) {
      if (Game.diff == 0) {
        int i = hud.getLevel() * 2;
        if (hud.getLevel() == 1) {
          enemyAmount = i + 1 - decreaseEnemySpawning;
        } else {
          enemyAmount = i - decreaseEnemySpawning;
        }
      }

      if (Game.diff == 1) {
        int i = hud.getLevel() * 3;
        if (hud.getLevel() == 1) {
          enemyAmount = i + 1 - decreaseEnemySpawning;
        } else {
          enemyAmount = i - decreaseEnemySpawning;
        }
      }
    }

    return enemyAmount;
  }

  public int ScoreKeepEnemy(HUD hud) {
    if (Game.gameState == STATE.Game2) {
      int i = 0;
      scoreKeepE = 25 + i;
    }

    if (Game.gameState == STATE.Game) {
    }

    return scoreKeepE;
  }

  public int nextRound(HUD hud, Game game) {
    if (Game.diff2 == 0) {
      nextRound = ((hud.getRound() * 100) / 2) + 50;
    }
    if (Game.diff2 == 1) {
      nextRound = ((hud.getRound() * 100) / 2) + 100;
    }
    return nextRound;
  }

  public int nextLevel(HUD hud, Game game) {
    if (Game.diff == 0) {
      nextLevel = (747 + (hud.getLevel() * 3));
    }

    if (Game.diff == 1) {
      nextLevel = (443 + (hud.getLevel() * 7));
    }

    return nextLevel;
  }

  public int totalScore(Game game, HUD hud) {
    if (Game.gameState == STATE.Game) {
      totalScore = hud.getLevel() * nextLevel + scoreKeep - nextLevel;
    }

    if (Game.gameState == STATE.Game2) {
    }

    return totalScore;
  }

  public void addParticles(Random r) {
    for (int i = 0; i < 50; i++) {
      addObject(new MenuParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.MenuParticle, this));
    }

  }

  public int getCash() {
    return Cash;
  }

  public void setCash(int cash) {
    Cash = cash;
  }

  public void addObject(GameObject object) {
    this.object.add(object);
  }

  public void removeObject(GameObject object) {
    this.object.remove(object);
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

}
