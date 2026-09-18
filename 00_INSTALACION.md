# Guía de Instalación — Ciencias de la Computación II

Antes de empezar cualquier laboratorio hay que preparar **dos entornos**:

1. **Thonny** — para programar el ESP32 en MicroPython (esto ya lo vieron en CC1).
2. **Java (JDK) + jSerialComm** — para programar la aplicación que lee los datos.

Sigue esta guía **una sola vez**. Después ya no la necesitas.

---

## Parte 1 — Thonny y el ESP32

### 1.1 Instalar Thonny

- Windows / macOS: descarga el instalador desde <https://thonny.org> y ejecútalo (Siguiente → Siguiente → Finalizar).
- Linux: `sudo apt install thonny` o desde la web.

### 1.2 Conectar el ESP32

1. Conecta el ESP32-WROVER a la PC con un cable USB **de datos** (no uno de solo carga).
2. Abre Thonny.
3. Ve a **Herramientas → Opciones → Intérprete**.
4. En "Intérprete" elige **MicroPython (ESP32)**.
5. En "Puerto" elige el que aparezca (algo como `COM3` en Windows o `/dev/ttyUSB0` en Linux/Mac).
6. Presiona **Aceptar**. Abajo, en la consola (Shell), debería aparecer `>>>`. Eso significa que ya estás hablando con el ESP32.

> Si el firmware de MicroPython no está instalado, en la misma ventana de "Intérprete" hay un botón para instalarlo. Elige el modelo ESP32 y presiona instalar.

### 1.3 Cómo correr un script en el ESP32

1. Abre el archivo `.py` del laboratorio en Thonny (menú **Archivo → Abrir**).
2. Presiona el botón verde **Run (F5)**.
3. Los datos que el ESP32 imprime con `print(...)` aparecen en la **consola (Shell)** de abajo. Ahí puedes confirmar que está enviando bien antes de conectar Java.

> **IMPORTANTE:** Java y Thonny **no pueden usar el puerto al mismo tiempo**.
> Cuando vayas a correr el programa de Java, primero detén el script en Thonny
> (botón rojo **Stop**) y cierra la conexión (o cierra Thonny). El ESP32 sigue
> ejecutando su último programa aunque cierres Thonny, así que seguirá enviando datos.

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

## Parte 3 — jSerialComm (para que Java lea el puerto serie)

Java por sí solo **no sabe leer un puerto USB/serie**, así que usamos una librería
muy pequeña y estándar llamada **jSerialComm**. Es **un solo archivo `.jar`**, no es
un plugin ni requiere instalación.

### 3.1 Descargar el `.jar`

1. Ve a <https://github.com/Fazecast/jSerialComm/releases>.
2. Descarga el archivo `jSerialComm-2.11.0.jar` (o la versión más reciente).
3. **Copia ese `.jar` dentro de la carpeta de cada laboratorio**, junto a tu archivo `.java`.

### 3.2 Cómo compilar y correr usando el `.jar`

Estando en la carpeta donde están tu `.java` y el `.jar`:

**Windows (usa `;` como separador):**
```
javac -cp ".;jSerialComm-2.11.0.jar" Lab1.java
java  -cp ".;jSerialComm-2.11.0.jar" Lab1
```

**Linux / macOS (usa `:` como separador):**
```
javac -cp ".:jSerialComm-2.11.0.jar" Lab1.java
java  -cp ".:jSerialComm-2.11.0.jar" Lab1
```

> Cambia `Lab1` por el nombre del laboratorio que estés corriendo.

---

## Parte 4 — Encontrar el nombre del puerto (COM)

Cada programa de Java tiene una línea como:

```java
String puerto = "COM3";   // <-- CAMBIA ESTO
```

Debes poner ahí el mismo puerto que Thonny mostró en la Parte 1.2:

- **Windows:** `COM3`, `COM4`, `COM5`, etc. (mira en el Administrador de Dispositivos → Puertos).
- **Linux:** `/dev/ttyUSB0` o `/dev/ttyACM0`.
- **macOS:** `/dev/cu.usbserial-XXXX` o `/dev/cu.SLAB_USBtoUART`.

---

## Resumen del flujo de trabajo (cada vez que hagas un lab)

1. Abre el script `.py` del lab en Thonny y presiona **Run** para cargarlo al ESP32.
2. Verifica en la consola de Thonny que salgan datos.
3. **Detén Thonny** (Stop) para liberar el puerto.
4. En la terminal, compila y corre el programa de Java con el `.jar`.
5. El programa de Java empieza a leer los datos que el ESP32 sigue enviando.

---

## Nota sobre las dos variantes de cada laboratorio

Cada laboratorio trae **dos scripts de Python**:

- `*_real.py` → usa los sensores físicos reales (potenciómetro, LM35, etc.).
- `*_random.py` → **no necesita sensores**, envía datos al azar en el mismo formato.

Usa la versión `random` para **probar tu programa de Java y la conexión serial
sin tener que cablear nada**. Cuando tu Java ya funcione con datos al azar,
cambias al script `real` y solo conectas los sensores. **El programa de Java es
exactamente el mismo para las dos versiones.**
