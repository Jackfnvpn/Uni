<style>
.container{
  display: flex; 
}
.image {
  float: left;
  width:70%;
}

.image2 {
  float: left;
  width:30%;
}


.paragraph {
  float:right;
  width: 30%;   
  margin-left: 5%;
  font-size:30px;
}

.paragraph2 {
  float:right;
  width: 30%;   
  font-size:20px;
}


</style>

# Il problema della gestione di insiemi disgiunti (UNION_FIND)
## Il problema
Mantenere una **collezione di insiemi disgiunti** contenenti elementi distinti (ad esempio, interi da $1,...,n$) durante l'esecuzione di una sequenza di operazioni del seguente tipo:  
+ **makeSet(x)**: crea il nuovo insieme **x={x}** di nome **x**;
+ **union(A,B)**: unisce gli insiemi **A** e **B** in un unico insieme, di nome **A** e dsitrugge i vecchi insiemi **A** e **B** (si suppone di avere un riferimento a questi due insiemi);
+ **find(x)**: restiuisce il nome dell'insieme contenente l'elemento **x** (si suppone di avere un riferimento diretto a questo elemento);

**Applicazioni**: Algoritmo di KrusKal per la determinazione del minimo albero ricoprente di un grafo, calcolo dei minimi antenati comuni, ecc...  
<div class="container">
  <div class="image">
    <img src="./Screen/op_uf.png">
  </div>
  <div class="paragraph">
    <p> 
      <b> n </b> = 6 <br>
      L'elemento in grassetto dà il nome all'insieme <br>
      <b>D:</b> Se ho <b>n</b> elementi, quante union posso fare al più       ? <br>
      <b>R: n-1</b>
    </p>
  </div>
</div>  

<span style="color: red">Obiettivo: </span> Progettare una struttura dati che sia efficiente su una sequenza arbitraria di operazioni  

<span style="color:red">Idea: </span> Rappresentare gli insiemi disgiunti con una foresta  

Ogni insieme è un **albero radicato**, la **radice** contiene il nome dell'insieme (elemento rappresentativo).  

Due strategie: **QuickFind** e **QuickUnion**.  

## Alberi QuickFind  

Usiamo una foresta di alberi di altezza 1 per rappresentare gli insiemi disgiunti. In ogni albero:  
- Radice = nome dell'insieme;
- Foglie = elementi (incluso quello rappresentativo).  

```PSEUDO
classe QuickFind implementa UnionFind:
dati: S(n) = O(n)
    una collezione di insiemi disgiunti di elementi elem; ogni insieme
    ha un nome name.
operazioni:
    - makeSet(elem e) T(n)=O(1)
      crea un nuovo albero, composto da due nodi: una radice ed un 
      unico figlio (foglia). 
      Memorizza e sia nella foglia dell'albero che come nome nella radice  
    - union(name a, name b) T(n)=O(n)
      considera l'albero A corrispondente all'insieme di nome a, e l'albero
      B corrispondente all'insieme di nome b. Sostituisce tutti i puntatori dalle foglie  
      di B alla radice di B con puntatori alla radice di A. Cancella
      la vecchia radice di B  
    - find(elem e)--> name T(n)=O(1)
      accede alla foglia x corrispondente all'elemento e. Da tale nodo
      segue il puntatore al padre, che è la radice dell'albero, e 
      restiuisce il nome memorizzato in tale radice.  
```   
![Complessità](./Screen/cs_op_qf.png)  

> [!NOTE]    
> **e se eseguo una squenza arbitraria di operazioni?**
> find e makeSet richiedono solo tempo $O(1)$ ma particolari sequenze di union possono essere molto inefficienti  
> <img src="./Screen/cs_op_un.png">  
>
> 1 cambio puntatore  
> 2 cambio puntatori   
> .  
> .  
> n-2 cambi puntatori  
> n-1 cambi puntatori  
> 
> <span style="font-size:30px">$\Theta(n^2)$</span>
