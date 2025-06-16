# PDF TEOREMI CALCOLO NUMERICO
## Interpolazione polinomiale   
### <span style="color:red">Teorema</span> 
Siano $(x_0,y_0),(x_1,y_1),...,(x_n,y_n) \in \R^2$ tali che $x_0,x_1,...,x_n$ sono tutti punti distinti. Allora esiste un unico polinomio $p(x) \in \R_n[x]$ tale che $p(x_i)=y_i \ \ \forall i=0,...,n$  

#### <span style="color:lightgreen">Dim (1)</span>

Sia $p(x)=a_0+a_1x+a_2+x^2+...+a_nx^n \in R_n[x]$.  
Questo soddisfa la proprietà: 

$$p(x_i)=y_i \ \ \forall i=0,...,n$$

se e solo se  

$$\begin{cases}
a{_0}+a_1 x{_0} + a_2 x{_0}^2 +...+a_nx{_0}^n = y_0 \\
a_0+a_1 x{_1} + a_2 x{_1}^2 +...+a_nx{_1}^n = y_1 \\
\vdots  \\
a_0+a_1{x_n} + a_2 {x_n}^2 +...+a_n{x_n}^n = y_n \\
\end{cases}$$ 

Dunque se il vettore dei coefficienti $(a_0,a_1,...,a_n)^\top$ soddisfa il sistema lineare:  
$$
\begin{bmatrix}
1 & x_0 & x{_0}^2 & \dots  & x{_0}^n \\
1 & x_1 & x{_1}^2 & \dots  & x{_1}^n \\
1 & x_2 & x{_2}^2 & \dots  & x{_2}^n \\
\vdots & \vdots & \vdots & \vdots & \vdots \\
1 & x_n & x{_n}^2 & \dots  & x{_n}^n
\end{bmatrix}
\
\begin{bmatrix}
a_0 \\
a_1 \\
a_2 \\
\vdots \\
a_n
\end{bmatrix}
\ = \
\begin{bmatrix}
y_0 \\
y_1 \\
y_2 \\
\vdots \\
y_n
\end{bmatrix}  
\ \ (S)
$$

Dimostreremo che la matrice di vandermonde $(V(x_0,...,x_n))$ è invertibile, dimostrando che il suo determinante è uguale a:  
$$\det[V(x_0,x_1,x_2,...,x_n)] = 
\begin{cases}
1, \text{se} \ n=0 \\
\prod^n_{i,j=0 \ , \ j<i} \ (x_i-x_j)=(x_1-x_0)\cdot(x_2-x_1)(x_2-x_0)\cdot(x_3-x_2)(x_3-x_1)(x_3-x_0)  ,  se \ n \geq 1 
\end{cases}  (\star)$$

da cui segue che $\det[V(x_0,x_1,x_2,...,x_n)] \neq 0$ in quanto $x_0,...,x_n$ sono tutti punti distinti.  
Dunque esiste un unica soluzione del sistema $S$, ovvero:  

$$
\begin{bmatrix}
1 & x_0 & x{_0}^2 & \dots  & x{_0}^n \\
1 & x_1 & x{_1}^2 & \dots  & x{_1}^n \\
1 & x_2 & x{_2}^2 & \dots  & x{_2}^n \\
\vdots & \vdots & \vdots & \vdots & \vdots \\
1 & x_n & x{_n}^2 & \dots  & x{_n}^n
\end{bmatrix}^{-1}
\
\begin{bmatrix}
y_0 \\
y_1 \\
y_2 \\
\vdots \\
y_n
\end{bmatrix}
\ = \
\begin{bmatrix}
a_0 \\
a_1 \\
a_2 \\
\vdots \\
a_n
\end{bmatrix}  
\ \ (\alpha)
$$  

dunque esiste un unico polinomio $p(x)\in \R_n[x]$ tale che $p(x_i)=y_i \ \ \forall i=0,...,n$. Questo polinomio ha gli stessi coefficienti dati da $(\alpha)$

Per concluedere la dimostrazione, resta solo da dimostrare $(\star)$ e lo facciamo per $n=3$, tanto la dimostrazione è la stessa per $n$ generico.  

Dim di $(\star)$ per $n=3. \ \forall i =1,...,3$ definisco   
$d_i=det[V(x_0,...,x_i)]$. Il nostro obiettivo è calcolare $d_3$.  

