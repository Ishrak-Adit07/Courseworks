import numpy as np
import matplotlib.pyplot as plt


def functionValue(x):
    numerator = 6.73*x+6.725*(10**-8)+7.26*(10**-4)*5*(10**-4)
    denominator = 3.62*(10**-12)*x+3.908*(10**-8)*x*5*(10**-4)

    return -numerator/denominator


def multipleSegmentIntegration(lower, higher, segment):
    h = (higher - lower) / segment
    summer = 0
    summer += functionValue(lower)

    for i in range(1, segment):
        summer += 2*functionValue(lower+i*h)
    summer += functionValue(higher)

    return h*summer/2


def multipleTrapezoidsDriver(lower, higher, nSegments):
    showAll = True
    prevIntegral = 1
    print("Exact Integral Value: ", prevIntegral)
    for i in range(1, nSegments+1):
        segmentIntegral = multipleSegmentIntegration(lower, higher, i)
        print("Value for ", i, " segments: ", segmentIntegral)
        if showAll and i != 1:
            pError = abs(prevIntegral - segmentIntegral) * 100 / segmentIntegral
            print("Error for ", i, " segments: ", pError, "%\n")
        prevIntegral = segmentIntegral


def SimpsonsMultipleSegmentIntegration(lower, higher, segment):
    h = (higher - lower) / segment
    summer = 0
    summer += functionValue(lower)

    for i in range(1, segment):
        if i % 2 == 1:
            summer += 4*functionValue(lower+i*h)
    for i in range(2, segment-1):
        if i % 2 == 0:
            summer += 2*functionValue(lower+i*h)
    summer += functionValue(higher)

    return h*summer/3


def SimpsonMultipleSegmentIntegrationWithSimples(lower, higher, segment):
    h = (higher - lower) / segment
    result = 0
    summer = 0

    a = lower
    b = lower + 2*h

    for i in range (0, int(segment/2)):
        summer = functionValue(a)+4*functionValue((a+b)/2)+functionValue(b)
        result += h*summer/3
        a += 2*h
        b += 2*h

    return result


def SimpsonDriver(lower, higher, nSegments):
    showAll = True
    prevIntegral = 1
    print("Exact Integral Value: ", prevIntegral)
    for i in range(1, nSegments + 1):
        segmentIntegral = SimpsonMultipleSegmentIntegrationWithSimples(lower, higher, 2*i)
        print("Value for ", 2*i, " segments: ", segmentIntegral)
        if showAll and i != 1:
            pError = abs(prevIntegral - segmentIntegral) * 100 / segmentIntegral
            print("Error for segment", 2*i, " segments: ", pError, "%\n")
        prevIntegral = segmentIntegral


def plot_graph():
    plt.close("all")
    y = [1.22, 1.20, 1.0, 0.8, 0.6, 0.4, 0.2]
    for i in range(0, 7):
        y[i] *= 10**-4
    x = np.ones(7)
    initial = 1.22*(10**-4)

    for i in range(0, 7):
        x[i] = SimpsonsMultipleSegmentIntegration(initial, y[i], 10)

    # plt.plot(x, y, 'r*')
    plt.plot(x, y, marker='o')
    plt.grid()
    plt.show()

# Main Program


choice = 0
while choice != 4:
    print("Enter desired operation: ")
    print("1. Trapezoid Integration\n2. Simpson's 1/3rd Rule\n3. Plot the Graph\n4. Exit")

    choice = int(input("Enter choice: "))
    if choice == 1:
        lowerLimit = 0.75*1.22*(10**-4)
        higherLimit = 0.25*1.22*(10**-4)
        segmentGiven = int(input("Enter intended segments: "))
        multipleTrapezoidsDriver(lowerLimit, higherLimit, segmentGiven)

    elif choice == 2:
        lowerLimit = 0.75*1.22*(10**-4)
        higherLimit = 0.25*1.22*(10**-4)
        segmentGiven = int(input("Enter intended segments: "))
        SimpsonDriver(lowerLimit, higherLimit, segmentGiven)

    elif choice == 3:
        plot_graph()

    elif choice == 4:
        exit(0)
