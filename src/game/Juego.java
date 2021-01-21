/**
 * @author Alemol
 * Creación del juego Arkanoid para java
 */
package game;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Juego {
	public static int FPS = 60;

	public static void main(String[] args) {
		JFrame ventJuego = new JFrame("Arkanoid ventana");	// Nombre provisional.
		ventJuego.setBounds(20,20,900,800);
		ventJuego.getContentPane().setLayout(new BorderLayout());
		
		Objeto listaObjs;
		
		// Creo y agrego un canvas, es un objeto que permitirá dibujar sobre él
		MiCanvas canvas = new MiCanvas(listaObjs);
		ventJuego.getContentPane().add(canvas, BorderLayout.CENTER);
		// Consigo que la ventana no se redibuje por los eventos de Windows
		ventJuego.setIgnoreRepaint(true);
		// Hago que la ventana sea visible
		ventJuego.setVisible(true);
		
	}

}