$d_3=   \begin{bmatrix}
1 & x_0 & x{_0}^2 & x{_0}^3 \\
1 & x_1 & x{_1}^2 & x{_1}^3 \\
1 & x_2 & x{_2}^2 & x{_2}^3 \\
1 & x_3 & x{_3}^2 & x{_3}^3
\end{bmatrix}
\ =^{C[4]\rightarrow C[4]-x_3C[3]}=$

$\begin{bmatrix}
1 & x_0 & x{_0}^2 & x{_0}^3-x_0^2x_3 \\
1 & x_1 & x{_1}^2 & x{_1}^3-x_1^2x_3\\
1 & x_2 & x{_2}^2 & x{_2}^3-x_2^2x_3 \\
1 & x_3 & x{_3}^2 & 0
\end{bmatrix} 
\ =^{C[3]\rightarrow C[3]-x_3C[2]}=$$

$\begin{bmatrix}
1 & x_0 & x{_0}^2-x_0x_3 & x{_0}^2(x_0-x_3) \\
1 & x_1 & x{_1}^2-x_1x_3 & x{_1}^2(x_1-x_3)\\
1 & x_2 & x{_2}^2-x_2x_3 & x{_2}^2(x_2-x_3) \\
1 & x_3 & 0 & 0
\end{bmatrix}  
\ =^{C[2]\rightarrow C[2]-x_3C[1]}=$

$\begin{bmatrix}
1 & x_0-x_3 & x_0(x_0-x_3) & x{_0}^2(x_0-x_3) \\
1 & x_1-x_3 & x_1(x_1-x_3) & x{_1}^2(x_1-x_3)\\
1 & x_2-x_3 & x_2(x_2-x_3) & x{_2}^2(x_2-x_3) \\
1 & 0 & 0 & 0
\end{bmatrix}  
$


Sviluppiamo con La Place sull'ultima riga  

$$(-1)^{n=3} \cdot \begin{vmatrix}
x_0-x_3 & x_0(x_0-x_3) & x{_0}^2(x_0-x_3) \\
x_1-x_3 & x_1(x_1-x_3) & x{_1}^2(x_1-x_3)\\
x_2-x_3 & x_2(x_2-x_3) & x{_2}^2(x_2-x_3) \\
\end{vmatrix}   
\ =$$

$$(-1)^{n=3}(x_0-x_3)(x_1-x_3)(x_2-x_3) \cdot \begin{vmatrix}
1 & x_0 & x{_0}^2 \\
1 & x_1 & x{_1}^2\\
1 & x_2 & x{_2}^2 \\
\end{vmatrix} \dots \\  
$$  

$= (x_3-x_0)(x_3-x_1)(x_3-x_2) d_2\\ 
= (x_3-x_0)(x_3-x_1)(x_3-x_2) \cdot (x_2-x_0)(x_2-x_1) d_1 \\
= (x_3-x_0)(x_3-x_1)(x_3-x_2) \cdot (x_2-x_0)(x_2-x_1) \cdot (x_1-x_0)$

Per ricorrenza, anche $d_2$ ammette uno sviluppo analogo a $d_3$ e quindi otteniamo

Abbiamo dimostrato $(\star) \ \  _\square$

#### <span style="color:lightgreen">Dim (2)</span>  
$\forall j=0,...n$ definisco il polinomio:  
$$L_j(x)=\prod^n_{i=0 \ \ i\neq j} \frac{x-x_i}{x_j-x_i}=\frac{(x-x_0)\cdot...\cdot (x-x_{j-1})\cdot(x-x_{j+1}\cdot...\cdot(x-x_n))}{(x_j-x_0)\cdot...\cdot(x_j-x_{j-1})\cdot(x_j-x_{j+1})\cdot...\cdot (x_j-x_n)}$$  

Gli $n+1$ polinomi $L_0(x),L_1(x),...,L_n(x)$ hanno tutti grado $n$ e quindi appartengono a $\R_n[x]$. Mostriamo che ora essi costituiscono una base di $\R_n[x]$ e per fare ciò ci basta dimostrare che essi sono lin. ind. in quanto essi sono in numero $n+1=\dim(\R_n[x])$.  
Per dimostrare che sono lin. ind, osserviamo che: 
$$\forall i,j=0,...,n$$

