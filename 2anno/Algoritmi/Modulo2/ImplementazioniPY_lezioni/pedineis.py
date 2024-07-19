def max_score(n, n_points, b_points):
    OPT = [[0]*(n+1) for _ in range(2)]
    
    for i in range(1, n+1):
        OPT[0][i] = max(OPT[0][i-2] + n_points[i-1], OPT[0][i-1] if i > 1 else 0)
        
        OPT[1][i] = max(OPT[1][i-1] , OPT[1][i-2] +  n_points[i-1], OPT[0][i-2] + b_points[i-1] if i > 1 else 0)
    
    return OPT

n = 8
n_points = [1, 3, 4, 2, 3,2,7,2]
b_points = [2, 7, 8, 4, 16,9,7,3]

print(max_score(n, n_points, b_points))  # Stampa il punteggio massimo
