def House(n,r,v,b):
    OPT=[0]*n  
    minimal=0
    OPT[0]=min((r[0],"r"),(v[0],"v"),(b[0],"b"))
    for i in range(1,n):
        if OPT[i-1][1]== "r":
            minimal=min((v[i],"v"),(b[i],"b"))
        elif OPT[i-1][1]== "v":
            minimal=min((r[i],"r"),(b[i],"b"))
        else:
            minimal=min((r[i],"r"),(v[i],"v"))  
        
        OPT[i]=(minimal[0]+OPT[i-1][0],minimal[1])
    return OPT



r=[7,6,7,8,9,20]
v=[3,8,9,22,12,8]
b=[16,10,4,2,5,7]
print(House(len(r),r,v,b))