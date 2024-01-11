package com.main.GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.main.Handler;
import com.main.gfx.SpriteSheet;

public class HugeEnemy extends GameObject{
	
	Handler handler;
	private BufferedImage entity;
		
	public HugeEnemy(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		
		velX = -4;
		velY = 6;

		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		entity = ss.grabImage(2, 2, 64, 64);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 64, 64);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 64) velY *= -1; 
		if (x <= 0 || x >= Game.WIDTH - 64) velX *= -1; 
	}
	
	public void render(Graphics g)
	{
		g.drawImage(entity, (int)x, (int)y, null);
	}

}

