## Risoluzione di sistemi lineari

Dato un sistema lineare:  
$$A\bold{x}=\bold{b}$$

con $A \in \Complex^{n \times n}$ invertibile. Tale sistema ha un'unica soluzione $\bold{x}=A^{-1}\bold{b}$.  
Vogliamo risolvere il sistema con un metodo iterativo, cioè un metodo che a partire da un vettore $\bold{x}^{(0)}$ costruisce una successione di vettori che convergano $\bold{x}$.

Consideriamo i seguenti metodi iterativi:  
$$\bold{x}^{(0)}\in \Complex^n, \\ \bold{x}^{(k+1)}=P\bold{x}^k+\bold{q}, \ \ k=0,1,2... \ \ \ (4.2)$$

dove $P \in \Complex^{n \times n}$ è detta *matrice d'iterazione*

#### <span style="color:red"> Teorema (condizione necessaria e sufficiente di convergenza). </span>

Supponiamo che il metodo (4.2) sia consistente con (4.1). Allora esso è convergente se e solo se $\rho(P) < 1$

#### <span style="color:lightgreen">Dim </span>

$(\impliedby)$
Poiché il metodo è consistente per ipotesi, vale:  
$$\bold{x}=P\bold{x}+\bold{q} \ \ (4.3)$$

Inoltre vale, ovviamente anche l'equazione del metodo:  
$$\bold{x}^{(k+1)}=P\bold{x}^{(k)}+\bold{q},\ \ k=0,1,2... \ \ (4.4)$$

Sottraendo membro a membro la $(4.4)$ e la $(4.3)$ si ottiene l’equazione dell’errore:

\[
\bold{e}^{(k+1)} = P \bold{e}^{(k)} \ \ \ \forall  k = 0, 1, 2, \dots \ \ (4.5)
\]

dove 

\[
\bold{e}^{(k)} = \bold{x}^{(k)} - \bold{x}
\]

è l’errore al passo \( k \).  

Sviluppando per ricorrenza la $(4.5)$ si ottiene:

\[
e^{(k+1)} = P e^{(k)} = P^2 e^{(k-1)} = P^3 e^{(k-2)} = \dots = P^{k+1} e^{(0)}, \ \ \ k = 0, 1, 2, \dots \]


Per ipotesi $\rho(P)<1$ , e un noto teorema dell'analisi numerica ci dice che $P^k \rightarrow \mathit{O}$.
Dunque si deduce che $\bold{e}^{(k)}\rightarrow 0$ e dunque ricordando a cosa è uguale $\bold{e}^{(k)}$ si ha:  
$$\bold{x}^{(k)} - \bold{x} \rightarrow 0 \iff \bold{x}^{(k)} \rightarrow \bold{x}$$

$\square$


### <span style="color:blue"> Corollario (Condizioni sufficienti di convergenza)</span>
Supponiamo che il metodo $(4.2)$ sia consistente con $(4.1)$. Se esiste una norma matriciale indotta $\|\cdot\|$ tale che $\|P\| < 1$ allora il metodo è convergente.

#### <span style="color:lightgreen">Dim </span>
 
Poiché \( \rho(P) \leq \|P\| \) per un teorema, la condizione \( \|P\| < 1 \) implica che \( \rho(P) < 1 \) e dunque il metodo è convergente.

### <span style="color:blue"> Corollario  (Condizioni necessarie di convergenza) </span>
  
Supponiamo che il metodo (4.2) sia consistente con (4.1).

- Se \( |\text{traccia}(P)| \geq n \) allora il metodo non è convergente.  
- Se \( |\det(P)| \geq 1 \) allora il metodo non è convergente.  

Quindi le condizioni \( |\text{traccia}(P)| < n \) e \( |\det(P)| < 1 \) sono necessarie per la convergenza del metodo $(4.2)$.

#### <span style="color:lightgreen">Dim </span>
+ Se \( |\text{traccia}(P)| \geq n \) allora esiste almeno un autovalore di \( P \) di modulo \( \geq 1 \).  
Infatti, se tutti gli autovalori \( \lambda_1, \dots, \lambda_n \) di \( P \) fossero di modulo \( < 1 \), allora avremmo:

    \[
|\text{traccia}(P)| = |\lambda_1 + \dots + \lambda_n| \leq |\lambda_1| + \dots + |\lambda_n| < n.
\]

     Poiché esiste almeno un autovalore di \( P \) di modulo \( \geq 1 \), si deduce che:

    \[
\rho(P) = \max(|\lambda_1|, \dots, |\lambda_n|) \geq 1
\]

    e dunque il metodo non è convergente.

