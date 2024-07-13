def Robot(M, J, n, m):
    
    OPT = [[0] * (m+1) for _ in range(n+1)]  
    
    for i in range(n+1):
        OPT[i][0]=float("-inf")
    for i in range(m+1):
        OPT[0][i]=float("-inf")


    for i in range(1,n+1):
        for j in range(1,m+1):
            if i==j==1:
                if J[0][0] == 0:
                    OPT[1][1] = M[0][0]
                else:
                    OPT[1][1] = -float("inf")
            elif J[i-1][j-1] == 1:
                OPT[i][j] = -float("inf")
            else:
                OPT[i][j] = max(OPT[i-1][j]+ M[i-1][j-1], OPT[i][j-1]+ M[i-1][j-1]) 

    return OPT

M = [
    [5, 4, 3],
    [3, 2, 1],
    [4, 5, 6]
]

T = [
    [0, 1, 0],
    [0, 1, 0],
    [0, 1, 0]
]

print(Robot(M, T, 3, 3))
