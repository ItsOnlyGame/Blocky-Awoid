package com.main.gfx;

import java.awt.Canvas;

import javax.swing.JFrame;

import com.main.Game;

public class Window extends Canvas{	

	private static final long serialVersionUID = 2104232951698985982L;

	public Window(int width, int height, String title, Game game)
	{
		JFrame frame = new JFrame(title);
		
        frame.setSize(width, height);
		frame.setUndecorated(false);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
		
	}
}
