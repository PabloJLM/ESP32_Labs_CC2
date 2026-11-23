from machine import Pin, ADC
import time

PIN_X = 34
PIN_Y = 35
PIN_BOTON = 19

# Cambio minimo (en lectura cruda 0-4095) para mandar un punto nuevo
UMBRAL = 40

# Mientras el boton siga presionado, repite UNDO cada este tiempo.
REPETIR_UNDO_MS = 150

adc_x = ADC(Pin(PIN_X))
adc_x.atten(ADC.ATTN_11DB)
adc_x.width(ADC.WIDTH_12BIT)

adc_y = ADC(Pin(PIN_Y))
adc_y.atten(ADC.ATTN_11DB)
adc_y.width(ADC.WIDTH_12BIT)

boton = Pin(PIN_BOTON, Pin.IN, Pin.PULL_UP)


def leer_promedio(adc):
    total = 0
    for _ in range(8):
        total += adc.read()
    return total // 8


ultimo_x = -1000
ultimo_y = -1000
ultimo_undo = 0

print("Fw_real listo.")

while True:
    if boton.value() == 0:
        ahora = time.ticks_ms()
        if time.ticks_diff(ahora, ultimo_undo) >= REPETIR_UNDO_MS:
            print("UNDO")
            ultimo_undo = ahora
    else:
        ultimo_undo = time.ticks_ms() - REPETIR_UNDO_MS
        x = leer_promedio(adc_x)
        y = leer_promedio(adc_y)
        if abs(x - ultimo_x) >= UMBRAL or abs(y - ultimo_y) >= UMBRAL:
            print(str(x) + "," + str(y))
            ultimo_x = x
            ultimo_y = y

    time.sleep_ms(30)
