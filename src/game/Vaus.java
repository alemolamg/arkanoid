package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Vaus extends Objeto {
	// Atributos
	private static int VELOCIDAD = 4;
	private boolean izquierda = false, derecha = false;
	private int ancho = 86;
	private int alto = 18;
	protected int vidas;
	
	// Métodos
	
	public Vaus(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void paint(Graphics g) {
		g.setColor(Color.gray);
		g.fillRoundRect(this.x, this.y, ancho, alto, 10, 10);

	}

	@Override
	public void actua() {
		// Compruebo las variables booleanas que determinan el movimiento
//				if (arriba) this.y -= VELOCIDAD;
//				if (abajo) this.y += VELOCIDAD;
				if (izquierda) this.x -= VELOCIDAD;
				if (derecha) this.x += VELOCIDAD;
				
				// Compruebo si el player sale del canvas por cualquiera de los cuatro márgenes
				mover(this.x);

	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public void mover(int nuevaX) {
		this.x = nuevaX;
		
		// Controlo los casos en los que el jugador pueda salir del Canvas
		MiCanvas canvas = Juego.getInstance().getCanvas(); // Referencia al objeto Canvas usado
				
				// Compruebo si el jugador sale por la derecha
				if (this.x > (canvas.getWidth() - this.ancho)) {
					this.x = canvas.getWidth() - this.ancho;
				}

				// Compruebo si el jugador sale por la izquierda
				if (this.x < 0) {
					this.x = 0;
				}
	}
	
	/**
	 * Se ejecuta al recibir un evento del teclado: tecla presionada
	 * @param e
	 */
	public void keyPressed (KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			izquierda = true; break;
		case KeyEvent.VK_RIGHT:
			derecha = true; break;
		}
	}
	
	/**
	 * Se ejecuta al recibir un evento del teclado: tecla liberada
	 * @param e
	 */
	public void keyReleased (KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			izquierda = false; break;
		case KeyEvent.VK_RIGHT:
			derecha = false; break;
		}
	}

}
