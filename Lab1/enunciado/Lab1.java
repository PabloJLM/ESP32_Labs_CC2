import com.fazecast.jSerialComm.SerialPort;

/**
 * Laboratorio 1 - Colores RGB con un Arreglo
 *
 * El ESP32 ya tiene el firmware cargado (esp32/lab1_neopixel.py) y espera
 * recibir por el puerto serie una linea de texto con el formato "R,G,B\n"
 * (cada valor entre 0 y 255). Al recibirla, enciende el neopixel con ese color.
 *
 * Tu trabajo es completar el programa de Java: debes representar el color
 * con UN SOLO arreglo de 3 enteros (indices 0=R, 1=G, 2=B) y modificarlo
 * POR INDICE para armar una secuencia de colores.
 *
 * No se permite crear un arreglo nuevo para cada color: es el mismo arreglo,
 * modificado por indice, en cada paso.
 */
public class Lab1 {

    public static void main(String[] args) throws InterruptedException {

        String nombrePuerto = "COM3"; // <-- CAMBIA ESTO por tu puerto (ver 00_INSTALACION.md)

        SerialPort puerto = SerialPort.getCommPort(nombrePuerto);
        puerto.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
        puerto.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (!puerto.openPort()) {
            System.out.println("No se pudo abrir el puerto " + nombrePuerto);
            return;
        }
        System.out.println("Puerto abierto correctamente.");

        // Dale un momento al ESP32 para terminar de arrancar el script.
        Thread.sleep(2000);

        // TODO 1: Declara aqui un arreglo de tamano fijo de 3 enteros llamado
        // "color", que usaras para las 3 secciones de abajo (R = indice 0,
        // G = indice 1, B = indice 2).


        // TODO 2: Modifica "color" POR INDICE para que sea ROJO puro
        // (255, 0, 0) y envialo con enviarColor(puerto, color).
        // Despues espera 2000 ms con Thread.sleep antes de continuar.


        // TODO 3: Modifica "color" POR INDICE (no declares un arreglo nuevo)
        // para que sea VERDE puro (0, 255, 0), envialo y espera 2000 ms.


        // TODO 4: Modifica "color" POR INDICE para que sea AZUL puro
        // (0, 0, 255), envialo y espera 2000 ms.


        // TODO 5: Elige un cuarto color de tu preferencia (por ejemplo una
        // mezcla), modifica "color" por indice, envialo y espera 2000 ms.


        apagarNeopixel(puerto);
        puerto.closePort();
        System.out.println("Puerto cerrado.");
    }

    /**
     * Convierte el arreglo de color (3 enteros, 0-255) en el mensaje de
     * texto que espera el ESP32 y lo envia por el puerto serie.
     */
    private static void enviarColor(SerialPort puerto, int[] color) {
        // TODO 6: Arma un String con el formato "R,G,B\n" usando
        // color[0], color[1] y color[2]. Reemplaza la linea de abajo.
        String mensaje = null;

        byte[] datos = mensaje.getBytes();
        puerto.writeBytes(datos, datos.length);
        System.out.println("Enviado: " + mensaje.trim());
    }

    /** Apaga el neopixel al terminar (ya esta completo, no lo modifiques). */
    private static void apagarNeopixel(SerialPort puerto) {
        String mensaje = "0,0,0\n";
        byte[] datos = mensaje.getBytes();
        puerto.writeBytes(datos, datos.length);
    }
}
