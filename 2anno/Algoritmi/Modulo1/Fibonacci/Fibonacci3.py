def fibonacci3(n):
    a=[1,1]
    
    
    for x in range(2,n):
        print(x)
        a.append(a[x-1]+a[x-2])
    
    return a
    

print(fibonacci3(100))






