import networkx as nx  
import matplotlib.pyplot as plt
G=nx.DiGraph()  

G.add_nodes_from(["S",1,2,3,4,"T"])  

G.add_edges_from([
    ("S", 1, {"weight": 1,"color":"black"}),
    ("S", 3, {"weight": 20,"color":"black"}),
    ("S", 4, {"weight": 1,"color":"blue"}),
    (1, 2, {"weight": 0,"color":"blue"}),
    (2, "T", {"weight": 0,"color":"blue"}),
    (2, 3, {"weight": 4,"color":"black"}),
    (3, 4, {"weight": 1,"color":"black"}),
    (3, "T", {"weight": 10,"color":"black"}),
    (4, "T", {"weight": 15,"color":"black"})
])  

def reduction(G:nx.DiGraph,k:int)->nx.DiGraph:

    G_1=nx.DiGraph()

    edges=list(G.edges)

    for edge in edges:
        node1,node2=edge
        weight_edge=G.edges[edge]["weight"]
        if G.edges[edge]["color"]=="black":
            for i in range(k+1):
                G_1.add_edge(str(node1)+"_"+str(i),str(node2)+"_"+str(i),weight=weight_edge,color="black")
        elif G.edges[edge]["color"]=="blue":
            for i in range(k):
                G_1.add_edge(str(node1)+"_"+str(i),str(node2)+"_"+str(i+1),weight=weight_edge,color="blue")
        
    for t in range(k+1):
        G_1.add_edge("T_"+str(t),"T",weight=0,color="black")

    return G_1

G_1=reduction(G,1)  

def draw_graph(G, path=None):
    pos = nx.circular_layout(G)
    edge_colors = [G[u][v]['color'] for u, v in G.edges]
    weights = [G[u][v]['weight'] for u, v in G.edges]
    labels = {edge: G.edges[edge]['weight'] for edge in G.edges}

    nx.draw(G, pos, with_labels=True, node_color='white', node_size=800, edge_color=edge_colors, width=2, font_size=10, font_weight='bold', font_color='blue')
    nx.draw_networkx_edge_labels(G, pos, edge_labels=labels, font_color='black', font_size=8)
    
    if path:
        path_edges = list(zip(path, path[1:]))
        nx.draw_networkx_edges(G, pos, edgelist=path_edges, edge_color='red', width=3)
    
    plt.show()

path=nx.shortest_path(G_1,source="S_0",weight="weight",target="T")
print(path)
draw_graph(G_1,path) 

