import com.fazecast.jSerialComm.SerialPort;

public class Lab1 {

    public static void main(String[] args) throws InterruptedException {

        String nombrePuerto = "COM3"; // cambiar por el puerto donde esta la ESP32

        SerialPort puerto = SerialPort.getCommPort(nombrePuerto);
        puerto.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
        puerto.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (!puerto.openPort()) {
            System.out.println("No se pudo abrir el puerto " + nombrePuerto);
            return;
        }
        System.out.println("Puerto abierto correctamente.");

        
        Thread.sleep(2000);

        // 1: Declara aqui un array de tamano fijo de 3 enteros llamado
        // "color" (debe seguir el orden RGB 0-1-2)


        // 2: Modifica "color" POR INDICE para que sea ROJO puro
        // (255, 0, 0) y envialo con enviarColor(puerto, color).
        // Despues espera 2000 ms con Thread.sleep antes de continuar.


        // 3: Modifica "color" POR INDICE (no declares un arreglo nuevo)
        // para que sea VERDE puro (0, 255, 0), envialo y espera 2000 ms.


        // 4: Modifica "color" POR INDICE para que sea AZUL puro
        // (0, 0, 255), envialo y espera 2000 ms.


        // 5: Elige un cuarto color de tu preferencia (por ejemplo una
        // mezcla), modifica "color" por indice, envialo y espera 2000 ms.


        apagarNeopixel(puerto);
        puerto.closePort();
        System.out.println("Puerto cerrado.");
    }

    private static void enviarColor(SerialPort puerto, int[] color) {
        // 6: Arma un String con el formato "R,G,B\n" usando color[0], color[1] y color[2]
        String mensaje = null; //Modifica aca 

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
