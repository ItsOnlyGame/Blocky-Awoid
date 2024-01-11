package com.main.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.main.AudioPlayer;
import com.main.Game;
import com.main.Handler;

public class Settings extends MouseAdapter{
	
	Handler handler;
	HUD hud;
	Shop shop;
	Game game;
	Random r;

	
	public static boolean EnableBG = true;
	public static boolean EnableMainMenuMusic = true;
	public static boolean EnableGameMusic = true;
	public static boolean isFullscreen = false;
	public static boolean EnableParticles = true;
	
	public Settings(Handler handler, Game game, Shop shop, HUD hud) {
		this.handler = handler;
		this.game = game;
		this.shop = shop;
		this.hud = hud;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font title = new Font("Arial", 1, 48);
		Font fnt = new Font("Arial", 1, 20);
		
		g.setFont(title);
		g.setColor(Color.white);
		g.drawString("Settings", game.getWidth() / 2 - 90, 80);
		
		g.setFont(fnt);
		g.drawString("Background", 70, 150);
		g.drawRect(250, 125, 34, 34);
		if (EnableBG) {
			g.fillRect(255, 130, 25, 25);
		}
		
		g.drawString("MainMenu Music", 70, 200);
		g.drawRect(250, 175, 34, 34);
		if (EnableMainMenuMusic) {
			g.fillRect(255, 180, 25, 25);
		}
		
		g.drawString("Game Music", 70, 250);
		g.drawRect(250, 225, 34, 34);
		if (EnableGameMusic) {
			g.fillRect(255, 230, 25, 25);
		}
		
		g.drawString("Game Music", 70, 250);
		g.drawRect(250, 225, 34, 34);
		if (EnableGameMusic) {
			g.fillRect(255, 230, 25, 25);
		}
		
		g.drawString("Particles", 70, 300);
		g.drawRect(250, 275, 34, 34);
		if (EnableParticles) {
			g.fillRect(255, 280, 25, 25);
		}
		
		
		
		
		g.drawString("To Exit Settings: SPACE", Game.WIDTH / 2 - 130, Game.HEIGHT - 100);

	}

	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (mouseOver(mx, my, 250, 125, 34, 34)) {
			if (EnableBG) {
				EnableBG = false;
			} else if (!EnableBG) {
				EnableBG = true;
			}
		}
		
		if (mouseOver(mx, my, 250, 175, 34, 34)) {
			if (EnableMainMenuMusic) {
				EnableMainMenuMusic = false;
				handler.StopMusic();
			} else if (!EnableMainMenuMusic) {
				EnableMainMenuMusic = true;
				AudioPlayer.playMusic("MainMenu");
			}
		}
		
		if (mouseOver(mx, my, 250, 225, 34, 34)) {
			if (EnableGameMusic) {
				EnableGameMusic = false;
			} else if (!EnableGameMusic) {
				EnableGameMusic = true;
			}
		}
		
		if (mouseOver(mx, my, 250, 275, 34, 34)) {
			if (EnableParticles) {
				EnableParticles = false;
			} else if (!EnableParticles) {
				EnableParticles = true;
			}
		}
	}
	
	public void Fullscreen() {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
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
}
