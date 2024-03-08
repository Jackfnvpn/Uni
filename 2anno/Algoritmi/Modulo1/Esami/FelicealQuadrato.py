from ..Ordinamento.MergeSort import MergeSort
from ..BinSearch import BinSearch

def FelicealQuadrato(A):
    MergeSort(A,0,len(A)-1)
    for x in A:
        index=BinSearch(A,x**2,0,len(A)-1)
        if index > 0:
            return "Elemento trovato"
    return "Elemento non trovato"

print(FelicealQuadrato([1,2,3,4,5,6,7,8,9]))



