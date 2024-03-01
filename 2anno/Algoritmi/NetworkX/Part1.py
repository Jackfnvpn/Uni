import matplotlib.pyplot as plt
import networkx as nx

graph=nx.Graph()

graph.add_edge(1,2)


graph.add_edge(2,3,weight=0.9)
graph.add_edge("A","B")
graph.add_edge("B","B")
graph.add_node("C")

nx.draw_spring(graph,with_labels=True)





#nx.draw(graph,node_color="red",node_size=3000)
#plt.margins(0.2)

plt.show()

