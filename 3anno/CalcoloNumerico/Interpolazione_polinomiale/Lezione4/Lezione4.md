## Forma di newton del polinomio d'interpolazione  
##### Definizione (differenze divise)  
Sia $f:[a,b]\rightarrow \R$ una funzione.  
+ Se $y$ è un punto in $[a,b]$ si definisce differenza divisa di $f(x)$ relativa a $y$ il numero $f[y]=f(y)$.  
+ Se $y_1,\dots,y_k \in [a,b]$ sono $k\geq 2$ punti distinti, si definisce differenza divisa di $f(x)$ relativa a $y_1,\dots,y_k$ il numero $f[y_1,\dots,y_k]=\frac{f[y_1,\dots,y_{k-2},y_k]-f[y_1,\dots,y_{k-1}]}{y_k-y_{k-1}}$
Esempio $k=2$,  
$f[y_1,y_2]=\frac{f[y_1]-f[y_2]}{y_2-y_1}=\frac{f(y_1)-f(y_2)}{y_2-y_1} \ \ (\text{ rapporto incrementale di } f(x) \text{ relativo ai punti } y_1,y_2)$  

#### <span style="color:red"> Teorema </span>  

Sia $f:[a,b]\rightarrow \R$ una funzione e siano $x_0,x_1,\dots,x_n \in [a,b]$ punti distinti. Allora il polinomio d'interpolazione di $f(x)$ sui nodi $x_0,x_1,\dots,x_n$ è   
$p(x)=f[x_0]+f[x_0,x_1](x-x_0)+f[x_0,x_1,x_2](x-x_0)(x-x_1)+\dots+f[x_0,x_1,\dots,x_n](x-x_0)(x-x_1)\dots(x-x_{n-1})$  $(\text{ Forma di Newton di }p(x)) \ (N)$

#### <span style="color:blue"> Corollario </span>  
Sia $f:[a,b]\rightarrow\R$ una funzione e siano $x_0,x_1,\dots,x_n \in [a,b]$ punti distinti. Allora $f[x_0,x_1,\dots,x_n]$ non cambia se vengono permutati i suoi $n+1$ nodi, cioè:  
$f[x_0,\dots,x_n]=f[x_{\sigma(0)},\dots,x_{\sigma(n)}] \ \forall$ permutazione $\sigma$ di $\set{0,\dots,n}$.  

> $n=2, \  \set{0,1,2}, \ x_0,x_1,x_2\\
\sigma=[2,0,1] \\ 
\sigma(0)=2 \\
\sigma(1)=0\\  
\sigma(2)=1\\
f[x_0,x_1,x_2]=f[x_2,x_0,x_1]$  

##### <span style="color:yellowgreen"> dim </span>  
 
Sia $\sigma$ una fissata (generica) permutazione di $\set{0,\dots,n}$. Applicando il Teorema precedente con i nodi $x_0,x_1,\dots,x_n$ si deduce che il polin. d'interpolazione $p(x)$ di $f(x)$ sui nodi $x_0,x_1,\dots,x_n$ è dato da $(N)$.  
Applicando il Teo. con i nodi $x_{\sigma(0)},x_{\sigma(1)},\dots,x_{\sigma(n)}$ si deduce che il polinomio d'interpolazione $p(x)$ di $f(x)$ sui nodi $x_{\sigma(0)},x_{\sigma(1)},\dots,x_{\sigma(n)}$ è dato da $(N_\sigma)$  

$p_\sigma(x)=f[x_{\sigma(0)}]+f[x_{\sigma(0)},x_{\sigma(1)}](x-x_{\sigma(0)})(x-x_{\sigma(1)})+\dots+f[x_{\sigma(0)},x_{\sigma(1)},\dots,x_{\sigma(n)}](x-x_{\sigma(0)})(x-x_{\sigma(1)})\dots(x-x_{\sigma(n-1)})$  

##### <u> OSS 1 </u>  
Il polinomio d'interpolazione $p(x)$ non dipende dall'ordinamento dei nodi e quindi $p(x)=p_{\sigma}(x)$.  

##### <u> OSS 2 </u>    
Il coefficiente direttore di $p(x)$ è $f[x_0,x_1,\dots,x_n]=$ il numero che moltiplica $x^n$ in $p(x)$  

Il coefficiente direttore di $p(x)$ è $f[x_0,x_1,\dots,x_n]=$ il numero che moltiplica $x^n$ in $p_{\sigma}(x)$

