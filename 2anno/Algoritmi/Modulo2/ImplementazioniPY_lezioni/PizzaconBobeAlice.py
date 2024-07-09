def opt(g:list[int])->int:
    n=len(g)
    M=[[0]*(n) for _ in range(n+1)]
    for i in range(n):
        M[i][i]=g[i]
    for d in range(1,n):
        for i in range(n-d): # scorrimento in diagonale  
            j=i+d
            M[i][j]=max(g[i]+M[i+2][j-1],g[j]+M[i+1][j-2])
    return M

print(opt([2,6,3,1,5]))