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
            
L=[1,2,3,4,5,3,5,6,7,8,9,6,5,4,0]

y=contaFoglie(L,0)
print(y);