Poiché $p(x)=p_\sigma(x)$ per l'oss.1, deve essere $f[x_0,x_1,\dots,x_n]=f[x_{\sigma(0)},x_{\sigma(1)},\dots,x_{\sigma(n)}]$  

Esempio:  
Scrivere in forma canonica e in forma di Newton il polinomio d'interpolazione $p(x)$ di $f(x)=\sqrt{x}$, sui nodi $x_0=0,x_1=0.16,x_2=0.64,x_3=1$  

Sol:  
Inizia dalla forma di Newton (da cui poi otteremo la forma canonica semplicemente sviluppando i calcoli). In base al teo. precedente $p(x)$ è dato in forma di Newton dalla formula:  

$$p(x)=f[x_0]+f[x_0,x_1](x-x_0)+f[x_0,x_1,x_2](x-x_0)(x-x_1)+f[x_0,x_1,x_2,x_3](x-x_0)(x-x_1)(x-x_2) \ \ \nu$$  
L'unica cosa da fare è calcolare le diff. divise. A tal fine si usa la <u> tabella delle differenze divise </u>  

<span style="color:red">$f[x_0]$ </span>  
$f[x_1]$ <span style="color:red"> $f[x_0,x_1]$ </span> 
$f[x_2] \ f[x_0,x_2]$ <span style="color:red"> $f[x_0,x_1,x_2]$ </span>
$f[x_3] \ f[x_0,x_3] \ f[x_0,x_1,x_3]$ <span style="color:red"> $f[x_0,x_1,x_2,x_3]$ </span>  

<span style="color:red"> $f[x_0]=f(x_0)=0$ </span>
$f[x_1]=f(x_1)=0.4\\
f[x_2]=f(x_2)=0.8 \\
f[x_3]=f(x_3)=1\\ $  

<span style="color:red">$f[x_0,x_1]=\frac{f[x_1]-f[x_0]}{x_1-x_0}=\frac{0.4}{0.16}=\frac{5}{2}$</span>

$f[x_0,x_2]=\frac{f[x_2]-f[x_0]}{x_2-x_0}=\frac{0.8}{0.64}=\frac{5}{4}$  

$f[x_0,x_3]=\frac{f[x_3]-f[x_0]}{x_3-x_0}=1$  

<span style="color:red"> $f[x_0,x_1,x_2]=\frac{f[x_0,x_2]-f[x_0,x_1]}{x_2-x_1}=\frac{-\frac{5}{4}}{0.64-0.16}=-\frac{125}{41}$ </span>  

$f[x_0,x_1,x_3]=\frac{f[x_0,x_3]-f[x_0,x_1]}{x_3-x_1}=\frac{1-\frac{5}{2}}{1-0.16}=-\frac{25}{14}$  

<span style="color:red"> $f[x_0,x_1,x_2,x_3]=\frac{f[x_0,x_1,x_3]-f[x_0,x_1,x_2]}{x_3-x_2}=\frac{-\frac{-25}{14}+\frac{125}{48}}{1-0.64}=\frac{6875}{3024}$ </span>  

Sostituiamo in $(\nu)$ gli elementi trovati rossi e così otteniamo la forma di Newton di $p(x)$  

$p(x)=0+\frac{5}{2}x-\frac{125}{48}x(x-0.16)+\frac{6875}{3024}x(x-0.16)(x-0.64)$  

Sviluppando i calcoli, possiamo riscrivere $p(x)$ in forma canonica   
$p(x)=\frac{6875}{3024}x^3-\frac{13375}{3024}x^2+\frac{2381}{756}x$  

<u>Oss</u>  
Supponiamo di avere dei dati $(x_0,y_0),(x_1,y_1),\dots,(x_n,y_n) \in \R^2$ con $x_0,x_1,\dots,x_n$ distinti.  
I numeri $y_0,y_1,\dots,y_n$ possono **sempre** essere interpretati come i valori in $x_0,x_1,\dots,x_n$ di una qualunque funzione $f:[a,b] \rightarrow \R$ definita su un qualche intervallo $[a,b]$ che contiene i nodi $x_0,\dots,x_n$.  
Dunque ha perfettamente senso parlare di forma di Newton del polinomio d'interpolazione dei dati $(x_0,y_0),\dots,(x_n,y_n)$: basta infatti immaginarsi una qualche $f(x)$ tale che $f(x_i)=y_i,\ \forall i=0,\dots,n$ e il gioco è fatto.  

