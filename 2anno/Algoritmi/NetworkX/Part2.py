import networkx as nx 
import matplotlib.pyplot as plt 
import numpy as np 




edge_list=[(1,2),(2,3),(3,4),(3,5),(4,6)]

#G=nx.Graph()

#G=nx.from_edgelist(edge_list)

G=nx.from_numpy_array(np.array([[0,1,0],
          [1,1,1],
          [0,0,0]]))


#G=nx.Graph()

#G.add_edges_from(edge_list)

print(nx.adjacency_matrix(G))


nx.draw_spring(G,with_labels=True)

plt.show()
