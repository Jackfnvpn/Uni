class Node():
    def __init__(self,element:int,next) -> None:
        self.element=element 
        self.next=next
        
class Stack():
    def __init__(self) -> None:
        self.pointer=None
        self.size=0

    def add_element(self,element:int)->None:
        node=Node(element,self.pointer)
        self.pointer=node
        self.size+=1
    
    def pop(self):

        if(self.is_empty()):
            return None

        element=self.pointer.element
        self.pointer=self.pointer.next
        self.size-=1
        return element
    
    def top(self):
        if self.is_empty():
            return None 
        element=self.pointer.element
        return element
    
    def is_empty(self):
        return self.size == 0



stack=Stack()

stack.add_element(5)
stack.add_element(4)

y=stack.top()
print(y)



        
