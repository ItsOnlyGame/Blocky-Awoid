package com.main.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.main.Game;
import com.main.Handler;

public class Menu extends MouseAdapter {

	private Handler handler;
	private Game game;
	private HUD hud;
	private Random r;
	private Shop shop;
	
	public int s1 = 0;

	public Menu(Game game, Handler handler, HUD hud, Shop shop) {
		this.handler = handler;
		this.game = game;
		this.hud = hud;
		this.shop = shop;
		r = new Random();
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (Game.gameState == STATE.Menu) {
			if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 250, 200, 64)) {
				Game.gameState = STATE.SelectMode;
			}
			
			if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 340, 200, 64)) {
				Game.gameState = STATE.SelectMode2;
			}
			
			if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 430, 200, 64)) {
				Game.gameState = STATE.Settings;
			}
			
			if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 520, 200, 64)) {
				System.exit(0);
			}
			
			return;
		}
		
		if (Game.gameState == STATE.Respawn) {
			Game.paused = true;
			if (Game.paused) {
				if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 350, 200, 64)) {
					HUD.HEALTH = 100;
					handler.setCash(handler.getCash() - shop.respawn);
					shop.respawn += 1500;
					Game.paused = false;
					Game.gameState = STATE.Game;
				}
				if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 250, 200, 64)) {
					handler.clearEnemys();				
					Game.paused = false;
					Game.gameState = STATE.GameOver;
					handler.StartMusic();
				}
			}
			return;
		}

		
		if (Game.gameState == STATE.GameOver) {
			if (mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT - 200, 200, 64)) {
				Game.paused = false;
				s1 = 0;
				handler.clearEnemys();
				Game.gameState = STATE.Menu;
				handler.StartMusic();
			}
			return;
		}
		
		
		if (Game.gameState == STATE.SelectMode2) { //Normal
			if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 150, 200, 64)) {
				Game.paused = false;
				Game.diff2 = 0;
				Game.gameState = STATE.Game2;
				handler.StartMusic();

				handler.addPlayer(game);
				handler.startGame(game, r);
			}
			
			if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 250, 200, 64)) { //Hard
				Game.paused = false;
				Game.diff2 = 1;
				Game.gameState = STATE.Game2;
				handler.StartMusic();
				handler.addPlayer(game);
				handler.startGame(game, r);
			}
			
			if (mouseOver(mx, my, Game.WIDTH - 150, 70, 96, 40)) {
				Game.gameState = STATE.Help2;
			}
			return;
		}
		
		
		if (Game.gameState == STATE.SelectMode) { //Normal
			if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 150, 200, 64)) {
				Game.paused = false;
				Game.diff = 0; 
				Game.gameState = STATE.Game;
				handler.StartMusic();
				handler.addPlayer(game);
				handler.startGame(game, r);
			}
	
			
			if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 250, 200, 64)) { //Hard
				Game.paused = false;
				Game.diff = 1; 
				
				Game.gameState = STATE.Game;
				handler.StartMusic();
				handler.addPlayer(game);
				handler.startGame(game, r);
			}
			
			
			/*if (mouseOver(mx, my, 20, 20, 45, 45)) { //Experimental
				Game.paused = false;
				game.diff = 2; 
				
				handler.scoreKeep = 900;
				handler.setCash(999999999);
				
				AudioPlayer.getMusic("GameMusic_E").loop();
				Game.gameState = STATE.Game;	
				handler.StartMusic();			
				handler.addPlayer(game);
				handler.startGame(game, r);
			}*/
			
			
			
	
			
			if (mouseOver(mx, my, 70, 220, 130, 50)) {
				handler.NmrOfplayers = 1;
			}
			
			if (mouseOver(mx, my, 70, 290, 130, 50)) {
				handler.NmrOfplayers = 2;
			}
			
			if (mouseOver(mx, my, Game.WIDTH - 150, 70, 96, 40)) {
				Game.gameState = STATE.Help;
			}
			
			
		}
		return;
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
	
	
	public void tick() {
		if (Game.gameState == STATE.GameOver) {
			handler.StopMusic();
			handler.clearEnemys();
			handler.totalScore(game, hud);
			if (s1 < 1) {
				handler.addParticles(r);
			}
			s1++;
		}
		
		if (Game.gameState == STATE.Menu) {
			handler.resetUpg(shop, hud, this);
		}
	}

	
	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		
		if (Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 40);
			Font fnt2 = new Font("arial", 1, 25);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("HELP", Game.WIDTH / 2 - 55, 50);
			
			g.setFont(fnt2);
			g.drawString("You have to awoid the enemies", Game.WIDTH / 2 - 200, 150);
			g.drawString("To move: WASD", Game.WIDTH / 2 - 110, 180);
			g.drawString("Space to open shop", Game.WIDTH / 2 - 130, 210);
			g.drawString("To pause: P", Game.WIDTH / 2 - 80, 240);
			g.drawString("Back to Menu: O", Game.WIDTH / 2 - 120, 270);
			g.drawString("To Exit the Game: ESC", Game.WIDTH / 2 - 155, 300);

			
			g.setFont(fnt3);
			g.drawString("To Exit Help Menu: SPACE", Game.WIDTH / 2 - 130, Game.HEIGHT - 100);
		}
		
		if (Game.gameState == STATE.Help2) {
			Font fnt = new Font("arial", 1, 40);
			Font fnt2 = new Font("arial", 1, 25);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("HELP", Game.WIDTH / 2 - 55, 50);
			
			g.setFont(fnt2);
			g.drawString("You have to awoid the waves of enemies", Game.WIDTH / 2 - 200, 150);
			g.drawString("To move: W - UP, S - DOWN", Game.WIDTH / 2 - 110, 180);
			g.drawString("Space to open shop", Game.WIDTH / 2 - 130, 210);
			g.drawString("To pause: P", Game.WIDTH / 2 - 80, 240);
			g.drawString("Back to Menu: O", Game.WIDTH / 2 - 120, 270);
			g.drawString("To Exit the Game: ESC", Game.WIDTH / 2 - 155, 300);
			
			g.setFont(fnt3);
			g.drawString("To Exit Help Menu: SPACE", Game.WIDTH / 2 - 130, Game.HEIGHT - 100);
		}
		
		if (Game.gameState == STATE.Loading) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 25);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("LOADING!", Game.WIDTH/2 - 120, Game.HEIGHT/2);
			g.setFont(fnt2);
			g.drawString("you may have to lower your sound", Game.WIDTH/2 - 200, Game.HEIGHT/2 + 50);
		}
		
		if (Game.gameState == STATE.Respawn) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Do you want to Respawn", Game.WIDTH / 2 - 300, 70);

			g.setFont(fnt2);
			g.drawString("Costs: "+ shop.respawn, Game.WIDTH / 2 - 95, 170);
			
			g.drawRect(Game.WIDTH / 2 - 100, 250, 200, 64);
			g.drawString("Yes", Game.WIDTH / 2 - 30, 290);

			g.drawRect(Game.WIDTH / 2 - 100, 350, 200, 64);
			g.drawString("No", Game.WIDTH / 2 -20, 390);
	}
		
		if (Game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 28);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("BLoCky AwOId", Game.WIDTH / 2 - 175, 70);

			g.setFont(fnt2);
			g.drawRect(Game.WIDTH / 2 - 100, 250, 200, 64);
			g.drawString("AvOIdANcE", Game.WIDTH / 2 - 75, 290);
			
			g.drawRect(Game.WIDTH / 2 - 100, 340, 200, 64);
			g.drawString("WaVEs", Game.WIDTH / 2 - 45, 380);
			
			g.drawRect(Game.WIDTH / 2 - 100, 430, 200, 64);
			g.drawString("Settings", Game.WIDTH / 2 - 50, 470);
			
			g.drawRect(Game.WIDTH / 2 - 100, 520, 200, 64);
			g.drawString("Quit", Game.WIDTH / 2 - 25, 560);
		}

		if (Game.gameState == STATE.GameOver) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("GAME OVER", Game.WIDTH / 2 - 150, 70);

			g.setFont(fnt2);
			g.drawString("You lost, you got to level: " + hud.getLevel(), Game.WIDTH / 2 - 120, 270);
			g.drawString("With a score of "+ handler.totalScore(game, hud), Game.WIDTH / 2 - 70, 300);

			g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT - 200, 200, 64);
			g.drawString("Back to Menu", Game.WIDTH / 2 - 60, Game.HEIGHT - 160);
		}
		
		if (Game.gameState == STATE.SelectMode2) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 24);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("WaVEs", Game.WIDTH / 2 - 90, 70);

			g.setFont(fnt2);
			g.drawRect(Game.WIDTH - 150, 70, 96, 40);
			g.drawString("Help?", Game.WIDTH - 135, 98);

			g.drawRect(Game.WIDTH / 2 - 100, 150, 200, 64);
			g.drawString("Normal", Game.WIDTH / 2 - 42, 190);

			g.drawRect(Game.WIDTH / 2 - 100, 250, 200, 64);
			g.drawString("Hard", Game.WIDTH / 2 - 30, 290);
			
			g.drawString("Space to go back", Game.WIDTH / 2 - 100, Game.HEIGHT - 100);
		}

		if (Game.gameState == STATE.SelectMode) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 24);
			@SuppressWarnings("unused")
			Font fnt3 = new Font("arial", 1, 16);
			Font fnt4 = new Font("arial", 1, 18);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("AvOIdANcE", Game.WIDTH / 2 - 135, 70);
			
			g.setFont(fnt2);
			g.drawRect(Game.WIDTH - 150, 70, 96, 40);
			g.drawString("Help?", Game.WIDTH - 135, 98);

			g.drawRect(Game.WIDTH / 2 - 100, 150, 200, 64);
			g.drawString("Normal", Game.WIDTH / 2 - 42, 190);

			g.drawRect(Game.WIDTH / 2 - 100, 250, 200, 64);
			g.drawString("Hard", Game.WIDTH / 2 - 30, 290);
			
			/*g.drawRect(20, 20, 45, 45);
			g.drawString("E", 35, 50);*/   //Experimental
			
			g.drawString("Space to go back", Game.WIDTH / 2 - 100, Game.HEIGHT - 100);

			if (handler.NmrOfplayers == 1) {
				g.drawString("Singleplayer - SELECTED", Game.WIDTH / 2 - 150, Game.HEIGHT - 150);
				
				g.setFont(fnt2);
				g.drawRect(70, 220, 130, 50);
				g.drawString("1 Player", 90, 250);
				
				g.setFont(fnt4);
				g.drawRect(70, 290, 130, 50);
				g.drawString("2 Players", 90, 320);
			}
			if (handler.NmrOfplayers == 2) {
				g.drawString("At least you have friends - SELECTED", Game.WIDTH / 2 - 220, Game.HEIGHT - 150);
				
				g.setFont(fnt4);
				g.drawRect(70, 220, 130, 50);
				g.drawString("1 Player", 100, 250);
				
				g.setFont(fnt2);
				g.drawRect(70, 290, 130, 50);
				g.drawString("2 Players", 85, 320);
			}
			
			g.setFont(fnt4);
			g.drawString("How many players?", 45, 200);
			}
		}
}

