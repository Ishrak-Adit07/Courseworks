import numpy as np
import matplotlib.pyplot as plt


def plot_Graph():
    plt.close("all")
    x = np.linspace(-.02, .12, 100)
    y = (x**3)-0.18*(x**2)+0.0004752
    plt.plot(x, y, 'k', label='Graph')
    plt.legend(loc='best')
    plt.xlabel("x axis")
    plt.ylabel("y axis")
    plt.grid()
    plt.show()


def function(x):
    return (x**3)-0.18*(x**2)+0.0004752


def derivative(x):
    return 3*(x**2)-0.36*x


#For answer check
radius = 0.12


def NR(root, error, maxIteration):
    for i in range(maxIteration):
        functionValue = function(root)
        derivativeValue = derivative(root)
        rootPrime = root - (functionValue / derivativeValue)
        pError = abs((root - rootPrime) * 100 / rootPrime)
        if pError < error  and 0<=rootPrime<=radius:
            return rootPrime
        elif pError<error and (rootPrime<0 or rootPrime>radius):
            continue
        else:
            root = rootPrime


def bisection(lower, higher, error, maxIteration):
    root = (lower + higher) / 2
    for i in range(maxIteration):
        if function(lower)*function(root) < 0:
            higher = root
            root = (lower+higher)/2
            pError = abs((higher - root)*100/root)
            if pError < error:
                return root
        elif function(root)*function(higher) < 0:
            lower = root
            root = (lower+higher)/2
            pError = abs((root-lower)*100/root)
            if pError < error:
                return root
        elif function(root)*function(lower) == 0:
            return root


print("Given Equation: x^5+3x^2-10=0")

while True:
    print("1.Plot the Graph\n2. Bisection Method\n3. Newton-Raphson Method\n4.Exit")
    choice = int(input("Enter Operation Choice: "))

    if choice == 1:
        plot_Graph()

    elif choice == 2:
        print("Initiating Bisection Method")
        while True:
            print("Enter Appropriate Lower Bound and Higher Bound)")
            print("(According to graph, appropriate bound should be within -0.02 to 0.15)")
            lower = float(input("Lower Bound: "))
            higher = float(input("Higher Bound: "))
            error = .5
            if function(lower) * function(higher) < 0:
                maxIteration = int(input("Enter Max Permissible Iteration: "))
                print(bisection(lower, higher, error, maxIteration))
                break
            else:
                print("Provided bounds are not suitable for bisection method")

    elif choice == 3:
        print("Initiating Newton-Raphson Method")
        print("(For appropriate root, enter assumption between .02 to .10, except 0)")
        root = 0
        while root == 0:
            root = float(input("Enter your initial assumption: "))
        error = .5
        maxIteration = int(input("Enter Max Permissible Iteration: "))
        print(NR(root, error, maxIteration))

    elif choice == 4:
        break