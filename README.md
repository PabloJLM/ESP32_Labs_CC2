# Ciencias de la Computación II — Labs con Welcome Kit

Son 6 laboratorios independientes para el curso de CC2, integrando hardware
y software: ESP32 + shield Welcome Kit (MicroPython) para el hardware, y
Java (POO) para la lógica y visualización.

Cada laboratorio es **independiente** (su propio hardware, su propia
entrega). No se heredan clases entre labs.

| Lab | Tema Java | Estructura de datos | Hardware |
|-----|-----------|---------------------|----------|
| 1 | [LEDs RGB](https://www.instructables.com/Neopixels-How-Do-They-Work/) con un Array | Arreglo de tamaño fijo | Neopixel |
| 2 | Estadísticas de un [sensor analógico](https://solectroshop.com/es/blog/que-son-los-sensores-analogicos-todo-sobre-su-funcionamiento-n91) (mínimo, máximo y promedio) | Arreglo estático | Potenciómetro, LM35 o LDR |
| 3 | [Display de 7 segmentos](https://es.wikipedia.org/wiki/Visualizador_de_siete_segmentos) con DIP Switch | Matriz | DIP switch de 4 bits y display de 7 segmentos |
| 4 | Algoritmos de ordenamiento [Bubble vs Insertion](https://www.youtube.com/shorts/FVGezEtSku4?feature=share) | Lista | Dos sensores analógicos |
| 5 | Estructuras dinámicas y [búsqueda binaria](https://algomaster.io/animations/dsa-concepts/binary-search) | Lista enlazada | LDR |
| 6 | Stack y LIFO ([Etch A Sketch](https://en.wikipedia.org/wiki/Etch_A_Sketch)) | Pila | Joystick o dos potenciómetros |

**Notas:**

- Lab 1 es el único donde el flujo va Java → ESP32 (Java arma el color y se
  lo manda al neopixel). En los demás labs el ESP32 lee el sensor y envía
  los datos a Java.
- Lab 3: el ESP32 envía el valor del DIP switch, Java calcula la
  combinación de segmentos y se la devuelve al ESP32 (o al display)  aun pendiente de preguntar xd 

## Estructura de carpetas de cada lab

```
LabN/
├── GUIA.md              - instrucciones
├── firmware_esp32.py    - firmware del ESP32
├── LabN_enunciado.java  - Base
└── LabN_solucion.java   - soluciion solo para entregas mias 
```

## Conexiones fijas usadas en el shield Welcome Kit

- **Neopixel:** pin **25**
- **LED integrado:** pin 2
- **Buzzer:** pin 26
- Pines analógicos de sensores: se indican en la guía de cada lab.
- Todos los scripts corren a **115200 baudios**.

## Estado

- [x] Lab 1 — Colores RGB con un Array (problema y solución listos)
- [ ] Lab 2 — Estadísticas de un sensor analógico
- [ ] Lab 3 — Display de 7 segmentos con DIP SW
- [ ] Lab 4 — Algoritmos de ordenamiento
- [ ] Lab 5 — Estructuras dinámicas y búsqueda binaria
- [ ] Lab 6 — Stack y LIFO (proyecto)

---

# Guía de instalación

Antes de empezar hay que instalar 2 dependencias/JDK

1. **Thonny** — para programar el ESP32 en MicroPython (esto ya lo vieron en CC1).
2. **Java (JDK) + jSerialComm** — para programar la aplicación que lee/envía los datos.

---

## Parte 1 — Thonny y el ESP32

### 1.1 Instalar Thonny

- Windows / macOS: descarga el instalador desde <https://thonny.org>
- Linux: `sudo apt install thonny` 

### 1.2 Conectar el ESP32

1. Conecta el ESP32 (shield Welcome Kit) a la PC con un cable USB 
2. Abre Thonny.
3. Ve a **Herramientas → Opciones → Intérprete**.
4. En "Intérprete" elige **MicroPython (ESP32)**.
5. En "Puerto" elige el que aparezca (algo como `COM3` en Windows o `/dev/ttyUSB0` en Linux/Mac). Anota este puerto, lo vas a necesitar más adelante.
6. Presiona **Aceptar**. Abajo, en la consola (Shell), debería aparecer `>>>`. Eso significa que ya estás hablando con el ESP32.

> Si el firmware de MicroPython no está instalado, en la misma ventana de "Intérprete" hay un botón para instalarlo. Elige el modelo ESP32 y presiona instalar.

### 1.3 Cómo correr un script en el ESP32

1. Abre el archivo `.py` del laboratorio en Thonny (menú **Archivo → Abrir**).
2. Presiona el botón verde **Run (F5)**.
3. Los datos que el ESP32 imprime con `print(...)` (o los mensajes de confirmación, según el lab) aparecen en la **consola (Shell)** de abajo. Ahí puedes confirmar que está funcionando bien antes de conectar Java.

> **IMPORTANTE:** Java y Thonny **no pueden usar el puerto al mismo tiempo**.
> Cuando vayas a correr el programa de Java, primero detén el script en Thonny
> (botón rojo **Stop**) y cierra la conexión (o cierra Thonny). El ESP32 sigue
> ejecutando su último programa aunque cierres Thonny.

---

## Parte 2 — Java (JDK)

### 2.1 Instalar el JDK

Descarga **Java 21 (Temurin)** desde <https://adoptium.net>:

- Windows: descarga el `.msi`, instálalo dejando las opciones por defecto (asegúrate de marcar "Add to PATH" si aparece).
- macOS: descarga el `.pkg` e instálalo.
- Linux: `sudo apt install openjdk-21-jdk`.

### 2.2 Verificar la instalación

Abre una terminal (en Windows: **CMD** o **PowerShell**) y escribe:

```
java -version
javac -version
```

Ambos deben mostrar una versión 21.x. Si dice "comando no encontrado", reinstala marcando la opción de agregar al PATH.

---

## Parte 3 — jSerialComm (para que Java lea/escriba el puerto serie)

Java por sí solo **no sabe leer un puerto USB/serie**, así que usamos una librería
muy pequeña y estándar llamada **jSerialComm**. Es **un solo archivo `.jar`**, no es
un plugin ni requiere instalación.

### 3.1 El `.jar` ya está en este repo

El archivo `jSerialComm-2.11.4.jar` ya está en esta carpeta (raíz del
repositorio, junto a este archivo). Si necesitas una versión más nueva,
descárgala de <https://github.com/Fazecast/jSerialComm/releases> y
reemplázalo aquí — todos los labs lo referencian desde un nivel arriba
(`..\jSerialComm-2.11.4.jar`).

### 3.2 Cómo compilar y correr usando el `.jar`

Estando en la carpeta de un lab (un nivel abajo de este archivo):

**Windows (usa `;` como separador):**
```
javac -cp ".;..\jSerialComm-2.11.4.jar" Lab1_solucion.java
java  -cp ".;..\jSerialComm-2.11.4.jar" Lab1_solucion
```

**Linux / macOS (usa `:` como separador):**
```
javac -cp ".:../jSerialComm-2.11.4.jar" Lab1_solucion.java
java  -cp ".:../jSerialComm-2.11.4.jar" Lab1_solucion
```

> Cambia `Lab1_solucion` por el archivo del laboratorio que estés corriendo.

### 3.3 Probar que todo quedó bien instalado (`SerialTest.java`)

Antes de meterte a cualquier lab, usa `SerialTest.java` (en la raíz de este
repo) para confirmar que el JDK y jSerialComm están funcionando:

```
javac -cp ".;jSerialComm-2.11.4.jar" SerialTest.java
java  -cp ".;jSerialComm-2.11.4.jar" SerialTest
```

Con el ESP32 conectado (y Thonny cerrado o detenido para no bloquear el
puerto), deberías ver algo como:

```
Available Ports:
0: COM3
Port successfully opened!
Port closed.
```

- Si la lista de puertos sale **vacía**, el problema es de driver/cable, no
  de Java: revisa el Administrador de Dispositivos.
- Si dice **"Failed to open port"**, seguramente Thonny (u otro programa)
  todavía tiene el puerto abierto.
- Si compila y corre sin errores de clase (`NoClassDefFoundError`), eso
  confirma que el `.jar` está bien referenciado — ese mismo `-cp` es el que
  vas a usar en cada lab.

---

## Parte 4 — Encontrar el nombre del puerto (COM)

Cada programa de Java tiene una línea como:

```java
String nombrePuerto = "COM3";   // <-- CAMBIA ESTO
```

Debes poner ahí el mismo puerto que Thonny (o `SerialTest.java`) mostró:

- **Windows:** `COM3`, `COM4`, `COM5`, etc. (mira en el Administrador de Dispositivos → Puertos).
- **Linux:** `/dev/ttyUSB0` o `/dev/ttyACM0`.
- **macOS:** `/dev/cu.usbserial-XXXX` o `/dev/cu.SLAB_USBtoUART`.

---

## Resumen del flujo de trabajo (cada vez que hagas un lab)

1. Abre el script `.py` del lab en Thonny y presiona **Run** para cargarlo al ESP32.
2. Verifica en la consola de Thonny que todo esté funcionando (imprime datos o mensajes de confirmación).
3. **Detén Thonny** (Stop) para liberar el puerto.
4. En la terminal, compila y corre el programa de Java con el `.jar` (Parte 3.2).
5. El programa de Java empieza a leer o enviar datos por el puerto serie.

---

## Nota sobre las variantes de los labs con sensores (Lab 2, 4 y 5)

Esos laboratorios traen (o van a traer) **dos scripts de Python**:

- `*_real.py` → usa los sensores físicos reales (potenciómetro, LM35, etc.).
- `*_random.py` → **no necesita sensores**, envía datos al azar en el mismo formato.

Usa la versión `random` para **probar tu programa de Java y la conexión serial
sin tener que cablear nada**. Cuando tu Java ya funcione con datos al azar,
cambias al script `real` y solo conectas los sensores. **El programa de Java
es exactamente el mismo para las dos versiones.**

> El Lab 1 no aplica aquí: no lee sensores, solo recibe colores desde Java.
