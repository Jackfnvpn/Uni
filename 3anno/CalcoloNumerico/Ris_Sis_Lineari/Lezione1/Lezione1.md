# Metodi iterativi per la risoluzione di sistemi lineari

E' dato un sistema lineare  

$A\vec{x}=\vec{b} \ (\star)$, con $\vec{b} \in \Complex^n \ e \ A \in \Complex^{n\times n}$ invertibile. 
Tale sistema, essendo $A$ invertibile, ha un'unica soluzione $\vec{x}=A^{-1}\vec{b}$. Ci proponiamo di risolvere $(\star)$ con un metodo iterativo, cioè un metodo che a partire da un vettore iniziale $\vec{x}^{(0)}$ scelto dall'utente, costruisce una successione di vettori $\vec{x}^{(0)},\vec{x}^{(1)},\vec{x}^{(2)},...$ Vogliamo che questa successione sia facile da costruire e converga a $\vec{x}$ (componente per componente) qualunque sia il vettore iniziale scelto $\vec{x}^{(0)}$.  

Per risolvere $(\star)$ consideriamo solo metodi iterativi stazionari, cioè metodi della forma:  

$$\vec{x}^{(0)}\in \Complex^n \ \text{ dato } \\ \vec{x}^{(k+1)}=P\vec{x}^{(k)}+\vec{q}, k=0,1,2,... \ (m)$$  

dove $P \in \Complex^{n \times n}$ è una matrice fissata detta **matrice d'iterazione** e $\vec{q}\in \Complex^n$ è un vetore fissato.

<u>**OSS**</u>
Se $\set{\vec{x}^{(k)}}_{k=0,1,2,...}$ è una succesione prodotta dal metodo $(m)$ che converge a un vettore $\vec{x}^{(\infin)}$ allora:  
$$\vec{x}^{(\infin)}=\lim_{k\rightarrow \infin}\vec{x}^{(k+1)}=\lim_{k\rightarrow \infin}P\vec{x}^{(k)}+\vec{q}=P\vec{x}^{(\infin)}+\vec{q}$$
> $(P\vec{x}^{(k)}+\vec{q})_1=\\P_{11}x_1^{(k)}+P{12}x_2^{(k)}+...+P_{1n}x_n^{(k)}+\vec{q_1} \rightarrow P_{11}x_1^{(\infin)}+P{12}x_2^{(\infin)}+...+P_{1n}x_n^{(\infin)}+q_1=(P\vec{x}^{(\infin)})+q)_1$

Quindi $\vec{x}^{(\infin)}$ deve soddisfare l'equazione $\vec{x}^{(\infin)}=P\vec{x}^{(\infin)}+\vec{q}$.  

<u>**Conclusione**</u>: Se la soluzione $\vec{x}$ del sistema $(\star)$ non soddisfa l'equazione $\vec{x}=P\vec{x}+\vec{q}$ allora non c'è speranza che una successione $\set{\vec{x}^{(k)}}_{k=0,1,2,...}$ prodotta dal metodo $(m)$ converga a $\vec{x}$

***DEF (Consistenza di un metodo iterativo)***
Il metodo iterativo $(m)$ si dice consistente con il sistema $(\star)$ se la soluzione $\vec{x}$ di $(\star)$ soddisfa l'equazione $\vec{x}=P\vec{x}+\vec{q}$.

***DEF (Convergenza di un metodo iterativo)***
Il metodo iterativo $(m)$ per risolvere il sistema $(\star)$ si dice convergente se **per ogni** scelta del vettore iniziale $\vec{x}^{(0)}$ la successione $\set{\vec{x}^{(k)}}_{k=0,1,2,...}$ generata dal metodo a partire da $\vec{x}^{(0)}$ converge alla soluzione $\vec{x}$ di $(\star)$.  

#### Teorema (CNS di convergenza)
Supponiamo che il metodo $(m)$ sia consistente con il sistema $(\star)$.
Allora esso è convergente se e solo se $\rho(P)<1$

##### Dim $(\impliedby)$

