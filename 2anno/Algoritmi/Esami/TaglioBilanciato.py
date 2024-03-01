def Oracolo(A):
    max=[0]*len(A)
    min=[0]*len(A)

    max[1]=A[1]
    min[len(A)-1]=A[len(A)-1]

    for i in range(1,len(A)):
        max[i]=A[i]+max[i-1]
    for j in range(len(A)-2,1,-1):
        min[j]=A[j]+min[j+1]

    return max,min 

def TaglioBilanciato(A,x,i,j):
    max,min=Oracolo(A)
    m=(i+j)//2
    if max[m]+x >= min[m]+x:
        if max[m-1]+x >= min[m-1]+x:
            return TaglioBilanciato(A,x,i,m-1)
        else: 
            return m 
    if max[m]+x < min[m]+x:
        return TaglioBilanciato(A,x,m+1,j)

print(TaglioBilanciato([1,5,6,4,3,2,5,6,7,5,7,8],2,0,11))
