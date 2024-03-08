class D_Heap():
    
    def __init__(self,d):
        self.heap=[]
        self.d=d+1

    
    def _muoviAlto(self,v):
        while v != 0 and self.heap[v] < self.heap[v//2]:
            self.heap[v],self.heap[v][3]=self.heap[v][3],self.heap[v]
    
    
    def _muoviBasso(self,v):
        while(1):
            children=[]
            min=0
            for x in range(v+1,self.d+1):
                if self.heap[x] is not None:
                    children.append((x,self.heap[x]))

            if len(children) > 0:
                min=min(children[0], key=lambda x:x[1])

                
                if self.heap[v][1] <= self.heap[min][1]:
                    break 
                else:
                    self.heap[v],self.heap[min]=self.heap[min],self.heap[v]

            else:
                break
                


    def insert(self, e, k):
        element=[e,k,0]
        self.heap.append(element)
        index = len(self.heap)-1

        if index==0:
            father=-1
        else:
            father=index//self.d

        self.heap[index][2]=father

        self._muoviAlto(index)

    def findMin(self):
        return self.heap[0]
    
    def delete(self,e):
        #TODO
        pass

    def decreaseKey(self,e,k):
        #TODO
        pass
d_heap=D_Heap(3)

d_heap.insert(5,4)
d_heap.insert(5,10)

y=d_heap.findMin()

print(y[1])
