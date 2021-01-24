package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class MiCanvas extends Canvas{
	//	Atributos
	private List<Objeto> objetos = new ArrayList<Objeto>();
	
	//	Métodos
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

}
