package mygame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Pistol {
	Image img;
	int x, y;
	boolean hasGun = false;
	
	public Pistol(int startX, int startY, String location){
		x = startX;
		y = startY;
		ImageIcon g = new ImageIcon(location);
		img = g.getImage();
	}
	public Rectangle getBounds(){
		return new Rectangle (x, y, 20, 20);
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
	public boolean hasGun(){
		return hasGun;
	}
	public void move(int xd, int left){
		if(xd == 1 && !((left + xd) < 150)){
		x = x - xd;
		}
	}
}
