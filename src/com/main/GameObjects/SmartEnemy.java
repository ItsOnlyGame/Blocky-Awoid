package com.main.GameObjects;



import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.main.Handler;
import com.main.gfx.SpriteSheet;

public class SmartEnemy extends GameObject{
	
	Handler handler;

	private GameObject player;
	
	private BufferedImage entity;

	
	public SmartEnemy(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		
		for (int i = 0; i < handler.object.size(); i++)
		{
			if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
			if (handler.object.get(i).getId() == ID.Player2) player = handler.object.get(i);
		}
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		entity = ss.grabImage(3, 1, 16, 16);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 16;
		float diffY = y - player.getY() - 16;
		float distance = (float) Math.sqrt((x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()));
		
		velX = ((-1/distance) * diffX);
		velY = ((-1/distance) * diffY);
	}
	
	public void render(Graphics g)
	{
		g.drawImage(entity, (int)x, (int)y, null);
	}

}

