## Matrici a diagonale dominante
***DEF***
Sia $A \in \Complex^{n\times n}$ una matrice.
+ Si dice che $A$ è a diagonale dominante (per righe)  <span style="color:darkblue">  per colonne </span>
  + $|a_{ii}| \geq \sum_{i=1, \ i \neq j}^n |a_{ij}| \ \forall i=1,...,n$ (questa condizione dice che 0 non può essere interno a nessun cerchio di G di $A$)  
    <span style="color:darkblue"> $|a_{jj}| \geq \sum_{i=1, \ i \neq j}^n |a_{ij}| \ \forall i=1,...,n$ (0 non può essere interno a nessun cerchio di G per colonne )</span>
  + esiste almeno un indice $k\in \set{1,...,n}$ per cui vale $|a_{kk}| \geq \sum_{j=1, \ j \neq k}^n |a_{jk}| \ \forall j=1,...,n$ (questa condizione dice che 0 non appartiene al cerchio $K_k$)
   <span style="color:darkblue"> $|a_{kk}| \geq \sum_{j=1, \ j \neq k}^n |a_{ik}| \ \forall j=1,...,n$ (questa condizione dice che 0 non appartiene al cerchio $H_k$ )</span>
+ Si dice che $A$ è a diagonale dominante in senso stretto (per righe) se:  
  $|a_{ii}| > \sum_{i=1, \ i \neq j}^n |a_{ij}|\ \forall j=1,...,n$ (questa condizione dice che 0 non appartiene a nessun cerchio G di $A$)
  <span style="color:darkblue"> $|a_{jj}| > \sum_{i=1, \ i \neq j}^n |a_{ij}| \ \forall j=1,...,n$ (0 non può essere interno a nessun cerchio di G per colonne )</span>

ES: Discutere le proprietà di dominanza diagonale per righe e per colonne della matrice:

$$A=\begin{bmatrix}
3&1&-2\\
0&-3&1\\
1-2i&-1&5\\
\end{bmatrix}$$

SOL:

$$\begin{matrix}
|3|=|1|+|-2| \\
|-3|>|0|+|1| & \ \implies A \text{ è a diagonale dominante per righe ma non in senso stretto }\\
|5|>|1-2i|+|-1|=\sqrt{5}+1
\end{matrix}$$
<br>
$$\begin{matrix}
|3|>|0|+|1-2i| \\
|-3|>|1|+|-1| & \ \implies A \text{ è a diagonale dominante in senso stretto per colonne }\\
|5|>|-2|+|1|=\sqrt{5}+1
\end{matrix}$$

#### <span style="color:red"> Teorema </span>
Supponiamo che $A \in \Complex^{n \times n}$ soddisfi almeno una delle seguenti condizioni:
+ $A$ è a diagonale dominante e irriducibile
+ $A$ è a diagonale dominante in senso stretto 
+ $A$ è a diagonale dominante per colonne e irriducibile
+ $A$ è a diagonale dominante in senso stretto per colonne  

Allora $A$ è invertibile  

##### <span style="color:lightgreen">Dim (Nel caso riquadrato)</span>
Per dimostrare che $A$ è invertibile dimostro che 0 non è un autovalore di $A$ usando i teo di G. Poiché $A$ è a diagonale dominante, 0 non può essere interno a nessun cerchio di G di $A$, dunque 0 sta sul bordo di quegli eventuali cerchi di G a cui appartiene. Inoltre, sempre per definizione di matrice a diagonale dominante di G t.c 0 non sta sul bordo di quel cerchio (in realtà non ci sta proprio in quel cerchio) $\implies$ siccome $A$ è irriducibile per ipotesi, per il 3° teorema di G (forte) 0 non può essere autovalore di $A$.

