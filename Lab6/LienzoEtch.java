import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Stack;

public class LienzoEtch extends JPanel {

    private Stack<Point> puntos = new Stack<>();

    public LienzoEtch() {
        setPreferredSize(new Dimension(410, 410));
        setBackground(Color.WHITE);
        JFrame ventana = new JFrame("Etch-A-Sketch");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(this);
        ventana.pack();
        ventana.setVisible(true);
    }

    public void dibujar(Stack<Point> pila) {
        puntos = pila;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (int i = 1; i < puntos.size(); i++) {
            Point anterior = puntos.get(i - 1);
            Point actual = puntos.get(i);
            g.drawLine(anterior.x, anterior.y, actual.x, actual.y);
        }
    }
}
