from machine import Pin, ADC
import time

PIN_POT1 = 34
PIN_POT2 = 35

adc1 = ADC(Pin(PIN_POT1))
adc1.atten(ADC.ATTN_11DB)
adc1.width(ADC.WIDTH_12BIT)

adc2 = ADC(Pin(PIN_POT2))
adc2.atten(ADC.ATTN_11DB)
adc2.width(ADC.WIDTH_12BIT)

print("Fw_real listo.")

while True:
    print(adc1.read())
    print(adc2.read())
    time.sleep_ms(300)
