package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;


public class MiCanvas extends Canvas{
	//	Atributos
	private List<Objeto> objetos = new ArrayList<Objeto>();
	private BufferStrategy strategy = null;
	
	//	MÃ©todos
	public MiCanvas(List<Objeto> objetos) {
		this.objetos = objetos;
	}
	
	@Override
	public void paint(Graphics g) {
		this.setBackground(Color.BLACK);
		for (Objeto o : objetos) {
			o.paint(g);
		}
	}
	
	
	public void pintaEscena () {
		// Tengo que inicializar el objeto "strategy" una única vez
		if (this.strategy == null) {
			// El Canvas se dibujará en pantalla con una estrategia de doble búffer
			this.createBufferStrategy(2);
			// Obtengo una referencia a la estrategia de doble bÃºffer.
			strategy = getBufferStrategy();
			// Resuelve un problema de sincronizaciÃ³n de memoria de vÃ­deo en Linux
			Toolkit.getDefaultToolkit().sync();			
		}
		// Obtengo el objeto grÃ¡fico que me permita pintar en el doble bÃºffer
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		// Pinto un rectÃ¡ngulo negro que ocupe toda la escena
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Pinto cada uno de los actores
		for (Objeto obj : this.objetos) {
			obj.paint(g); 
		}

		// Muestro en pantalla el buffer con el nuevo frame creado para el juego
		strategy.show();

	}

}
