
try:
    import urandom as random
except ImportError:
    import random
import time

print("Fw_random listo.")

while True:
    print(random.randint(0, 15))
    time.sleep(2)
