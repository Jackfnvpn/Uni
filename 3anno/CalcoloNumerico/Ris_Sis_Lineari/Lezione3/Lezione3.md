## Costruzione di metodi iterativi mediante decomposizione della matrice

$$A\ \vec{x}=\vec{b} \ (\star),\  A \text{ invertibile}, \  \exist! \text{ soluzione }\vec{x}=A^{-1}\vec{b}$$

$$\begin{cases}
    \vec{x}^{(0)} \in \Complex^n \ \text{dato}\\
    \vec{x}^{(k+1)}= P\vec{x}^{(k)}+\vec{q}, \ k=0,1,2,...
\end{cases} \ (m)$$  

Vediamo una procedura standard per costruire un metodo della forma $(m)$ per risolvere il sistema $(\star)$.  

Si considera una decomposizione di $A$ del tipo:  
$$A=M-(M-A)$$ con $M \in \Complex^{n \times n}$ invertibile, detta **precondizionatore**. Si osserva che  
$$A\vec{x}=\vec{b}\iff(M-(M-A))\vec{x}=\vec{b}\iff M\vec{x}=(M-A)\vec{x}+\vec{b}\iff \underline{\vec{x}=M^{-1}(M-A)\vec{x}+M^{-1}\vec{b}} \ (\alpha)$$  $$\iff \vec{x}=(I-M^{-1}A)\vec{x}+M^{-1}\vec{b}\iff\vec{x}=M^{-1}A\vec{x}+M^{-1}\vec{b}\iff \underline{\vec{x}=\vec{x}+M^{-1}\vec{r}(\vec{x})} \ (\beta)$$ dove $\vec{r}(\vec{y})=b-A\vec{y}$ è il residuo in $y$ del sistema $(\star)$.  Si definisce il metodo   
$$\begin{cases}
    x^{(0)} \in \Complex^n \text{ dato }\\
    \vec{x}^{(k+1)}=\underbrace{M^{-1}(M-A)\vec{x}^{(k)}+M^{-1}\vec{b}}_{1° Formula}=\underbrace{\vec{x}^{(k)}+M^{-1}\vec{r}^{(k)}}_{2°Formula}
