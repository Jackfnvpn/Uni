import numpy as np
def fibonacci6(n):
    A=np.array([[1,1],[1,0]])
    
    M=potenzadimatrice(A,n-1)
    return M[0][0]

def potenzadimatrice(A,k):
    if k==0:
        t=np.array([[1,0],[0,1]])
        return t  
    else:
        M=potenzadimatrice(A,k//2)
        M=np.dot(M,M)
    
    if (k%2==1):
        M=np.dot(M,A)
    
    return M



print(fibonacci6(8))