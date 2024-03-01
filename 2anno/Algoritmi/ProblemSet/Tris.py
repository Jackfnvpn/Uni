def Tris(N:list,n:int,m:int)->int:
    height=[0]*n
    game=1
    for movement in N:
        start=movement[0]-1
        end=movement[1]-1
        end=start+end
        max_height=max(height[start:end+1])
        for i in range(start, end+1):
            if max_height > 0:
                height[i] = max_height+movement[2]
            else:
                height[i] += movement[2]
            if(height[i]>m):
                return game
        game+=1
    return 0  

        




N=[(1, 3, 12), (6, 3, 3), (2, 5, 2), (8, 1, 5), (4, 2, 3), (2, 2, 2), (4, 3, 7)]

y=Tris(N,8,16)
print(y)
