
def predictTheWinner(nums: list[int]):
        n=len(nums)
        opt=[[0]*(n) for _ in range(n+1)]
        choice=[[0]*(n) for _ in range(n+1)]
        
        for i in range(n):
            opt[i][i]=nums[i]
            choice[i][i]=i

        for d in range(1,n):
            for i in range(n-d):
                j=i+d
                if nums[i]+opt[i+2][j]>=nums[j]+opt[i][j-2]:
                    choice[i][j]=i
                else:
                    choice[i][j]=j
                
                opt[i][j]=max(nums[i]+opt[i+2][j],nums[j]+opt[i][j-2],nums[i]+opt[i+1][j-1],nums[j]+opt[i+1][j-1])
        
        i=0
        j=n-1
        path = []
        turn = 1  
        while i <= j:
            pick = choice[i][j]
            if pick == i:
                path.append((i, "Player 1" if turn == 1 else "Player 2"))
                i += 1
            else:
                path.append((nums[j], "Player 1" if turn == 1 else "Player 2"))
                j -= 1
            turn = 3 - turn 
        
        player2=sum(x[0] for x in path if x[1] == "Player 2")
        
        print(opt[0][n-1],player2)
        return opt[0][n-1]>=player2 


print(predictTheWinner([1,5,2]))