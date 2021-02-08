package game;

import java.awt.Color;
import java.awt.Graphics;


public class Pelota extends Objeto {
	protected int radio = 15;
	protected int velocidadX = 5; 
	protected int velocidadY = 5;
	
	public Pelota(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, radio, radio);
	}
	
	@Override
	public void actua() {
		// El monstruo se mueve de manera horizontal, en cada FPS
		this.x += this.velocidadX;
		// Si el monstruo abandona la escena por la izquierda o la derecha, rebota
		if (this.x < 0 || (this.x + this.radio) > Juego.getInstance().getCanvas().getWidth()) {
			this.velocidadX = -this.velocidadX;
		}
				
		// Copiamos el esquema anterior para el movimiento vertical
		this.y += this.velocidadY;
		// Si el monstruo abandona la escena por la izquierda o la derecha, rebota
		if (this.y < 0 || (this.y + this.radio) > Juego.getInstance().getCanvas().getHeight()) {
			this.velocidadY = -this.velocidadY;
		}
		
	}


	public int getRadio() {
		return radio;
	}


	public void setRadio(int radio) {
		this.radio = radio;
	}
	
	@Override
	public int getAlto() {
		// TODO Auto-generated method stub
		return getRadio();
	}


	@Override
	public int getAncho() {
		// TODO Auto-generated method stub
		return getRadio();
	}

}
