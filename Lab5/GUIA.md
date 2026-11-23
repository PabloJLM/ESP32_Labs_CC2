# Laboratorio 5 — Estructuras Dinámicas y Búsqueda Binaria

## Objetivo

Practicar `LinkedList` insertando cada lectura en su posición ordenada
(en vez de ordenar todo al final), y luego implementar búsqueda binaria
sobre esa lista para encontrar un valor.

## Archivos

- `main.py` — lee el LDR, ya completo
- `Fw_random.py` — firmware de prueba sin hardware
- `Lab5_enunciado.java`
- `Lab5_solucion.java`

> Guarda `main.py` directo en el ESP32 (Thonny: Archivo → Guardar como...
> → MicroPython device → `main.py`) para que corra solo en cada reset.

## Funcionamiento

```
[ESP32: LDR] -->(serial, un valor 0-4095 por linea)--> [Java: LinkedList ordenada, busqueda binaria]
```

## Hardware

- LDR en pin **34**.
- Cada 300 ms el ESP32 manda una lectura.
- Con `Fw_random.py` no hace falta cablear nada.

## Lo que ya está dado en `Lab5_enunciado.java`

El `main` abre el puerto, lee 15 lecturas del serial y por cada una llama a
`insertarOrdenado`. Luego imprime la lista, pide por teclado un valor a
buscar y llama a `busquedaBinaria`, que devuelve la posición donde está o
`-1` si no está. Eso no se modifica.

## Lo que se debe programar

**`insertarOrdenado(lista, valor)`**

Mete `valor` en la lista de forma que siempre quede ordenada de menor a
mayor. Se recorre la lista desde la posición 0 mientras el número guardado
sea menor que `valor`. Donde se detiene, ahí va el nuevo, con
`lista.add(i, valor)`. Así nunca hace falta ordenar al final.

**`busquedaBinaria(lista, objetivo)`**

Se usan dos variables, `bajo` y `alto`, que marcan el pedazo de la lista
donde todavía puede estar el número. Se mira el del medio. Si es el
buscado se devuelve su posición. Si es menor, el buscado está a la derecha
y `bajo` pasa a `medio + 1`. Si es mayor, está a la izquierda y `alto`
pasa a `medio - 1`. Cuando `bajo` pasa a `alto` ya no queda dónde buscar y
se devuelve `-1`.

## Compilar y correr

```
javac -cp ".;..\jSerialComm-2.11.4.jar" Lab5_enunciado.java
java  -cp ".;..\jSerialComm-2.11.4.jar" Lab5_enunciado
```
