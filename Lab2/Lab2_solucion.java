import com.fazecast.jSerialComm.SerialPort;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lab2_solucion {

    private static final int NUM_LECTURAS = 10;

    public static void main(String[] args) throws IOException {

        String nombrePuerto = "COM23"; 

        SerialPort puerto = SerialPort.getCommPort(nombrePuerto);
        puerto.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
        puerto.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 2000, 0);

        if (!puerto.openPort()) {
            System.out.println("No se pudo abrir el puerto " + nombrePuerto);
            return;
        }

        BufferedReader lector = new BufferedReader(new InputStreamReader(puerto.getInputStream()));

        int[] lecturas = new int[NUM_LECTURAS];
        int contador = 0;

        while (contador < NUM_LECTURAS) {
            String linea = lector.readLine();
            if (linea == null || linea.trim().isEmpty()) {
                continue;
            }

            int valor;
            try {
                valor = Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                continue;
            }

            lecturas[contador] = valor;
            contador++;
            System.out.println("Lectura #" + contador + ": " + valor);
        }

        int minimo = lecturas[0];
        int maximo = lecturas[0];
        int suma = 0;

        for (int i = 0; i < NUM_LECTURAS; i++) {
            if (lecturas[i] < minimo) minimo = lecturas[i];
            if (lecturas[i] > maximo) maximo = lecturas[i];
            suma += lecturas[i];
        }

        double promedio = (double) suma / NUM_LECTURAS;
        System.out.printf("Estadisticas: %d | %d | %.2f%n", minimo, maximo, promedio);

        puerto.closePort();
    }
}
