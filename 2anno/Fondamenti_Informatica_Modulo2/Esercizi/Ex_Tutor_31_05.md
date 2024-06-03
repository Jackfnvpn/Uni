# Esercizi Tutor 31/05
## Ex 1

+ $\mathcal{I}_{\Gamma} = \set{\lang G = (V, E), k \rang : G \text{ è un grafo non orientato e } k \text{ un intero positivo } \in \N}$.
+ $\mathcal{S}_{\Gamma}(G, k) = \set{V' \subset V }$.
+ $\mathcal{\pi}_{\Gamma}(G, k, \mathcal{S}_{\Gamma}(G, k)) = \nexists V' \in \mathcal{S}_{\Gamma}(G, k) : |V| \geq k \land \forall (u, v) \in V' [ (u,v)\in E]$.  

Lavoriamo sul complemento del problema  

+ $\mathcal{I}_{\Gamma^c} = \set{\lang G = (V, E), k \rang : G \text{ è un grafo non orientato e } k \text{ un intero positivo } \in \N}$.
+ $\mathcal{S}_{\Gamma^c}(G, k) = \set{V' \subset V }$.
+ $\mathcal{\pi}_{\Gamma^c}(G, k, \mathcal{S}_{\Gamma^c}(G, k)) = \exist V' \in \mathcal{S}_{\Gamma^c}(G, k) : |V| \geq k \land \forall (u, v) \in V' [ (u,v)\in E]$. 

Dato un qualsiasi certificato di $\Gamma^c$ per verificarlo impiegiamo tempo $O(|V|^2|E|)$ dunque il problema è in $\bold{NP}$

Per dimostrare la completezza di $\Gamma^c$ in $\bold{NP}$ , mostriamo che il problema $CL$ è riducibile nel problema $\Gamma^c$, ossia $CL \leq \Gamma^c$.  

Descrivo formalmente il problema $CL$:  

Prendo un istanza di $CL$:  

$\mathcal{I}_{CL} = \set{\lang G' = (V'', E''), z \rang : G \text{ è un grafo non orientato e } k \text{ un intero positivo } \in \N}$. E la trasformo in un'istanza di $\Gamma^c$.  

Osserviamo cze le istanze dei due problemi sono identicze, $G'=G$ e $k=z$.  

Dunque trovare una clique nel grafo $G'$ di almeno $z$ nodi corrisponde ad avere nel grafo $G$ un sottinsieme $V' \subseteq V$ tale cze $\forall u,v \in V'(u,v \in E) \ \land \ |V'| \geq k$.  Viceversa trovare un sottoinsieme dei nodi $V' \subseteq V$ tale $\forall u,v \in V'[(u,v)\in E] \ \land |V'|\geq k$ corrisponde a trovare una clique nel grado $G'$ di almeno $z$ nodi.    

Siccome $\Gamma^c$ è $\bold{NP}$-completo allora $\Gamma$ è $\bold{coNP}$-completo

## Ex 2  

Consider the following decision problem: given an undirected and weighted
graph $𝐺 = 𝑉, 𝐸, 𝑤$ with $𝑤: 𝐸 → ℕ$ and two integers $ℎ$ and $𝑘$, decide whether $𝐺$ has a clique of $𝑘$ nodes such that the sum of the weights of its edges is at most $ℎ$.
After formalizing the problem using the triple $(𝐼, 𝑆,𝜋)$ , answer the following questions (in the order deemed appropriate), providing justification for each response.
• Is the problem in $\bold{P}$?
• Is the problem in $\bold{NP}$?
• Is the problem in $\bold{coNP}$?

+ $\mathcal{I}_{\Gamma}= \set{\lang G = (V, E,w), k \rang : G \text{ è un grafo non orientato pesato e } k,h \text{ due interi } \in \N}$.
+ $\mathcal{S}_{\Gamma}(G, k) = \set{V' \subset V }$.
+ $\mathcal{\pi}_{\Gamma}(G, k, \mathcal{S}_{\Gamma}(G, k)) = \exist V' \in \mathcal{S}_{\Gamma}(G, k) : |V'| = k \land \forall (u, v) \in V' [ (u,v)\in E] \ \land \ \sum_{u,v\in V'}w(u,v) \leq h$. 

Dimostriamo che il problema $\Gamma$ è in $\bold{NP}$  

Dato un generico certificato del problema , verificarlo impigherebbe tempo $O(|V|^2|E|)$, che è polinomiale nella dimensione dell'istanza  

Dimostriamo che $\Gamma$ è $\bold{NP}$-completo, attraverso una riduzione dal problema $CL$, dunque dobbiamo dimostrare che $CL \leq \Gamma$  

Prendiamo una generica istanza di $CL$  

$\mathcal{I}_{CL} = \set{\lang G' = (V'', E''), z \rang : G \text{ è un grafo non orientato e } k \text{ un intero positivo } \in \N}$. E la trasformo in un'istanza di $\Gamma^c$. Trasformiamola in un istanza di $\Gamma$  


Osserviamo che il grafo $G'$ è uguale al grafo $G$, tranne per l'assenza dei pesi, dunque associo ad ogni arco in $G'$ una funzione peso tale che $\forall (u,v) \in E''[w(u,v)=1]$ e $z=k$ e $h=|E|$

If $𝐺'$ has a clique of $𝒌$ vertices, $𝐺$ has a clique of $𝒌$ vertices, such that the sum of the weights of its edges is at most $𝒉$, $\mathcal{I}_{\Gamma}$ is a “yes” instance
If $𝐺'$ doesn’t have a clique of $𝒌$ vertices, $𝐺'$ doesn’t have a clique of $𝒌$ vertices, $\mathcal{I}_{\Gamma}$ is a “no” instance





