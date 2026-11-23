from machine import Pin, ADC
import time

PIN_LDR = 34

adc = ADC(Pin(PIN_LDR))
adc.atten(ADC.ATTN_11DB)
adc.width(ADC.WIDTH_12BIT)

print("Fw_real listo.")

while True:
    print(adc.read())
    time.sleep_ms(300)
