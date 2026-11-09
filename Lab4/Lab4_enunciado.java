import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortTimeoutException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Lab4_enunciado {

    private static final int NUM_LECTURAS = 20;

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

        ArrayList<Integer> datos = new ArrayList<>();

        while (datos.size() < NUM_LECTURAS) {
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

            datos.add(valor);
            System.out.println("Lectura #" + datos.size() + ": " + valor);
        }

        ArrayList<Integer> copiaBubble = new ArrayList<>(datos);
        ArrayList<Integer> copiaInsertion = new ArrayList<>(datos);

        int comparacionesBubble = bubbleSort(copiaBubble);
        int comparacionesInsertion = insertionSort(copiaInsertion);

        System.out.println("Original: " + datos);
        System.out.println("Bubble sort: " + copiaBubble + " | comparaciones: " + comparacionesBubble);
        System.out.println("Insertion sort: " + copiaInsertion + " | comparaciones: " + comparacionesInsertion);

        puerto.closePort();
    }

    private static int bubbleSort(ArrayList<Integer> lista) {
        return 0;
    }

    private static int insertionSort(ArrayList<Integer> lista) {
        return 0;
    }
}
