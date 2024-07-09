import networkx as nx
import matplotlib.pyplot as plt


class OPT:

    def __init__(self) -> None:
        self.senza_v = 0
        self.con_v = 0
        self.opt = 0

    def to_string(self):
        print(f"OPTSV: {self.senza_v}, OPTCV: {self.con_v}, OPT: {self.opt}")


Tree = nx.Graph()

Tree.add_node(1, weight=2, attr=OPT())
Tree.add_node(2, weight=7, attr=OPT())
Tree.add_node(3, weight=6, attr=OPT())
Tree.add_node(4, weight=3, attr=OPT())
Tree.add_node(5, weight=1, attr=OPT())
Tree.add_node(6, weight=2, attr=OPT())
Tree.add_node(7, weight=3, attr=OPT())
Tree.add_node(8, weight=3, attr=OPT())
Tree.add_edges_from([(1, 2), (1, 3), (2, 4), (2, 5), (3, 6), (3, 7), (3, 8)])


def dfs(tree: nx.Graph, s, parent=None, visited=None):
    if visited is None:
        visited = set()

    visited.add(s)

    n = tree.nodes[s]
    n['attr'].senza_v = 0
    n['attr'].con_v = n['weight']

    for neighbor in tree.neighbors(s):
        if neighbor != parent and neighbor not in visited:
            dfs(tree, neighbor, s, visited)
            neighbor_node = tree.nodes[neighbor]
            n['attr'].senza_v += neighbor_node['attr'].opt
            n['attr'].con_v += neighbor_node['attr'].senza_v

    n['attr'].opt = max(n['attr'].senza_v, n['attr'].con_v)
    return


dfs(Tree, 1)
OPT = Tree.nodes[1]['attr']
OPT.to_string()


# Print the OPT values for all nodes for verification
for node in Tree.nodes():
    print(f"Node {node} -> ", end="")
    Tree.nodes[node]['attr'].to_string()

pos = nx.drawing.nx_pydot.graphviz_layout(Tree, prog='dot')
nx.draw(Tree,
        pos,
        with_labels=True,
        node_color='lightblue',
        node_size=1500,
        font_size=12,
        font_weight='bold',
        edge_color='gray')
plt.show()
