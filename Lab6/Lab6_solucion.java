import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortTimeoutException;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Lab6_solucion {

    public static void main(String[] args) throws IOException {

        String nombrePuerto = "COM6";

        SerialPort puerto = SerialPort.getCommPort(nombrePuerto);
        puerto.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
        puerto.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 200, 0);

        if (!puerto.openPort()) {
            System.out.println("No se pudo abrir el puerto " + nombrePuerto);
            return;
        }

        BufferedReader lector = new BufferedReader(new InputStreamReader(puerto.getInputStream()));

        Stack<Point> pila = new Stack<>();
        LienzoEtch lienzo = new LienzoEtch();

        while (true) {
            String linea;
            try {
                linea = lector.readLine();
            } catch (SerialPortTimeoutException e) {
                continue;
            }
            if (linea == null || linea.trim().isEmpty()) {
                continue;
            }
            linea = linea.trim();

            if (linea.equals("UNDO")) {
                deshacer(pila);
            } else {
                String[] partes = linea.split(",");
                if (partes.length != 2) {
                    continue;
                }
                int x;
                int y;
                try {
                    x = Integer.parseInt(partes[0]) / 10;
                    y = Integer.parseInt(partes[1]) / 10;
                } catch (NumberFormatException e) {
                    continue;
                }
                agregarPunto(pila, x, y);
            }

            lienzo.dibujar(pila);
        }
    }

    private static void agregarPunto(Stack<Point> pila, int x, int y) {
        pila.push(new Point(x, y));
    }

    private static void deshacer(Stack<Point> pila) {
        if (!pila.isEmpty()) {
            pila.pop();
        }
    }
}
