## Norme Vettoriali

### Definizione
Una funzione $||\cdot||: \Complex \rightarrow \R$ si dice norma vettoriale se soddisfa le seguenti proprietà:
1. $||\bold{x}||\geq 0 \forall \bold{x}\in \Complex^n e ||\bold{x}||\iff\bold{x}=\bold{0} \ \ \ \text{ Positività}$
2. $||\alpha\bold{x}||=\alpha ||\bold{x}||\forall \bold{x}\in \Complex^n, \ \alpha \in \Complex \ \ \ \text{ Omogeneità}$
3. $||\bold{x}+\bold{y}||\leq ||\bold{x}||+||\bold{y}|| \ \ \ \text{ Disuguaglianza triangolare}$

### Definizione

Sia $||\cdot||: \Complex \rightarrow \R$ una norma vettoriale, definiamo la distanza tra due vettori $\bold{x},\bold{y} \in \Complex^n$ come $||\bold{x}-\bold{y}||$

### Definizione
Una funzione $||\cdot||: \Complex^{n\times n} \rightarrow \R$ si dice norma matriciale se soddisfa le seguenti proprietà:
1. $||A||\geq 0 \forall \bold{x}\in \Complex^n e ||A||=0 \iff A=0 \ \ \ \text{ Positività}$
2. $||\alpha A||=\alpha ||A||\forall A \in \Complex^{n\times n}, \ \alpha \in \Complex \ \ \ \text{ Omogeneità}$
3. $||A+B||\leq ||A||+||B|| \ \ \ \text{ Disuguaglianza triangolare}$

### Definizione
Sia $||\cdot||: \Complex^{n\times n} \rightarrow \R$ una norma matriciale, definiamo la distanza tra due matrici $A,B \in \Complex^{n\times n}$ come $||A-B||$

### Definizione

Data una norma vettoriale $||\cdot||: \Complex \rightarrow \R$ e una matrice $A \in \Complex^{n \times n}$ definiamo norma matriciale indotta dalla norma vettoriale $||\cdot||$:
$$||A||=\max_{\bold{x}\neq 0}\frac{||A\bold{x}||}{||\bold{x}||}=\max_{\bold{x}\neq 0}||A \frac{\bold{x}}{||\bold{x}||}||=\max_{||\bold{y}||=1}||A\bold{y}||$$


### <span style="color:red">Teorema</span>

Sia $||\cdot||: \Complex^{n\times n} \rightarrow \R$ una norma matriciale indotta dalla norma vettoriale $||\cdot||$ e siano $A,B \in \Complex^{n \times n}$. Allora valgono le seguenti proprietà:
1. $||I||=1$
2. $||A \bold{x}||\leq ||A|| \ ||\bold{x}|| \ \forall \bold{x}\in \Complex^n$
3. $||A||$ è la più piccola costante $C$ che soddisfa $||A \bold{x}||\leq C||\bold{x}||\ \forall \bold{x} \in C^n$
4. $||AB||\leq ||A||\ ||B|| \ \ \ \text{ Submoltiplicatività}$
5. $\rho(A)\leq ||A||$

#### <span style="color:lightgreen">Dim </span>

1. $$||I||=\max_{||\bold{y}||=1}||I\bold{y}||=\max_{||\bold{y}||=1}||\bold{y}||=1 $$
2. Per ogni $\bold{x}\neq \bold{0}$ si ha
   $$\frac{||A\bold{x}||}{||\bold{x}||}\leq \max_{\bold{y}\neq \bold{0}}\frac{||A\bold{y}||}{||\bold{y}||}=||A|| \implies ||A\bold{x}||\leq ||A||\ ||\bold{x}||$$
3. Presa una qualsiasi costante $C$ che soddisfa $||A\bold{x}||\leq C ||\bold{x}|| \forall \bold{x} \in \Complex^n$ ho
 $$\frac{||A\bold{x}||}{||\bold{x}||}\leq C \ \ \forall \bold{x}\neq \bold{0}\implies ||A||=\max_{\bold{x}\neq \bold{0}}\frac{||A\bold{x}||}{||\bold{x}||}\leq C$$
4.
5. Sia $\lambda$ un autovalore di modulo massimo e sia $\bold{x}\neq \bold
{0}$ un corrispondente autovettore. Dall'equazione $A\bold{x}=\lambda \bold{x}$ otteniamo:
$$||A\bold{x}||=||\lambda \bold{x}||=|\lambda| ||\bold{x}||=\rho(A)||\bold{x}||\implies \rho(A)=\frac{||A\bold{x}||}{||\bold{x}||}\leq\max_{\bold{y}\neq \bold{0}}\frac{||A\bold{y}||}{||\bold{y}||}=||A||$$

### <span style="color:red">Teorema</span>

Sia $A \in C^{n \times n}$. Allora:
$$\lim_{k \rightarrow \infty}A^k=\mathit{O} \iff \rho(A)<1$$

#### <span style="color:lightgreen">Dim </span>

Dimostriamo solo nel caso in cui $A$ è diagonalizzabile. 
In tal caso esistono una matrice $X$ invertibile e una matrice diagonale $\text{diag}(\lambda_1,...,\lambda_n)$ tali che:  
$$A=XDX^{-1}\\A^2=XD^2X^{-1} \\ \vdots\\ A^k=XD^kX^{-1}$$

$(\impliedby)$

Se $\rho(A)<1$ allora dall'equazione $A^k=XD^kX^{-1}$ e dalla proprietà di submoltiplicatività applicata alla norma $||\cdot ||_{\infty}$ si ottiene:
$$||A^k||_{\infty}=||XD^kX^{-1}||_{\infty}\leq||X||_{\infty}||D^k||_{\infty}||X^{-1}||_{\infty}=||X||_{\infty}||(\rho(A))^k||_{\infty}||X^{-1}||_{\infty}\rightarrow 0$$ 

> Questo perche $||D^k||_{\infty}=||\text{diag}(\lambda_1^k,....,\lambda_n^k)||_{\infty}=\max(|\lambda_1^k|,...,|\lambda_n^k|)=\rho(A)^k$ 
>
> In più tende a $0$ perchè $\lim_{k\rightarrow 0}\rho(A)^k=0$, siccome $\rho(A)<1$

Dunque $||A^k||_{\infty} \rightarrow 0$ e $A^k \rightarrow \mathit{O}$.
> Questo perché se cio che  $||A^k||_{\infty} \rightarrow 0$ significa che 
> $\max(||A^k_{[1]}||_1,...,||A^k_{[n]}||_1)\rightarrow 0$, ove ogni $||A^k_{[i]}||_1$ corrisponde alla somma dei moduli delle componenti della matrice $A^k$ riga per riga. Se il massimo tende a 0 significa che ogni componente della matrice tende a 0 e quindi $A^k$ tende al vettore nullo.

$(\implies)$

Se $A^k=\mathit{O}$
Abbiamo $A^k=XD^kX^{-1}$ da questa uguaglianza otteniamo $D^k=XA^kX^{-1}$ e 
$$\rho(A)^k=||D^k||_\infty=||XA^kX^{-1}||_\infty\leq||X||_\infty||A^k||_\infty||X^{-1}||_\infty \rightarrow 0$$

> Siccome $A^k\rightarrow \mathit{O}$ allora $||A^k||_\infty \rightarrow 0$

Dunque $\rho(A)<1$ poiché $\rho(A)^k\rightarrow 0$

