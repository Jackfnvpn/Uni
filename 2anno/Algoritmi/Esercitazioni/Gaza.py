def Gaza(L):
    est=[]
    ovest=[]
    count=0

    for x in L:
        if x == "!":
            count=1
            est.append(-1)
        
        if count==0:
            est.append(count)

        if x!="!" and count >= 1:
            count+=x
            est.append(count-1)

    L=L[::-1]

    for x in L:
        if x == "!":
            count=1
            ovest.append(-1)
        
        if count==0:
            ovest.append(count)

        if x!="!" and count >= 1:
            count+=x
            ovest.append(count-1)

    for x in range(len(L)):
        #TODO
        pass 
    return est,ovest



a=[3,4,5,"!",6,7,8,9,10,"!",11,"!"]
print(Gaza(a))

