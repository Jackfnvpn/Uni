## Analisi di matrici  

Una matrice Hermitiana ha i valori sulla diagonale reali.

Una matrice Hermitiana ha autovalori reali, infatti

Infatti preso $\lambda \in \Complex$ autovalore di $A$ e $\bold{x}\neq \bold{0}$ un corrispondente autovettore ho
$$A\bold{x}=\lambda\bold{x} \iff \bold{x}^*A\bold{x}=\bold{x}^*\lambda\bold{x}=\lambda\sum_{i=0}^n|x_i|^2 \implies \lambda=\frac{\bold{x}^*A\bold{x}}{\sum_{i=0}^n|x_i|^2}$$

Sotto in quanto ho un modulo ho sicuro un numero reale.
Al numeratore ho un numero reale in quanto  $\bold{x}^∗A\bold{x}$ è un numero reale essendo uguale al suo complesso coniugato:

$\overline{\bold{x}^∗A\bold{x}}=(\bold{x}^∗A\bold{x})^*=\bold{x}^*A^*\bold{x}^{**}=\bold{x}^*A\bold{x}$

Una matrice \( A \in \mathbb{C}^{n \times n} \) si dice **definita positiva** se 

\[
\operatorname{Re}(x^* A x) > 0 \quad \text{per ogni } x \in \mathbb{C}^{n} \setminus \{0\}.
\]

Notiamo che:

\[
\operatorname{Re}(x^* A x) = \frac{x^* A x + x^* A x^*}{2}
= \frac{x^* A x + (x^* A x)^*}{2}
= \frac{x^* A x + x^* A^* x}{2}
= x^* \left( \frac{A + A^*}{2} \right) x = x^* \operatorname{Re}(A) x,
\]

dove

\[
\operatorname{Re}(A) \coloneqq \frac{A + A^*}{2}, \quad
\operatorname{Im}(A) \coloneqq \frac{A - A^*}{2i}, \quad
A = \operatorname{Re}(A) + i \operatorname{Im}(A).
\]

$\text{Re}(A)$ e $\text{Im}(A)$ sono matrici hermitiane.

Infatti 

$$(\text{Re}(A))^*=\Big(\frac{A+A^*}{2}\Big)^*=\frac{A^*+A^{**}}{\overline{2}}=\frac{A+A^*}{2}$$

$$(\text{Im}(A))^*=\Big(\frac{A-A^*}{2i}\Big)^*=-\frac{A^*-A^{**}}{2i}=\frac{A-A^*}{2i}$$



$A$ è definita positiva se e soltanto se $\text{Re}(A)$ è definita positiva

$\begin{matrix} 
A \text{ è definita positiva}&\iff& \forall \bold{x} \in C^{n\times n}\backslash\set{\bold{0}} \ \text{Re}(\bold{x}^*A\bold{x})>0 \\ 
&\iff& \forall \bold{x} \in C^{n\times n}\backslash\set{\bold{0}} \ \bold{x}^*\text{Re}(A)\bold{x}>0\\
&\iff&\forall \bold{x} \in C^{n\times n}\backslash\set{\bold{0}} \ \text{Re}(\bold{x}^*\text{Re}(A)\bold{x})>0 \\
&\iff& \text{Re}(A) \text{ è definita positiva}
\end{matrix}$

> La terza equivalenza è data dal fatto che essendo $\bold{x}^*\text{Re}(A)\bold{x}$ reale allora $\bold{x}^*\text{Re}(A)\bold{x}=\text{Re}(\bold{x}^*\text{Re}(A)\bold{x})$ cioè sto prendendo la parte reale di un numero reale... Il numero stesso insomma.  

Se una matrice $A$ è definita positiva allora è invertibile perché i suoi autovalori sono positivi. Infatti preso $\lambda \in \Complex$ autovalore di $A$ e $\bold{x}\neq \bold{0}$ un corrispondente autovettore ho
$$A\bold{x}=\lambda\bold{x} \iff \bold{x}^*A\bold{x}=\bold{x}^*\lambda\bold{x}=\lambda\sum_{i=0}^n|x_i|^2 \implies \lambda=\frac{\bold{x}^*A\bold{x}}{\sum_{i=0}^n|x_i|^2}$$

