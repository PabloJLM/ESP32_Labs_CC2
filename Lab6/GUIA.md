# Laboratorio 6 — Stack y LIFO (Etch A Sketch)

## Objetivo

Practicar `Stack` (pila, LIFO) usándola como historial de un dibujo tipo
Etch-A-Sketch: cada movimiento se apila, y un botón hace "deshacer"
quitando el último punto apilado.

## Archivos

- `main.py` — lee 2 potenciómetros (X, Y) y un botón, ya completo
- `Fw_random.py` — firmware de prueba sin hardware
- `LienzoEtch.java` — panel Swing que dibuja los puntos, ya completo
- `Lab6_enunciado.java`
- `Lab6_solucion.java`

> Guarda `main.py` directo en el ESP32 (Thonny: Archivo → Guardar como...
> → MicroPython device → `main.py`) para que corra solo en cada reset.

## Funcionamiento

```
[ESP32: pot X, pot Y, boton] --(serial: "x,y" o "UNDO")--> [Java: Stack<int[]>, dibuja en Swing]
```

## Hardware

- Potenciómetro X en pin **34**, potenciómetro Y en pin **35**.
- Botón en pin **19**, conectado a GND (pull-up interno, presionado = LOW).
- El ESP32 manda `"x,y"` con las lecturas crudas (0-4095) solo cuando
  algún potenciómetro se movió lo suficiente (filtra el ruido del ADC, si no
  la pila se llenaría de puntos repetidos y el `UNDO` no se notaría).
  Mientras el botón esté presionado manda `"UNDO"` repetido (cada ~150 ms),
  así se puede ir borrando el trazo manteniéndolo apretado.
- Con `Fw_random.py` no hace falta cablear nada (también simula el `UNDO`
  de vez en cuando).

## Lo que ya está dado en `Lab6_enunciado.java`

El `main` abre el puerto y lee líneas sin parar. Si la línea es `UNDO`
llama a `deshacer`. Si es `x,y` divide cada lectura entre 10 (0 a 4095 pasa
a 0 a 409, el tamaño de la ventana) y llama a `agregarPunto`. Después de
cualquiera de las dos le pasa la pila a `lienzo.dibujar` para repintar.

`LienzoEtch` ya viene completo. Abre la ventana y une con una línea cada
punto de la pila con el anterior, en el orden en que se apilaron.

Cada punto es un `Point` de Java, que solo guarda dos números, `x` y `y`.

## Lo que se debe programar

**`agregarPunto(pila, x, y)`**

Apila el punto nuevo con `pila.push(new Point(x, y))`.

**`deshacer(pila)`**

Quita el último punto con `pila.pop()`, pero solo si la pila no está
vacía, porque hacer `pop` a una pila vacía da error. Como el lienzo dibuja
lo que hay en la pila, quitar el de arriba borra el trazo más reciente.
Eso es LIFO, lo último que entra es lo primero que sale.

## Compilar y correr

Este lab tiene 2 archivos `.java` (`Lab6_enunciado.java` y
`LienzoEtch.java`), hay que compilarlos juntos:

```
javac -cp ".;..\jSerialComm-2.11.4.jar" Lab6_enunciado.java LienzoEtch.java
java  -cp ".;..\jSerialComm-2.11.4.jar" Lab6_enunciado
```
