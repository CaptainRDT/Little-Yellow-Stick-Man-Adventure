package mygame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class BlueEnemy {
	Image img;
	int x, y;
	boolean isAlive = true;
	
	public BlueEnemy(int startX, int startY, String location){
		x = startX;
		y = startY;
		ImageIcon l = new ImageIcon(location);
		img = l.getImage();
	}
	
	
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, 50, 100);
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
	public boolean getAlive(){
		return isAlive;
	}
	public void move(int xd, int left){
		if(xd == 1 && !((left + xd) < 150)){
		x = x - xd;
		}
	}
}
