# Cómo probar el Lab 1 (guía para mí, no es para el estudiante)

Checklist rápido para verificar que todo funciona antes de entregárselo al
catedrático o al estudiante.

## 1. Cargar el firmware al ESP32 (Thonny)

### 1.1 Si no tienes Thonny instalado

- Descarga el instalador desde <https://thonny.org> y ejecútalo
  (Siguiente → Siguiente → Finalizar). No necesita configuración especial.

### 1.2 Conectar el ESP32 y configurar el intérprete

1. Conecta el ESP32 (shield Welcome Kit) por USB con un cable **de datos**
   (varios cables "de carga" no traen las líneas de datos y Thonny nunca
   va a detectar el puerto).
2. Abre Thonny.
3. Ve a **Herramientas → Opciones → Intérprete**.
4. En "Intérprete" selecciona **MicroPython (ESP32)**.
5. En "Puerto" elige el que aparezca (algo como `COM3`, `COM4`, etc. en
   Windows). **Anota este puerto**, lo vas a necesitar en la sección 2.
6. Presiona **Aceptar**. Abajo, en la consola (Shell) de Thonny, debería
   aparecer el prompt `>>>`. Eso confirma que Thonny ya está hablando con
   el ESP32.
   - Si no aparece `>>>` y en vez de eso da error de puerto: prueba con
     otro cable, otro puerto USB, o revisa en el Administrador de
     Dispositivos de Windows que el ESP32 aparezca como puerto COM (driver
     CP210x o CH340 instalado).
   - Si Thonny dice que no tiene el firmware de MicroPython instalado, en
     esa misma ventana de "Intérprete" hay un botón para instalarlo:
     elige el modelo ESP32 y presiona instalar (esto solo se hace una vez
     por placa).

### 1.3 Cargar y correr `firmware_esp32.py`

1. En Thonny, **Archivo → Abrir** y selecciona `firmware_esp32.py`
   (dentro de la carpeta `Lab1`).
2. Presiona el botón verde **Run (F5)**. Esto sube y ejecuta el script
   directamente en el ESP32 (no hace falta "grabarlo" aparte).
3. En la consola (Shell) de abajo debe aparecer:
   ```
   Lab1 listo. Esperando colores por serial en formato R,G,B
   ```
   Esta consola es donde vas a ver los mensajes `OK r g b` que imprime el
   ESP32 cada vez que reciba un color válido — es tu forma de confirmar
   que el firmware sí está recibiendo datos, sin depender de ver el
   neopixel encendiéndose.
   - Si no aparece nada: revisa que el pin del neopixel sea el 25 en tu
     shield, y que no haya un error de sintaxis marcado en rojo en la
     consola.
4. **IMPORTANTE — Thonny y Java no pueden usar el puerto al mismo tiempo.**
   Antes de correr el programa de Java (sección 3), presiona el botón rojo
   **Stop** en Thonny para liberar el puerto (o cierra Thonny por completo).
   El ESP32 se queda corriendo el último programa que le cargaste aunque
   detengas o cierres Thonny, así que va a seguir esperando colores.
5. Si necesitas volver a ver los mensajes `OK r g b` mientras Java está
   corriendo: no puedes tener Thonny y tu programa de Java abiertos al
   mismo puerto a la vez. Cierra Java, vuelve a abrir Thonny (sin presionar
   Run, solo para ver el Shell) para inspeccionar, y ciérralo de nuevo
   antes de volver a correr Java.

## 2. Preparar el `.java` que vas a probar

Para probar tu propia solución de referencia:

```
cd Lab1
notepad Lab1_solucion.java   (o el editor que uses)
```

Cambia esta línea por el puerto real que viste en Thonny:

```java
String nombrePuerto = "COM3"; // <-- pon tu puerto real
```

(Si en vez de eso quieres probar que el `enunciado` compila sin errores
antes de dárselo al estudiante, haz lo mismo con `Lab1_enunciado.java`,
aunque ese no va a hacer nada útil hasta que se completen los TODOs).

## 3. Compilar y correr (Windows, PowerShell o CMD)

Parado dentro de la carpeta `Lab1`:

```
javac -cp ".;..\jSerialComm-2.11.4.jar" Lab1_solucion.java
java  -cp ".;..\jSerialComm-2.11.4.jar" Lab1_solucion
```

Deberías ver en consola:

```
Puerto abierto correctamente.
Enviado: 255,0,0
Enviado: 0,255,0
Enviado: 0,0,255
Enviado: 128,0,128
Puerto cerrado.
```

Y el neopixel del shield debe pasar por rojo → verde → azul → morado, con
~2 segundos entre cada uno, y apagarse al final.

## 4. Errores comunes al probar

- **"No se pudo abrir el puerto COMx"** → el puerto está mal (revísalo en el
  Administrador de Dispositivos de Windows) o Thonny todavía lo tiene
  abierto (dale Stop o cierra Thonny).
- **`javac` no reconocido** → falta el JDK 21 en el PATH (ver
  `00_INSTALACION.md`).
- **`NoClassDefFoundError` o `ClassNotFoundException` para jSerialComm** →
  revisa que el `.jar` sí esté un nivel arriba (`PropuestaJava/`) y que el
  `-cp` lo esté apuntando bien (`..\jSerialComm-2.11.4.jar` en Windows).
- **El neopixel no cambia de color pero en consola sí dice "Enviado"** →
  revisa en la consola de Thonny (ábrela de nuevo sin correr nada, solo para
  ver el log) si el ESP32 está imprimiendo `OK r g b` por cada mensaje; si
  no imprime nada, el firmware no está corriendo o el cable es solo de carga.
- **Compila pero no hace nada / se cuelga** → confirma que llamaste
  `Thread.sleep(2000)` entre colores y que no cerraste el puerto antes de
  enviar todos los mensajes.

## 5. Antes de entregarlo al catedrático

- Corre este checklist con `Lab1_solucion.java` primero (debe funcionar).
- Después intenta compilar `Lab1_enunciado.java` tal cual (con los TODOs
  vacíos) solo para confirmar que **compila** aunque no haga nada útil —
  así te aseguras que no le vas a entregar al estudiante un archivo roto.