+ Se \( |\det(P)| \geq 1 \) allora esiste almeno un autovalore di \( P \) di modulo \( \geq 1 \).  
Infatti, se tutti gli autovalori \( \lambda_1, \dots, \lambda_n \) di \( P \) fossero di modulo \( < 1 \), allora avremmo:

    \[
|\det(P)| = |\lambda_1 \cdot \dots \cdot \lambda_n| = |\lambda_1| \cdot \dots \cdot |\lambda_n| < 1.
\]

    Poiché esiste almeno un autovalore di \( P \) di modulo \( \geq 1 \), si deduce che:

    \[
\rho(P) = \max(|\lambda_1|, \dots, |\lambda_n|) \geq 1
\]

    e dunque il metodo non è convergente.


#### <span style="color:red"> Teorema </span>
Supponiamo che la matrice \( A \in \mathbb{C}^{n \times n} \) soddisfi almeno una delle seguenti condizioni:

- \( A \) è a diagonale dominante e irriducibile;
- \( A \) è a diagonale dominante in senso stretto;
- \( A \) è a diagonale dominante per colonne e irriducibile;
- \( A \) è a diagonale dominante in senso stretto per colonne.

Allora i metodi di Jacobi e Gauss-Seidel per risolvere un sistema lineare di matrice \( A \) sono convergenti.

#### <span style="color:lightgreen">Dim </span>
Dimostriamo il teorema per il metodo di Gauss-Seidel sotto l'ipotesi di dominanza diagonale e irriducibilità.  
Dobbiamo dimostrare che $\rho(G)<1$, ove $G=I-E^{-1}A$ è la matrice d'iterazione del metodo di Gauss-Seidel. Per l'osservazione "Smart", gli autovalori di $G$ sono le radici del polinomio:  
\[
\det(\lambda E + A - E) =
\begin{vmatrix}
\lambda a_{11} & a_{12} & a_{13} & a_{14} \\
\lambda a_{21} & \lambda a_{22} & a_{23} & a_{24} \\
\lambda a_{31} & \lambda a_{32} & \lambda a_{33} & a_{34} \\
\lambda a_{41} & \lambda a_{42} & \lambda a_{43} & \lambda a_{44}
\end{vmatrix} \ \ \ (\star)
\]

Nessun numero di $\lambda$ di modulo $\geq 1$ può essere radice di questo polinomio. Infatti se $|\lambda|\geq 1$ allora la matrice $\lambda E+A-E$ è a diagonale dominante e irriducibile esattamente come $A$.
La dimostrazione di ciò si basa su due osservazioni:  
+ $\lambda E+A-E$ è a diagonale dominante come $A$. Infatti $\forall i=0,...,n$
    $$|\lambda a_{ii}|=|\lambda||a_{ii}|\geq |\lambda|(|a_{i1}|+...+|a_{i\ i-1}|+|a_{i\ i+1}|+...+|a_{i  n}|)$$
    > Questo perché $|a_{ii}| \geq |a_{i1}|+...+|a_{i\ i-1}|+|a_{i\ i+1}|+...+|a_{i  n}|$ poiché $A$ è a diagonale dominante.  

    $$=|\lambda a_{i1}|+...+|\lambda a_{i \ i-1}|+|\lambda|\cdot |a_{i\ i+1}|+...+|\lambda|\cdot|a_{in}|$$ 
    
    $$\geq |\lambda a_{i1}|+...+|\lambda a_{i \ i-1}|+ |a_{i\ i+1}|+...+|a_{in}|$$
    > Siccome $|\lambda|\geq 1$.
    
    Dunque è a diagonale dominante poiche: 

    $$|\lambda a_{ii}| \geq |\lambda a_{i1}|+...+|\lambda a_{i \ i-1}|+ |a_{i\ i+1}|+...+|a_{in}| $$ 
+ $\lambda E+A-E$ è irriducibile perché gli elementi nulli di $\lambda E+A-E$ si trovano nelle stesse posizioni in cui si trovano gli elementi nulli di $A \implies \lambda E+A-E$ ed $A$ hanno lo stesso grafo $\implies \lambda E+A-E$ è irriducibile perché $A$ è irriducibile.


Dunque nessun numero $\lambda$ di modulo $\geq 1$ può essere radice di $(\star)$, altrimenti la matrice $\lambda E+A-E$ è a diagonale dominante e irriducibile, per cui è invertibile per un teorema e dunque $\det(\lambda E+A-E)\neq 0$. In definitiva gli autovalori di $G$ devono essere per forza in modulo $<1$ e perciò $\rho(G)< 1$.

$\square$

## me so dimenticato de salva l'ultimo teorema....