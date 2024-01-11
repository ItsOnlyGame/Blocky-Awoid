package com.main.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.main.Game;
import com.main.Handler;

public class Shop extends MouseAdapter {

	private Handler handler;
	HUD hud;
	Random r;
	
	public int lowerPrices = 0;
	
	public int B1 = 500;
	public int B2 = 500;
	public int B3 = 750;
	public int B4 = 250000;
	public int B5 = 1000;
	public int B6 = 1200;
	public int respawn = 2000;
	
	public Shop(Handler handler, HUD hud)
	{
		this.handler = handler;
		this.hud = hud;
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;

	}
	
	public void render(Graphics g)
	{
		
		if (Game.gameState == STATE.Shop2) {
			
			g.setColor(Color.white);
			g.setFont(new Font("arial", 0, 48));
			g.drawString("Shop", Game.WIDTH/2-70, 50);
			
			g.setFont(new Font("arial", 0, 14));
			g.drawString("Refill Health", 100, 120);
			g.drawString("Cost: " + (B1 - lowerPrices), 100, 140);
			g.drawRect(90, 100, 120, 60);
			
			g.drawString("Upgrade Speed", 230, 120);
			g.drawString("Cost: " + (B2 - lowerPrices), 230, 140);
			g.drawRect(220, 100, 120, 60);
			
			g.drawString("Cash: " + handler.getCash(), Game.WIDTH/2-70, 480);
			g.drawString("Press Space to Go Back!", Game.WIDTH/2-105, 500);
		}
		
		if (Game.gameState == STATE.Shop) {
			if (handler.NmrOfplayers == 1) {
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", 0, 48));
				g.drawString("Shop", Game.WIDTH/2-70, 50);
				
				
				g.setFont(new Font("arial", 0, 14));
				g.drawString("Refill Health", 100, 120);
				g.drawString("Cost: " + (B1 - lowerPrices), 100, 140);
				g.drawRect(90, 100, 120, 60);
				
				g.drawString("Upgrade Speed", 230, 120);
				g.drawString("Cost: " + (B2 - lowerPrices), 230, 140);
				g.drawRect(220, 100, 120, 60);
				
				
				g.drawString("Upgrade Health", 360, 120);
				g.drawString("Cost: " + (B3 - lowerPrices), 360, 140);
				g.drawRect(350, 100, 120, 60);
				
				g.drawString("Unlimited Score", 490, 120);
				g.drawString("Cost: " + B4, 490, 140);
				g.drawRect(480, 100, 120, 60);
				
				g.drawString("Lower Prices", 100, 200);
				g.drawString("Cost: " + B5, 100, 220);
				g.drawRect(90, 180, 120, 60);
				
				g.drawString("Enemy Spawn -2", 230, 200);
				g.drawString("Cost: " + B6, 230, 220);
				g.drawRect(220, 180, 120, 60);
				
				
				g.drawString("Cash: " + handler.getCash(), Game.WIDTH/2-70, 480);
				g.drawString("Press Space to Go Back!", Game.WIDTH/2-105, 500);
				
			}
			
			if (handler.NmrOfplayers == 2) {
				g.setColor(Color.white);
				g.setFont(new Font("arial", 0, 48));
				g.drawString("Shop", Game.WIDTH/2-70, 50);
				
				
				g.setFont(new Font("arial", 0, 14));
				g.drawString("Refill Health", 100, 120);
				g.drawString("Cost: " + (B1 - lowerPrices), 100, 140);
				g.drawRect(90, 100, 120, 60);
				
				g.drawString("Upgrade Speed", 230, 120);
				g.drawString("Cost: " + (B2 - lowerPrices), 230, 140);
				g.drawRect(220, 100, 120, 60);
				
				g.drawString("Lower Prices", 360, 120);
				g.drawString("Cost: " + B5, 360, 140);
				g.drawRect(350, 100, 120, 60);
				
				g.drawString("Unlimited Score", 490, 120);
				g.drawString("Cost: " + B4, 490, 140);
				g.drawRect(480, 100, 120, 60);
				
				g.drawString("Enemy Spawn -2", 100, 200);
				g.drawString("Cost: " + B6, 100, 220);
				g.drawRect(90, 180, 120, 60);

				
				g.drawString("Cash: " + handler.getCash(), Game.WIDTH/2-70, 480);
				g.drawString("Press Space to Go Back!", Game.WIDTH/2-105, 500);
				
			}
		}
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		if (Game.gameState == STATE.Shop2) {
			
			if (mouseOver(mx, my, 70, 100, 120, 60))
			{
				if (handler.getCash() >= (B1 - lowerPrices))
				{
					if (HUD.HEALTH < 200 + handler.HealthUpg) {
						handler.setCash(handler.getCash() - (B1 - lowerPrices));
						B1 += 350;
						HUD.HEALTH = 200 + handler.HealthUpg;
					}
				}
			}
			
			if (mouseOver(mx, my, 200, 100, 120, 60))
			{
				if (handler.getCash() >= (B2 - lowerPrices))
				{
					handler.setCash(handler.getCash() - (B2 - lowerPrices));
					B2 += 500;
					handler.speed++;
				}
			}
		}
		
		
		if (Game.gameState == STATE.Shop) {
			if (handler.NmrOfplayers == 1) {
				if (mouseOver(mx, my, 70, 100, 120, 60))
				{
					if (handler.getCash() >= (B1 - lowerPrices))
					{
						if (HUD.HEALTH < 200 + handler.HealthUpg) {
							handler.setCash(handler.getCash() - (B1 - lowerPrices));
							B1 += 350;
							HUD.HEALTH = 200 + handler.HealthUpg;
						}
					}
				}
				
				
				if (mouseOver(mx, my, 200, 100, 120, 60))
				{
					if (handler.getCash() >= (B2 - lowerPrices))
					{
						handler.setCash(handler.getCash() - (B2 - lowerPrices));
						B2 += 500;
						handler.speed++;
					}
				}
				
				if (mouseOver(mx, my, 350, 100, 120, 60))
				{
					if (handler.getCash() >= B3)
					{
						handler.setCash(handler.getCash() - B3);
						handler.HealthUpg += 20;
						HUD.HEALTH = 200 + handler.HealthUpg;
						B3 += 500;
					}
				}
				
				if (mouseOver(mx, my, 460, 100, 120, 60))
				{
					if (handler.getCash() >= B4)
					{			
						handler.setCash(999999999);
					}
				}
				
				if (mouseOver(mx, my, 90, 180, 120, 60))
				{
					if (handler.getCash() >= B5)
					{
						handler.setCash(handler.getCash() - B5);
						lowerPrices += 250;
						B5 += 500;
					}
				}
				
				if (mouseOver(mx, my, 220, 180, 120, 60))
				{
					if (handler.getCash() >= B6)
					{
						int i = 2;
						handler.setCash(handler.getCash() - B6);
						handler.decreaseEnemySpawning += i;
						i++;
						B6 += 300;
					}
				}
			}
		}
		
		
		
		
		
		if (handler.NmrOfplayers == 2) {
			if (mouseOver(mx, my, 70, 100, 120, 60))
			{
				if (handler.getCash() >= (B1 - lowerPrices))
				{
					if (HUD.HEALTH < 200 + handler.HealthUpg) {
						handler.setCash(handler.getCash() - (B1 - lowerPrices));
						B1 += 350;
						HUD.HEALTH = 200;
						HUD.HEALTH2 = 300;
					}
				}
			}
			
			
			if (mouseOver(mx, my, 200, 100, 120, 60))
			{
				if (handler.getCash() >= (B2 - lowerPrices))
				{
					handler.setCash(handler.getCash() - (B2 - lowerPrices));
					B2 += 500;
					handler.speed++;
				}
			}
			
			if (mouseOver(mx, my, 460, 100, 120, 60))
			{
				if (handler.getCash() >= B4)
				{			
					handler.setCash(999999999);
				}
			}
			
			if (mouseOver(mx, my, 350, 100, 120, 60))
			{
				if (handler.getCash() >= B5)
				{
					handler.setCash(handler.getCash() - B5);
					lowerPrices += 250;
					B5 += 500;
				}
			}
			
			if (mouseOver(mx, my, 90, 180, 120, 60))
			{
				if (handler.getCash() >= B6)
				{
					int i = 2;
					handler.setCash(handler.getCash() - B6);
					handler.decreaseEnemySpawning += i;
					i++;
					B6 += 300;
				}
			}
		}
	}
}
