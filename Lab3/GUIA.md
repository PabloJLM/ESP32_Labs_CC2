# Laboratorio 3 — Display de 7 Segmentos con DIP SW

## Objetivo

Practicar matrices: representar en una matriz qué segmentos de un
display de 7 segmentos están encendidos para un valor binario/hex (0-15)
desde un DIP switch real, y dibujarlo en consola y con java swing

## Archivos

- `main.py` — lee el DIP switch (4 bits)
- `Fw_random.py` — firmware de prueba sin hardware
- `DisplaySegmentos.java` — GUI de Swing (NO TOCAR)
- `Lab3_enunciado.java`
- `Lab3_solucion.java`

## funcionamiento

```
[ESP32 lee DIP switch 4 bits] -->(serial, valor 0-15 al cambiar)--> [Java: matriz 5x3 --> dibuja en consola]
```

> **Importante:** guarda `main.py` directo en el ESP32 (Thonny: Archivo →
> Guardar como... --> MicroPython device --> `main.py`), tambien lo puedes hacer con play en Thonny luego cerrar

## Hardware

- DIP switch de 4 bits en pines **19 (bit0), 18 (bit1), 5 (bit2), 4 (bit3)**.
- Cada switch va a **GND** (pull-up interno)
- Con `Fw_random.py` no hace falta cablear nada.

## La matriz del display (5 filas x 3 columnas)

Cada segmento (A-G) ocupa una posición fija en una matriz de 5x3:

```
 - 
|  |
 - 
|  |
 - 
```

- A: fila 0, col 1 (`-`)
- B: fila 1, col 2 (`|`)
- C: fila 3, col 2 (`|`)
- D: fila 4, col 1 (`-`)
- E: fila 3, col 0 (`|`)
- F: fila 1, col 0 (`|`)
- G: fila 2, col 1 (`-`)

Ya están dadas en `FILA`, `COLUMNA` y `CARACTER` (arreglos paralelos,
índice 0-6 = A-G), junto con `TABLA_SEGMENTOS`, la tabla de verdad clásica
de 7 segmentos para 0-15 (hex).

## Lo que se debe programar

1. Crear `matriz[5][3]` de `char`, llena de `' '`.
2. Recorrer los 7 segmentos (`i` de 0 a 6): si `TABLA_SEGMENTOS[valor][i]`
   es `true`, poner `CARACTER[i]` en `matriz[FILA[i]][COLUMNA[i]]`.
3. Recorrer la matriz fila por fila e imprimir cada fila como texto.
4. Descomentar `display.mostrar(TABLA_SEGMENTOS[valor]);` para que la GUI
   dibuje el dígito también (ya está creada arriba, solo se usa).

## Compilar y correr

Hay que compilar `Lab3_enunciado.java` junto con `DisplaySegmentos.java`:

```
javac -cp ".;..\jSerialComm-2.11.4.jar" *.java
java  -cp ".;..\jSerialComm-2.11.4.jar" Lab3_enunciado
```

Al correrlo se abre, además de la consola, una ventana "Display 7
segmentos"
