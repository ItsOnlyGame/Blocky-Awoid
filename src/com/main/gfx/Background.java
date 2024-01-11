package com.main.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.GameStates.Settings;

public class Background {
	
	private int s1 = 0;
	
	private BufferedImage bg1;
	private BufferedImage bg2;
	private BufferedImage bg3;
	private BufferedImage bg4;
	private BufferedImage bg5;
	private BufferedImage bg6;
	
	public Background() {
		BufferedImageLoader loader = new BufferedImageLoader();
		bg1 = loader.loadImage("/bg/bg1.png");
		bg2 = loader.loadImage("/bg/bg2.png");
		bg3 = loader.loadImage("/bg/bg3.png");
		bg4 = loader.loadImage("/bg/bg4.png");
		bg5 = loader.loadImage("/bg/bg5.png");
		bg6 = loader.loadImage("/bg/bg6.png");
	}
	
	public void tick() {
		s1++;
	}
	
	public void render(Graphics g) {
		if (Settings.EnableBG) {
			movingBG(g);
		}
	}
	
	private void movingBG(Graphics g) {
		if (s1 <= 10) { g.drawImage(bg1, 0, 0, null); }
		if (s1 >= 10 && s1 <= 19) { g.drawImage(bg2, 0, 0, null); }
		if (s1 >= 20 && s1 <= 29) { g.drawImage(bg3, 0, 0, null); }
		if (s1 >= 30 && s1 <= 39) { g.drawImage(bg4, 0, 0, null); }
		if (s1 >= 40 && s1 <= 49) { g.drawImage(bg5, 0, 0, null); }
		if (s1 >= 50 && s1 <= 59) { g.drawImage(bg6, 0, 0, null); }
		if (s1 >= 59) { s1 = 0; }
	}

}