OSS:
L'esempio del quadrifoglio mostra che nella dimostrazione precedente dobbiamo per forza usare il 3° teorema di G (forte) perché quello debole non è sufficiente.  
ES: concreto:
$$\begin{bmatrix}
\bold{1}&1&0&0&0\\
0&\bold{i}&1&0&0\\
0&0&\bold{-1}&1&0\\
0&0&0&\bold{-i}&1\\
\frac{1}{4}&0&0&0&\bold{1}
\end{bmatrix}$$ Questa matrice ha i cerchi dell'esempio del quadrifoglio (quindi a diagonale dominante) ed è irriducibile perché c'è nel suo grafo un ciclo  
$1 \rightarrow 2 \rightarrow 3 \rightarrow 4 \rightarrow 5 \rightarrow 1$ che tocca tutti i nodi.  
$\implies$ per il teo. precedente è invertibile, ma a questa conclusione non potevo giungere con il 3° teorema debole $(0 \notin \mathcal{B})$  

## Norme vettoriali  
### Introduzione
Consideriamo il sistema lineare  

$$\begin{bmatrix}
8&1&1\\
1&5&1\\
1&-1&5\\
\end{bmatrix}\begin{bmatrix}
x_1\\
x_2\\
x_3\\
\end{bmatrix}=\begin{bmatrix}
26\\
7\\
7\\
\end{bmatrix}$$

soluzione $x=\begin{bmatrix}
3\\
1\\
1\\
\end{bmatrix}$

Supponiamo di avere due approssimazioni della soluzione $x$.

$y=\begin{bmatrix}
2.99972\\
1.00023\\
1.00030\\
\end{bmatrix},\ z=\begin{bmatrix}
3.00027\\
0.99971\\
0.99955\\
\end{bmatrix}$

Come possiamo stabilire quale fra $y,z$ è più vicino a $x$?
Occorre introdurre un concetto di distanza sullo spazio dei vettori e misurare la distanza di $y,z$ da $x$: quello che dista meno è il più vicino.  

***DEF (norma vettoriale):***
Una funzione $||\cdot||: \Complex^n \rightarrow \R$ si dice norma vettoriale se soddisfa:
1. $||x||\geq 0 \ \forall x \in \Complex^n \ e \ ||x||=0 \iff x=0 \ \text{[ positività ]}$
2. $||\alpha x||=|\alpha|\cdot ||x|| \ \forall \alpha \in \Complex^n \ \text{[ omogeneità ]}$  
3. $||x+y||\leq ||x||+||y|| \ \forall x,y \in C^n \ \text{[ disug. triangolare]}$

Data una norma vettoriale $||\cdot||: \Complex^n \rightarrow \R$ , definiamo la distanza fra due vettori $x,y$ come $||x-y||$.  

> Norme importanti in $\Complex^n$:
> 
> $$||x||_1=|x_1|+|x_2|+...+|x_n| \rightarrow ||x-y||_1=|x_1-y_1|+|x_2-y_2|+...+|x_n-y_n|$$
> 
>$$||x||_2=\sqrt{|x_1|^2+|x_2|^2+...+|x_n|^2} \rightarrow ||x-y||_2=\sqrt{|x_1-y_1|^2+|x_2-y_2|^2+...+|x_n-y_n|^2}$$
>
>$$||x||_\infty=\max(|x_1|,|x_2|,...,|x_n|) \rightarrow ||x-y||_\infty=\max(|x_1-y_1|,|x_2-y_2|,...,|x_n-y_n|)$$

Tornando all'esempio introduttivo, si ha:  

$$x-y=\begin{bmatrix}
0.000028\\
-0.00023\\
-0.00030
\end{bmatrix}, \ 
x-z=\begin{bmatrix}
-0.000027\\
0.00029\\
0.00045
\end{bmatrix}$$  

$||x-y||_\infty=0.00030$

$||x-z||_\infty=0.00045$

$\implies y$ è più vicino a $x$ rispetto $z$.

#### <span style="color:red"> Teorema (Equivalenza delle norme) </span> 
Tutte le norme vettoriali in $\Complex^n$ sono equivalenti, nel senso che se prendiamo 2 norme vettoriali 
$||\cdot||' , \ ||\cdot||'': \Complex^n \rightarrow \R$ allora si ha: 

