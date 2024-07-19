# Algoritmi di approssimazione  
## K-center
Il problema $k$-center consiste nel trovare $k$ centri in modo che la distanza massima tra un sito e il centro più vicino sia minimizzata. 

Dati in input $n$ siti e un intero $k>0$. Dobbiamo selezionare $k$ centri $C$ in modo tale che la distanza massima dal sito più lontano al centro più vinico sia minimizzata. Il centro è un punto qualsiasi che serve come punto di riferimento per i siti circostanti.
+ $dist(x,y):$ distanza da $x$ a $y$
+ $dist(s_i,C)=min_{c \in C}dist(s_i,C):$ distanza dal sito $s_i$ al centro più vicino
+ $r(C)=max_idist(s_i,C):$ minimo raggio ricoprente  

L'obiettivo è dunque quello di trovare un insieme di centri $C$ tale che $r(C)$ è minima e $|C|=k$ 
```
K-Center(k, Sites)
    C = NULL
    for i = 1 to k
        s_i = argmax(s_i, C) // il sito più lontano da ogni centro
        aggiungi s_i a C
    return C
```
L'algoritmo greedy è un $2$-approssimazione.