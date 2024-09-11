import numpy as np


def GaussianElimination(A, B, pivot, showAll):
    n = len(A)
    # Forward Elimination
    for k in range(n - 1):
        for i in range(k + 1, n):
            # Partial Pivot Operation
            if A[k, k] != np.max(A[k:n, k]) and pivot:
                p = np.argmax(A[k:n, k])
                p = p + i - 1

                for j in range(n):
                    temp = A[k][j]
                    A[k][j] = A[p][j]
                    A[p][j] = temp
                for j in range(n):
                    rightTemp = B[k, 0]
                    B[k, 0] = B[p, 0]
                    B[p, 0] = rightTemp

                print("Partial pivot required")
                print(A)
                print(B)

            # Registering Forward Elimination
            if A[k, k] != 0:
                m = A[i, k] / A[k, k]
                A[i, k: n] = A[i, k: n] - m * A[k, k: n]
                B[i] = B[i] - m * B[k]
            elif A[k, k] == 0:
                return "Matrix not eligible for singular solution by gaussian elimination"

            if showAll:
                print("Elimination Operated")
                print(A)
                print(B)

    # Determinant of A calculated at the end of FE
    if showAll:
        determinant = 1
        for k in range(n):
            determinant = determinant * A[k, k]

        print("Determinant of coefficient matrix:", '%.4f' % determinant)

    # Back Substitution
    for k in range(n - 1, -1, -1):
        # Keeping the solutions directly in B
        B[k] = (B[k] - np.dot(A[k, k + 1: n], B[k + 1: n])) / A[k, k]

    # Returning Solution
    print("Solution of the variables: ")
    return B


# Main Program
n = int(input())
pivot = True
showAll = True

# Coefficient Inputs
st = input()
for i in range(n - 1):
    st = st + " " + input()
# Building Coefficient Matrix
elements = list(map(float, st.split()))
matrix = np.array(elements).reshape(n, n)

# Constants Inputs
ptr = input()
for i in range(n - 1):
    ptr = ptr + " " + input()
# Building Constant Matrix
constants = list(map(float, ptr.split()))
rightMatrix = np.array(constants).reshape(n, 1)

# Ensuring Both Matrices
np.set_printoptions(formatter={'float':'{: 0.4f}'.format})
if showAll:
    print("Coefficient Matrix: ")
    print(matrix)
    print("Constant Matrix: ")
    print(rightMatrix)

# Calling Gaussian Elimination Function that will return the solution
print(GaussianElimination(matrix, rightMatrix, pivot, showAll))



