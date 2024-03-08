from typing import List


def MergeSort(L: list, start: int, end: int) -> None:
    if start < end:
        medium = (start + end) // 2
        MergeSort(L, start, medium)
        MergeSort(L, medium + 1, end)
        Merge(L, start, medium, end)

def Merge(L:list,start:int,end_1:int,end_2:int)->None:
    X=[0]*(end_2 - start + 1)

    i=0
    s1=start
    s2=end_1+1

    while s1 <= end_1 and s2 <= end_2:
        if L[s1] <= L[s2]:
            X[i]=L[s1]
            s1+=1 
        else:
            X[i]=L[s2]
            s2+=1 
        i+=1

    while s1 <= end_1:
        X[i]=L[s1]
        s1+=1
        i+=1

    while s2 <= end_2:
        X[i]=L[s2]
        s2+=1
        i+=1


    for i in range(start,end_2+1):
        L[i]=X[i-start]


def BinSearch_min(a:list,x:int,i:int,j:int)->int:
    if(i>j):
        return -1
    m=i+j//2

    if(a[m]==x):
        if[m-1]==x:
            return BinSearch_min(a,x,i,m-1)
        else:
            return m
    if(a[m]>x):
        return BinSearch_min(a,x,i,m-1)
    else:
        return BinSearch_min(a,x,m+1,j)

def BinSearch_max(a:list,x:int,i:int,j:int)->int:
    if(i>j):
        return -1
    m=i+j//2

    if(a[m]==x):
        if[m+1]==x:
            return BinSearch_max(a,x,m+1,j)
        else:
            return m
    if(a[m]>x):
        return BinSearch_max(a,x,i,m-1)
    else:
        return BinSearch_max(a,x,m+1,j)



def create_Oracle(Y:list)->list:
    MergeSort(Y,0,len(Y)-1)
    return Y 

def Query(Y:list,a:int,b:int)->int:
    new_struct=create_Oracle(Y)
    if b > new_struct[len(new_struct)-1]:
        b=new_struct[len(new_struct)-1]
    if a < new_struct[0]:
        a=new_struct[0]
    x=BinSearch_min(new_struct,a,0,len(new_struct)-1)
    y=BinSearch_max(new_struct,b,0,len(new_struct)-1)

    number=y-x+1
    return number

print(Query([1,10,4,5,5,20,3,3],3,30))

