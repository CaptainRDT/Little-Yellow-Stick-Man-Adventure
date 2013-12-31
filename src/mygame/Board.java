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
	BlueEnemy ben;
	BlueEnemy ben2;
	Pistol gun;
	boolean lost = false;
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
		ben = new BlueEnemy(1000, 276, "src/img/enemyblue.png");
		ben2 = new BlueEnemy(1100, 276, "src/img/enemyblue.png");
		gun = new Pistol(550, 355, "src/img/pistol.png");
		
	}
	public void actionPerformed(ActionEvent e){
		checkCollision();
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
		if(c.x > 400){
			gun.move(c.getXD(), c.getLeft());
		}
		if(c.x > 1100){
			ben.move(c.getXD(), c.getLeft());
		}
		if(c.x > 1200){
			ben2.move(c.getXD(), c.getLeft());
		}
		repaint();
	}
	public void checkCollision(){
		Rectangle gunr = gun.getBounds();
		Rectangle benr = ben.getBounds();
		Rectangle ben2r = ben.getBounds();
		ArrayList bullets = Character.getBullets();
		for (int w = 0; w < bullets.size(); w++){
			Bullet m = (Bullet) bullets.get(w);
			Rectangle m1 = m.getBounds();
			
			if (benr.intersects(m1) && ben.getAlive()){
				ben.isAlive = false;
				m.visible = false;
			}else if (ben2r.intersects(m1) && ben2.getAlive()){
				ben2.isAlive = false;
				m.visible = false;
			}
		}
		Rectangle player = c.getBounds();
		if (gunr.intersects(player)){
			if(!gun.hasGun){
				c.totalammo = 10;
			}
			gun.hasGun = true;
		}
		//if(player.intersects(benr) || (player.intersects(ben2r))){
			//if(ben.getAlive()/* || ben2.getAlive()*/){
				//	lost = true;
			//}
			
		//}
		Rectangle d = c.getBounds();
        if (d.intersects(benr) || d.intersects(ben2r))
                lost = true;
		
	}
	boolean k = false;
	public void paint(Graphics g){
		
		if(lost){
			System.exit(0);
			//animator = new Thread(this);
			//animator.start();
		}
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
		if(!gun.hasGun()){
		g2d.setColor(Color.RED);
		g2d.drawString("No Gun!", 500, 40);
		}
		g2d.setColor(Color.GREEN);
		g2d.drawString("Ammo Remaining " + c.ammo +"/" + c.totalammo, 500, 20);
		if(!gun.hasGun){
			g2d.drawImage(gun.getImage(), gun.getX(), gun.getY(), null);
		}
		if(c.x > 1100){
			if(ben.getAlive()){
				g2d.drawImage(ben.getImage(), ben.getX()/* + 300 */, ben.getY(), null);
			}
		}
		if(c.x > 1200){
			if(ben2.getAlive()){
				g2d.drawImage(ben2.getImage(), ben2.getX()/* + 300 */, ben2.getY(), null);
			}
		}
		System.out.println(c.getX());
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
