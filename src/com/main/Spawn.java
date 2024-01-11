package com.main;

import java.util.Random;

import com.main.GameObjects.BasicEnemy;
import com.main.GameObjects.BigEnemy;
import com.main.GameObjects.FastEnemy;
import com.main.GameObjects.HugeEnemy;
import com.main.GameObjects.ID;
import com.main.GameObjects.Player;
import com.main.GameStates.HUD;
import com.main.GameStates.STATE;

public class Spawn {

  private Handler handler;
  private HUD hud;
  private Random r = new Random();
  private Game game;

  private int Entity001Exit = 0;

  public Spawn(Handler handler, HUD hud, Game game, Player player) {
    this.handler = handler;
    this.hud = hud;
    this.game = game;
  }

  public void tick() {

    if (handler.Entity001Exit) {
      Entity001Exit++;
      for (int i = 0; i < 50; i++) {
        handler.addObject(
            new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.BasicEnemy, handler, game));
        handler.addObject(
            new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.FastEnemy, handler, game));
        handler.addObject(
            new BigEnemy(r.nextInt(Game.WIDTH - 35), r.nextInt(Game.HEIGHT - 35), ID.BigEnemy, handler, game));
        handler.addObject(
            new HugeEnemy(r.nextInt(Game.WIDTH - 70), r.nextInt(Game.HEIGHT - 70), ID.HugeEnemy, handler, game));
      }
      if (Entity001Exit >= 50) {
        System.exit(1);
      }
    }

    if (handler.whichEnemy == 0) {
      handler.whichEnemy(r);
    }
    handler.scoreKeep++;

    if (Game.gameState == STATE.Game2) {

      if (handler.scoreKeep >= handler.nextRound(hud, game)) {
        handler.scoreKeep = 0;
        hud.setRound(hud.getRound() + 1);
        handler.Cash += hud.getRound() * 2 + 50;

        handler.SpawnAmount(r, hud, game);
      }
    }

    if (Game.gameState == STATE.Game) {
      if (handler.scoreKeep >= handler.nextLevel(hud, game)) {
        handler.scoreKeep = 0;

        handler.SpawnAmount(r, hud, game);

        hud.setLevel(hud.getLevel() + 1);
        handler.Cash += hud.getLevel() * 2 + 50;

      }
    }
  }

}
