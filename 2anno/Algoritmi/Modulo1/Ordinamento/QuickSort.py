import random as rd

def Partition(A:list, s:int, e:int)->int:
    #n=rd.randint(0,len(A)-1)
    x=A[s]
    inf=s
    sup=e

    while(1):
        while inf <= e and A[inf]<=x:
            inf+=1 
        while A[sup] > x:
            sup-=1
        if inf < sup:
            A[inf],A[sup]=A[sup],A[inf]
        else:
            break

    A[s],A[sup]=A[sup],A[s]
    return sup

    
def QuickSort(A:list, s:int, e:int)->None:
    if s < e:
        m = Partition(A,s,e)
        QuickSort(A,s,m-1)
        QuickSort(A,m+1,e)

A=[1,2,4,10,6,6,4,4,3,4,5,6,3,2,5]

QuickSort(A,0,len(A)-1)

print(A)
