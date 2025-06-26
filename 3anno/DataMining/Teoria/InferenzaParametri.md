Un modello che costruiamo può assumere una delle seguenti forme:
+ Una funzione $y(\cdot)$ che per ogni elemento $\bold{x}$, restituisce un valore $y(\bold{x})$ come stima di $t$. Questa funzione agisce come predittore diretto, mappando le feature in ingresso nello spazio del target.
+ Una distribuzione di probabilità che associa a ciascun possibile valore $\overline{y}$ nel dominio del target la sua probabilità corrispondente $p(y=\overline{y},\bold{x})$. Questo approccio cattura l'incertezza insita nel compito di previsione.

## Approccio probabilistico

Nell’approccio probabilistico, assumiamo che la relazione tra elementi $x$ e relativi target $t$ non sia deterministica, ma che a un stesso $x$ possano corrispondere valori target diversi. 

In questo caso, la relazione tra features e target è espressa mediante una distribuzione di probabilità condizionata $p(t|x)$, chiaramente sconosciuta, che vogliamo apprendere, almeno approssimativamente, a partire dal training set $T$.

Operiamo definendo un universo di possibili distribuzioni caratterizzate da parametri, che differiscono tra loro per il valore dei parametri stessi, ed effettuiamo la ricerca di $p^*$ all’interno di quell’universo.

1. consideriamo, per ipotesi, una classe di possibili distribuzioni condizionate $P$ (un modello probabilistico)
2. selezioniamo (inferiamo) la “migliore” distribuzione condizionata $p^∗ \in P$ a partire dalla conoscenza disponibile (cioè, il training set), secondo una qualche misura $q$
3. dato un qualsiasi nuovo elemento $x$, applicheremo $p^∗(t|x)$ per assegnare probabilità a ciascun valore possibile
del corrispondente target.

La classe delle possibili distribuzioni condizionate $P$ è definita attraverso un approccio parametrica: strutture comuni e insiemi di parametri.

### Inferire una distribuzione ottimale
Qual'è la misura $q(p,T)$ della qualità della distribuzione condizionata parametrica $p(t|x;\theta)$, dato il traning set $T$. Dobbiamo massimizzare quindi $q$.

Idea:
+ fare riferimento ad un dataset generato campionando casualmente $n$ coppie ($n=|X|$ feature in $T$) secondo la distribuzione congiunta $p(x,t,\theta)$, potrebbe essere simile al traning set $T$.
+ In particolare, possiamo considerare la probabilità che il dataset ottenuto in questo modo corrisponda al training set $T = (X,t)$, vale a dire la probabilità che proprio $T$ sia ottenuto mediante il procedimento seguente, basato sull’identità: 
$$p(x, t; θ) = p(x; θ)p(t|x; θ)$$
    + Per ogni esempio $i = 1, ..., n$ :Generi $x_i$ da una distribuzione $p(x; θ)$ che si assume uniforme o nota, quindi non dipende da $θ$

    + Generi $t_i$ condizionatamente a $x_i$, usando $p(t | x_i; θ)$
  
    + Si noti che, applicando questa procedura, le $n$ coppie $(x_i,t_i)$ sono generate sotto l’ipotesi di essere **indipendenti e identicamente distribuite**, con probabilità $p(x, t; θ) = p(x)p(t|x; θ)$. 
    Questo comporta che la probabilità dell’intero training set $p(X,t; θ)$ può essere espressa come prodotto delle probabilità dei suoi singoli elementi, calcolate con riferimento alla stessa distribuzione:
    $$p(X, t; θ) = \prod_{i=1}^{n} p(xᵢ, tᵢ; θ) = \prod_{i=1}^{n} p(xᵢ) \cdot p(tᵢ | xᵢ; θ)$$

    + Possiamo usare tale probabilità come misura della qualità $q(p, T)$ e cercare la distribuzione $p(t|x; θ^∗)$ che rende massima $p(X,t; θ)$ assumendo che $p(x)$ sia la distribuzione uniforme e $p(t|x; θ)$ sia la distribuzione parametrica condizionata.

### Modelli probabilistici

Un **modello probabilistico** è una collezione di distribuzioni di probabilità con la stessa struttura, definite sul dominio dei dati. Le distribuzioni di probabilità sono istanze del modello probabilistico e sono caratterizzate dai valori assunti da un insieme di **parametri**.

Dato un dataset $T$ e una distribuzione di probabilità $p$ con parametri $θ$, l’espressione $p(T |θ)$ può essere intesa
in due modi diversi:
+ possiamo considerare $θ$ come fissato e $T$ variabile, $p(T |θ)$; questo è il modo convenzionale di interpretare
l’espressione.

