import com.fazecast.jSerialComm.SerialPort;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lab2_enunciado {

    private static final int NUM_LECTURAS = 0; 

    public static void main(String[] args) throws IOException {

        String nombrePuerto = "COM3"; 

        SerialPort puerto = SerialPort.getCommPort(nombrePuerto);
        puerto.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
        puerto.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 2000, 0);

        if (!puerto.openPort()) {
            System.out.println("No se pudo abrir el puerto " + nombrePuerto);
            return;
        }

        BufferedReader lector = new BufferedReader(new InputStreamReader(puerto.getInputStream()));


        puerto.closePort();
    }
}
