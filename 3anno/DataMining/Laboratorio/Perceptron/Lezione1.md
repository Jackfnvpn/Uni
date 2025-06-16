#### Algoritmo PERCEPTRON
**Input:** 

$$x\in \R^{n \times d}, \ y\in \set{-1,1}^n, \ w=(0,\dots,0) \in \R^{d+1},\\ n\text{-iter},\ \eta \in (0,1) \text{ (tasso di apprendimento)}$$

**ES:**

$x^{(i)} \cdot w>0$, dunque per il modello appartiene a classe $1$ ma potrebbe essere in realtà $-1$. Dunque sbaglia.

Allora 
$$y_i(x^{(i)} \cdot w)>0 \iff x_i \text{ classificato correttamente}$$

Per un massimo di $n\text{-iter}$ volte
```python
if continua
for i in 1,...,n:
    if y_i( prod_scal(w,x_i) ) <= 0:
        w = w + η y_i x^(i)
        continua=True

fit:
    import numpy as np
    for _ range(n_iter):
        for xi,target in zip(X,y):
            # prodotto scalare
            if target * (np.dot(xi,w[1:])+w[0]) <=0:
                w[1:]+= η * target * xi
                w[0]+= η * target
                # variabile inizializzata a 0
                # all'uscita del 1 for se è 0, faccio break
                errors+=1

```

> $x^{(i)}=i\text{-esimo vettore}\\
> y_i=i\text{-esima componente del vettore }y$

#### Teorema
se $X',y$ è sep. linearmente, allora Perceptron converge su un piano separatore.  

#### DEF
+ $x=(x_1,...,x_d), \vert \vert x \vert \vert=\sqrt{x_1^2+...+x_d^2} \ \text{(Norma)}$

+ $a \cdot b=\vert \vert a \vert \vert \ \vert \vert b \vert \vert  \cos(\beta)$

#### Lemma
$$\text{Dato }H: w_0+w_1x_1+...+w_dx_d=0 \implies w=(w_1,...,w_d) \text{ è perpendicolare a }H$$
##### Dim
Dimostro su $H'$ dove $w_0=0$ parallelo a $H$.  

Preso un punto nel piano $p$ (vettore dall'orgine  a $p$), per cui $p\cdot w=0$.
Sia $\beta$ l'angolo tra $p$ e $w$

Ho che $p\cdot w=\underbrace{\vert \vert p \vert \vert \cdot \vert \vert w \vert \vert }_{>0} \cos(\beta)=0 \iff \cos(\beta)=0\implies \beta=\plusmn90$     
$\square$

#### Lemma
Preso un punto nel piano cartesiano $p$, la distanza $d$ da $p$ al piano $H$ è :
$$\frac{w \cdot p+w_0}{||w||}$$

##### dim
Preso un punto qualsiasi di $H$, che chiamo $u$. Prendo il vettore $p-u$.
Chiamo $\beta$ l'angolo tra $p-u$ e $H$. 
$d=\vert \vert p-u \vert \vert \sin(\beta)$

Sappiamo che $w$ è il vettore perpendicolare ad $H$

Ho

$$(p-u )\cdot w=\vert \vert p-u \vert \vert \cdot \vert \vert w \vert \vert  \cos(90-\beta)=\vert \vert p-u \vert \vert \cdot \vert \vert w \vert \vert  \sin(\beta)$$

Dunque 
$$d=\frac{\cancel{\vert \vert p-u \vert \vert} \ (p-u)\cdot w}{\cancel{\vert \vert p-u \vert \vert} \ \vert \vert w \vert \vert}=\frac{p\cdot w-u\cdot w}{\vert\vert w \vert \vert}=^{\text{Poichè }u \text{ è in }H}\frac{p\cdot w+w_0}{\vert\vert w \vert \vert}$$

$\square$


Definisco il margine (distanza minima tra un punto e classificatore)
#### DEF

$$\gamma=\min_i \set{y_i \frac{w^* x^{(i)}}{\vert\vert w^*\vert\vert}}>0$$

$$U=\max_i \set{||x^{(i)}||}$$