Sotto al denominatore ho sicuramente una quantità positiva in quanto $\bold{x}\neq 0$ e ogni somma è un valore al quadrato.
Sopra invece ho un valore positivo in quanto $A$ è definita positiva e per definizione 

$$\forall \bold{x}\neq 0 \ \bold{x}^*A\bold{x}>0$$


### <span style="color:red">Teorema</span>
Sia \( A \in \mathbb{C}^{n \times n} \) una matrice hermitiana e siano \( A_1, A_2, \dots, A_n \) le sue sottomatrici principali di testa:

\[
A_1 = [a_{11}], \quad
A_2 =
\begin{bmatrix}
a_{11} & a_{12} \\
a_{21} & a_{22}
\end{bmatrix},
\quad
A_3 =
\begin{bmatrix}
a_{11} & a_{12} & a_{13} \\
a_{21} & a_{22} & a_{23} \\
a_{31} & a_{32} & a_{33}
\end{bmatrix},
\quad \dots, \quad A_n = A.
\]

Le seguenti condizioni sono equivalenti:

- \( A \) è definita positiva;
- \( \bold{x}^* A \bold{x} > 0 \) per ogni \( x \in \mathbb{C}^n \setminus \{0\} \);
- Gli autovalori di \( A \) sono reali e positivi;
- \( \det(A_k) > 0 \) per ogni \( k = 1, \dots, n \).



### <span style="color:red">Teorema</span>
Se $p(\lambda)$ è un polinomio e $A \in \Complex^{n \times n}$ è una matrice con autovalori $\lambda_1,...,\lambda_n$, allora gli autovalori della matrice $p(A)$ sono $p(\lambda_1),...,p(\lambda_n)$.
#### <span style="color:lightgreen">dim</span>
Dimostreremo il teorema solo in 3 casi:  
+ Caso 1: **Il polinomio $p(\lambda)=a_0$ è costante**
    +  In tal caso $P(A)=a_0I$ e i suoi autovalori sono $a_0,...,a_0$ (ripetuto $n$ volte) . 
    >Questo perché $a_0I$ è una matrice diagonale con tutti $a_0$ sulla diagonale

    Dunque gli autovalori di $p(A)$ sono $p(\lambda_1),...,p(\lambda_n)$ e la tesi del teorema vale.
+ Caso 2: **Il polinomio $p(\lambda)=a_0+a_1\lambda$ ha grado 1**
  +  In tal caso, il polinomio caratteristico di $p(A)$ e quello di $A$ sono legati dalla seguente relazione $\forall \lambda \in \Complex$:
$$C_{p(A)}(\lambda)=\det(\lambda I-p(A))=\det(\lambda I - (a_0I+a_1A))=\det((\lambda-a_0)I-a_1A)\\=\det(a_1(\frac{\lambda-a_0}{a_1}I-A))=a^n_1 \det(\frac{\lambda -a_0}{a_1}I-A)=a_1^nC_A(\frac{\lambda-a_0}{a_1})$$

        Dunque gli autovalori di $p(A)$ sono:  
       
$$\{ \lambda \in \mathbb{C} \mid C_{p(A)}(\lambda) = 0 \} =
\left\{ \lambda \in \mathbb{C} \mid C_A \left( \frac{\lambda - a_0}{a_1} \right) = 0 \right\} = 
\left\{ \lambda \in \mathbb{C} \mid \frac{\lambda - a_0}{a_1} = \lambda_1, \dots, \lambda_n \right\} = \\
\left\{ \lambda \in \mathbb{C} \mid \lambda = a_0 + a_1 \lambda_1, \dots, a_0 + a_1 \lambda_n \right\} =
\{ a_0 + a_1 \lambda_1, \dots, a_0 + a_1 \lambda_n \} =
\{ p(\lambda_1), \dots, p(\lambda_n) \}$$


