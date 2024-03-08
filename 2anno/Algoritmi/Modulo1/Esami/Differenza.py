import math as mt
def Oracolo(A):

    zero=[0]*len(A)
    uno=[0]*len(A)

    if A[1]==0:
        zero[1]=1

    else:
        uno[1]=1

    for x in range(1,len(A)):
        if A[x]==0:
            zero[x]=1+zero[x-1]
            uno[x]=uno[x-1]
        else:
            uno[x]=1+uno[x-1]
            zero[x]=zero[x-1]
    return uno,zero 

def Differenza(A,i,j):
    B,C=Oracolo(A)

    count_zero=C[j]-C[i-1]
    count_uno=B[j]-B[i-1]

    x=mt.fabs(count_zero-count_uno)
    return int(x)

print(Differenza([0,0,1,0,0,1,0,1,0],2,5))






