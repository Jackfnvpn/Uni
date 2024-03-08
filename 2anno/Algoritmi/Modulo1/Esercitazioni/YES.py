def BinarySearchS(L,s,e,x):

    m=(s+e)//2 
    if (L[m]==x):
        if L[m-1]==x:
            return BinarySearchS(L,s,m-1,x)
        else:
            return m 
    elif (L[m] in ("Y","E")):
        return BinarySearchS(L,m+1,e,x)

def BinarySearchE(L,s,e,x):
    m=(s+e)//2 
    if L[m]==x:
        if L[m-1]==x:
            return BinarySearchE(L,s,m-1,x) 
        else:
            return m 
    elif (L[m] == "Y"):
        return BinarySearchE(L,m+1,e,x)
    else:
        return BinarySearchE(L,s,m-1,x)


def yes(L):
    #y=BinarySearchY(L,0,len(L)-1,"Y")
    e=BinarySearchE(L,0,len(L)-1,"E")
    s=BinarySearchS(L,0,len(L)-1,"S")

    countY=e
    countS=len(L)-s 
    countE=len(L)-(countS+countY) 

    print(countY,countE,countS)



L=["Y","Y","Y","Y","E","E","S","S","S"]

yes(L)


