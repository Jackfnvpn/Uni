def createOracle(X:list)->tuple:
    massimo=max(X)
    Y=[0]*(massimo+1) 
    i=0
    while i < len(X):
        Y[X[i]]+=1
        i+=1

    for x in range(1,massimo+1):
        Y[x]=Y[x]+Y[x-1]
    
    return Y,massimo



def Query(Y:list,a:int,b:int)->int:
    
    newStruct,k=createOracle(Y)

    if b > k:
        b=k 
    if a <= 1:
        return newStruct[b]
    else:
        return newStruct[b]-newStruct[a-1]

y=Query([1,10,4,5,5,20,3,3],2,30)
print(y)
