def Somma(A,B):
    count_b=0
    for x in range(len(B)-1,0,-1):
        count_b+=B[x]
    
    if A[1]>count_b:
        return 1
    else:
        count_b-=B[1]

    for x in range(1,len(A)):
        if A[x]+A[x-1] > count_b:
            return x 
        else:
            count_b-=B[x]
            A[x]=A[x]+A[x-1]

print(Somma([1,5,6,7,5,4,3,2,5,6,1],[1,2,4,5,6,7,5,2,3,2,10]))  
