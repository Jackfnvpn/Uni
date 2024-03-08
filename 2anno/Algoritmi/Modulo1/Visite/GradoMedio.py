def contaFoglie(L:list,r:int)->int:

    if r>=len(L):
        return 0

    sx=contaFoglie(L,2*r+1)
    dx=contaFoglie(L,2*r+2)

    if sx==0:
        return 1
    if dx==0:
        return 1
    
    return sx+dx
            


def GradoMedio(L:list, r:int)->int:

    grado=SommaGradi(L,r)//(len(L)-contaFoglie(L,r))
    return grado
    

def SommaGradi(L:list, r:int)->int:

    if r>=len(L):
        return 0

    sx=SommaGradi(L,2*r+1)
    dx=SommaGradi(L,2*r+2)

    return sx+dx+1


L=[1,2,2,5,4,3,3,6,5,4]
y=GradoMedio(L,0)
print(y)




