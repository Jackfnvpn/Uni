def bocciature(f,m,d):
    n=len(f)
    OPT=[[0]*(n+1) for _ in range(3)]  
    
    # 0 : facile
    # 1 : medio 
    # 2 : difficile 
    
    for k in range(3):
        OPT[k][n]=0

    for i in range(n - 1, -1, -1):

        OPT[0][i] = f[i] + max(OPT[0][i + 1],OPT[1][i + 1],OPT[2][i + 1])

        if i + 1 < n:
            OPT[1][i] = m[i] + OPT[0][i + 1]

        else:
             OPT[1][i]=OPT[0][i]
             OPT[2][i]=OPT[0][i]

        if i + 3 < n-1:
            OPT[2][i] = d[i] + f[i + 1] + f[i + 2] + f[i + 3] + max(OPT[0][i + 4], OPT[1][i + 4], OPT[2][i + 4])
        
        else:
            OPT[2][i] = max(OPT[0][i],OPT[1][i])

    return max(OPT[0][0],OPT[1][0],OPT[2][0])




f=[1,4,5,3,4,3,2]
m=[3,6,7,5,4,5,6]
d=[5,8,10,6,5,8,9]

print(bocciature(f,m,d))




