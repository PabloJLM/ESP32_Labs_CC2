# Proyecto Final — Adivina el Animal (Árbol binario)

## Objetivo

Armar un árbol binario de decisión que adivine animales con preguntas de
sí/no, igual que el juego de las 20 preguntas. Tú decides qué animales y qué
características poner. El programa además **guarda el árbol en un archivo**
y **aprende** animales nuevos cuando falla.

## Archivos

- `main.py` — lee un potenciómetro (SÍ / NO) y un botón, y controla el neopixel, ya completo
- `Fw_random.py` — firmware de prueba sin hardware (responde SI/NO al azar)
- `Proyecto_enunciado.java`
- `Proyecto_solucion.java`

> Guarda `main.py` directo en el ESP32 (Thonny: Archivo → Guardar como...
> → MicroPython device → `main.py`) para que corra solo en cada reset.

## Funcionamiento

```
[ESP32: potenciómetro + botón] --(serial: "SI" o "NO")--> [Java: árbol binario]
[ESP32: neopixel] <--(serial: "ACIERTO" o "FALLO")-- [Java]
```

## Hardware

- Potenciómetro en pin **34**: de la mitad hacia un lado es **SÍ** y hacia el
  otro es **NO**.
- Botón en pin **19**, conectado a GND (pull-up interno, presionado = LOW):
  **confirma** la respuesta que marca el potenciómetro.
- Neopixel en pin **25**: el LED 2 verde indica que el potenciómetro está en
  SÍ y el LED 0 rojo que está en NO. El LED del centro lo controla Java:
  verde = adivinó, rojo = falló.
- Con `Fw_random.py` no hace falta cablear nada (responde al azar cada 1.5 s).

## Cómo es el árbol

Cada nodo es una **pregunta** (con un camino para SÍ y otro para NO) o un
**animal** (la hoja, donde se termina de preguntar).

```
Es mamifero?
    [SI] Vive en el agua?
        [SI] Delfin
        [NO] Perro
    [NO] Tiene plumas?
        [SI] Aguila
        [NO] Serpiente
```

Para armarlo en código se usan dos funciones que ya están hechas:

```java
pregunta("texto", loQueVaPorSI, loQueVaPorNO)
animal("Nombre")
```

El ejemplo de arriba en Java se escribe así:

```java
return pregunta("Es mamifero?",
        pregunta("Vive en el agua?",
                animal("Delfin"),
                animal("Perro")),
        pregunta("Tiene plumas?",
                animal("Aguila"),
                animal("Serpiente")));
```

## Lo que ya está dado en `Proyecto_enunciado.java`

El `main` abre el puerto, carga el árbol (de `arbol.txt` si existe, y si no
llama a `construirArbol`), lo imprime en consola y empieza a jugar. En cada
partida baja por el árbol haciendo preguntas que se responden con el
potenciómetro y el botón. Al llegar a un animal pregunta si acertó y avisa al neopixel.

Si falla, pide por teclado el animal en el que se pensaba y una pregunta que
lo distinga, y el árbol crece solo. Al terminar cada partida se guarda en
`arbol.txt`. Eso no se modifica.

## Lo que se debe programar

**`construirArbol()`**

Arma tu árbol de animales con `pregunta(...)` y `animal(...)`. Tú decides
qué características preguntar. Requisitos:

1. La primera pregunta (la raíz) es **`Es mamifero?`**.
2. Mínimo **8 animales** distintos.
3. Mínimo **3 niveles** de preguntas en alguna rama (no basta con una sola
   pregunta seguida de animales).
4. Cada pregunta se contesta solo con sí o no, y separa de verdad a los
   animales (por ejemplo `Vuela?`, `Tiene plumas?`, `Vive en el agua?`,
   `Come carne?`, `Tiene cuernos?`, `Tiene patas?`, `Tiene rayas?`).

Sin tildes ni signos de apertura (`¿`) para que la consola no los
distorsione.

## Importante: `arbol.txt`

Si ya existe `arbol.txt`, el programa carga ese archivo y **ignora**
`construirArbol`. Si cambias tu árbol en el código, borra primero
`arbol.txt` para que se use el nuevo.

## Compilar y correr

```
javac -cp ".;..\jSerialComm-2.11.4.jar" Proyecto_enunciado.java
java  -cp ".;..\jSerialComm-2.11.4.jar" Proyecto_enunciado
```
