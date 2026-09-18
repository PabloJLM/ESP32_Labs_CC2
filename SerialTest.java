import com.fazecast.jSerialComm.SerialPort;

public class SerialTest {
    public static void main(String[] args) {
        // 1. List all available serial ports
        SerialPort[] availablePorts = SerialPort.getCommPorts();
        System.out.println("Available Ports:");
        for (int i = 0; i < availablePorts.length; i++) {
            System.out.println(i + ": " + availablePorts[i].getSystemPortName());
        }

        // 2. Select the first available port (for demonstration)
        SerialPort serialPort = availablePorts[0];
        
        // 3. Open the port
        if (serialPort.openPort()) {
            System.out.println("Port successfully opened!");
            
            // 4. Set parameters
            serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
            
            // 5. Use port (Example: Close it right after)
            serialPort.closePort();
            System.out.println("Port closed.");
        } else {
            System.out.println("Failed to open port.");
        }
    }
}
