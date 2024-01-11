package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.main.GameObjects.GameObject;
import com.main.GameObjects.ID;
import com.main.GameStates.HUD;
import com.main.GameStates.STATE;
import com.main.GameStates.Settings;
import com.main.GameStates.Shop;

public class KeyInput extends KeyAdapter {

  private Handler handler;
  private boolean[] keyDown = new boolean[4];

  Game game;

  public KeyInput(Handler handler, Game game, HUD hud, Shop shop, Settings settings) {
    this.handler = handler;
    this.game = game;

    keyDown[0] = false;
    keyDown[1] = false;
    keyDown[2] = false;
    keyDown[3] = false;
  }

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();

    for (int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);

      if (tempObject.getId() == ID.Player) {
        if (key == KeyEvent.VK_W) {
          tempObject.setVelY(-handler.speed - handler.shift);
          keyDown[0] = true;
        }
        if (key == KeyEvent.VK_S) {
          tempObject.setVelY(handler.speed + handler.shift);
          keyDown[1] = true;
        }
        if (Game.gameState == STATE.Game) {
          if (key == KeyEvent.VK_D) {
            tempObject.setVelX(handler.speed + handler.shift);
            keyDown[2] = true;
          }
          if (key == KeyEvent.VK_A) {
            tempObject.setVelX(-handler.speed - handler.shift);
            keyDown[3] = true;
          }
        }

      }
    }

    for (int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);

      if (tempObject.getId() == ID.Player2) {
        if (key == KeyEvent.VK_UP) {
          tempObject.setVelY(-handler.speed - handler.shift);
          keyDown[0] = true;
        }
        if (key == KeyEvent.VK_DOWN) {
          tempObject.setVelY(handler.speed + handler.shift);
          keyDown[1] = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
          tempObject.setVelX(handler.speed + handler.shift);
          keyDown[2] = true;
        }
        if (key == KeyEvent.VK_LEFT) {
          tempObject.setVelX(-handler.speed - handler.shift);
          keyDown[3] = true;
        }
      }
    }

    if (key == KeyEvent.VK_P) {
      if (Game.paused)
        Game.paused = false;
      else
        Game.paused = true;
    }
    if (Game.gameState == STATE.Game) {
      if (key == KeyEvent.VK_I) {
        Game.gameState = STATE.Game;
      }
    }
    if (key == KeyEvent.VK_ESCAPE)
      System.exit(1);

    if (key == KeyEvent.VK_SPACE && !handler.justPressed) {
      if (Game.gameState == STATE.Settings) {
        Game.gameState = STATE.Menu;
      }

      if (Game.gameState == STATE.SelectMode) {
        Game.gameState = STATE.Menu;
      }

      if (Game.gameState == STATE.SelectMode2) {
        Game.gameState = STATE.Menu;
      }

      if (Game.gameState == STATE.Game) {
        Game.gameState = STATE.Shop;
      } else if (Game.gameState == STATE.Shop) {
        Game.gameState = STATE.Game;
      }

      if (Game.gameState == STATE.Game2) {
        Game.gameState = STATE.Shop2;
      } else if (Game.gameState == STATE.Shop2) {
        Game.gameState = STATE.Game2;
      }

      if (Game.gameState == STATE.Help) {
        Game.gameState = STATE.SelectMode;
      }

      if (Game.gameState == STATE.Help2) {
        Game.gameState = STATE.SelectMode2;
      }

      if (Game.gameState == STATE.GameOver) {
        Game.gameState = STATE.Menu;
      }

    }

    if (key == KeyEvent.VK_O) {
      if (Game.gameState == STATE.Game || Game.gameState == STATE.Game2) {
        Game.paused = false;
        handler.clearEnemys();
        Game.gameState = STATE.GameOver;
      }
    }
  }

  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();

    for (int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);

      if (tempObject.getId() == ID.Player) {
        if (key == KeyEvent.VK_W)
          keyDown[0] = false; // tempObject.setVelY(0);
        if (key == KeyEvent.VK_S)
          keyDown[1] = false; // tempObject.setVelY(0);
        if (key == KeyEvent.VK_D)
          keyDown[2] = false; // tempObject.setVelX(0);
        if (key == KeyEvent.VK_A)
          keyDown[3] = false; // tempObject.setVelX(0);

        if (!keyDown[0] && !keyDown[1])
          tempObject.setVelY(0);
        if (!keyDown[2] && !keyDown[3])
          tempObject.setVelX(0);
      }

      if (tempObject.getId() == ID.Player2) {
        if (key == KeyEvent.VK_UP)
          keyDown[0] = false; // tempObject.setVelY(0);
        if (key == KeyEvent.VK_DOWN)
          keyDown[1] = false; // tempObject.setVelY(0);
        if (key == KeyEvent.VK_RIGHT)
          keyDown[2] = false; // tempObject.setVelX(0);
        if (key == KeyEvent.VK_LEFT)
          keyDown[3] = false; // tempObject.setVelX(0);

        if (!keyDown[0] && !keyDown[1])
          tempObject.setVelY(0);
        if (!keyDown[2] && !keyDown[3])
          tempObject.setVelX(0);
      }
    }
    if (key == KeyEvent.VK_SHIFT) {
      handler.shift = 0;
    }
  }

}
