# Laboratorio 4 — Algoritmos de Ordenamiento (Bubble vs Insertion)

## Objetivo

Practicar `ArrayList` implementando dos algoritmos de ordenamiento
(bubble sort e insertion sort) sobre el mismo conjunto de datos, y comparar
su eficiencia contando cuántas comparaciones hace cada uno.

## Archivos

- `main.py` — lee 2 potenciómetros, ya completo
- `Fw_random.py` — firmware de prueba sin hardware
- `Lab4_enunciado.java`
- `Lab4_solucion.java`

> Guarda `main.py` directo en el ESP32 (Thonny: Archivo → Guardar como...
> → MicroPython device → `main.py`) para que corra solo en cada reset.

## funcionamiento

```
[ESP32: 2 potenciometros] -->(serial, un valor 0-4095 por linea, alternando sensor)--> [Java: ArrayList, bubble vs insertion]
```

## Hardware

- Potenciómetro 1 en pin **34**, potenciómetro 2 en pin **35**.
- Cada ciclo (300 ms) el ESP32 manda primero la lectura del pin 34 y luego
  la del pin 35, cada una en su propia línea.
- Con `Fw_random.py` no hace falta cablear nada.

## Lo que ya está dado en `Lab4_enunciado.java`

El `main` ya hace todo el trabajo tedioso: abre el puerto, lee 20 líneas
del serial y las va guardando en un `ArrayList<Integer> datos` (sin
importar de cuál de los 2 potenciómetros vino cada lectura — para este lab
solo importa tener un conjunto de números para ordenar), saca dos copias
independientes de esa lista (`copiaBubble` y `copiaInsertion`, para que
cada algoritmo ordene su propia copia y no se estorben), y al final
imprime los 3 resultados con sus comparaciones. Eso no se modifica.

## Lo que se debe programar

Completar `bubbleSort(lista)` e `insertionSort(lista)`. Ambos reciben el
`ArrayList<Integer>` y deben:

1. Ordenarlo de menor a mayor, **modificándolo en el mismo objeto**
   (con `lista.get(i)` / `lista.set(i, valor)`, no creando una lista nueva).
2. Contar cada comparación entre dos elementos (cada vez que preguntas
   "¿es este mayor que aquel?", sin importar si terminó en intercambio o no)
   y devolver ese total como `int`.

**Bubble sort:** recorre la lista varias veces comparando parejas de
elementos consecutivos (`lista[j]` con `lista[j+1]`); si están en el orden
incorrecto, los intercambia. En cada pasada el elemento más grande
"burbujea" hasta el final, así que cada vuelta siguiente necesita revisar
una posición menos.

**Insertion sort:** toma los elementos uno por uno (de izquierda a
derecha) y cada uno lo va insertando en su posición correcta dentro de la
parte de la lista que ya está ordenada, comparando hacia atrás y
corriendo los elementos mayores un lugar a la derecha hasta encontrar
dónde cabe.

Al final vas a ver impreso cuántas comparaciones hizo cada uno sobre el
mismo conjunto de datos — esa es la comparación de eficiencia del lab.

## Compilar y correr

```
javac -cp ".;..\jSerialComm-2.11.4.jar" Lab4_enunciado.java
java  -cp ".;..\jSerialComm-2.11.4.jar" Lab4_enunciado
```
