def Selection_Sort(A:list)->list:
    k=0
    n=len(A)
    while(k<n-1):
        min=k
        j=k+1 
        while(j<n):
            if A[j] < A[min]:
                min=j
            j+=1
        A[min],A[k]=A[k],A[min]
        k+=1
    return A


a=[1,2,10,5,6,4,3,6,7,5,3,3,5,6,4,3]
Y=Selection_Sort(a)

print(Y)