Dobbiamo dimostrare che per ogni scelta $\vec{x}^{(0)}$, la successione $\set{\vec{x}^{(k)}}_{k=0,1,2,...}$ generata dal metodo $(m)$ converge a $\vec{x}$ soluzione di $(\star)$.
Siccome il metodo è consistente per ipotesi vale l'equazione 
$$(\$) \ \ \vec{x}=P\vec{x}+\vec{q}$$Inoltre, per definizione del metodo, vale che 
$$(\$\$)\ \ \vec{x}^{(k+1)}=P\vec{x}^{(k)}+\vec{q}\ \ \forall k =0,1,2,...$$ Sottraendo membro a membro la $(\$\$)\ e \ (\$)$ otteniamo 
$$\underbrace{\vec{x}^{(k+1)}-\vec{x}}_{\text{Errore al passo k+1: }\vec{e}^{(k+1)}}=P\vec{x}^{(k)}+\cancel{\vec{q}}-(P\vec{x}+\cancel{\vec{q}})=P\underbrace{(\vec{x}^{(k)}-\vec{x})}_{\text{ Errore al passo k: } \vec{e}^{(k)}} \ \ \forall k=0,1,2,...$$

$$\vec{e}^{(k+1)}=P\vec{e}^{k}=P^2\vec{e}^{(k-1)}=P^3\vec{e}^{(k-2)}=...=P^{k+1}\vec{e}^{(0)} \ \ \forall k=0,1,2,...\\
\vec{e}^{(k)}=P^k\vec{e}^{(0)}\ \ \forall k =0,1,2,... \ (§)$$

Siccome $\rho(P)<1, P^k \rightarrow 0 \implies$ dalla $(§)$ si deduce che 
$$\vec{e}^{(k)} \rightarrow 0\vec{e}^{(0)}=0 \implies \vec{x}^{(k)}-\vec{x}\rightarrow 0 \implies \vec{x}^{(k)} \rightarrow \vec{x}$$  

$\square$  

#### Corollario (CS di convergenza)  
Supponiamo che il metodo $(m)$ sia consistente con il sistema $(\star)$:  
Se esiste una norma matriciale **indotta** $||\cdot||$ t.c $||P||<1$ allora il metodo $(m)$ è convergente.  

##### Dim 

Poiché $\rho(P)\leq ||P||$, la condizione $||P||<1$, dunque il metodo è convergente per il teorema precedente.

#### Corollario (CN di convergenza)
Supponiamo che il metodo $(m)$ sia consistente al sistema $(\star)$
+ Se $|\text{traccia}(P)|\geq n$ allora il metodo **non** è convergente.  
+ Se $|\det(P)|\geq 1$ allora il metodo **non** è convergente.

Quindi le due condizioni negate sono condizioni necessarie per la convergenza del metodo $(m)$.

##### Dim
+ Supponiamo che $|\text{traccia}(P)|\geq n$ allora esiste almeno un autovalore di $P$ di modulo $\geq 1$.
  Infatti, se tutti gli autovalori $\lambda_1,\lambda_2,...,\lambda_n$ di $P$ fossero di modulo $<1$ allora avremmo:  
  $$|\text{traccia}(P)|=|\lambda_1+\lambda_2+...+\lambda_n|\leq |\lambda_1|+|\lambda_2|+...+|\lambda_n|<n$$ il che è impossibile per l'ipotesi.
  Dunque siccome esiste almeno un autovalore di $P$ il cui modulo $\geq 1$, anche $\rho(P)\geq 1 \implies$ il metodo non è convergente  
+ Supponiamo che $|\det(P)|\geq n$ allora esiste almeno un autovalore di $P$ di modulo $\geq 1$.
  Infatti, se tutti gli autovalori $\lambda_1,\lambda_2,...,\lambda_n$ di $P$ fossero di modulo $<1$ allora avremmo:  
  $$|\det(P)|=|\lambda_1\lambda_2...\lambda_n|\leq |\lambda_1||\lambda_2|...|\lambda_n|<1$$ il che è impossibile per l'ipotesi.
  Dunque siccome esiste almeno un autovalore di $P$ il cui modulo $\geq 1$, anche $\rho(P)\geq 1 \implies$ il metodo non è convergente.

$\square$
Consideriamo il sistema lineare  

$$A\vec{x}=\vec{b}, A=\begin{bmatrix}
2&1\\
1&2\\
\end{bmatrix}, \vec{b}=\begin{bmatrix}
1\\
1\\
\end{bmatrix}$$  

