from machine import Pin, ADC
import time

PIN_X = 34
PIN_Y = 35
PIN_BOTON = 19

adc_x = ADC(Pin(PIN_X))
adc_x.atten(ADC.ATTN_11DB)
adc_x.width(ADC.WIDTH_12BIT)

adc_y = ADC(Pin(PIN_Y))
adc_y.atten(ADC.ATTN_11DB)
adc_y.width(ADC.WIDTH_12BIT)

boton = Pin(PIN_BOTON, Pin.IN, Pin.PULL_UP)
estado_anterior = 1

print("Fw_real listo.")

while True:
    print(str(adc_x.read()) + "," + str(adc_y.read()))

    actual = boton.value()
    if actual == 0 and estado_anterior == 1:
        print("UNDO")
    estado_anterior = actual

    time.sleep_ms(100)
