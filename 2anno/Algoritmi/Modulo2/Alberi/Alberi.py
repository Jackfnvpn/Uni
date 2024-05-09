class Tree:
    def __init__(self, d: int) -> None:
        self.tree = []
        self.d = d

    def addElement(self, key: int) -> None:
        self.tree.append(key)

    def getChilds(self, index: int):
        start_index = index * self.d + 1
        end_index = min(start_index + self.d, len(self.tree))
        return [i for i in range(start_index, end_index)]

    def printTree(self):
        print(self.tree)

    def DFS(self, index: int):
        if index > len(self.tree):
            return None
        print(self.tree[index])
        for x in self.getChilds(index):
            self.DFS(x)
