
try:
    import urandom as random
except ImportError:
    import random
import time

print("Fw_random listo.")

while True:
    print(random.randint(0, 4095))
    time.sleep_ms(300)
