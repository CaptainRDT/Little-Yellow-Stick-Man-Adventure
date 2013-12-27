package mygame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame {
	static ImageIcon icon = new ImageIcon("src/img/icon.png");
	public static void main(String[] args){
		frame();
	}
	public static void frame(){
		JFrame frame = new JFrame("Little Yellow Stick Man Adventure");
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(new Board());
		frame.setVisible(true);
		frame.setIconImage(icon.getImage());
	}
}
