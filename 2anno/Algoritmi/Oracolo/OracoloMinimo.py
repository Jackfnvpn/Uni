def Oracolo(A):
    B=[0]*(len(A)-1)
    C=[0]*(len(A)-1)
    n=len(A)
    min=1

    for i in range(n-1):
        if A[i]<A[min]:
            min=i 
        B[i]=min 
    min=len(A)-1
    for i in range(n-2,0,-1):
        if A[i] < A[min]:
            min=i
        C[i]=min 
    return B,C 

def Query(A,k):
    B,C = Oracolo(A)
    return B[k],C[k]

a=[2,4,7,8,10,1]

print(Query(a,3))
