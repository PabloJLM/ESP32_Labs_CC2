from machine import Pin, ADC
from neopixel import NeoPixel
import sys
import select
import time

PIN_SINO = 34
PIN_BOTON = 19
PIN_NEOPIXEL = 25

adc_sino = ADC(Pin(PIN_SINO))
adc_sino.atten(ADC.ATTN_11DB)
adc_sino.width(ADC.WIDTH_12BIT)

boton = Pin(PIN_BOTON, Pin.IN, Pin.PULL_UP)
np = NeoPixel(Pin(PIN_NEOPIXEL), 3)

entrada = select.poll()
entrada.register(sys.stdin, select.POLLIN)


def leer_sino():
    if adc_sino.read() >= 2048:
        return 1
    return 0


# LED 0 rojo = el potenciometro esta en NO, LED 2 verde = esta en SI
def mostrar(sino):
    if sino == 1:
        np[0] = (0, 0, 0)
        np[2] = (0, 40, 0)
    else:
        np[0] = (40, 0, 0)
        np[2] = (0, 0, 0)
    np.write()


print("Fw_real listo.")

while True:
    sino = leer_sino()
    mostrar(sino)

    # Boton: confirma la respuesta y espera a que lo suelten
    if boton.value() == 0:
        if sino == 1:
            print("SI")
        else:
            print("NO")
        np[1] = (0, 0, 0)  # apaga el resultado anterior
        while boton.value() == 0:
            time.sleep_ms(10)

    # Java avisa si adivino o no (LED del centro)
    if entrada.poll(0):
        linea = sys.stdin.readline().strip()
        if linea == "ACIERTO":
            np[1] = (0, 40, 0)  # verde
        elif linea == "FALLO":
            np[1] = (40, 0, 0)  # rojo

    time.sleep_ms(20)
