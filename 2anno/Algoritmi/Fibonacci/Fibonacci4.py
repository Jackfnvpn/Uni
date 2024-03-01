def fibonacci4(n):
    a=1
    b=1
    
    for x in range(2,n):
        c=a+b
        b=a
        a=c
    
    return c



print(fibonacci4(100))



