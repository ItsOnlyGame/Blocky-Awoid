package com.main.GameObjects;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.main.Game;
import com.main.Handler;
import com.main.GameStates.HUD;
import com.main.gfx.SpriteSheet;

public class Player2 extends GameObject {
	
	Random r = new Random();
	Handler handler;	
	private BufferedImage entity;	
	
	public Player2(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);	
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		entity = ss.grabImage(4, 4, 32, 32);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		
		collision();
	}
	
	private void collision()
	{
		for (int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.BasicEnemy ||tempObject.getId() == ID.BigEnemy ||tempObject.getId() == ID.FastEnemy ||tempObject.getId() == ID.SmartEnemy ||tempObject.getId() == ID.HugeEnemy )
			{
				if (getBounds().intersects(tempObject.getBounds()))
				{
					HUD.HEALTH2 -= 1;
				}
			}
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(entity, (int)x, (int)y, null);
	}

}
