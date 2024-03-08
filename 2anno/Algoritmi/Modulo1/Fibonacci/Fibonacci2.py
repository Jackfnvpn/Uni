def fibonacci2(n):
    if n <=2:
        return 1
    else:
        return fibonacci2(n-1)+fibonacci2(n-2)


print(fibonacci2(40))