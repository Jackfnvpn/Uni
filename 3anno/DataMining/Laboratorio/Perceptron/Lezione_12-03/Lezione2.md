#### Teorema

L'algoritmo Perceptron converge

##### dim
Poiché l'istanza è lin. separabile allora esiste $w^*$ piano separatore.

+ $w^{(0)}=(0,...,0)$ (pesi iniziali)
+ $w^{(k)}=w^{(k-1)}+\eta y^{(i)}x^{(i)}$ (pesi alla $k°$ iterazione) 


$$y^{(i)}w^{(k-1)}\cdot x^{i}\leq 0 < \gamma \text{ (Margine)} \leq y^{(i)}\frac{w^* x^{(i)}}{||w^*||}$$

> La prima disuguaglianza perché abbiamo aggiornato i pesi altrimenti era maggiore stretto.

Vogliamo dimostrare che durante la fase di addestramento l'angolo $\beta$ diventa sempre più piccolo.

Da $w^* \cdot w^{(k)}=||w^*||\cdot||w^{(k)}||\cos(\beta)$ si ha:

$$\cos(\beta)=\frac{w^* \cdot w^{(k)}}{||w^*||\ ||w^{(k)}||}=\frac{w^*\cdot w^{(k)}}{}$$

Ho che

1

$$\frac{w^* \cdot w^{(k)}}{||w^*||}=\frac{w^*(w^{(k-1)}+\eta y^{(i)}x^{(i)})}{||w^*||}=\frac{w^* w^{(k-1)}}{||w^*||}+\frac{\eta y^{i}x^{(i)} \cdot w^*}{||w^*||}$$

$$\geq \frac{w^* w^{(k-1)}}{||w^*||}+\eta \gamma \geq\frac{w^* w^{(k-2)}}{||w^*||}+2\eta \gamma\geq...\geq \bold{0}+k \eta \gamma$$

> Dopo $k$ passi si ottiene $w^{(0)}=(0,0,...,0)$

Dunque 
$$\frac{w^* \cdot w^{(k)}}{||w^*||} \geq k\eta \gamma$$

2 

$$||w^{(k)}||^2=||w^{(k-1)}+\eta y^{(i)}x^{(i)}||^2=||w^{(k-1)}||^2+\overbrace{||\eta y^{(i)}x^{(i)}||^2}^{\leq (U\eta)^2} + \overbrace{2\eta y^{(i)}w^{(k-1)}\cdot x^{(i)}}^{\leq 0}$$

>$$||a+b||^2=(a_1+b_1)^2+(a_2+b_2)^2+...+(a_n+b_n)^2=$$
>
>$$a_1^2+...+a_n^2+b_1^2,...,b_n^2+2a_1b_1+2a_2b_2+...+2a_nb_n=||a||^2+||b||^2+2ab$$

> quel $\leq (U\eta)^2$ è dovuto al fatto che
> 
> $$||\eta y^{(i)}\bold{x}^{(i)}||^2=||\eta \bold{x}^{(i)}||^2 \leq (U\eta)^2$$
> 
> siccome $U=\max_i\set{||\bold{x}^{(i)}||}$


$$\leq ||w^{(k-1)}||^2+(\eta U)^2 \leq ||w^{(k-2)}||^2+2(\eta U)^2\leq...\leq ||w^{(0)}||^2+k(\eta U)^2\leq k(\eta U)^2$$



Sappiamo che 

$$1 \geq \cos(\beta)\geq \frac{k \eta \gamma}{\sqrt{k (\eta U)^2}}=\underbrace{\frac{\gamma \sqrt{k}}{U}}_{\rightarrow 1}>0 \implies \beta \rightarrow 0$$

$\implies \cos(\beta)\rightarrow 1 \iff \beta \rightarrow 0$ 

Inoltre $k \leq \big(\frac{U}{\gamma}\big)^2$  numero massimo di iterazioni che impiega Perceptron a convergere


## ADAptive LInear NEuron (ADALINE)

Qui l'idea chiave è di definire e minimizzare una funzione di costo.
La differenza sostanziale qui rispetto al Perceptron è la funzione di attivazione. Infatti ne viene usata una lineare piuttosto che una funzione di passo unitario.

### Funzione di attivazione

$$\phi(w\cdot x)=w\cdot x$$

Preso un esempio $x^{(i)}$ e calcoliamo 
$$(x^{(i)}\cdot w-y^{(i)})^2 \text{ (Scarto quadratico) }$$ 

errore compiuto dal modello su quella specifica istanza.

#### DEF SSE (somma degli scarti quadratici)

$$J(w)=\frac{1}{2}\sum_{i=1}^n((x^{(i)}\cdot w-y^{(i)})^2)$$

La funzione $J$ è la nostra funzione di costo per apprendere i pesi. L'obiettivo è minimizzare la funzione $J$.

Questa funzione va bene poiché:
1. Differenziabile
2. Convessa, dunque esiste un minimo relativo che corrisponde al minimo assoluto.

$\nabla J$ è il vettore di massima pendenza$=(\frac{dJ}{dw_1},...,\frac{dJ}{w_d})$
### Discesa del gradiente

Algoritmo usato per trovare il minimo di $J$.

$w \leftarrow w-\eta \nabla J(w)$ 

$$\frac{dJ}{dw_j}\frac{1}{2}\sum_{i=1}^n(x^{(i)}\cdot w-y^{(i)})^2=\frac{1}{\cancel{2}}\sum_{i=1}^n\cancel{2}(x^{(i)}\cdot w-y^{(i)}) \underbrace{\frac{d}{dw_j}(x^{(i)}\cdot w-y^{(i)})}_{{x_j}^{(i)}}$$


$$=\sum_{i=1}^n (x^{(i)}\cdot w-y^{(i)})x_j^{(i)}=-\sum_{i=1}^n (y^{(i)}-x^{(i)}\cdot w)x_j^{(i)}$$

> $(y^{(i)}-x^{(i)}\cdot w)$ è l'errore $i$-esimo mentre $x_j^{(i)}$ è la componente $j$-esima dell'$i$-esima riga

Dunque 
$$\nabla J=X^T\cdot \bold{err}$$

e la regola di aggiornamento diventa:
$$w \leftarrow w - \eta X^T \cdot \bold{err}$$

Ci fermiamo nel caso dopo un certo numero di passi.