+ possiamo considerare $θ$ come variabile e $T$ fissato, $p(T |θ)$; in questo caso parliamo di **verosimiglianza** di
$θ$ rispetto a $T$ . Possiamo interpretare questo valore come la probabilità che, estraendo a caso un dataset di
dimensione $|T|$ secondo la distribuzione con parametri θ, si ottenga esattamente $T$ . In questo caso possiamo ad esempio cercare il valore di $θ$ per cui la probabilità $p(T |θ)$ dei dati osservati è massima ovvero il fatto che il dataset sia generato campionando indipendentemente punti da $p(x, t; θ)$

### Stima di massima verosimiglianza (punto 2)

I parametri sono variabili deterministiche, il cui valore è sconosciuto e deve essere stimato.
Vogliamo, come detto, determinare il valore che massimizza la verosimiglianza:
$$θ^* = \arg\max_θ \ p(X, t | θ)=$$

Sotto l’ipotesi che $p(x)$ sia uniforme (cioè indipendente da $θ$), puoi ignorarlo:

$$θ^* = \arg\max_θ \prod_{i=1}^{n} p(t_i | x_i, θ)$$

Il logaritmo di questa funzione:
$$\log p(T | θ) = \sum_{i=1}^n \log p(t_i | x_i, θ)$$

che è monotono (non cambia il punto di massimo) e i prodotti diventano somme.
Ora il problema di ottimizzazione è: 
$$
θ^* = \arg\max_θ \sum_{i=1}^n \log p(t_i | x_i, θ)
$$

Risolvendo il sistema di equazioni in cui **il gradiente della log-verosimiglianza è nullo**:

$$
∇ \log p(T | θ) = 0
$$

Questa è **una condizione necessaria**: significa che sei in un **punto stazionario** (potrebbe essere un massimo, minimo o punto sella).
Per trovare il **massimo globale**, puoi:

+ usare **algoritmi più robusti** (es. ottimizzazione numerica, gradient ascent avanzati, inizializzazioni multiple),
+ o scegliere modelli dove la **funzione è concava**, così ogni massimo locale è anche globale (es: regressione logistica).

Una volta trovato $θ*$, hai il tuo modello $p(t | x, θ*)$, che puoi usare per **fare predizioni** su nuove osservazioni $x$.

### Stima di massima verosimiglianza nella logreg
Assumiamo che i target degli elementi possano essere modellati condizionatamente (rispetto ai coefficienti del
modello) tramite una distribuzione di Bernoulli. Ovvero, assumiamo:
$$p(tᵢ \mid xᵢ; w, b) = pᵢ^{tᵢ} (1 - pᵢ)^{1 - tᵢ}$$

La probabilità stimata $p_i$ è calcolata applicando la **funzione sigmoide** a una **combinazione lineare delle feature**:

$$
p_i = \sigma(yᵢ) = \frac{1}{1 + e^{-yᵢ}}, \quad \text{con } y_i = \sum_{k=1}^d x_{ik} w_k + b
$$
Poiché i dati sono indipendenti (i.i.d.), la verosimiglianza del dataset è:

$$
p(t \mid X; w, b) = \prod_{i=1}^n p(tᵢ \mid xᵢ; w, b) = \prod_{i=1}^n \left[ \sigma(yᵢ)^{tᵢ} \cdot (1 - \sigma(yᵢ))^{1 - tᵢ} \right]
$$

Per semplificare il calcolo (e trasformare i prodotti in somme), si lavora con il **logaritmo** della verosimiglianza:

$$
\log p(t \mid X; w, b) = \sum_{i=1}^n \left[ tᵢ \log \sigma(yᵢ) + (1 - tᵢ) \log(1 - \sigma(yᵢ)) \right]
$$

Questa è la **funzione obiettivo** da massimizzare: vuoi trovare $(w, b)$ che **massimizzano la log-verosimiglianza** 

Utilizzando le proprietà generali delle derivate e quella relativa alla funzione sigmoide è possibile mostrare che questo sistema è non lineare, data la presenza della funzione $σ$, per cui non è risolubile analiticamente come
quello risultante per la regressione lineare.

Per massimizzare la log verosimiglianza, possiamo allora applicare un algoritmo di ascesa (non discesa, in quanto
vogliamo massimizzare) del gradiente:

 Per ogni peso $w_k$:

$$
w_k^{(j+1)} = w_k^{(j)} + η \sum_{i=1}^n (tᵢ - pᵢ^{(j)}) \cdot x_{ik}
$$

Per il bias $b$:

$$
b^{(j+1)} = b^{(j)} + η \sum_{i=1}^n (tᵢ - pᵢ^{(j)})
$$

Dove:

* $η$ è il **learning rate**
* $p_i^{(j)} = σ(w^{(j)} \cdot x_i + b^{(j)})$