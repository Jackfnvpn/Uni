import matplotlib.pyplot as plt

class Graph:

    def __init__(self) -> None:
        self.nodes = {}
        self.size = 0

    def add_node(self, n: int | float | str):
        if n in self.nodes:
            return -1, f"[INFO]: Node {n} already present in the graph."
        self.nodes[n] = []
        self.size += 1
        return 0, f"[INFO]: Node {n} added."

    def add_edge(self, n1, n2: int | float | str):
        if n1 not in self.nodes or n2 not in self.nodes:
            return -1, f"[INFO]: Can't add edge between {n1} and {n2}."

        if n2 not in self.nodes[n1]:
            self.nodes[n1].append(n2)
        if n1 not in self.nodes[n2]:
            self.nodes[n2].append(n1)
        return 0, f"[INFO]: Edge added between {n1} and {n2}."

    def show(self):
        # Manually specify the positions of the nodes
        pos = {"A": (0, 0), "B": (1, 1), "C": (2, 0)}

        # Create a figure and axis
        fig, ax = plt.subplots()

        # Draw the nodes
        for node, (x, y) in pos.items():
            ax.scatter(x,
                       y,
                       s=3000,
                       c='lightblue',
                       edgecolors='black',
                       zorder=2)
            ax.text(x,
                    y,
                    node,
                    fontsize=15,
                    ha='center',
                    va='center',
                    zorder=3)

        # Draw the edges
        for node, neighbors in self.nodes.items():
            for neighbor in neighbors:
                x_values = [pos[node][0], pos[neighbor][0]]
                y_values = [pos[node][1], pos[neighbor][1]]
                ax.plot(x_values, y_values, c='black', zorder=1)

        # Set limits and remove axes
        ax.set_xlim(-1, 3)
        ax.set_ylim(-1, 2)
        ax.axis('off')

        # Show the plot
        plt.show()


# Example usage
G = Graph()

G.add_node("A")
G.add_node("B")
G.add_node("C")

G.add_edge("A", "B")
G.add_edge("A", "C")
G.add_edge("B", "C")

G.show()