\end{cases} \ (m')$$ $(\vec{r}^{k}=\vec{r}(\vec{x}^{(k)})=\vec{b}-A\vec{x}^{(k)})$. 

Il metodo $(m')$ è della forma $(m)$ con matrice d'iterazione $P=\underbrace{M^{-1}(M-A)}_{I-M^{-1}A}$ e $\vec{q}=M^{-1}\vec{b}$.

In virtù di $(\alpha)$ e $(\beta)$ il metodo $(m')$ è sicuramente consistente con $(\star)$.  

#### Teorema
Il metodo $(m')$ è convergente se e solo se $\rho(I-M^{-1}A)<1$  

OSS "Smart":
Il polinomio caratteristico di $I-M^{-1}A$ è dato da:  
$$C_{I-M^{-1}A}(\lambda)=\det(\lambda I-I+M^{-1}A)=\det(M^{-1}(\lambda M-M+A))=^{\text{Binet}} \overbrace{\det(M^{-1})}^{\neq 0}\det(\lambda M-M+A)$$ Dunque 
$$C_{I-M^{-1}A}(\lambda)=0 \iff \textcolor{red}{\det(\lambda M-M+A)=0}$$   Conclusione: gli autovalori e il raggio spettrale di $I-M^{-1}A=$ matrice d'iterazione di $(m')$, si possono calcolare risolvendo l'equazione rossa, senza calcolare $M^{-1}$ ne la matrice d'iterazione stessa.  

OSS:
L'iterazione $k$-esima del metodo $(m')$ viene calcolata con la formula 2° normalmente
$$\vec{x}^{(k)}+M^{-1}\vec{r}^{(k)}$$ e richiede il calcolo del vettore $\vec{z}^{(k)}=M^{-1}\vec{r}^{(k)}$ detto **residuo precondizionato**.
Tale calcolo viene fatto risolvendo il sistema lineare $M\vec{z}^{(k)}=\vec{r}^{(k)}$ e **non** calcolando l'inversa $M^{-1}$ (cosa tipicamente sconveniente dal punto di vista computazionale).
Ovviamente, il sistema lineare $M\vec{z}^{(k)}=\vec{r}^{(k)}$ deve essere facile/rapido da risolvere rispetto al sistema originario $A\vec{x}=\vec{b}$, altrimenti non c'è nessun guadagno nel risolvere il sistema originario con $(m')$.

OSS:
Intuitivamente, quanto più il precondizonatore $M$ "è vicino/assomiglia" alla matrice $A$, tanto più il metodo $(m')$ convergerà velocemente. Infatti, se $M\approx A$ allora intuitivamente 
$$M-A\approx 0, \ M^{-1}A\approx I \ e \ M^{-1}(M-A)=I-M^{-1}A$$ per cui ci si può aspettare che $\rho(I-M^{-1}A)$ sia piccolo.

Il caso limite $M=A$ è quello in cui $I-M^{-1}A=0$ e si può verificare che il metodo $(m')$ converge in un'unica iterazione alla soluzione esatta $\vec{x}$, ma al prezzo che tale iterazione costa come la risoluzione del sistema originario $A\vec{x}=\vec{b}$.
Nella scelta del precondizonatore $M$ occorre quindi mediare tra 2 cose:
1. Qualità dell'approssimazione $M\approx A$ (una buona approssimazione $M\approx A$ generalmente assicura una buona velocità di convergenza)
2. Facilità|Rapidità della risoluzione di un sistema lineare di matrice $M$ ( questa assicura che ogni iterazione del metodo $(m')$ è rapida)

## Metodo di Jacobi:
Supponiamo che $A$ non abbia elementi diagonali nulli. In tal caso, la parte diagonale di $A$, cioè la matrice diagonale $D$ ottenuta ricopiando la parte diagonale di $A$, è invertibile e quindi possiamo definire il metodo di Jacobi, cioè il metodo $(m')$ con $M=D:$

$$\begin{cases}
    \vec{x}^{(0)}\in \Complex^n \text{ dato }\\
    \vec{x}^{(k+1)}=D^{-1}(D-A)\vec{x}^{(k)}+D^{-1}\vec{b}=\vec{x}^{(k)}+D^{-1}\vec{r}^{(k)}, \ k=0,1,2,...
\end{cases}$$
Il metodo di Jacobi è convergente se e solo se $\rho(J)<1$, dove $J=D^{-1}(D-A)=I-D^{-1}A$
L'iterazione $k$-esima del metodo di Jacobi richiede di calcolare $\vec{z}^{(k)}=D^{-1}\vec{r}^{(k)}$ risolvendo il sistema diagonale $D\vec{z}^{(k)}=\vec{r}^{(k)}$, il che è facilissimo

$$\begin{cases}
    a_{11}\ z_1^{(k)}&&&&=r_1^{(k)}\\
    &a_{22}\ z_2^{(k)}&&&=r_2^{(k)}\\
    && \ddots          &&   \vdots\\
    &&&a_{nn}\ z_n^{(k)}&=r_n^{(k)}\\
\end{cases} \iff \begin{cases}
    z_1^{(k)}&=\frac{r_1^{(k)}}{a_{11}}\\
    z_2^{(k)}& =\frac{r_2^{(k)}}{a_{22}}\\
    \vdots & \vdots\\
    z_n^{(k)}&=\frac{r_n^{(k)}}{a_{nn}}
\end{cases} \ (\text{ totale n divisioni})$$

## Metodo di Gauss-Seidel
Supponiamo che $A$ abbia elementi diagonali non nulli. 
In tal caso, la parte triangolare inferiore di $A$, cioè la matrice triangolare inferiore $E$ ottenuta ricopiando la parte triangolare inferiore di $A$ (inclusa la diagonale) è invertibile e quindi posso definire il metodo di Gauss-Seidel, cioè il metodo $(m')$ con matrice precondizonatore $M=E$:  
$$\begin{cases}
    \vec{x}^{(0)} \in \Complex^n \\  
    \vec{x}^{(k+1)}=E^{-1}(E-A)\vec{x}^{(k)}+E^{-1}\vec{b}=\vec{x}^{(k)}+\overbrace{E^{-1}\vec{r}^{(k)}}^{\vec{z}^{(k)}},\ k=0,1,2,...
\end{cases}$$

Il metodo di GS è convergente se e solo se $\rho(G)<1$, dove $G=E^{-1}(E-A)=I-E^{-1}A$

L'iterazione $k$-esima di GS richiede il calcolo di $\vec{z}^{(k)}$, risolvendo il sistema triangolare inferiore
$$E\vec{z}^{(k)}=\vec{r}^{(k)}$$ il che è facile:

$$\begin{cases}
a_{11}z_1^{(k)}&&&&=r_1^{(k)}\\
a_{21}z_1^{(k)}+a_{22}z_2^{(k)}&&&&=r_2^{(k)}\\
a_{31}z_1^{(k)}+a_{32}z_2^{(k)}+a_{33}z_3^{(k)}&&&&=r_3^{(k)}\\
\vdots \\
a_{n1}z_1^{(k)}+a_{n2}z_2^{(k)}+...+a_{nn}z_n^{(k)}&&&&=r_n^{(k)}\\
\end{cases}\iff\begin{cases}
    z_1^{(k)}=\frac{r_1^{(k)}}{a_{11}}\\
    z_2^{(k)}=\frac{(r_2^{(k)}-a_{21}z_1^{(k)})}{a_{22}}\\
    z_3^{(k)}=\frac{(r_3^{(k)}-a_{31}z_1^{(k)}-a_{32}z_2^{(k)})}{a_{33}}\\
    \vdots\\
    z_n^{(k)}=\frac{(r_n^{(k)}-a_{n1}z_1^{(k)}-a_{n2}z_2^{(k)}-...-a_{nn-1}z_{n-1}^{(k)})}{a_{nn}}
\end{cases}$$

$\forall i=1,...,n$, il costo del calcolo 
$$z_i^{(k)}=\frac{r_i^{(k)}-a_{i1}z_1^{(k)}-a_{i2}z_2^{(k)}-...-a_{ii-1}z_{i-1}^{(k)}}{a_{ii}}$$

è 
$$1D+(i-1)M+(i-1)A$$ il costo del calcolo di $\vec{z}^{(k)}$ è  
$$\sum_{i=1}^n \ (1D+(i-1)M+(i-1)A)=nD+\sum_{i=1}^n\ (i-1)M + \sum_{i=1}^n \ (i-1)A=nD+\frac{(n-1)n}{2}M+\frac{(n-1)n}{2}A \approx nD+\frac{n}{2}M+\frac{n}{2}A$$

OSS:
Confrontando i precondizonatori $D$ ed $E$ di J e GS si nota quanto segue:
+ l'approssimazione $E\approx A$ è migliore dell'approssimazione $D\approx A$ perché $E-A$ ha più zeri di $D-A$.
  Questo spiega perché nella pratica GS converge più velocemente di J ( cioè $\rho(G)<\rho(J)$).
+ la risoluzione di un sistema lin. di matrice $E$ è più costosa della risoluzione di un sistema lin. di matrice $D \implies$ un'iterazione di GS è più costosa di un'iterazione di J.  
  