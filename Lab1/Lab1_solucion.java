import com.fazecast.jSerialComm.SerialPort;

public class Lab1 {

    public static void main(String[] args) throws InterruptedException {

        String nombrePuerto = "COM3"; // <-- cambiar por el puerto real

        SerialPort puerto = SerialPort.getCommPort(nombrePuerto);
        puerto.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
        puerto.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (!puerto.openPort()) {
            System.out.println("No se pudo abrir el puerto " + nombrePuerto);
            return;
        }
        System.out.println("Puerto abierto correctamente.");

        Thread.sleep(2000);

        // Un solo arreglo de tamano fijo, reutilizado y modificado por indice.
        int[] color = new int[3];

        // Rojo
        color[0] = 255;
        color[1] = 0;
        color[2] = 0;
        enviarColor(puerto, color);
        Thread.sleep(2000);

        // Verde
        color[0] = 0;
        color[1] = 255;
        color[2] = 0;
        enviarColor(puerto, color);
        Thread.sleep(2000);

        // Azul
        color[0] = 0;
        color[1] = 0;
        color[2] = 255;
        enviarColor(puerto, color);
        Thread.sleep(2000);

        // Color a eleccion (morado)
        color[0] = 128;
        color[1] = 0;
        color[2] = 128;
        enviarColor(puerto, color);
        Thread.sleep(2000);

        apagarNeopixel(puerto);
        puerto.closePort();
        System.out.println("Puerto cerrado.");
    }

    private static void enviarColor(SerialPort puerto, int[] color) {
        String mensaje = color[0] + "," + color[1] + "," + color[2] + "\n";
        byte[] datos = mensaje.getBytes();
        puerto.writeBytes(datos, datos.length);
        System.out.println("Enviado: " + mensaje.trim());
    }

    private static void apagarNeopixel(SerialPort puerto) {
        String mensaje = "0,0,0\n";
        byte[] datos = mensaje.getBytes();
        puerto.writeBytes(datos, datos.length);
    }
}
