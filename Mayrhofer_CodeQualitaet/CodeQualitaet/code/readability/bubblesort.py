import numpy as np
import random

array = np.arange(1, 20)
random.shuffle(array)
print(array)

for j in range(len(array)):
        for k in range(len(array) - 1):
            if array[k] > array[k + 1]:
                swap = array[k]
                array[k] = array[k + 1]
                array[k + 1] = swap
print(array)


random.shuffle(array)
for j in range(len(array)):
    for k in range(len(array) - 1):
        if array[k] > array[k + 1]:
            array[k] ^= array[k + 1]
            array[k + 1] ^= array[k]
            array[k] ^= array[k + 1]
print(array)