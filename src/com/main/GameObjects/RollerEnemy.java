package com.main.GameObjects;



import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.main.Game;
import com.main.Handler;
import com.main.gfx.SpriteSheet;

public class RollerEnemy extends GameObject{
	
	Handler handler;
	private BufferedImage entity;
	Random r;
		
	public RollerEnemy(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		
		velY = 5;
		velX = 0;
		

		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		entity = ss.grabImage(2, 1, 16, 16);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick()
	{
		if (y > Game.HEIGHT + 32) {
			velY = 0;
			handler.removeObject(this);
		}
		
		x += velX;
		y += velY;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(entity, (int)x, (int)y, null);
	}

}

