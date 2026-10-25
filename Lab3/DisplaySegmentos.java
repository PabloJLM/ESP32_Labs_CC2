import javax.swing.*;
import java.awt.*;

public class DisplaySegmentos extends JPanel {

    private boolean[] segmentos = new boolean[7];

    public DisplaySegmentos() {
        setPreferredSize(new Dimension(120, 200));
        setBackground(Color.BLACK);

        JFrame frame = new JFrame("Display 7 segmentos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void mostrar(boolean[] nuevosSegmentos) {
        segmentos = nuevosSegmentos;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(segmentos[0] ? Color.RED : Color.DARK_GRAY); g.fillRect(20, 10, 60, 12);  // A
        g.setColor(segmentos[1] ? Color.RED : Color.DARK_GRAY); g.fillRect(85, 20, 12, 60);  // B
        g.setColor(segmentos[2] ? Color.RED : Color.DARK_GRAY); g.fillRect(85, 100, 12, 60); // C
        g.setColor(segmentos[3] ? Color.RED : Color.DARK_GRAY); g.fillRect(20, 160, 60, 12); // D
        g.setColor(segmentos[4] ? Color.RED : Color.DARK_GRAY); g.fillRect(10, 100, 12, 60); // E
        g.setColor(segmentos[5] ? Color.RED : Color.DARK_GRAY); g.fillRect(10, 20, 12, 60);  // F
        g.setColor(segmentos[6] ? Color.RED : Color.DARK_GRAY); g.fillRect(20, 85, 60, 12);  // G
    }
}
