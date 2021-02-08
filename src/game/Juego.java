/**
 * @author Alemol
 * Creación del juego Arkanoid para java
 */
package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.rmrsoft.spaceInvaders.Actor;

public class Juego {
	public static int FPS = 60;
	private JFrame ventJuego = new JFrame("Arkanoid ventana");	// Nombre provisional
	private List<Objeto> listaObjs = new ArrayList<Objeto>();
	private MiCanvas canvas = null;
	Vaus jugador = null;
	
	// Para utilizar un patr�n singleton necesitamos la siguiente propiedad est�tica
	private static Juego instance = null;
		
	/**
	 * Este m�todo representa la principal funcionalidad de un patr�n Singleton.
	 * Devuelve la �nica instancia que puede existir del esta clase. Si no se ha 
	 * inicializado, en la primera llamada a este m�todo se realiza dicha 
	 * inicializaci�n.
	 */
	public static Juego getInstance () {
		if (instance == null) { // Si no est� inicializada, se inicializa
			instance = new Juego();
		}
		return instance;
	}
		
		
	
	public Juego() {
		ventJuego.setBounds(20,20,450,700);
		ventJuego.getContentPane().setLayout(new BorderLayout());
		ventJuego.setResizable(false);
		
		preparaObjetos();	// Prepara los objetos del juego para jugar.
		
		// Creo y agrego un canvas, es un objeto que permitirá dibujar sobre él
		canvas = new MiCanvas(listaObjs);
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
		} );
			
		// Movemos Vaus con el ratón.
		canvas.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				jugador.mover(e.getX());
			}	
		} );
		
		
		// Movemos Vaus con las teclas.
				canvas.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						super.keyPressed(e);
						jugador.keyPressed(e);
					}

					@Override
					public void keyReleased(KeyEvent e) {
						super.keyReleased(e);
						jugador.keyReleased(e);
					}
				});
		
	}
	
	
	public void juego(){
		int millisPorCadaFrame = 1000 / FPS;
		do {
			// No s� cuando se va a mostar la ventana y hasta entonces no puedo utilizar la instrucci�n canvas.requestFocus();
			// Por tanto, en este bucle compruebo constantemente si el canvas tiene el foco y, si no lo tiene, se lo doy
			if (ventJuego.getFocusOwner() != null && !ventJuego.getFocusOwner().equals(canvas)) {
				canvas.requestFocus();
			}
			
			
			// Redibujo la escena tantas veces por segundo como indique la variable FPS
			// Tomo los millis actuales
			long millisAntesDeProcesarEscena = new Date().getTime();
			
			// Redibujo la escena
			canvas.pintaEscena();
			
			// Recorro todos los actores, consiguiendo que cada uno de ellos act�e
			for (Objeto obj : listaObjs) {
				obj.actua();
			}
			
			// Tras hacer que cada actor act�e y antes de agregar y eliminar actores, detecto colisiones
//			detectaColisiones();
			
			// Acualizo los actores, incorporando los nuevos y eliminando los que ya no se quieren
//			actualizaActores();
			
			// Calculo los millis que debemos parar el proceso, generando 60 FPS.
			long millisDespuesDeProcesarEscena = new Date().getTime();
			int millisDeProcesamientoDeEscena = (int) (millisDespuesDeProcesarEscena - millisAntesDeProcesarEscena);
			int millisPausa = millisPorCadaFrame - millisDeProcesamientoDeEscena;
			millisPausa = (millisPausa < 0)? 0 : millisPausa;
			// "Duermo" el proceso principal durante los milllis calculados.
			try {
				Thread.sleep(millisPausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
	}
	
	/**
	 * Prepara los objetos que se añaden a la lista de objetos.
	 */
	public void preparaObjetos () {
		creaLadrillos(5);
		creaPelota();
		creaVaus();
	}
	
	
	private void creaVaus() {
		int x = 145;
		int y = 600;
		this.jugador = new Vaus(x, y);
		this.listaObjs.add(jugador);
		
	}



	/**
	 * crea los ladrillos necesarios para jugar.
	 * @param numFilas (int) Número de filas que queremos de ladrillos;
	 * @param ventana
	 */
	public void creaLadrillos(int numFilas) {
		int parametroFilas = (Ladrillo.getAlto() * numFilas) + numFilas;

		Color colorLadrillo = Color.blue;
		for (int j = 10; j < parametroFilas; j += (Ladrillo.getAlto() + 2)) {
			colorLadrillo = generaColor(j);
			for (int i = 10; i + Ladrillo.getAncho() < ventJuego.getBounds().width - 10; i += Ladrillo.getAncho() + 2) {
				listaObjs.add(new Ladrillo(i, j, colorLadrillo));
			}
		}

		//System.out.println("Coordx = " + ventJuego.getBounds().width + "\nCoordy = " + ventJuego.getY());
	}
	
	
	public void creaPelota() {
		int posX = (int) Math.round(Math.random() * ventJuego.getBounds().width);
		int posY = (int) Math.round(Math.random() * ventJuego.getBounds().height);
		listaObjs.add(new Pelota(posX, posY));
	}
	
	/**
	 * Método para generar el color de los ladrillos.
	 * @param num número que se usa para elegir el color.
	 * @return color que usamos.
	 */
	private Color generaColor(int num) {
		int numRan = (int) Math.round(Math.random() * (9));
		switch (numRan) {
		case 0 :
			return Color.orange;
		case 1:
			return Color.cyan.darker();
		case 2: 
			return Color.green;
		case 3:
			return Color.yellow;
		case 4:
			return Color.pink;
		case 5 :
			return Color.magenta.darker();
		default :
			return Color.red.darker();		
		}
	}
	
	
	/**
	 * Al cerrar la aplicación preguntaremos al usuario si está seguro de que desea salir.
	 */
	private void cerrarAplicacion() {
		String [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(ventJuego,"¿Desea cerrar la aplicación?","Salir de la aplicación",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	
	public MiCanvas getCanvas() {
		return canvas;
	}
	
	/**
	 * Detecta colisiones entre actores e informa a los dos
	 */
	private void detectaColisiones() {
		// Una vez que cada actor ha actuado, intento detectar colisiones entre los actores y notificarlas. Para detectar
		// estas colisiones, no nos queda más remedio que intentar detectar la colisión de cualquier actor con cualquier otro
		// sólo con la excepción de no comparar un actor consigo mismo.
		// La detección de colisiones se va a baser en formar un rectángulo con las medidas que ocupa cada actor en pantalla,
		// De esa manera, las colisiones se traducirán en intersecciones entre rectángulos.
		for (Objeto objeto1 : this.listaObjs) {
			// Creo un rectángulo para este actor.
			Rectangle rect1 = new Rectangle(objeto1.getX(), objeto1.getY(), objeto1.getAncho(), objeto1.getAlto());
			// Compruebo un actor con cualquier otro actor
			for (Objeto objeto2 : this.listaObjs) {
				// Evito comparar un actor consigo mismo, ya que eso siempre provocaría una colisión y no tiene sentido
				if (!objeto1.equals(objeto2)) {
					// Formo el rectángulo del actor 2
					Rectangle rect2 = new Rectangle(objeto2.getX(), objeto2.getY(), objeto2.getAncho(), objeto2.getAlto());
					// Si los dos rectángulos tienen alguna intersección, notifico una colisión en los dos actores
					if (rect1.intersects(rect2)) {
						objeto1.colisionaCon(objeto2); // El actor 1 colisiona con el actor 2
						objeto2.colisionaCon(objeto1); // El actor 2 colisiona con el actor 1
					}
				}
			}
		}
	}
	
	
	
	// Main
	public static void main(String[] args) {
		Juego.getInstance().juego();
	}
	
	
	

}
