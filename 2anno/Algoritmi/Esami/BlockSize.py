def Oracolo(A):
    n=len(A)
    B=[0]*len(A)


    for i in range(n):
        if A[i]==1:
            B[i]=0
        else:
            B[i]=1+B[i-1]

    if B[n-1] > 0:
        B[n-2]=B[n-1]

    
    for j in range(n-2,0,-1):
        if B[j+1]>0 and A[j]==0:
            B[j]=B[j+1]
    return B


zero=[1,0,0,0,1,0,0,1,0,0]
print(Oracolo(zero))


