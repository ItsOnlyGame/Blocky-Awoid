package com.main.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	private BufferedImage image;
	
	public BufferedImage loadImage(String path) {
        try {
			File test = new File(System.getProperty("user.dir")+"/res"+ path);
			image = ImageIO.read(test);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
}
