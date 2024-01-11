package com.main.GameObjects;



import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.main.Handler;
import com.main.gfx.SpriteSheet;

public class FastEnemy extends GameObject{
	
	Handler handler;
	private BufferedImage entity;
	
	public FastEnemy(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
	
		velX = 9;
		velY = 3;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		entity = ss.grabImage(1, 2, 16, 16);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 16) velY *= -1; 
		if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1; 
	}
	
	public void render(Graphics g)
	{
		g.drawImage(entity, (int)x, (int)y, null);
	}

}
