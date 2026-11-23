import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortTimeoutException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

public class Lab5_solucion {

    private static final int NUM_LECTURAS = 15;

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

        LinkedList<Integer> lista = new LinkedList<>();

        while (lista.size() < NUM_LECTURAS) {
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

            insertarOrdenado(lista, valor);
            System.out.println("Lectura #" + lista.size() + ": " + valor);
        }

        puerto.closePort();

        System.out.println("Lista ordenada: " + lista);

        Scanner teclado = new Scanner(System.in);
        System.out.print("Valor a buscar: ");
        int objetivo = teclado.nextInt();

        int indice = busquedaBinaria(lista, objetivo);

        if (indice == -1) {
            System.out.println(objetivo + " no esta en la lista");
        } else {
            System.out.println(objetivo + " encontrado en la posicion " + indice);
        }
    }

    private static void insertarOrdenado(LinkedList<Integer> lista, int valor) {
        int i = 0;
        while (i < lista.size() && lista.get(i) < valor) {
            i++;
        }
        lista.add(i, valor);
    }

    private static int busquedaBinaria(LinkedList<Integer> lista, int objetivo) {
        int bajo = 0;
        int alto = lista.size() - 1;
        while (bajo <= alto) {
            int medio = (bajo + alto) / 2;
            if (lista.get(medio) == objetivo) {
                return medio;
            }
            if (lista.get(medio) < objetivo) {
                bajo = medio + 1;
            } else {
                alto = medio - 1;
            }
        }
        return -1;
    }
}
