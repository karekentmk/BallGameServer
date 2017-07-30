package com.karekent.ballgame;

import java.awt.Graphics2D;



public class Ball {

	private int x;
	private int y;
	private int speed = 3;
	public boolean up,down,right,left;
	public Ball()
	{
		
	}
	public void update()
	{
		if(up == true)
			y = y - speed;
		if(down == true)
			y = y + speed;
		if(left == true)
			x = x - speed;
		if(right == true) 
			x = x + speed;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void render(Graphics2D g)
	{
		g.fillRect(x, y, 20, 20);
	}
}
