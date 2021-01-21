package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class MiCanvas extends Canvas{
	//	Atributos
	private List<Objeto> objetos = new ArrayList<Objeto>();
	
	//	Métodos
	public MiCanvas() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void paint(Graphics g) {
		for (Objeto o : objetos) {
			o.paint(g);
		}
	}

}
