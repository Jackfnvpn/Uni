def BinSearch(a,x,i,j):
    if(i>j):
        return -1
    m=i+j//2

    if(a[m]==x):
        return m
    if(a[m]>x):
        return BinSearch(a,x,i,m-1)
    else:
        return BinSearch(a,x,m+1,j)


