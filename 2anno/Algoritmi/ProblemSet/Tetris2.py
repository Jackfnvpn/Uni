from math import sqrt, ceil

def Tris2(N:list, n:int, m:int)->int:
    chunk_width=ceil(sqrt(n))
    chunk_max=[0]*chunk_width
    for x in N:
        #chunk=x[0]/chunk_width
        start_chunk=(x[0]-1)//chunk_width
        end_chunck=(x[0]-1+x[1]-1)//chunk_width
        print(chunk_width,x,start_chunk, end_chunck)
        
    return 0
        
        










Mosse = [ (1, 3, 12), (6, 3, 3), (2, 5, 2), (8, 1, 5), (4, 2, 3),
(2, 2, 2), (4, 3, 7)]


Y=Tris2(Mosse, 8, 12)
print(Y)









