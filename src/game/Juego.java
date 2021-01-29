/**
 * @author Alemol
 * Creación del juego Arkanoid para java
 */
package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Juego {
	public static int FPS = 60;
	public static JFrame ventJuego = new JFrame("Arkanoid ventana");	// Nombre provisional
	public static List<Objeto> listaObjs = new ArrayList<Objeto>();
	
	public static void main(String[] args) {
		ventJuego.setBounds(20,20,450,700);
		ventJuego.getContentPane().setLayout(new BorderLayout());
		
		preparaObjetos();	// Prepara los objetos del juego para jugar.
		
		// Creo y agrego un canvas, es un objeto que permitirá dibujar sobre él
		MiCanvas canvas = new MiCanvas(listaObjs);
		ventJuego.getContentPane().add(canvas, BorderLayout.CENTER);
		// Consigo que la ventana no se redibuje por los eventos de Windows
		ventJuego.setIgnoreRepaint(true);
		// Hago que la ventana sea visible
		ventJuego.setVisible(true);


		// Control del evento de cierre de ventana
				ventJuego.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				ventJuego.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						cerrarAplicacion();
					}
				});
		
	}
	
	/**
	 * Prepara los objetos que se añaden a la lista de objetos.
	 */
	public static void preparaObjetos () {
		creaLadrillos(5);
		creaPelota();
	}
	
	
	/**
	 * crea los ladrillos necesarios para jugar.
	 * @param numFilas (int) Número de filas que queremos de ladrillos;
	 * @param ventana
	 */
	public static void creaLadrillos(int numFilas) {		
		int parametroFilas = (Ladrillo.getAlto() * numFilas) + numFilas;
		
		Color colorLadrillo = Color.blue;
		for (int j = 10; j < parametroFilas; j += (Ladrillo.getAlto() + 2)) {
			colorLadrillo = generaColor(j);
			for(int i = 10; i + Ladrillo.getAncho() < ventJuego.getBounds().width - 10; i+=Ladrillo.getAncho() + 2) {
			listaObjs.add(new Ladrillo(i,j,colorLadrillo));
			}
	}
		
		System.out.println("Coordx = " + ventJuego.getBounds().width
				+"\nCoordy = " + ventJuego.getY());
	}
	
	
	public static void creaPelota() {
		int posX = (int) Math.round(Math.random() * ventJuego.getBounds().width);
		int posY = (int) Math.round(Math.random() * ventJuego.getBounds().height);
		listaObjs.add(new Pelota(posX, posY));
	}
	
	/**
	 * Método para generar el color de los ladrillos.
	 * @param num número que se usa para elegir el color.
	 * @return color que usamos.
	 */
	private static Color generaColor(int num) {
		int numRan = (int) Math.round(Math.random() * (9));
		switch (numRan) {
		case 0 :
			return Color.orange;
		case 1:
			return Color.blue;
		case 2: 
			return Color.green;
		case 3:
			return Color.yellow;
		case 4:
			return Color.pink;
		case 5 :
			return Color.cyan;
		case 6 :
			return Color.magenta.brighter();
		default :
			return Color.red;		
		}
	}
	
	
	/**
	 * Al cerrar la aplicación preguntaremos al usuario si está seguro de que desea salir.
	 */
	private static void cerrarAplicacion() {
		String [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(ventJuego,"¿Desea cerrar la aplicación?","Salir de la aplicación",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	

}
