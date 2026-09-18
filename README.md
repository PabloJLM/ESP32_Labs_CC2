# Ciencias de la Computación II — Labs con Welcome Kit

Son 6 laboratorios complementarios para el curso de CC2 haciendo integracion de hardware y software. 
Usando la ESP32, Welcome Kit y Java para la visualizacion y logica **OOP**


Cada laboratorio es **independiente** (su propio hardware, su propia
entrega). 

*No se heredan clases entre labs.*

| Lab | Tema Java | Estructura de datos | Hardware |
|-----|-----------|---------------------|----------|
| 1 | [LEDs RGB](https://www.instructables.com/Neopixels-How-Do-They-Work/) con un Array | Arreglo de tamano fijo | Neopixel |
| 2 | Estadisticas de un [sensor analogico](https://solectroshop.com/es/blog/que-son-los-sensores-analogicos-todo-sobre-su-funcionamiento-n91) (minimo, maximo y promedio) | Arreglo estatico | Potenciometro, LM35 o LDR |
| 3 | [Display de 7 segmentos](https://es.wikipedia.org/wiki/Visualizador_de_siete_segmentos) con DIP Switch | Matriz | DIP switch de 4 bits y display de 7 segmentos |
| 4 | Algoritmos de ordenamiento  [Bubble vs Insertion](https://www.youtube.com/shorts/FVGezEtSku4?feature=share) | Lista | Dos sensores analogicos |
| 5 | Estructuras dinamicas y [busqueda binaria](https://algomaster.io/animations/dsa-concepts/binary-search) | Lista enlazada | LDR |
| 6 | Stack y LIFO ([Etch A Sketch](https://en.wikipedia.org/wiki/Etch_A_Sketch)) | Pila | Joystick o dos potenciometros |

**Notas:**

- Lab 3: el ESP32 envía el valor del DIP switch, Java calcula la
combinación de segmentos y se la devuelve al ESP32 (o al display), a
confirmar al diseñar ese lab xd

## Estructura de carpetas de cada lab

```
LabN/
├── GUIA.md            <- Instrucciones
├── esp32/
│   └── labN_*.py       <- firmware del ESP32 (No tocar)
├── enunciado/
│   └── LabN.java       <- 
└──  solucion/
│    └── LabN.java       <- solución de referencia completa (quitar xd)
```

## Antes de empezar

Lee **`00_INSTALACION.md`** — instala Thonny, Java 21 y el `jSerialComm.jar`.

## Conexiones fijas usadas en el shield Welcome Kit

- **Neopixel:** pin **25** (fijas en el Welcome Kit)
- **LED integrado:** pin 2
- **Buzzer:** pin 26
- Pines analógicos de sensores: se indican en la guía de cada lab.
- Todos los scripts corren a **115200 baudios**.

## Estado

- [x] Lab 1 — Colores RGB con un Array (problema, solución y reporte listos)
- [ ] Lab 2 — Estadísticas de un sensor analógico
- [ ] Lab 3 — Display de 7 segmentos con DIP SW
- [ ] Lab 4 — Algoritmos de ordenamiento
- [ ] Lab 5 — Estructuras dinámicas y búsqueda binaria
- [ ] Lab 6 — Stack y LIFO (proyecto)