Ex:  
Scrivere in forma canonica e in forma di Newton, il polinomio d'interpolazione di $f(x)=cos(\frac{\pi}{2}x)\log_2(x)$, sui nodi $x_0=1,x_1=2,x_2=4,x_3=8$  

Ex:  
Scrivere in forma canonica, di Lagrange e di Newton il polinomio d'interpolazione dei valori $y_0=0,y_1=3,y_2=-3$ sui nodi $x_0=0,x_1=1,x_2=2$.  

#### Algoritmo di valutazione del polinomio d'interpolazione in un punto  
+ Algoritmo basato sulla forma di Newton 
+ Algoritmo Efficiente dal punto di vista computazionale  

Sia $f:[a,b]\rightarrow\R$, siano $x_0,\dots,x_n\in[a,b]$ e sia $t\in \R$ si vuole costruire un algoritmo per calcolare $p(t)$, dove $p(x)$ è il polinomio d'interpolazione di $f(x)$ sui nodi $x_0,\dots,x_n$  
Per semplicità, descriviamo l'algoritmo nel caso $n=3$, cosicchè per la $(N)$ è  

$$p(x)=f[x_0]+f[x_0,x_1](x-x_0)+f[x_0,x_1,x_2](x-x_0)(x-x_1)+f[x_0,x_1,x_2,x_3](x-x_0)(x-x_1)(x-x_2)$$  

L'algoritmo è composto di due parti.  

$(1.)($indipendente dal punto $t$ in cui devo valutare $p(x))$

Consiste nel calcolo delle differenze divise, calcolo che viene effettuato con la tabella delle differenze divise come nell'esempio precedente.  

$(2.)$
Una volta calcolate le differenze divise per calcolare $p(t)$ si usa un metodo noto come algoritmo di Ruffini-Horner: si scrive $p(t)$ nella forma   

$p(t)=\underbrace{f[x_0]+(t-x_0)(\underbrace{f[x_0,x_1]+(t-x_1)(\underbrace{f[x_0,x_1,x_2]+\underbrace{f[x_0,x_1,x_2,x_3]}_{h_3}(t-x_2)}_{h_2})}_{h_1})}_{h_0}$  

Si pone:  


$\begin{rcases}
h_3=f[x_0,x_1,x_2,x_3]\\
h_2=f[x_0,x_1,x_2]+(t-x_2)h_3\\
h_1=f[x_0,x_1]+(t-x_1)h_2\\
h_0=f[x_0]+(t-x_0)h_1=p(t)
\end{rcases}  
$ Per $i=2,1,0 \ \  h_i=f[x_0,\dots,x_i](t-x_i)h_{i+1}$ 

Valutiamo il costo computazionale dell'algoritmo.  

$(1.)$  
Si devono calcolare $6=\frac{n(n+1)}{2}$ elementi della tabella delle differenze divise (cioè tutti gli elementi meno quelli della prima colonna che sono noti)
Il numero $\frac{n(n+1)}{2}$ di elementi da calcolare, coincide con il numero di elementi  della parte triangolare inferiore (inclusa la diagonale) di una matrice $n\times n$, ossia $\frac{n^2-n}{2}+n=\frac{n(n+1)}{2}=1+2+\dots+n$.  

Per calcolare ognuno di questi $\frac{n(n+1)}{2}$ elementi, occorrono 2 sottrazioni e una divisione, per cui in totale:  
+ $n(n+1)$ sottrazioni 
<br> 
+ $\frac{n(n+1)}{2}$ divisioni  

$n(n+1)A+\frac{n(n+1)}{2}D$

$(2.)$   
Si devono calcolare $h_2,h_1,h_0 \ ( \ h_3=h_n$ non deve essere calcolato perché è una differenza divisa già calcolata nella parte 1 $)$

Per il calcolo di ciascun $h_i , \forall i=n-1,\dots,0$, sono richieste $1$ sottrazione, $1$ divisione e $1$ moltiplicazione.  

In totale:  
+ $n$ sottrazioni 
+ $n$ addizioni
+ $n$ moltiplicazioni  

$2nA+nM$  

Dunque il costo totale $c(n)=(n^2+3n)A+nM+(\frac{n^2}{2}+\frac{n}{2})D \approx n^2A+\frac{n^2}{2}D$