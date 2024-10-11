import networkx as nx  
import matplotlib.pyplot as plt

graph = nx.DiGraph()  

graph.add_nodes_from(["Analisi","Discreta","Programmazione",
                      "Architettura","Geometria","Logica","Fisica","Algoritmi","Fondamenti","Database","SOR","LMP","Statistica","RO"])  

graph.add_edges_from([("Analisi","Statistica"),("Analisi","Algoritmi"),("Analisi","Fisica"),("Discreta","Fondamenti"),("Discreta","Algoritmi"),("Programmazione","SOR"),
                      ("Programmazione","Algoritmi"),("Programmazione","LMP"),("Architettura","SOR"),("Programmazione","Database"),("Discreta","Database")])  

def TopologicalSort(graph,visited):
    sort=[]
    for node in graph.nodes():
        if not visited[node]:
            DFS(node,sort,visited)  
    
    return sort


def DFS(node,sort,visited):
    if node == None:
        return 
    
    visited[node]=True

    for neighbor in graph.neighbors(node):
        if not visited[neighbor]:
            DFS(neighbor,sort,visited)

    sort.append(node)    


visited={node:False for node in graph.nodes()}

sort=TopologicalSort(graph,visited)  
print(sort)
print(sort[::-1])


