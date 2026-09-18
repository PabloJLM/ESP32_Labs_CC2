# Reporte — Laboratorio 1: Colores RGB con un Arreglo

**Nombre:** ______________________________
**Carné:** ______________________________
**Fecha:** ______________________________

## 1. Objetivo

(Explica en tus propias palabras qué se practicó en este laboratorio.)

## 2. Marco teórico breve

- ¿Qué es el modelo de color RGB?
- ¿Por qué se dice que un color digital es "solo un conjunto de 3 números"?

## 3. Descripción del hardware

- Microcontrolador usado y shield.
- Componente de salida (neopixel) y pin utilizado.
- Foto de la conexión (opcional, el neopixel ya viene en el shield).

## 4. Descripción de la solución

- Explica cómo declaraste y modificaste el arreglo `color` por índice.
- Explica el protocolo que usaste para enviar el color por serial
  (formato del mensaje, cómo lo arma `enviarColor`).
- Incluye el fragmento de código más relevante de tu `Lab1.java`.

## 5. Evidencia de funcionamiento

- Captura de pantalla de la consola de Java mostrando los 4 colores
  enviados ("Enviado: ...").
- Foto o video (enlace) del neopixel cambiando entre los 4 colores.

## 6. Preguntas de análisis

1. ¿Qué pasaría si envías un valor fuera del rango 0–255? ¿Tu programa lo
   evita, o depende del firmware del ESP32?

2. ¿Por qué conviene usar un arreglo de 3 posiciones en vez de 3 variables
   sueltas (`r`, `g`, `b`)?

3. Si quisieras controlar 2 neopixels en vez de 1, ¿qué cambiarías en el
   arreglo y en el protocolo de comunicación?

## 7. Conclusiones

(Mínimo 3 conclusiones basadas en lo aprendido.)

## 8. Dificultades encontradas

(Opcional.)
