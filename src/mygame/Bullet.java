package mygame;

import java.awt.*;

import javax.swing.ImageIcon;

public class Bullet {
	int x, y;
	Image img;
	boolean visible;
	
	public Bullet(int startX, int startY){
		x = startX;
		y = startY;
		ImageIcon newBullet = new ImageIcon("src/img/bullet.png");
		img = newBullet.getImage();
		visible = true;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Image getImage(){
		return img;
	}
	public boolean getVisible(){
		return visible;
	}
	public void move(){
		x = x + 2;
		if(x > 1000){
			visible = false;
		}
	}
}
