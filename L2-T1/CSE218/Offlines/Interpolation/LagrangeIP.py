import numpy as np
import matplotlib.pyplot as plt
import csv


def LagrangeIP1(x, y, n):
    desiredX = float(input("Enter Interpolation value: "))

    finalValue = 0
    storeValue = 1

    for i in range(n):
        for j in range(n):
            if j != i:
                storeValue *= (desiredX - x[j]) / (x[i] - x[j])
        storeValue *= y[i]
        finalValue += storeValue
        storeValue = 1

    return finalValue


def LagrangeIP2(x, y, n, desiredX):
    finalValue = 0
    storeValue = 1

    for i in range(n):
        for j in range(n):
            if j != i:
                storeValue *= (desiredX - x[j]) / (x[i] - x[j])
        storeValue *= y[i]
        finalValue += storeValue
        storeValue = 1

    return finalValue


x = np.zeros(15)
y1 = np.zeros(15)
y2 = np.zeros(15)

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

print("Cubic: ")
print(LagrangeIP1(x, y1, 3))
print("Quartic: ")
print(LagrangeIP1(x, y1, 4))


def plot_graph(x, y, n):
    plt.close("all")
    fx = np.ones(15);

    for i in range(n + 1):
        fx[i] = LagrangeIP2(x, y, n, x[i])

    print(fx)
    plt.plot(x[0:n], fx[0:n]);
    plt.grid()
    plt.show()
