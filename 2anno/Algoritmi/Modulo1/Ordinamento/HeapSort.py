
def FixHeap(i:int , A:list, Heapsize:int)->None:
    s=(2*i)+1
    d=(2*i)+2
    
    if s <= Heapsize and A[s]>A[i]:
        massimo=s
    else:
        massimo=i

    if d <= Heapsize and A[d]>A[massimo]:
        massimo=d

    if massimo != i:
        A[i],A[massimo]=A[massimo],A[i]
    
        FixHeap(massimo,A,Heapsize)

def Heapify(i:int,A:list)->None:
    if i<len(A):
        Heapify((2*i)+1,A)
        Heapify((2*i)+2,A)
        FixHeap(i,A,len(A)-1)


def HeapSort(A):
    Heapify(0,A)
    Heapsize=len(A)-1

    i=len(A)-1
    while i >= 1:
        A[0],A[i]=A[i],A[0]
        Heapsize-=1
        FixHeap(0,A,Heapsize)
        i-=1
    
    
        




A=[12,6,54,4,3,2,4,6,7,19]
HeapSort(A)
print(A)






