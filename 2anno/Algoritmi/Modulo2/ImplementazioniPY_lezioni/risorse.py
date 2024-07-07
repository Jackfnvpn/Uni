import networkx as nx
import matplotlib.pyplot as plt
from networkx.drawing.nx_pydot import graphviz_layout, to_pydot

def reduction(r: list[tuple], a: list[int]):
    n = len(r)
    m = len(a)

    D = nx.DiGraph()

    D.add_node("S")
    D.add_nodes_from(["D" + str(i) for i in range(n)])
    D.add_nodes_from(["A" + str(i) for i in range(m)])
    D.add_node("T")

    for i in range(n):
        D.add_edge("S", 'D' + str(i), capacity=r[i][0])

    for i in range(len(r)):
        for j in r[i][1]:
            D.add_edge('D' + str(i), 'A' + str(j), capacity=1e6)

    for j in range(m):
        D.add_edge('A' + str(j), "T", capacity=a[j])

    return D


def show_graph(D):

    # Visualizzazione del grafo
    pos = {}
    num_nodes_D = len(r)
    num_nodes_A = len(a)
    y_spacing_D = 1 / (num_nodes_D + 1)
    y_spacing_A = 1.0 / (num_nodes_A + 1)

    # Posizione del nodo 's'
    pos['S'] = (0.04, 0.2)

    # Posizionamento dei nodi 'D' al centro
    for i in range(num_nodes_D):
        pos['D' + str(i)] = (0.3, 1 - (i + 3) * y_spacing_D)

    # Posizionamento dei nodi 'A' in basso
    for j in range(num_nodes_A):
        pos['A' + str(j)] = (0.7, 0.5 - (j + 1) * y_spacing_A)

    pos['T'] = (1, 0.2)

    # Visualizzazione del grafo
    nx.draw(D,
            pos,
            with_labels=True,
            node_color='skyblue',
            node_size=1500,
            font_size=12,
            font_weight='bold')

    # Ottieni e modifica le etichette dei pesi
    labels = nx.get_edge_attributes(D, 'capacity')
    labels = {k: 1e6 if v == 1e6 else v for k, v in labels.items()}
    nx.draw_networkx_edge_labels(D, pos, edge_labels=labels)

    plt.title('Grafo con Nodi in Disposizione Verticale')
    plt.show()

r = [(4, [0, 2, 3, 4]), (3, [1, 5]), (2, [2, 3, 4]), (1, [5, 3, 2, 1]),
     (5, [2, 1]), (6, [1, 3, 4, 5])]
a = [4, 3, 2, 1, 5, 6]
D = reduction(r, a)
show_graph(D)

source = 'S'
sink = 'T'
flow_value, flow_dict = nx.maximum_flow(D, source, sink)
print("Maximum flow:", flow_value)

is_good = sum(a) == flow_value
print("Ho un'assegnazione possibile" if is_good else "Non ho un'assegnazione possibile")
