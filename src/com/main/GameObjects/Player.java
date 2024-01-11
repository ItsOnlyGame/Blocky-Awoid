package com.main.GameObjects;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.main.Game;
import com.main.Handler;
import com.main.GameStates.HUD;
import com.main.gfx.SpriteSheet;

public class Player extends GameObject {
	
	Random r = new Random();
	Handler handler;
	
	public static int lastY;
	public static int lastX;
	
	private BufferedImage entity;
	
	public Player(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);	
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		entity = ss.grabImage(1, 1, 32, 32);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		y = Game.clamp(y, 0, Game.HEIGHT - 32);
		x = Game.clamp(x, 0, Game.WIDTH - 32);
		
		collision();
		
		setLastX((int)x);
		setLastY((int)y);
	}
	
	private void collision() {
		for (GameObject tempObject : handler.object) {
			if (tempObject.getId() == ID.BasicEnemy ||tempObject.getId() == ID.BigEnemy ||tempObject.getId() == ID.FastEnemy ||tempObject.getId() == ID.SmartEnemy ||tempObject.getId() == ID.HugeEnemy || tempObject.getId() == ID.RollerEnemy )
			{
				if (getBounds().intersects(tempObject.getBounds()))
				{
					HUD.HEALTH -= 1;
				}
			}
		}
	}
	
	public void render(Graphics g)
	{	
		g.drawImage(entity, (int)x, (int)y, null);
	}

	public static int getLastY() {
		return lastY;
	}

	public void setLastY(int lastY) {
		Player.lastY = lastY;
	}

	public static int getLastX() {
		return lastX;
	}

	public void setLastX(int lastX) {
		Player.lastX = lastX;
	}

}
