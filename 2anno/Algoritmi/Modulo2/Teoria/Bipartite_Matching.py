import networkx as nx
import matplotlib.pyplot as plt
from networkx.algorithms import bipartite


def show_graph(D):
    # TODO
    pos = {
        'S': (0.1, 0.5),
        1: (0.3, 0.8),
        2: (0.3, 0.65),
        3: (0.3, 0.5),
        4: (0.3, 0.35),
        5: (0.3, 0.2),
        'a': (0.7, 0.8),
        'b': (0.7, 0.65),
        'c': (0.7, 0.5),
        'd': (0.7, 0.35),
        'e': (0.7, 0.2),
        'T': (0.9, 0.5)
    }

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


def reduction(graph: nx.Graph) -> nx.DiGraph:
    """
    graph is bipartite
    """

    dgraph = nx.DiGraph()

    dgraph.add_node('S', bipartite=0)
    dgraph.add_node('T', bipartite=1)

    dgraph.add_nodes_from(list(graph.nodes(data=True)))

    for edge in graph.edges():
        dgraph.add_edge(edge[0], edge[1], capacity=1e6)

    for node in graph.nodes():
        if graph.nodes[node]["bipartite"] == 0:
            dgraph.add_edge('S', node, capacity=1)
        else:
            dgraph.add_edge(node, 'T', capacity=1)

    return dgraph


# https://www.mat.uniroma2.it/~guala/07_Network_Flow_II_2023.pdf

bgraph = nx.Graph()

# Add nodes with the node attribute "bipartite"

bgraph.add_nodes_from([1, 2, 3, 4, 5], bipartite=0)  # L
bgraph.add_nodes_from(["a", "b", "c", "d", "e"], bipartite=1)  # R
bgraph.add_edges_from([(1, "a"), (1, "b"), (2, "b"), (3, "a"), (3, "c"),
                       (3, "d"), (4, "b"), (4, "e"), (5, "b"), (5, "e")])

dgraph = reduction(bgraph)
show_graph(dgraph)
nx.max_flow_min_cost(dgraph)
