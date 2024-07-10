def monete_pila(m):
    n=len(m)  
    OPT=[0]*(n+1) 
    OPT[n]=0  
    OPT[n-1]=0
    OPT[n-2]=m[n-1]+OPT[n]

    for i in range(n-3,-1,-1):
        OPT[i]=max(m[i]+OPT[i+3],m[i+1]+OPT[i+2])  
    return OPT[0]

print(monete_pila([3, 8, 4,5]))  

