## EX 3  
Si dimostri che il complemento di un linguaggio $NP$-completo è $coNP$-completo  

### dim  

Dato $L \subseteq \Sigma^*_2$ un linguaggio $NP$-completo, allora:  
+ $L \in NP$  
+ $\forall \bar{L} \in NP[\bar{L} \leq L]$.  

Siccome $L \in NP$, per definizione della classe $coNP$, ovvero:  

$coNP=\set{L \subseteq \Sigma^*: L^c \in NP}$,  

allora $L^c \in coNP$.  

Siccome $L$ è $NP$-completo, allora per ogni $\bar{L} \subseteq \Sigma^*_1$ e $\bar{L}\in NP$, 
$\bar{L} \leq L$ e dunque $\exist f: \Sigma^*_1 \rightarrow \Sigma^*_2: \forall x \in \Sigma^*_1[x \in \bar{L} \iff f(x) \in L]$.  

Ma questo significa che $\forall x \in \Sigma^*[x\notin \bar{L} \iff f(x) \notin L]$, ossia $\forall x \in \Sigma^*[x\in \bar{L}^c \iff f(x) \in L^c]$. Dunque $L^c$ è $coNP$-completo