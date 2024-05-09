class Node:
    def __init__(self, element: int, nameset=0) -> None:
        if nameset == 1:
            self.name = element
            self.cardinality = 1
            self.children = []
        else:
            self.value = element
            self.father = None


class QuickFind:
    def __init__(self) -> None:
        self.listset = []

    def makeSet(self, element: int) -> None:
        self.name_set = Node(element, 1)
        self.element_set = Node(element)
        self.element_set.father = self.name_set
        self.name_set.children.append(self.element_set)
        self.listset.append(self.name_set)

    def find(self, element: object) -> object:
        return element.father.name

    def union(self, name_a: int, name_b: int):
        if self.listset[name_a].cardinality >= self.listset[name_b].cardinality:
            for x in self.listset[name_b].children:
                x.father = self.listset[name_a]
                self.listset[name_a].cardinality += 1
            self.listset.remove(name_b)
        else:
            for x in self.listset[name_a].children:
                x.father = self.listset[name_b]
                self.listset[name_b].cardinality += 1
            self.listset.remove(name_a)


quickfind = QuickFind()

quickfind.makeSet(4)
quickfind.makeSet(5)
