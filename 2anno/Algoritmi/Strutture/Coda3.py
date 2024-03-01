class Node:
    def __init__(self,element,next=None) -> None:
        self.element=element
        self.next=next
        

class Coda:
    def __init__(self) -> None:
        self.head=None
        self.tail=None
        self.size=0
    
    def enqueue(self,element):
        node=Node(element)
        
        if self.is_Empty():
            self.head=node 
            self.tail=node
            self.size+=1

        else:
            self.tail.next=node
            self.tail=node 
            self.size+=1

    def dequeue(self):
        element=self.head.element
        self.head=self.head.next
        self.size-=1
        return element
    
    def first(self):
        element=self.head.element
        return element

    def is_Empty(self):
        return self.size==0

coda=Coda()

coda.enqueue(5)
coda.enqueue(4)
coda.enqueue(3)

y=coda.dequeue()
y=coda.dequeue()
y=coda.first()
print(y)


        
