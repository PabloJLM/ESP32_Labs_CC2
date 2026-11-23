import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortTimeoutException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Proyecto_enunciado {

    private static final String ARCHIVO = "arbol.txt";

    private static SerialPort puerto;
    private static BufferedReader lector;
    private static Scanner teclado = new Scanner(System.in);

    // Un nodo es una pregunta (tiene si y no) o un animal (si y no son null).
    static class Nodo {
        String texto;
        Nodo si;
        Nodo no;

        Nodo(String texto, Nodo si, Nodo no) {
            this.texto = texto;
            this.si = si;
            this.no = no;
        }

        boolean esHoja() {
            return si == null;
        }
    }

    private static Nodo pregunta(String texto, Nodo si, Nodo no) {
        return new Nodo(texto, si, no);
    }

    private static Nodo animal(String nombre) {
        return new Nodo(nombre, null, null);
    }

    public static void main(String[] args) throws IOException {

        String nombrePuerto = "COM3";

        puerto = SerialPort.getCommPort(nombrePuerto);
        puerto.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
        puerto.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 200, 0);

        if (!puerto.openPort()) {
            System.out.println("No se pudo abrir el puerto " + nombrePuerto);
            return;
        }

        lector = new BufferedReader(new InputStreamReader(puerto.getInputStream()));

        Nodo raiz = cargarArbol();
        imprimirArbol(raiz, "", "");

        do {
            jugar(raiz);
            guardarArbol(raiz);
        } while (preguntar("Jugar otra vez?"));

        puerto.closePort();
    }

    // Arma el arbol de animales. Siempre empieza con "Es mamifero?".
    
    private static Nodo construirArbol() {
        return pregunta("Es mamifero?",
                animal("Perro"),
                animal("Gallina"));
    }


    // NO TOCAR
    private static void jugar(Nodo raiz) throws IOException {
        System.out.println();
        System.out.println("Piensa en un animal...");

        Nodo actual = raiz;
        while (!actual.esHoja()) {
            if (preguntar(actual.texto)) {
                actual = actual.si;
            } else {
                actual = actual.no;
            }
        }

        if (preguntar("Tu animal es: " + actual.texto + "?")) {
            System.out.println("Lo adivine!");
            enviar("ACIERTO");
        } else {
            enviar("FALLO");
            aprender(actual);
        }
    }

    
    private static void aprender(Nodo hoja) {
        System.out.print("En que animal pensabas? ");
        String nuevo = teclado.nextLine().trim();

        System.out.println("Escribe una pregunta que sea SI para " + nuevo
                + " y NO para " + hoja.texto + ":");
        String nuevaPregunta = teclado.nextLine().trim();

        String viejo = hoja.texto;
        hoja.texto = nuevaPregunta;
        hoja.si = animal(nuevo);
        hoja.no = animal(viejo);
        System.out.println("Aprendido!");
    }

    
    private static boolean preguntar(String texto) throws IOException {
        System.out.println(texto + "  (potenciometro SI/NO + boton)");
        while (true) {
            String respuesta = leerLinea();
            if (respuesta.equals("SI")) {
                return true;
            }
            if (respuesta.equals("NO")) {
                return false;
            }
        }
    }

    private static String leerLinea() throws IOException {
        while (true) {
            String linea;
            try {
                linea = lector.readLine();
            } catch (SerialPortTimeoutException e) {
                continue;
            }
            if (linea != null && !linea.trim().isEmpty()) {
                return linea.trim();
            }
        }
    }

    // Manda una palabra al ESP32 (ACIERTO = LED del centro verde, FALLO = rojo).
    private static void enviar(String mensaje) {
        byte[] datos = (mensaje + "\n").getBytes();
        puerto.writeBytes(datos, datos.length);
    }

    
    private static void imprimirArbol(Nodo nodo, String etiqueta, String sangria) {
        System.out.println(sangria + etiqueta + nodo.texto);
        if (!nodo.esHoja()) {
            imprimirArbol(nodo.si, "[SI] ", sangria + "    ");
            imprimirArbol(nodo.no, "[NO] ", sangria + "    ");
        }
    }

    

    private static Nodo cargarArbol() throws IOException {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return construirArbol();
        }
        BufferedReader in = new BufferedReader(new FileReader(archivo));
        Nodo raiz = leerNodo(in);
        in.close();
        System.out.println("Arbol cargado de " + ARCHIVO);
        return raiz;
    }

    private static void guardarArbol(Nodo raiz) throws IOException {
        PrintWriter out = new PrintWriter(ARCHIVO);
        escribirNodo(raiz, out);
        out.close();
    }

    private static void escribirNodo(Nodo nodo, PrintWriter out) {
        if (nodo.esHoja()) {
            out.println("A|" + nodo.texto);
        } else {
            out.println("P|" + nodo.texto);
            escribirNodo(nodo.si, out);
            escribirNodo(nodo.no, out);
        }
    }

    private static Nodo leerNodo(BufferedReader in) throws IOException {
        String linea = in.readLine();
        String texto = linea.substring(2);
        if (linea.startsWith("A|")) {
            return animal(texto);
        }
        Nodo si = leerNodo(in);
        Nodo no = leerNodo(in);
        return pregunta(texto, si, no);
    }
}
