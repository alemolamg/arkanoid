package game;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota extends Objeto {
	protected int radio = 8; 
	public Pelota(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, radio, radio);
	}

}