$$||x||''\leq ||x||'\leq \beta ||x||'' \ \forall x \in \Complex^n, \ \alpha,\beta>0 \text{ costanti indipendenti da } x$$

Verifichiamo per esempio che $||\cdot||_1 \ e \ ||\cdot||_\infty$ sono equivalenti. $\forall x\in \Complex^n:$  

$$1\cdot||x||_\infty \leq ||x||_1 \leq n \cdot ||x||_\infty \implies \text{ vale il teorema con } \alpha=1,\beta=n \text{ nel caso in cui }||\cdot||'=||\cdot||_1 \ e \ ||\cdot||''=||\cdot||_\infty$$

$$\frac{1}{n}\cdot||x||_1 \leq ||x||_\infty \leq 1 \cdot ||x||_1 \implies \text{ vale il teorema con } \alpha=\frac{1}{n},\beta=1 \text{ nel caso in cui }||\cdot||'=||\cdot||_\infty \ e \ ||\cdot||''=||\cdot||_1$$

## Successioni di vettori  
Una successione di vettori $x^{(0)},x^{(1)},x^{(2)},... \in \Complex^n$ si dice convergente al vettore $x\in \Complex^n$ rispetto alla norma $||\cdot||$ se $||x^{(k)}-x|| \rightarrow 0$

Poiché tutte le norme vettoriali sono equivalenti, se una successione di vettori converge a $x$ a una norma $||\cdot||$ allora converge a $x$ rispetto a tutte le norme.  

##### <span style="color:lightgreen">Dim</span>
Supponiamo che $x^{(k)}\rightarrow x$ rispetto allora norma $||\cdot||$ e sia $||\cdot||'$ un'altra norma.
Poiché $||\cdot|| \ e \ ||\cdot||'$ sono equivalenti, esistono 2 costanti $\alpha,\beta>0$ tale che:  
$$\alpha||y||\leq ||y||'\leq \beta ||y|| \ \forall y \in \Complex^n \implies \alpha ||x^{(k)}-x||\leq ||x^{(k)}-x||' \leq \beta ||x^{(k)}-x|| \ \forall k \geq 0$$

Siccome $||x^{(k)}-x|| \rightarrow 0 \ ($ perché  $x^{(k)}\rightarrow x$ rispetto a  $||\cdot||)$ deduciamo dal teo. dei 2 carabinieri che $||x^{(k)}-x||' \rightarrow 0$ (cioè $x^{(k)}\rightarrow x'$ in $||\cdot||$)

***DEF***
Una successione di vettori $x^{(0)},x^{(1)},x^{(2)},... \in \Complex^n$ si dice convergente (componente per componente) al vettore $x \in \Complex^n$ se $x^{(k)}\leftarrow x$ componente per componente cioè se:

$$\begin{cases}
  x_1^{(k)} \rightarrow x_1 \\
  x_1^{(k)} \rightarrow x_2 \\
\vdots \\
x_n^{(k)} \rightarrow x_n \\
\end{cases} \iff \begin{cases}
|x_1^{(k)}-x_1| \rightarrow 0 \\
|x_2^{(k)}-x_2| \rightarrow 0 \\
\vdots \\
|x_n^{(k)}-x_n| \rightarrow 0 \\
\end{cases}\iff \max(|x_1^{(k)}-x_1| ,|x_2^{(k)}-x_2| ,...,|x_n^{(k)}-x_n|) \rightarrow 0 \iff ||x^{(k)}-x||_\infty \rightarrow 0$$

Conclusione: La convergenza componente per componente altro non è che la convergenza in $||\cdot||$. 
Dunque, ricordando l'equivalenza delle norme e il risultato dimostrato sopra, dire $x_1^{(k)} \rightarrow x$ componente per componente è lo stesso che dire che $x_1^{(k)} \rightarrow x$ in qualsiasi norma