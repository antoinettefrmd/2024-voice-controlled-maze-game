package modele;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Clavier extends KeyAdapter {

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar());
		
	}

	
	

}
