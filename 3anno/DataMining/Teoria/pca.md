# PCA

In generale, molti attributi implicano spazi ad alta dimensione.

Le alte dimensioni comportano difficoltà per gli algoritmi di apprendimento automatico (affidabilità inferiore o necessità di un numero elevato di coefficienti); questo fenomeno è noto come **maledizione della dimensionalità**.

## Riduzione della dimensionalità
Per un qualsiasi classificatore, la dimensione del set di addestramento necessaria per ottenere una certa accuratezza cresce in modo esponenziale rispetto al numero di attributi. È quindi importante limitare il numero di attributi, identificando quelli meno discriminanti.
+ Feature selection: identificare un sottoinsieme di attributi che siano ancora discriminanti o che, in generale, rappresentino ancora la maggior parte della varianza del dataset
+ Feature extraction: identificare una proiezione del dataset in uno spazio a dimensionalità inferiore, in modo tale da rappresentare comunque la maggior parte della varianza del dataset.

### Ricerca di iperpiani
Ricerca di iperpiani per il dataset.
Approccio: verificare se gli elementi del set di addestramento giacciono su un iperpiano (uno spazio a dimensionalità inferiore), a parte una variabilità limitata (che può essere vista come rumore).

L’**analisi delle componenti principali (PCA)** cerca uno spazio a $d'$ dimensioni $(d' < d)$ tale che la proiezione lineare degli elementi su questo spazio sia una rappresentazione “fedele” del dataset originale. Per rappresentazione “fedele” si intende che le distanze tra gli elementi e le loro proiezioni siano piccole, possibilmente minime.

$(d'=0)$
Obiettivo: rappresentare tutti i vettori $x_1, ... , x_n$ di dimensione d mediante un unico vettore $x_0$, nel modo più
fedele possibile, cioè tale che:
$$J(x_0)=\sum_{i=1}^n ||x_0-x_i||^2$$

sia minimo.
È facile dimostrare che:
$$x_0=m=\frac{1}{n}\sum_{i=1}^n x_i$$

$(d'=1)$

Un singolo vettore è una rappresentazione troppo concisa del dataset: qualsiasi informazione relativa alla variabilità
dei dati va persa. Un caso più interessante è quello in cui i vettori vengono proiettati su una retta passante per $m$.

Per semplicità, assumiamo che tutti i punti xi siano traslati di $m$, in modo che la loro media sia $0$ e la retta
che cerchiamo passi per l’origine. 

Indichiamo come $u_1$ un vettore unitario $(||u1|| = 1)$ nella direzione della retta.
L’equazione della retta è quindi:
$$x = αu_1$$

dove $α$ è la distanza di $x$ dall’origine lungo la retta.

Sia $\tilde{x}_i = \alpha_i u_i$ 1 la proiezione di $x_i (i = 1, . . . , n)$ sulla retta: dati $x_1, . . . , x_n$, vogliamo trovare l’insieme di proiezioni che minimizza l’errore quadratico:

$$
J(\alpha_1, \dots, \alpha_n, u_1) = \sum_{i=1}^n \|x_i - \alpha_i u_1\|^2
$$

La cui derivata rispetto a $α_k$ è nulla quando 
$$\alpha_k = u_1^T x_k$$.

Dall’esame della seconda derivata si può verificare che il valore trovato è effettivamente un minimo. Questo ci conferma che la proiezione migliore sulla retta è quella ortogonale alla retta stessa.

Per determinare ora la miglior direzione $u_1$ della retta, consideriamo la matrice di covarianza del dataset.

Una volta sostituiti i valori ottimali di $\alpha_i = u_1^T x_i$, l’errore diventa:

$$
J(u_1) = \sum_{i=1}^n \|x_i\|^2 - n u_1^T S u_1
$$

* Il primo termine $\sum \|x_i\|^2$ è **costante**
* Quindi **minimizzare $J(u_1)$** $\iff$ **massimizzare $u_1^T S u_1$**

$u_1^T S u_1$ rappresenta la **varianza dei dati proiettati** lungo la direzione $u_1$.

Per trovare la direzione che **massimizza la varianza** proiettata, risolvi:

$$
\max_{u_1, \|u_1\| = 1} u_1^T S u_1
$$

quindi un problema di ottimizzazione con vincolo $\|u_1\| = 1$


per un opportuno $λ_1$.
Quindi:
+ $u$ è massimizzato se $u_1$ è un autovettore di $S$
+ la varianza complessiva delle proiezioni è uguale al corrispondente autovalore
$$u^T_1 Su_1 = u^T_1 λ_1u_1 = λ_1u^T_1 u_1 = λ_1$$
+ la varianza delle proiezioni è massimizzata (e l’errore minimizzato) se $u_1$ è l’autovettore di $S$ corrispondente al massimo autovalore $λ_1$

Di conseguenza, l’errore quadratico è minimizzato proiettando i vettori su un iperpiano definito dalle direzioni associate ai $d'$ autovettori corrispondenti ai $d'$ autovalori più grandi di $S$

Dato che la varianza complessiva degli elementi dell’insieme:
$$
\text{Var}_{\text{complessiva}} = \sum_{i=1}^{d} \lambda_i
$$
Il rapporto 
$$
\text{Frazione di varianza spiegata} = \frac{\sum_{i=1}^{d'} \lambda_i}{\sum_{i=1}^{d} \lambda_i}
$$

fornisce la frazione di tale varianza rispetto alla dimensione data dall’autovettore $u_k$.

Se i primi 2 autovalori di un dataset 10D sono:

$$
\lambda_1 = 5.0,\quad \lambda_2 = 2.5,\quad \text{e il totale } \sum \lambda_i = 10
$$

Allora, la varianza spiegata proiettando in 2D è:

$$
\frac{5.0 + 2.5}{10} = 0.75 \quad \Rightarrow \quad 75\% \text{ della varianza è conservata}
$$

### Scelta di d'
La distribuzione dei valori degli autovalori è solitamente caratterizzata da una rapida diminuzione iniziale seguita
da una diminuzione più contenuta.

Gli autovalori misurano la quantità di varianza della distribuzione mantenuta nella proiezione.
Consideriamo, per ogni $k < d$, il valore:

$$
r_k = \frac{\sum_{i=1}^{k} \lambda_i}{\sum_{i=1}^{n} \lambda_i}
$$

che fornisce una misura della frazione di varianza associata ai $k$ autovalori più grandi.

Quando sono noti $r_1 < . . . < r_d$, è possibile mantenere una certa quantità p di varianza scegliendo 
$$d' = \argmin_{i\in \set{1,...,d}} r_i > p$$