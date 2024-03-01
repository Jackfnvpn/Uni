class Nodo:
    def __init__(self,nome,valore) -> None:
        self.nome=nome
        self.valore=valore
        
class Graph:
    def __init__(self)->None:
        self.vertici={} 

    def creaArchi(self,vertice_1,vertice_2):
        if vertice_1 in self.vertici:
            self.vertici[vertice_1].append(vertice_2)
        else:
            return "Inserisci prima il nodo 1"
        if vertice_2 in self.vertici:
            self.vertici[vertice_2].append(vertice_1)
        else:
            return "Inserisci prima il nodo 2"

        
    def InserisciNodo(self,nome,valore):
        nodo=Nodo(nome,valore)
        self.vertici[nodo.nome]=[]

g=Graph() 

g.InserisciNodo("A",5)
g.InserisciNodo("B",6)

g.creaArchi("A","B")


