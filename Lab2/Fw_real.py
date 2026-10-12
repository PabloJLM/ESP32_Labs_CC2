from machine import Pin, ADC
import time

SENSOR = "LDR"  # "POT" o "LDR"
PIN_POT = 15	
PIN_LDR = 35

pin = PIN_POT if SENSOR == "POT" else PIN_LDR
adc = ADC(Pin(pin))
adc.atten(ADC.ATTN_11DB)
adc.width(ADC.WIDTH_12BIT)

print("Fw_real listo. Sensor:", SENSOR, "pin", pin)

while True:
    print(adc.read())
    time.sleep_ms(300)
