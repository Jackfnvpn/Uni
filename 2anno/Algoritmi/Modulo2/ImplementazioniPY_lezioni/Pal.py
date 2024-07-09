def opt(s:str)->int:
    n=len(s)
    M=[[0]*n for _ in range(n)]
    for i in range(n):
        M[i][i]=1  
    for d in range(1,n):
        for i in range(n-d): # scorrimento in diagonale  
            j=i+d
            if s[i]==s[j]:
                M[i][j]=2+M[i+1][j-1]
            else:
                M[i][j]=max(M[i+1][j],M[i][j-1])  
    return M[0][n-1]    

print(opt("algoritmo"))