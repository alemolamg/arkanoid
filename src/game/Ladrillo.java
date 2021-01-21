package game;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Objeto {
	// Atributos
	protected int ancho = 25;
	protected int alto = 10;
	
	public Ladrillo(int x, int y, int ancho, int alto) {
		super(x, y);
		this.ancho = ancho;
		this.alto = alto;
	}

	@Override
	protected void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, ancho, alto);
	}

}
