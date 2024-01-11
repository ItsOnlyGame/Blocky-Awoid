package com.main.GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.main.Handler;
import com.main.GameStates.STATE;
import com.main.gfx.SpriteSheet;

public class BasicEnemy extends GameObject {

  Handler handler;
  private BufferedImage entity;

  public BasicEnemy(int x, int y, ID id, Handler handler, Game game) {
    super(x, y, id);
    this.handler = handler;
    if (Game.gameState == STATE.Game) {
      velY = 6;
      velX = -6;
    }

    if (Game.gameState == STATE.Game2) {
      velX = -6;
    }

    SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
    entity = ss.grabImage(2, 1, 16, 16);
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 16, 16);
  }

  public void tick() {
    if (Game.gameState == STATE.Game) {
      x += velX;
      y += velY;

      if (x <= 0 || x >= Game.WIDTH - 16)
        velX *= -1;
      if (y <= 0 || y >= Game.HEIGHT - 16)
        velY *= -1;
    }

    if (Game.gameState == STATE.Game2) {
      x += velX;
      if (x < -10) {
        handler.removeObject(this);
      }
    }

  }

  public void render(Graphics g) {
    g.drawImage(entity, (int) x, (int) y, null);
  }

}