e il metodo iterativo  
$$x^{(0)}\in \Complex^2 \text{ dato}\\   
x^{(k+1)}=(I-A)\vec{x}^{(k)}+\vec{b}, \ \ \ \forall k=0,1,2,...$$
1. Stabilire se il metodo è consistente con il sistema dato
2. Stabilire se il metodo è convergente con il sistema dato
3. Calcolare le prime 5 iterazioni del metodo partendo da $\vec{x}^{(0)}=\begin{bmatrix}
  0\\
  0\\
\end{bmatrix}$ e confrontarle con la soluzione esatta.  

<u>**Soluzione**</u>
1. Il metodo dato è nella forma $(m)$ con $P=I-A$ e $\vec{q}=\vec{b}$.  
   Sostituendo la soluzione $\vec{x}$ del sistema $A\vec{x}$ nell'equazione del metodo al posto di $\vec{x}^{(k)}$ e $\vec{x}^{(k+1)}$ otteniamo  
   $$\vec{x}=(I-A)\vec{x}+\vec{b} \iff \cancel{\vec{x}}=\cancel{\vec{x}}-\vec{x}A=\vec{b} \iff A\vec{x}=\vec{b}$$ Che è un'equazione soddisfatta.  
   Dunque il metodo è consistente con il sistema dato.  
2. La matrice d'iterazione è   
   $$P=I-A=\begin{bmatrix}
  -1&-1\\
  -1&-1\\ 
   \end{bmatrix}$$ Si nota che $|\text{traccia}(P)|=|-2|=2\geq $ dim. del sistema $(n=2)$.  
   Dunque il metodo non converge.  
3. La soluzione esatta $\vec{x}$ del sistema possiamo calcolarla con il metodo di Gauss:  
   $$A\vec{x}=\vec{b}\iff \begin{cases}
   2x_1 +x_2=1\\
   x_1+2x_2=1
   \end{cases}\iff\begin{cases}
    2x_1 +x_2=1\\
    \frac{3}{2}x_2=\frac{1}{2}
   \end{cases} \iff \begin{cases}
    x_1=\frac{1}{3}\\
    x_2=\frac{1}{3}
   \end{cases}\iff \vec{x}=\begin{bmatrix}
    1/3\\
    1/3\\
   \end{bmatrix}$$  
   $$\vec{x}^{(1)}=\begin{bmatrix}
    -1&-1\\
    -1&-1\\
   \end{bmatrix}\begin{bmatrix}
    0\\
    0\\
   \end{bmatrix}+\begin{bmatrix}
    1\\
    1\\
   \end{bmatrix}=\begin{bmatrix*}
    1\\
    1\\
   \end{bmatrix*}$$

   $$\vec{x}^{(2)}=\begin{bmatrix}
    -1&-1\\
    -1&-1\\
   \end{bmatrix}\begin{bmatrix}
    1\\
    1\\
   \end{bmatrix}+\begin{bmatrix}
    1\\
    1\\
   \end{bmatrix}=\begin{bmatrix*}
    -1\\
    -1\\
   \end{bmatrix*}$$

   $$\vec{x}^{(3)}=\begin{bmatrix}
    -1&-1\\
    -1&-1\\
   \end{bmatrix}\begin{bmatrix}
    -1\\
    -1\\
   \end{bmatrix}+\begin{bmatrix}
    1\\
    1\\
   \end{bmatrix}=\begin{bmatrix*}
    3\\
    3\\
   \end{bmatrix*}$$

   $$\vec{x}^{(4)}=\begin{bmatrix}
    -1&-1\\
    -1&-1\\
   \end{bmatrix}\begin{bmatrix}
    3\\
    3\\
   \end{bmatrix}+\begin{bmatrix}
    1\\
    1\\
   \end{bmatrix}=\begin{bmatrix*}
    -5\\
    -5\\
   \end{bmatrix*}$$

   $$\vec{x}^{(5)}=\begin{bmatrix}
    -1&-1\\
    -1&-1\\
   \end{bmatrix}\begin{bmatrix}
    -5\\
    -5\\
   \end{bmatrix}+\begin{bmatrix}
    1\\
    1\\
   \end{bmatrix}=\begin{bmatrix*}
    11\\
    11\\
   \end{bmatrix*}$$  

<u>**ESEMPIO**</u>
Consideriamo il sistema lineare  
$$A\vec{x}=\vec{b}, \ A=\begin{bmatrix}
  2&1\\
  1&2\\