$$L_j(x_i)=\begin{cases}
    1 \text{ se } i=j\\
    0 \text{ se } i \neq j
\end{cases}$$

> **Caso 1 $(i=j)$:**  
> Se sostituiamo $x_j$ in $L_j(x)$ otteniamo:  
> $$L_j(x_j)=\prod^n_{i=0 \ \ i\neq j}\frac{x_j-x_i}{x_j-x_i}$$
> 
> ma ogni termine della frazione è del tipo:  
> $$\frac{x_j-x_i}{x_j-x_i}$$
>
> Quindi il prodotto di tutti questi termini è $1$
>
> **Caso 2 $(i \neq j)$**
> Ora valutiamo $L_j(x)$ in un altro nodo $x_i$, con $i\neq j$  
> $$L_j(x_i)=\prod^n_{k=0 \ \ k\neq j}\frac{x_i-x_k}{x_j-x_k}$$
>
> Nota che nel **numeratore** compare un termine $x_i-x_i$ (dato che $i$ fa parte dell'indice del prodotto)
> Dunque essendoci nel numeratore un fattore che diventa $0$, tutto il prodotto è uguale a 0:  
> $$L_j(x_i)=0$$

Se 
$$a_0L_0(x)+a_1L_1(x)+...+a_nL_n(x)=0 \ \ \forall x \in \R$$

allora in particolare deve essere che 
$$a_0L_0(x_i)+a_0L_1(x_i)+...+a_nL_n(x_i)=0 \ \ \forall i=0,...,n$$   

cioè 
$$a_i=0 \ \ \forall i=0,...n$$

> Abbiamo la seguente combinazione lineare:
>
> \[
\alpha_0 L_0(x) + \alpha_1 L_1(x) + \dots + \alpha_n L_n(x) = 0, \quad \forall x \in \mathbb{R}.
\]
>
>Questo significa che vogliamo trovare i coefficienti \( \alpha_0, \dots, \alpha_n \) per cui questa combinazione lineare è sempre il polinomio nullo.
>
>Ora valutiamo questa equazione **nei nodi di interpolazione** \( x_0, x_1, \dots, x_n \), ovvero:
>
>\[
\alpha_0 L_0(x_i) + \alpha_1 L_1(x_i) + \dots + \alpha_n L_n(x_i) = 0, \quad \forall i = 0, \dots, n.
\]
>Per l'osservazione vista prima:
\[
L_j(x_i) =
\begin{cases}
1, & \text{se } i = j \\
0, & \text{se } i \neq j.
\end{cases}
\]
> Abbiamo che il seguente sistema lineare:
>
>\[
\begin{bmatrix}
L_0(x_0) & L_1(x_0) & \dots & L_n(x_0) \\
L_0(x_1) & L_1(x_1) & \dots & L_n(x_1) \\
\vdots & \vdots & \ddots & \vdots \\
L_0(x_n) & L_1(x_n) & \dots & L_n(x_n)
\end{bmatrix}
\begin{bmatrix} 
\alpha_0 \\ \alpha_1 \\ \vdots \\ \alpha_n 
\end{bmatrix}
=
\begin{bmatrix} 0 \\ 0 \\ \vdots \\ 0 \end{bmatrix}.
\]
>è uguale a 
\[
\begin{bmatrix}
1 & 0 & \dots & 0 \\
0 & 1 & \dots & 0 \\
\vdots & \vdots & \ddots & \vdots \\
0 & 0 & \dots & 1
\end{bmatrix}.
\begin{bmatrix} 
\alpha_0 \\ \alpha_1 \\ \vdots \\ \alpha_n 
\end{bmatrix}
=
\begin{bmatrix} 0 \\ 0 \\ \vdots \\ 0 \end{bmatrix}.
\]
>
> E sappiamo che l'unica soluzione del sistema è:
>
>\[
\alpha_0 = \alpha_1 = \dots = \alpha_n = 0.
\]

Questo mostra che $L_0(x),L_1(x),...,L_n(x)$ sono linearmente indipendenti e sono una base di $\R_n[x]$  

Definiamo il polinomio:  
$$p(x)=y_0L_0(x)+y_1L_1(x)+...+y_nL_n(x)$$

$p(x)\in \R_n[x]$ e per ogni $i=0,...,n$ si ha 
$$p(x_i)=y_0L_0(x_i)+y_1L_1(x_i)+...+y_nL_n(x_i)=y_i$$
Quindi abbiamo provato l’esistenza di un polinomio in $\R_n[x]$ che nei nodi $x_i$ assume i valori $y_i$.

Supponiamo che $q(x)$ sia un altro polinomio in $\R_n[x]$ che nei nodi $x_i$ assume i valori $y_i$. Poiché $L_0(x),...,L_n(x)$ è una base di $\R_n[x]$, esistono $\beta_0,\beta_1,...,\beta_n \in \R$ tali che 
$$q(x)=\beta_0L_0(x)+\beta_1L_1(x)+...+\beta_nL_n(x)$$.  
Valutando $q(x)$ nei nodi $x_i$ otteniamo che $\forall i=0,..,n$  
$$y_i=q(x_i)=\beta_0L_0(x_i)+...+\beta_nL_n(x_i)=\beta_i$$ 

da cui si ricava che 
$$q(x)=y_0L_0(x)+y_1L_1(x)+...+y_nL_n(x)=p(x)$$
Questo prova che $p(x)$ è l’unico polinomio in $\R_n[x]$ che nei nodi $x_i$ assume i valori $y_i$ $\square$.

#### <span style="color:red">Teorema</span>
Sia $f:[a,b]\rightarrow \R$ una funzione di classe $C^{n+1}[a,b]$ e sia $p(x)$ il polinomio d'interpolazione di $f(x)$ sui punti distinti $x_0,...,x_n\in [a,b]$. Allora $\forall x\in [a,b]$ esiste un punto $\xi=\xi(x) \in [a,b]$ tale che:  
$$|p(x)-f(x)|=|\frac{f^{(n+1)}(\xi)}{(n+1)!}(x-x_0)(x-x_1)...(x-x_n)|$$

#### <span style="color:lightgreen">Dim</span>  
Sia $x \in [a,b]$ fissato.  
+ Caso 1: $x$ coincide con uno dei nodi $x_i$  
    + In tal caso posso scegliere un qualsiasi $\xi \in (a,b)$ e sono sicuro che l'uguaglianza vale perché ottengo $0=0$ (perché il primo membro è 0 perché $p(x_i)=f(x_i)\forall i=0,\dots,n$ per definizione, mentre il 2 membro è $0$ perché uno dei termini è 0).
+ Caso 2: $x$ non coincide con uno dei nodi $x_0,x_1,\dots,x_n$ 
    + Definiamo   
        $$\pi(y)=(y-x_0)(y-x_1)\dots(y-x_n)$$

        $$r(y)=f(y)-p(y)$$
        
        $$z:[a,b]\rightarrow \R, \ z(y)=r(y)-\frac{r(x)}{\pi(x)}\pi(y)$$
    
    Questa funzione $z$ è di classe $C^{(n+1)}$ siccome non è altro che la composizione di funzioni di classe $C^{(n+1)}$.  
    Inoltre si annulla in tutti i nodi $x_i$ e si annulla in $x$. Dunque si annulla in almeno $n+2$ punti di $[a,b]$.
    Dunque per il teorema di Rolle si ha che $z'(y)$ si annulla almeno in $n+1$ punti di $(a,b)$, $z''(y)$ si annulla almeno in $n$ punti di $(a,b)$,...,$z^{(n+1)}$ si annulla in almeno un punto , che chiamo $\xi \in (a,b)$.
    Siccome $p^{(n+1)}(y)$ è nullo (siccome $p(y)$ è un polinomio di grado minore uguale a $n$) e $\pi^{(n+1)}=(n+1)!$ si ha:  
    $$0=z^{(n+1)}(\xi)=r^{(n+1)}(\xi)-\frac{r(x)}{\pi(x)}\pi^{(n+1)}(\xi)=\\ 
    =f^{(n+1)}(\xi)-p^{(n+1)}(\xi)-\frac{f(x)-p(x)}{\pi(x)}(n+1)!=\\
    =f^{(n+1)}(\xi)-\frac{f(x)-p(x)}{(x-x_0)(x-x_1)\cdots(x-x_n)}(n+1)!$$

$\square$

