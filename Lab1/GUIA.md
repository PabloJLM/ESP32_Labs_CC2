# Laboratorio 1 — Colores RGB con un Arreglo

## Objetivo

Que el estudiante practique el manejo de arreglos de tamaño fijo (declaración
y modificación por índice) en Java, y refuerce el concepto de que un color
digital es simplemente un conjunto de 3 números (R, G, B), enviando esos
valores a un ESP32 que enciende un neopixel con el color recibido.

## Arquitectura de este laboratorio

A diferencia de los demás labs del curso, aquí **Java es quien manda datos**
y el ESP32 solo obedece:

```
[Java: arreglo color[3]] --(serial, "R,G,B\n")--> [ESP32: enciende neopixel]
```

## Hardware

- ESP32 con shield Welcome Kit (neopixel integrado en el pin **25**).
- Cable USB de datos.
- No se necesita ningún sensor para este laboratorio.

## Protocolo de comunicación

Java envía por el puerto serie una línea de texto con el formato:

```
R,G,B
```

donde `R`, `G` y `B` son enteros entre 0 y 255, seguidos de un salto de línea
(`\n`). Ejemplo: para rojo puro se envía `255,0,0\n`.

## Firmware del ESP32 (ya está completo)

El archivo `esp32/lab1_neopixel.py` **ya viene resuelto** — no se modifica.
Solo debes cargarlo al ESP32 con Thonny (ver `00_INSTALACION.md`) y dejarlo
corriendo antes de ejecutar tu programa de Java. Una vez cargado y corriendo:

1. En la consola de Thonny debe aparecer `Lab1 listo. Esperando colores...`
2. Presiona **Stop** en Thonny para liberar el puerto (Java y Thonny no
   pueden usar el puerto al mismo tiempo).
3. El ESP32 se queda corriendo el script aunque cierres Thonny.

## Lo que debes programar en Java (`enunciado/Lab1.java`)

El archivo tiene 6 partes marcadas con `TODO`. En resumen debes:

1. Declarar **un solo arreglo** de tamaño fijo `int[3]` para el color
   (índice 0 = R, índice 1 = G, índice 2 = B).
2. Modificar ese mismo arreglo **por índice** (no crear arreglos nuevos)
   para producir, en orden: rojo, verde, azul y un cuarto color de tu
   elección, enviando cada uno por el puerto serie con una pausa de 2
   segundos entre colores.
3. Completar el método `enviarColor`, que arma el `String "R,G,B\n"` a
   partir del arreglo y lo escribe en el puerto serie.

**Regla clave:** todo el trabajo de armar los colores se hace modificando
`color[0]`, `color[1]` y `color[2]` sobre el mismo arreglo — es el punto
central del laboratorio.

## Cómo compilar y correr

Sigue la Parte 3 de `00_INSTALACION.md`. Resumen (Windows):

```
javac -cp ".;jSerialComm-2.11.4.jar" Lab1.java
java  -cp ".;jSerialComm-2.11.4.jar" Lab1
```

Recuerda cambiar `"COM3"` en el código por el puerto real que te mostró
Thonny.

## Criterios de evaluación sugeridos

| Criterio | Puntos |
|---|---|
| Declara correctamente el arreglo de tamaño fijo | 15 |
| Modifica el arreglo por índice (no crea arreglos nuevos por color) | 30 |
| `enviarColor` arma el formato "R,G,B\n" correctamente | 25 |
| Los 4 colores se ven correctamente en el neopixel, con pausas | 20 |
| Reporte completo | 10 |

## Preguntas para el reporte

1. ¿Qué pasaría si envías un valor fuera del rango 0–255? ¿Tu programa lo
   evita, o depende del firmware del ESP32?
2. ¿Por qué conviene usar un arreglo de 3 posiciones en vez de 3 variables
   sueltas (`r`, `g`, `b`)?
3. Si quisieras controlar 2 neopixels en vez de 1, ¿qué cambiarías en el
   arreglo y en el protocolo de comunicación?
