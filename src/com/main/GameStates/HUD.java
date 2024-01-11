package com.main.GameStates;

import java.awt.Color;
import java.awt.Graphics;

import com.main.Game;
import com.main.Handler;

public class HUD {
	
	public static float HEALTH = 200;
	private float greenValue = 255;
	
	public static float HEALTH2 = 200;
	private float greenValue2 = 255;
	
	private int level = 1;
	private int round = 1;

	private Handler handler;
	
	public HUD(Handler handler) {
		this.handler = handler;
	}
	
	
	public void tick()
	{
		
		if (Game.gameState == STATE.Game2) {
			HEALTH = Game.clamp(HEALTH, 0, 50);
			greenValue = HEALTH*4;
			greenValue = Game.clamp((int)greenValue, (int)0, (int)255);
		}
		
		if (Game.gameState == STATE.Game) {
			if (handler.NmrOfplayers == 2) {
				
				HEALTH = Game.clamp(HEALTH, 0, 200);
				HEALTH2 = Game.clamp(HEALTH2, 0, 200);

				greenValue = HEALTH*2;
				greenValue2 = HEALTH2*2;

				greenValue = Game.clamp((int)greenValue, (int)0, (int)255);
				greenValue2 = Game.clamp((int)greenValue2, (int)0, (int)255);
			} else {
				
				HEALTH = Game.clamp(HEALTH, 0, 200 + (handler.HealthUpg / 2));
				greenValue = HEALTH*2;
				greenValue = Game.clamp((int)greenValue, (int)0, (int)255);
			}
		}
	}
	
	public void render(Graphics g)
	{
		if (Game.gameState == STATE.Game2) {
			g.setColor(Color.gray);
			g.fillRect(Game.WIDTH / 2 - 100, 15, 200 + handler.HealthUpg / 2, 32);
			g.setColor(new Color((int)75, (int)greenValue, (int)0));
			g.fillRect((int)Game.WIDTH / 2 - 100, (int)15, (int)HEALTH * 4, (int)32);
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH / 2 - 100, 15, 200 + handler.HealthUpg / 2, 32);
			
			g.drawString("Cash: " + handler.Cash, Game.WIDTH / 2 - 30, 80);
			g.drawString("Round: " + round, Game.WIDTH / 2 - 25, 95);
			g.drawString("Space for SHOP", Game.WIDTH / 2 - 45, 65);
		}
		
		if (Game.gameState == STATE.Game) {
			if (handler.NmrOfplayers == 2) {
				
				g.setColor(Color.gray);
				g.fillRect(Game.WIDTH / 2 - 300, 15, 200, 32);
				g.setColor(new Color((int)75, (int)greenValue, (int)0));
				g.fillRect((int)Game.WIDTH / 2 - 300, (int)15, (int)HEALTH, (int)32);
				g.setColor(Color.white);
				g.drawRect(Game.WIDTH / 2 - 300, 15, 200, 32);
				
				g.setColor(Color.gray);
				g.fillRect(Game.WIDTH / 2 + 300, 15, -200, 32);
				g.setColor(new Color((int)75, (int)greenValue2, (int)0));
				g.fillRect((int)Game.WIDTH / 2 + 300, (int)15, (int)-HEALTH2, (int)32);
				g.setColor(Color.white);
				g.drawRect(Game.WIDTH / 2 + 100, 15, 200, 32);
				
				g.drawString("Cash: " + handler.Cash, Game.WIDTH / 2 - 27, 55);
				g.drawString("Level: " + level, Game.WIDTH / 2 - 20, 70);
				g.drawString("Space for SHOP", Game.WIDTH / 2 - 45, 40);
				
				g.drawString("Player 1 - Health", Game.WIDTH / 2 - 300, 70);
				g.drawString("Player 2 - Health", Game.WIDTH / 2 + 200, 70);

				
			} else {
				
				g.setColor(Color.gray);
				g.fillRect(Game.WIDTH / 2 - 100, 15, 200 + handler.HealthUpg / 2, 32);
				g.setColor(new Color((int)75, (int)greenValue, (int)0));
				g.fillRect((int)Game.WIDTH / 2 - 100, (int)15, (int)HEALTH, (int)32);
				g.setColor(Color.white);
				g.drawRect(Game.WIDTH / 2 - 100, 15, 200 + handler.HealthUpg / 2, 32);
				
				g.drawString("Cash: " + handler.Cash, Game.WIDTH / 2 - 30, 80);
				g.drawString("Level: " + level, Game.WIDTH / 2 - 25, 95);
				g.drawString("Space for SHOP", Game.WIDTH / 2 - 45, 65);
			}
		}
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}


	public int getRound() {
		return round;
	}


	public void setRound(int round) {
		this.round = round;
	}
	
}
