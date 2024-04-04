class Node:
    def __init__(self, element: int) -> None:
        self.element = element
        self.next_sx = None
        self.next_cx = None
        self.next_dx = None


class Tree:
    def __init__(self) -> None:
        self.L = None
        self.n = 0

    def addElement(self, element: int, father: object = None) -> None:
        new_node = Node(element)
        if father == None:
            self.L = new_node
            self.n += 1
        else:
            if father.next_sx == None:
                father.next_sx = new_node
                self.n += 1
            elif father.next_cx == None:
                father.next_cx = new_node
                self.n += 1
            else:
                father.next_dx = new_node

        return new_node

    def computeOPT(self, index: object):
        if index == None:
            return "F", 0

        pr1, sx = self.computeOPT(index.next_sx)
        pr2, cx = self.computeOPT(index.next_cx)
        pr3, dx = self.computeOPT(index.next_dx)

        if pr1 == "F" and pr2 == "F" and pr3 == "F":
            index.element += sx + cx + dx
            return "T", index.element

        elif sx + cx + dx > index.element:
            index.element = sx + cx + dx
            return "F", sx + cx + dx

        else:
            return "T", index.element

    def printRadix(self):
        print(self.L.element)


tree = Tree()
a = tree.addElement(2)
b = tree.addElement(7, a)
c = tree.addElement(6, a)
d = tree.addElement(3, b)
e = tree.addElement(1, b)
f = tree.addElement(2, c)
g = tree.addElement(3, c)
h = tree.addElement(3, c)

tree.computeOPT(a)
tree.printRadix()
