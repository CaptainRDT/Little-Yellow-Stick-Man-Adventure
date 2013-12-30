package mygame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Character {
	int x, xd, y, nx2, nx, left, yd, ammo, totalammo, ammodiff;
	Image still;
	ImageIcon l = new ImageIcon("src/img/charleft.png");
	ImageIcon i = new ImageIcon("src/img/char.png");
	ImageIcon j = new ImageIcon("src/img/charjump.png");
	static ArrayList bullets;
	public Character(){
		
		still = i.getImage();
		
		x = 450;
		nx2 = 985;
		nx = 0;
		y = 276;
		left = 150;
		ammo = 0;
		totalammo = 0;
		ammodiff = 0;
		
		bullets = new ArrayList();
	}
	public static ArrayList getBullets(){
		return bullets;
	}
	public void fire(){
		if(ammo > 0){
		ammo--;
		Bullet b = new Bullet((left + 40), (y + 40));
		bullets.add(b);
		}
	}
	public void move(){
		if(xd != -1){
			if(left + xd <= 150){
				left = left + xd;
			}else{
				x = x + xd;
				nx2 = nx2 + xd;
				nx = nx + xd;
			}
		}else{
			if(left + xd > 0){
				left = left + xd;
			}
		}
	}
	public Rectangle getBounds(){
		return new Rectangle(left, y, 50, 100);
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Image getImage(){
		return still;
	}
	public int getXD(){
		return xd;
	}
	public int getLeft(){
		return left;
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT){
		xd = -1;
		still = l.getImage();
		}
		if (key == KeyEvent.VK_RIGHT){
		xd = 1;
		still = i.getImage();
		}
		if (key == KeyEvent.VK_SPACE){
			fire();
		}
		if (key == KeyEvent.VK_UP){
			yd = 1;
			still = j.getImage();
		}
		if (key == KeyEvent.VK_R){
			if(totalammo > 0 && ammo < 30){
				ammodiff = 30 - ammo;
				if(totalammo < 30 && ammo == 0){
					ammo = totalammo;
				}else{
				ammo = 30;
				}
				totalammo = totalammo - ammodiff;
				if(totalammo < 0){
					totalammo = 0;
				}
			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT){
		xd = 0;
		}
		if (key == KeyEvent.VK_RIGHT){
		xd = 0;
		}
		if (key == KeyEvent.VK_UP){
			yd = 0;
			still = i.getImage();
		}
	}
}
