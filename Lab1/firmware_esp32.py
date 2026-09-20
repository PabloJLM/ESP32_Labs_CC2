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
