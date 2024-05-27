import numpy as np
import csv


def NewtonD(x, y, n):
    desiredX = float(input("Enter Interpolation value: "))

    FinalValue = y[0]
    multiplicator = 1
    storeValue = 0
    hold = 1;

    while n >= 0:
        for i in range(n):
            DividedDifference[i] = (y[i + 1] - y[i]) / (x[i + hold] - x[i])
            y[i] = DividedDifference[i]

        for i in range(hold):
            multiplicator *= (desiredX - x[i])

        storeValue += multiplicator * y[0]

        multiplicator = 1
        hold += 1
        n -= 1

    FinalValue += storeValue
    return FinalValue


# Main Program
x = np.zeros(15)
y1 = np.zeros(15)
y2 = np.zeros(15)
DividedDifference = np.zeros(15)

with open('./dissolve.txt') as file:
    csv_reader = csv.reader(file, delimiter=',')

    line = 0
    for row in csv_reader:
        if line == 0:
            line += 1
        else:
            x[line-1] = float(row[0])
            y1[line-1] = float(row[1])
            # y2[line-1] =float(row[2])
            line += 1

print(line)
print(x)
print(y1)
print(y2)
n = line - 2

print(NewtonD(x, y1, n))