+ Caso 3: La matrice \( A \) è diagonalizzabile  

    + In tal caso, esistono una matrice invertibile \( X \) e una matrice diagonale  

    \[
    D = \text{diag}(\lambda_1, \dots, \lambda_n)
    \]

    (avente sulla diagonale gli autovalori di \( A \)) tali che  

    \[
    A = X D X^{-1},
    \]

    \[
    A^2 = X D X^{-1} X D X^{-1} = X D^2 X^{-1},
    \]

    \[
    A^3 = X D X^{-1} X D X^{-1} X D X^{-1} = X D^3 X^{-1},
    \]

    \[
    \vdots
    \]

    Pertanto, fissato un polinomio  

    \[
    p(\lambda) = a_0 + a_1 \lambda + a_2 \lambda^2 + \dots + a_m \lambda^m,
    \]

    si ha  

    \[
    p(A) = a_0 I + a_1 A + a_2 A^2 + \dots + a_m A^m.
    \]

    Sostituendo \( A = XDX^{-1} \), otteniamo  

    \[
    p(A) = X \left( a_0 I + a_1 D + a_2 D^2 + \dots + a_m D^m \right) X^{-1}.
    \]

    Usando la notazione \( p(D) \), otteniamo  

    \[
    p(A) = X p(D) X^{-1}, \quad (3.2)
    \]

    dove  

    \[
    p(D) = a_0 I + a_1 D + a_2 D^2 + \dots + a_m D^m =
    \begin{bmatrix}
    
    \end{bmatrix}. \quad (3.3)
    \]


### <span style="color:red">Teorema  (Primo Teorema di Gershgorin)</span>

Gli autovalori di una matrice $A \in \Complex^{n \times n}$ stanno tutti nell'unione dei cerchi di Gershgorin di $A$.

#### <span style="color:lightgreen">dim</span>

Sia $\lambda$ un autovalore di $A$ e sia $\vec{u}\neq \vec{0}$ un corrispondente autovettore. Si ha:
$$A\vec{u}=\lambda \vec{u} \iff (A\vec{u})_i=(\lambda\vec{u})_i \ \forall i=1,...,n \iff \sum_{j=1}^na_{ij}u_j=\lambda u_i \ \forall i=1,...,n$$

Selezionando un indice $i_0$ corrispondente a una componente $u_{i_0}$ di modulo massimo di $\vec{u}$, la precedente equazione $i_0$-esima ci dice che:
$$\sum_{j=1}^n a_{ij}u_j=\lambda u_i \ \forall i=1,...,n  \iff \\ a_{i_0i_0}u_{i_0}+\sum_{j=1, \ j\neq i_0}^n a_{i_0j}u_j=\lambda u_{i_0} \iff (\lambda-a_{i_0i_0})u_{i_0}=\sum_{j=1, \ j\neq i_0}^n a_{i_0j}u_j$$ 


$$\implies |(\lambda-a_{i_0i_0})| |u_{i_0}|
=|\sum_{j=1, \ j\neq i_0}^n a_{i_0j}u_j| 
\\ \leq \sum_{j=1, \ j\neq i_0}^n |a_{i_0j}||u_j|$$

Per la disuguaglianza triangolare

$$\leq  \sum_{j=1, \ j\neq i_0}^n |a_{i_0j}||u_{i_0}|$$

$u_{i_0}$ è il massimo di tutti gli $u_i$

$$=|u_{i_0}|\sum_{j=1, \ j\neq i_0}^n |a_{i_0j}|$$

Dunque 
$$|(\lambda-a_{i_0i_0})| \ \cancel{|u_{i_0}|} \leq \cancel{|u_{i_0}|} \ \sum_{j=1, \ j\neq i_0}^n |a_{i_0j}|$$

dunque $\lambda$ dista da $a_{i_0i_0}$ $($che è il centro del cerchio di G. $K_{i_0}) \leq$ del raggio $\sum_{j=1, \ j\neq i_0}^n |a_{i_0j}|$ di $K_{i_0} \implies \lambda \in K_{i_0}\implies \lambda \in \bigcup^n_{i=1}K_i$    $\square$.  

### <span style="color:red">Teorema  (Secondo Teorema di Gershgorin)</span>

Supponiamo che l’unione di \( k \) cerchi di Gershgorin di \( A \in \mathbb{C}^{n \times n} \) sia disgiunta dall’unione degli altri \( n - k \) cerchi. Allora \( k \) autovalori di \( A \) stanno nella prima unione e \( n - k \) nella seconda.


