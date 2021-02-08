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
	
	/**
	 * Método que permite que cada actor realice las acciones que necesite en la creación de cada Frame
	 */
	public abstract void actua ();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public abstract int getAncho();
	
	public abstract int getAlto();
	
	public abstract void colisionaCon(Objeto elOtro);

}


