package com.main.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage ss) {
		this.image = ss;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * 64) - 64, (row * 64) - 64, width, height);
		return img;
	}
	
}
