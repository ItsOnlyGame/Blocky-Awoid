package com.main.GameObjects;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.main.Handler;
import com.main.gfx.SpriteSheet;

public class Entity001 extends GameObject{
	
	Handler handler;
	private BufferedImage entity;
	
	public Entity001(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 0;
		velY = 1;
		
		SpriteSheet ss = new SpriteSheet(Game.entity001);
		entity = ss.grabImage(1, 1, 64, 64);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 0, 0);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if (y == (Game.HEIGHT / 2 - 32)) {
			velY = 0;
		}
		
		if (y == (Game.HEIGHT / 2 - 32)) {
			handler.Entity001Exit = true;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(entity, (int)x, (int)y, null);
	}

}
