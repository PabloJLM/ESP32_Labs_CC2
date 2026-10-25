# Laboratorio 2 — Estadísticas de un Sensor Analógico

## Objetivo

Practicar el recorrido de arreglos con ciclos: guardar e imprimir en vivo
las lecturas de un sensor analógico que llegan por el puerto serie, y al
final calcular mínimo, máximo y promedio con un solo recorrido del arreglo.

## Archivos

- `main.py` — firmware con el sensor físico (POT o LDR)
- `Fw_random.py` — firmware de prueba sin hardware
- `Lab2_enunciado.java`
- `Lab2_solucion.java`

## funcionamiento

```
[ESP32: lee ADC] -->(manda por serial la lectura de 0-4095)--> [Java arreglo estático, min/max/prom]
```

> **Importante:** guarda `main.py` directo en el ESP32 (Thonny: Archivo →
> Guardar como... → MicroPython device → `main.py`), no solo le des Run.
> Así corre solo cada vez que enciendes o reseteas la placa, sin Thonny
> abierto.

## Hardware

- Potenciómetro en pin **34** o LDR en pin **35** (elige con la constante
  `SENSOR` en `main.py`).
- Con `Fw_random.py` no hace falta cablear nada.

## Protocolo

El ESP32 envía una línea por lectura, un entero entre 0 y 4095, cada 300 ms.

## Lo que se debe programar

1. Tamaño del arreglo (`NUM_LECTURAS`).
2. Declarar `lecturas[NUM_LECTURAS]` y `contador` en 0.
3. Ciclo mientras `contador < NUM_LECTURAS`: leer línea, parsear a int,
   guardar en `lecturas[contador]`, incrementar `contador`, e imprimir la
   lectura en vivo (ej. `Lectura #3: 2048`).
4. Con el arreglo ya lleno, un solo `for` de `0` a `NUM_LECTURAS - 1` para
   calcular mínimo, máximo y suma.
5. `promedio = (double) suma / NUM_LECTURAS` e imprimir:
   ```
   Estadisticas: min | max | avg
   ```

Las estadísticas se calculan una sola vez, al final, no en cada lectura.


## Compilar y correr

```
javac -cp ".;..\jSerialComm-2.11.4.jar" Lab2_enunciado.java
java  -cp ".;..\jSerialComm-2.11.4.jar" Lab2_enunciado
```
