package mygame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Board extends JPanel implements ActionListener, Runnable{
	Character c;
	Image bg;
	Timer time;
	Thread animator;
	int v = 276;
	static Font font = new Font("SansSerif", Font.BOLD, 24);
	public Board(){
		addKeyListener(new AL());
		c = new Character();
		setFocusable(true);
		ImageIcon i = new ImageIcon("src/img/bg.png");
		bg = i.getImage();
		
		time = new Timer(5, this);
		time.start();
		
	}
	public void actionPerformed(ActionEvent e){
		ArrayList bullets = Character.getBullets();
		for (int w = 0; w < bullets.size(); w++){
			Bullet m = (Bullet) bullets.get(w);
			if(m.getVisible() == true){
				m.move();
			}else{
				bullets.remove(w);
			}
		}
		c.move();
		repaint();
	}
	boolean k = false;
	public void paint(Graphics g){
		if(c.yd == 1 && k == false){
			k = true;
			animator = new Thread(this);
			animator.start();
		}
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if((c.getX() - 450) % 2000 == 0){
			c.nx = 0;
		}
		if((c.getX() - 1450) % 2000 == 0){
			c.nx2 = 0;
		}
		g2d.drawImage(bg, 985-c.nx, 0, null);
		if(c.getX() >= 450){
			g2d.drawImage(bg, 985-c.nx2, 0, null);
		}
		g2d.drawImage(c.getImage(), c.left, v, null);
		
		ArrayList bullets = Character.getBullets();
		for (int w = 0; w < bullets.size(); w++){
			Bullet m = (Bullet) bullets.get(w);
			g2d.drawImage(m.getImage(), m.getX(), m.getY(), null);
		}
		g2d.setFont(font);
		g2d.setColor(Color.GREEN);
		g2d.drawString("Ammo Remaining " + c.ammo +"/" + c.totalammo, 500, 20);
	}
	private class AL extends KeyAdapter{
		public void keyReleased(KeyEvent e){
			c.keyReleased(e);
		}
		public void keyPressed(KeyEvent e){
			c.keyPressed(e);
		}
	}
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;
		beforeTime = System.currentTimeMillis();
		while(done == false){
			cycle();
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = 10 - timeDiff;
			if(sleep < 0){
				sleep = 2;
			}
			try{
				Thread.sleep(sleep);
			}catch(Exception e){
				
			}
			beforeTime = System.currentTimeMillis();
		}
		done = false;
		k = false;
		h = false;
	}
	boolean h = false;
	boolean done = false;
	public void cycle(){
		if(h == false){
			v--;
		}
		if(v == 200){
			h = true;
		}
		if(h == true && v <= 276){
			v++;
			if(v == 276){
				done = true;
			}
		}
	}
}
