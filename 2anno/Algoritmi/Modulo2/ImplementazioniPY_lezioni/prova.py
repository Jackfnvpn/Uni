def minimo_costo_traversata(n, costs):
    OPT = [[0]*n for _ in range(4)]
  
    for i in range(n): 
        for c in range(4):
            if i==0:
                OPT[c][i]=0
            if c < 1:
                OPT[c][i]=OPT[c][i-1]
            else: 
                OPT[c][i]=min(costs[0][i-1]+costs[0][i-2]+OPT[c][i-2],
                    costs[0][i-1]+costs[1][i-2]+OPT[c-1][i-2],
                    costs[1][i-1]+costs[1][i-2]+OPT[c][i-2],
                    costs[1][i-1]+costs[0][i-2]+OPT[c-1][i-2]) 
                  
    return OPT[c][n-1]

n = 5  
costs = [
    [3,2,7,6,1],
    [5,4,8,3,2]
]

print(minimo_costo_traversata(n, costs))
