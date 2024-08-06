import networkx as nx
import matplotlib.pyplot as plt
G=nx.Graph()

G.add_nodes_from(["S",1,2,3,"T"])
G.add_edges_from([
    ("S",1,{"label":2}),
    ("S",2,{"label":1}),
    ("S",3,{"label":3}),
    (2,3,{"label":2}),
    (1,4,{"label":3}),
    (2,4,{"label":1}),
    (1,"T",{"label":1}),
    (3,"T",{"label":2}),
])

def reduction(G:nx.Graph)->nx.Graph:
    G_1=nx.Graph()
    distinct_label=[]
    for edge in G.edges: # O(m)
        if G.edges[edge]["label"] not in distinct_label: #O(k)
            distinct_label.append(G.edges[edge]["label"])
    distinct_label.sort() #O(klog(k))  

    k=len(distinct_label)

    #print(distinct_label)


    for edge in G.edges: #O(m)
        node1,node2=edge
        label=G.edges[edge]["label"]
        for i in range(k): 
            if label==distinct_label[i]:
                G_1.add_edge(str(node1)+"_"+str(i),str(node2)+"_"+str(i),weight=label)
        #for i in range(k-1):
            elif i <= k-2 and label==distinct_label[i+1]:
                #elif label+i-1 <= k-1 and label==distinct_label[label+i-1]:
                G_1.add_edge(str(node1)+"_"+str(i),str(node2)+"_"+str(i+1),weight=label)
    
    for t in range(k):
        G_1.add_edge("T_"+str(t),"T",weight=0)
    
    return G_1  

def draw_graph(G, path=None):
    pos = nx.planar_layout(G)
    #edge_colors = [G[u][v]['color'] for u, v in G.edges]
    #weights = [G[u][v]['weight'] for u, v in G.edges]
    #labels = {edge: G.edges[edge]['weight'] for edge in G.edges}

    nx.draw(G, pos, with_labels=True, node_color='white', node_size=800,  width=2, font_size=10, font_weight='bold', font_color='blue')
    #nx.draw_networkx_edge_labels(G, pos,edge_labels=labels ,font_color='black', font_size=8)
    
    
    plt.show()
G_1=reduction(G)
#print(draw_graph(G_1))
BFS_tree=nx.bfs_tree(G_1,"S_0")
draw_graph(BFS_tree)
                



