import networkx as nx  
import matplotlib.pyplot as plt  

Tree=nx.Graph()  

Tree.add_node(1,weight=2)
Tree.add_node(2,weight=7)
Tree.add_node(3,weight=6)
Tree.add_node(4,weight=3)
Tree.add_node(5,weight=1)
Tree.add_node(6,weight=2)
Tree.add_node(7,weight=3)
Tree.add_node(8,weight=3)  
Tree.add_edges_from([(1, 2), (1, 3), (2, 4), (2, 5),(3,6),(3,7),(3,8)])


def II(Tree,s):
    
    Tree.neighbors(1)
    pass

pos = nx.drawing.nx_pydot.graphviz_layout(Tree, prog='dot')
nx.draw(Tree, pos, with_labels=True, node_color='lightblue', node_size=1500, font_size=12, font_weight='bold', edge_color='gray')
plt.show()