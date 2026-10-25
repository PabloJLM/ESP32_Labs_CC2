

from machine import Pin
import time

PINES = [19, 18, 5, 4]  

entradas = [Pin(p, Pin.IN, Pin.PULL_UP) for p in PINES]

def leer_valor():
    valor = 0
    for i, pin in enumerate(entradas):
        encendido = not pin.value()  
        valor |= encendido << i
    return valor

anterior = -1
print("Fw_real listo. DIP switch en pines", PINES)

while True:
    actual = leer_valor()
    if actual != anterior:
        print(actual)
        anterior = actual
    time.sleep_ms(50)
