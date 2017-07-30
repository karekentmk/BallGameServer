package com.karekent.ballgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Game extends JPanel implements KeyListener {

	Ball myBall;
	Client client;
	public String id = "tomb";
	UserDataDecoder userData;
	HashMap<String,Ball> otherBalls;
	public enum GameState{INITIALISE,GAMESTATE};
	
	public Game() {
		myBall = new Ball();
		client = new Client();
		client.setSendGameData("/"+id+":"+"000000");
		otherBalls = new HashMap<String,Ball>();
		userData = new UserDataDecoder(otherBalls,id);
		setFocusable(true);
		this.addKeyListener(this);
	}
	
	private void move() {
		
		userData.updateUsersData(client.getReceiveGameData(),myBall);
		
		myBall.update();
		
		for(Entry<String, Ball> map : otherBalls.entrySet())
		{
			if(!map.getKey().equals(id))
				map.getValue().update();
		}
		client.setSendGameData(userData.sendData());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		myBall.render(g2d);
		g2d.setColor(Color.BLACK);
		for(Entry<String, Ball> map : otherBalls.entrySet())
		{
			if(!map.getKey().equals(id))
				map.getValue().render(g2d);
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Ball Game");
		Game game = new Game();
		frame.add(game);
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
			myBall.up = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			myBall.down = true;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			myBall.right = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			myBall.left = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
			myBall.up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			myBall.down = false;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			myBall.right = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			myBall.left = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}