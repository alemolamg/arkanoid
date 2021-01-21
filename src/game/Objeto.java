package game;

import java.awt.Graphics;

public abstract class Objeto {
	protected int x;
	protected int y;
	
	
	public Objeto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	protected abstract void paint(Graphics g);	

}
