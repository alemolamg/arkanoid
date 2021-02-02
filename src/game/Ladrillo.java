package game;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Objeto {
	// Atributos
	protected static int ancho = 50;
	protected static int alto = 32;
	protected Color color = Color.red;
	
//	public Ladrillo(int x, int y, int ancho, int alto) {
//		super(x, y);
//		this.ancho = ancho;
//		this.alto = alto;
//	}
//	
	public Ladrillo(int x, int y) {
		super (x,y);
	}
	
	public Ladrillo (int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}

	@Override
	protected void paint(Graphics g) {
		g.setColor(this.color);
		g.fillRect(x, y, ancho, alto);
	}

	public static int getAncho() {
		return ancho;
	}

	public static int getAlto() {
		return alto;
	}
	
	@Override
	public void actua() {
		// TODO Auto-generated method stub	
	}
	
	

}
