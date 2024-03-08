def Insertion_Sort(a:list)-> None:
    n=len(a)
    k=1
    while(k<n):
        j=k
        while(j>0):
            if a[j]<a[j-1]:
                a[j],a[j-1]=a[j-1],a[j]
            j-=1 
        k+=1 

A=[1,2,4,5,6,7,90,4,3,4,10,7,7,5,4,3]
Insertion_Sort(A)
print(A)