\end{bmatrix}, \vec{b}=\begin{bmatrix}
  1\\
  1\\
\end{bmatrix}$$ e il metodo iterativo  
$$\vec{x}^{(0)} \in \Complex^n \text{ dato}\\
\vec{x}^{(k+1)}=(I-\frac{1}{2}A)+\frac{1}{2}\vec{b}, \ \ \forall k=0,1,2,...$$

1. Stabilire se il metodo è consistente con il sistema dato
2. Stabilire se il metodo è convergente
3. Calcolare le prime 5 iterazioni del metodo partendo da $\vec{x}^{(0)}=\begin{bmatrix}
  0\\
  0\\
\end{bmatrix}$ e confrontarle con la soluzione esatta.    

<u>**Soluzione**</u>
1. Il metodo è della forma $(m)$ con matrice d'iterazione
$$P=I-\frac{1}{2}A=\begin{bmatrix}
 0&-\frac{1}{2} \\
 -\frac{1}{2}&0
\end{bmatrix}\ e \ \vec{q}=\frac{1}{2}\vec{b}=\begin{bmatrix}
1/2\\
1/2\\
\end{bmatrix}$$  
Sostituisco la soluzione $\vec{x}$ del sistema nell'equazione del metodo al posto di $\vec{x}^{(k)}$ e $\vec{x}^{(k+1)}$ e ottengo 
$$\vec{x}=(I-\frac{1}{2}A)\vec{x}+\frac{1}{2}\vec{b} \iff \cancel{\vec{x}}=\cancel{\vec{x}}-\frac{1}{2}A\vec{x}+\frac{1}{2}\vec{b} \iff \frac{1}{2}A\vec{x}=\frac{1}{2}\vec{b}$$ che è un'equazione soddisfatta $\implies$ il metodo è consistente con il sistema dato. 
2. $||P||_\infin=\frac{1}{2}<1 \implies$ il metodo è convergente per il corollario.
3. La soluzione $\vec{x}$ del sistema è $\vec{x}=\begin{bmatrix}
1/3\\
1/3
\end{bmatrix}$.  
$$\vec{x}^{(1)}=\begin{bmatrix}
  0 & -\frac{1}{2}\\
  -\frac{1}{2} & 0
\end{bmatrix} \begin{bmatrix}
  0 \\
  0 \\
\end{bmatrix}+\begin{bmatrix}
  1/2\\
  1/2\\
\end{bmatrix}=\begin{bmatrix}
  1/2\\
  1/2\\
\end{bmatrix}$$

$$\vec{x}^{(2)}=\begin{bmatrix}
  0 & -\frac{1}{2}\\
  -\frac{1}{2} & 0
\end{bmatrix} \begin{bmatrix}
  1/2 \\
  1/2 \\
\end{bmatrix}+\begin{bmatrix}
  1/2\\
  1/2\\
\end{bmatrix}=\begin{bmatrix}
  1/4\\
  1/4\\
\end{bmatrix}$$  

$$\vec{x}^{(3)}=\begin{bmatrix}
  0 & -\frac{1}{2}\\
  -\frac{1}{2} & 0
\end{bmatrix} \begin{bmatrix}
  1/4 \\
  1/4 \\
\end{bmatrix}+\begin{bmatrix}
  1/2\\
  1/2\\
\end{bmatrix}=\begin{bmatrix}
  3/8\\
  3/8\\
\end{bmatrix}$$  

$$\vec{x}^{(4)}=\begin{bmatrix}
  0 & -\frac{1}{2}\\
  -\frac{1}{2} & 0
\end{bmatrix} \begin{bmatrix}
  3/8 \\
  3/8 \\
\end{bmatrix}+\begin{bmatrix}
  1/2\\
  1/2\\
\end{bmatrix}=\begin{bmatrix}
  5/16\\
  5/16\\
\end{bmatrix}$$  

$$\vec{x}^{(5)}=\begin{bmatrix}
  0 & -\frac{1}{2}\\
  -\frac{1}{2} & 0
\end{bmatrix} \begin{bmatrix}
  5/16 \\
  5/16 \\
\end{bmatrix}+\begin{bmatrix}
  1/2\\
  1/2\\
\end{bmatrix}=\begin{bmatrix}
  11/32\\
  11/32\\
\end{bmatrix}$$