### <span style="color:red">Teorema  (Terzo Teorema di Gershgorin)</span>

Supponiamo che \( A \in \mathbb{C}^{n \times n} \) sia irriducibile. Allora i punti che stanno sul bordo di quei cerchi di Gershgorin a cui appartengono, ma non sul bordo di tutti i cerchi, non sono autovalori di \( A \).


### <span style="color:red">Teorema  (Terzo Teorema di Gershgorin Debole)</span>

Supponiamo che \( A \in \mathbb{C}^{n \times n} \) sia irriducibile e sia \( B \) il bordo dell’unione dei cerchi di Gershgorin. Allora i punti di \( B \) che non stanno sul bordo di tutti i cerchi non sono autovalori di \( A \).


---

#### Definizione: Dominanza diagonale
Sia \( A \in \mathbb{C}^{n \times n} \) una matrice.

+ La matrice \( A \) è detta **a diagonale dominante per righe** se:  

  - \[
|a_{ii}| \geq \sum_{j \neq i} |a_{ij}| \ \ \forall i = 1, \dots, n \]
  
  - esiste almeno un indice \( k \in \{1, \dots, n\} \) per il quale vale la disuguaglianza stretta:
  \[
  |a_{kk}| > \sum_{j \neq k} |a_{kj}|.
  \]
  
+ La matrice \( A \) è detta **a diagonale dominante in senso stretto per righe** se:  

\[
|a_{ii}| > \sum_{j \neq i} |a_{ij}| \ \ \forall i = 1, \dots, n
\]

+ La matrice \( A \) è detta **a diagonale dominante per colonne** se:  

  - \[
|a_{jj}| \geq \sum_{i \neq j} |a_{ij}| \ \ \forall i = 1, \dots, n
\]

  - esiste almeno un indice \( \ell \in \{1, \dots, n\} \) per il quale vale la disuguaglianza stretta:
  \[
  |a_{\ell\ell}| > \sum_{i \neq \ell} |a_{i\ell}|.
  \]

La matrice \( A \) è detta **a diagonale dominante in senso stretto per colonne** se:  

\[
|a_{jj}| > \sum_{i \neq j} |a_{ij}| \ \ \forall i = 1, \dots, n
\]


---

### <span style="color:red">Teorema</span>
Supponiamo che la matrice \( A \in \mathbb{C}^{n \times n} \) soddisfi almeno una delle seguenti condizioni:

- \( A \) è **a diagonale dominante e irriducibile**;
- \( A \) è **a diagonale dominante in senso stretto**;
- \( A \) è **a diagonale dominante per colonne e irriducibile**;
- \( A \) è **a diagonale dominante in senso stretto per colonne**.

Allora **\( A \) è invertibile**.

#### <span style="color:lightgreen">dim</span>

Dimostriamo secondo l'ipotesi che $A$ sia a diagonale dominante e irriducibile.
Mostriamo che $0$ non è autovalore di $A$ usando il terzo teorema di Gershgorin. Siccome $A$ è a diagonale dominante, se $0$ appartiene a un cerchio di Gershgorin $K_i$ allora deve per forza stare sul bordo di $K_i$. Infatti non può stare all'interno, perché per l'ipotesi di dominanza diagonale si ha:  
$$\text{ raggio di }K_i=\sum_{j\neq i}|a_{ij}|\leq |a_{ii}|=\text{distanza}(a_{ii,0})=\text{distanza(centro di }K_i,0)$$

Dunque $0$ sta per forza di cose sul bordo di quei cerchi. Inoltre sempre per ipotesi di dominanza diagonale, esiste un indice $k$ per cui 
$$|a_{kk}|>\sum_{j\neq k}|a_{kj}|$$

Questo significa che $0$ non sta neanche sul bordo di $K_k$ e dunque $0$ non sta sul bordo di tutti i cerchi di G. di $A$. Siccome $A$ è irriducibile, il terzo teorema di Gershgorin ci dice che $0$ non può essere autovalore di $A$. Dunque è invertibile.