# Il problema LP è NP-completo
Dati un grafo non orientato $G = (V,E)$, una coppia di nodi $s$ e $t$, e un intero $k ∈ ℕ$, esiste in $G$ un percorso da $s$ a $t$ di almeno $k$ archi?


+ $\mathcal{I}_{LP} = \set{\lang G = (V, E),s,t,k\rang : G \text{ è un grafo non orientato }\ \land \ s\in V \ \land \ t \in V \ \land \ k \in \N}$.
+ $\mathcal{S}_{LP}(G, k) = \set{\lang u_1,u_2,...,u_h\rang: \text{ per } i=1,...,h \ u_i \in V}$.
+ $\mathcal{\pi}_{LP}(G, k, \mathcal{S}_{LP}(G, k)) = \exist \lang u_1,u_2,...,u_n\rang \in \mathcal{S}_{LP}(G, k) : s=u_1\ \land \ t=u_h \ \land \ \forall i=1,...,h-1[(u_i,u_{i+1})\in E] \ \land \ \forall i,j=1,...,h, \ e  \ i \neq j \ [ u_i \neq u_j]\ \land h \geq k$.        

Questo problema è davvero molto simile a $HP$.
In effetti, la dimostrazione che $LP ∈ \bold{NP}$ è identica a quella che prova che $HP ∈ \bold{NP}$.
A guardarlo bene, $HP$ è un caso particolare di $LP$: un’istanza di $HP$ è un’istanza di $LP$ in cui $k = |V|$.
Cioè, è banale ridurre polinomialmente $HP$ a $LP$: trasformiamo una istanza $\lang G=(V,E), s, t \rang$ di $HP$ nell’istanza $\lang G=(V,E), s, t, |V| \rang$ di $LP$. Quindi, che $HP \leq LP$ e $LP ∈ \bold{NPC}$.

