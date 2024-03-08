class Node:
    def __init__(self,value,key,father=None,left=None,right=None) -> None:
        self.value=value 
        self.key=key 
        self.left=left
        self.right=right
        self.father=father
        
class BinarySearchTree:
    def __init__(self,value,key) -> None:
        self.root=Node(value,key) 
        self.len=0

    def search(self,node,key):

        if node is None:
            return None
        if node.key==key:
            return node
        elif key < node.key:
            return self.search(node.left,key)
        elif key > node.key:
            return self.search(node.right,key)


    def insert(self,node,value,key):
        if key < node.key:
            if node.left is None:
                node.left = Node(value, key, node)
            else:
                self.insert(node.left, value, key)
        elif key > node.key:
            if node.right is None:
                node.right = Node(value, key, node)
            else:
                self.insert(node.right, value, key)

    
    def _max(self,nodo):
        v=nodo 
        while(v.right is not None):
            v=v.right
        return v

    def _pred(self,nodo):
        if nodo.left is not None:
            return self._max(nodo.left)
        while(nodo.father is not None and nodo.father.left==nodo):
            nodo=nodo.father 
        return nodo.father

    def _del_wonechild(self, node):
        if node.left and not node.right:
            if node.father:
                if node == node.father.left:
                    node.father.left = node.left
                else:
                    node.father.right = node.left
                node.left.father = node.father
            del node
        elif not node.left and node.right:
            if node.father:
                if node == node.father.left:
                    node.father.left = node.right
                else:
                    node.father.right = node.right
                node.right.father = node.father
            del node
    
    def delete(self,radix,key):
        node=self.search(radix,key)
        if node:
            if not node.left and not node.right:
                if node.father:
                    if node == node.father.left:
                        node.father.left = None
                    else:
                        node.father.right = None
                del node
            elif (node.left and not node.right) or (not node.left and node.right):
                self._del_wonechild(node)
            else:
                predecessor = self._pred(node)
                node.key, node.value,predecessor.key,predecessor.value = predecessor.key,predecessor.value, node.key, node.value
                self.delete(radix, predecessor.key)

            


radix = BinarySearchTree(30, 30)
radix.insert(radix.root, 3, 24)
radix.insert(radix.root, 4, 25)
radix.insert(radix.root, 2, 22)
radix.insert(radix.root, 1, 28)

result = radix.search(radix.root, 28)

radix.delete(radix.root,28)

result = radix.search(radix.root, 28)




