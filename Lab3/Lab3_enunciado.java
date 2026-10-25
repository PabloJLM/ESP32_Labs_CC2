import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortTimeoutException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lab3_enunciado {

    // matriz 5x3
    private static final int[] FILA    = {0, 1, 3, 4, 3, 1, 2};
    private static final int[] COLUMNA = {1, 2, 2, 1, 0, 0, 1};
    private static final char[] CARACTER = {'-', '|', '|', '-', '|', '|', '-'};

    // Que segmentos (A..G) estan encendidos por cada digito, 0-15 (hex)
    private static final boolean[][] TABLA_SEGMENTOS = {
        {true,  true,  true,  true,  true,  true,  false}, // 0
        {false, true,  true,  false, false, false, false}, // 1
        {true,  true,  false, true,  true,  false, true }, // 2
        {true,  true,  true,  true,  false, false, true }, // 3
        {false, true,  true,  false, false, true,  true }, // 4
        {true,  false, true,  true,  false, true,  true }, // 5
        {true,  false, true,  true,  true,  true,  true }, // 6
        {true,  true,  true,  false, false, false, false}, // 7
        {true,  true,  true,  true,  true,  true,  true }, // 8
        {true,  true,  true,  true,  false, true,  true }, // 9
        {true,  true,  true,  false, true,  true,  true }, // A
        {false, false, true,  true,  true,  true,  true }, // b
        {true,  false, false, true,  true,  true,  false}, // C
        {false, true,  true,  true,  true,  false, true }, // d
        {true,  false, false, true,  true,  true,  true }, // E
        {true,  false, false, false, true,  true,  true }, // F
    };

    public static void main(String[] args) throws IOException {

        String nombrePuerto = "COM3"; 

        SerialPort puerto = SerialPort.getCommPort(nombrePuerto);
        puerto.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
        puerto.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 200, 0);

        if (!puerto.openPort()) {
            System.out.println("No se pudo abrir el puerto " + nombrePuerto);
            return;
        }

        BufferedReader lector = new BufferedReader(new InputStreamReader(puerto.getInputStream()));
        DisplaySegmentos display = new DisplaySegmentos();

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

            int valor;
            try {
                valor = Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                continue;
            }
            if (valor < 0 || valor > 15) {
                continue;
            }

            // crea una matriz 5x3 llena de espacios ' '

            // recorre los 7 segmentos

            // display.mostrar(TABLA_SEGMENTOS[valor]);

            System.out.println("Valor: " + Integer.toHexString(valor));
        }
    }
}
