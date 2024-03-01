def Bubble_Sort(A:list)->None:
    n=len(A)
    i=0
    while(i<n-1):
        j=i
        while(j<n-1):
            if A[i]>A[j]:
                A[i],A[j]=A[j],A[i]
            j+=1 
        i+=1 

A=[1,2,4,6,7,4,3,5,7,8,5,3,21]
Bubble_Sort(A)
print(A)
