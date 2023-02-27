package vue;

import java.swing.JPanel;
import java;awt.Graphics;

public class PionGraphique extends JPanel{

    private Pion pion;

    public PionGraphique (Pion p, int x, int y) {
        this.pion = p;
    }

    public void paint (Graphics g) {
        setBackground(p.getCouleur);
        g.drawOval(x, y, x+90, y+90);
    }

}