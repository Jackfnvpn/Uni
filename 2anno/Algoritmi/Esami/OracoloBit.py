def Oracolo(A):
    B=[0]*len(A)
    n=len(A)-1

    if A[n]==0:
        B[n]=-1

    for i in range(n-1,0,-1):
        if A[i]==1:
            B[i]=i 
        else:
            B[i]=i+1
    return B

def Query(A,i):
    oracolo=[0]*len(A)

    oracolo=Oracolo(A)

    value=oracolo[i]

    return value



bit=[1,0,0,1,1,0,0,1,0]

r=Query(bit,2)

print(r)
