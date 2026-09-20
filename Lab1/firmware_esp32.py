"""
Laboratorio 1 - Colores RGB con un Arreglo
Firmware ESP32 (MicroPython) - YA COMPLETO, no se modifica.

Este script hace lo opuesto a los demas labs: en vez de enviar datos de un
sensor, se queda ESPERANDO que Java le mande un color por el puerto serie
y enciende un neopixel con ese color.

Formato esperado, una linea de texto terminada en salto de linea:
    R,G,B
donde R, G y B son enteros entre 0 y 255. Ejemplo:
    255,0,0

Conexiones:
- Neopixel: pin 25 (ya soldado en el shield Welcome Kit)
- LED integrado: pin 2 (parpadea brevemente cada vez que recibe un color valido)

Baudios: 115200
"""

from machine import Pin
from neopixel import NeoPixel
import sys

PIN_NEOPIXEL = 25
NUM_PIXELS = 1

np = NeoPixel(Pin(PIN_NEOPIXEL), NUM_PIXELS)
led = Pin(2, Pin.OUT)


def apagar():
    np[0] = (0, 0, 0)
    np.write()


def limitar(valor, minimo=0, maximo=255):
    return max(minimo, min(maximo, valor))


apagar()
print("Lab1 listo. Esperando colores por serial en formato R,G,B")

while True:
    try:
        linea = sys.stdin.readline()
        if not linea:
            continue

        linea = linea.strip()
        if linea == "":
            continue

        partes = linea.split(",")
        if len(partes) != 3:
            print("Formato invalido:", linea)
            continue

        r = limitar(int(partes[0]))
        g = limitar(int(partes[1]))
        b = limitar(int(partes[2]))

        np[0] = (r, g, b)
        np.write()

        led.value(1)
        print("OK", r, g, b)
        led.value(0)

    except Exception as e:
        print("Error:", e)
