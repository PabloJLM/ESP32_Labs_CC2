try:
    import urandom as random
except ImportError:
    import random
import time

print("Fw_random listo.")

while True:
    if random.randint(0, 19) == 0:
        print("UNDO")
    else:
        print(str(random.randint(0, 4095)) + "," + str(random.randint(0, 4095)))
    time.sleep_ms(100)
