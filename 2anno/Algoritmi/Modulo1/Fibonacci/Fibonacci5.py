import numpy as np

def fibonacci4(n):
    M=np.array([[1,0],[0,1]])
    c=np.array([[1,1],[1,0]])
    for x in range(0, n-1):
        M=np.dot(M,c)
    
    return M[0][0]

print(fibonacci4(1000))