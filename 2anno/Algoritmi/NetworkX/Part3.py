
import networkx as nx
import matplotlib.pyplot as plt



#T = nx.full_rary_tree(2, 7)

#T=nx.balanced_tree(2,7)

#nx.draw_spring(T)


T = nx.Graph()

# Aggiunta dei nodi
T.add_nodes_from([1, 2, 3, 4, 5])

# Aggiunta degli archi
T.add_edges_from([(1, 2), (1, 3), (2, 4), (2, 5)])

nx.draw(T)


plt.show()

