## Norme matriciali
Si vuole introdurre un concetto di distanza sullo spazio delle matrici per misurare la "vicinanza" tra due matrici $A,B \in \Complex^{n \times n}$  

***PRIMA IDEA:*** interpreto una matrice $A \in \Complex^{n \times n}$ come un vettore di $n^2$ componenti e utilizzo come distanza una delle norme vettoriali già viste.  
***PROBLEMA:*** questo procedimento spesso conduce a norme che "non si comportano bene" rispetto al prodotto di matrici.  

***DEF***
Una funzione $||\cdot||:\Complex^{n \times n} \rightarrow \R$ si dice norma matriciale se soddisfa le seguenti proprietà:
+ $||A||\geq 0 \ \ \forall A\in \Complex^{n \times n}\ e \ ||A||=0$ se e solo se $A=0 \ [\text{positività}]$
+ $||\alpha A||=|\alpha|\cdot ||A|| \ \forall A \in \Complex^{n \times n} \ \forall \alpha \in \Complex$
+ $||A+B||\leq ||A||+||B|| \ \forall A,B \in \Complex^{n \times n} \ [\text{disug. triangolare}]$

>$||AB||\leq ||A||\cdot||B|| \ \forall A,B \in \Complex^{n \times n} \ [\text{submoltiplicatività}]$

Data una norma matriciale $||\cdot||:\Complex^{n \times n} \rightarrow \R$, la distanza tra due matrici $A,B\in \Complex^{n \times n}$ è $||A-B||$

ES: Prendiamo la norma matriciale data dall'analoga della norma $\infty$ dei vettori: data $A \in C^{n \times n}$, si immagina $A$ come se fosse un vettore di $n^2$ elementi e si definisce la sua $|A|_\infty$ come la norma $\infty$ di questo vettore di $n^2$ componenti.

$$|A|_\infty=\max_{i,j=1,...,n}|a_{ij}|$$

La $|\cdot|_\infty$ non si comporta bene rispetto al prodotto di matrici:  
$$A=\begin{bmatrix}
1&1\\
0&1\\
\end{bmatrix},\ B=\begin{bmatrix}
1&0\\
1&1\\
\end{bmatrix}, \ AB=\begin{bmatrix}
2&1\\
1&1\\
\end{bmatrix}$$

$|A|_\infty=1\\
|B|_\infty=1\\
|AB|_\infty=2 > |A|_\infty|B|_\infty \implies |\cdot |$ non è submolitiplicativa

## Norme matriciali indotte  
***DEF***
Data una norma vettoriale $||\cdot||:\Complex^{n \times n} \rightarrow \R$ e una matrice $A \in \Complex^{n \times n}$, definiamo il numero:  

$$||A||=\max_{\vec{x}\neq 0}\frac{||A\vec{x}||}{\vec{x}}=\max_{\vec{x}\neq 0}\frac{1}{||\vec{x}||}||A\vec{x}||=\max_{\vec{x}\neq 0}||\frac{1}{||\vec{x}||}A\vec{x}||=\max_{\vec{x}\neq 0}||A\frac{\vec{x}}{||\vec{x}||}||=$$

> $B(\alpha\gamma)=\alpha B \gamma \ \forall \alpha \in \Complex \ B \in \Complex^{n \times n}\ \gamma \in \Complex^n$

$$=(\vec{y}=||\frac{\vec{x}}{||\vec{x}||}||=\frac{1}{||\vec{x}||}\cdot ||x||=1) \ \max_{\vec{y}\in \Complex^n ,\  ||\vec{y}||=1}||A\vec{y}||$$

Si può dimostrare che $||\cdot||:\Complex^{n \times n} \rightarrow \R$ che ad ogni $\Complex^{n \times n}$ associa il numero $||A||$ sopra definito è una norma matriciale che si chiama <span style="color:purple">norma matriciale indotta</span> della norma vettoriale  
<span style="color:red">[Oss. la norma matriciale indotta dalla norma vettoriale $||\cdot||$ viene <u>sempre</u> denotata con lo stesso simbolo ||\cdot||]</span>

#### Teorema  
Sia $||\cdot||:\Complex^{n \times n} \rightarrow \R$ una norma matriciale indotta dalla norma vettoriale $||\cdot||$ e siano $A,B\in \Complex^{n \times n}$:

+ $||I||=1$
+ $||A\vec{x}||\leq ||A||\cdot||\vec{x}|| \ \forall \vec{x}\in \Complex^n$
+ $||A||$ è la più piccola costante $c$ che soddisfa $||A\vec{x}||\leq C||\vec{x}|| \ \forall \vec{x} \in \Complex^n$
+ $||AB||\leq ||A||\cdot ||B||$
+ $\rho(A)\leq ||A||$

##### <span style="color:lightgreen">Dim</span>
1. $||I||=\max_{||\vec{x}||=1}||I\vec{x}||=\max_{||\vec{x}||=1}||\vec{x}||=1$
2. $\forall \vec{x}\neq \vec{0}, \ \frac{||A\vec{x}||}{||\vec{x}||}\leq \max_{\vec{y}\neq \vec{0}}\frac{||A\vec{y}||}{||\vec{y}||}:= ||A||\implies ||A\vec{x}||\leq ||A||\cdot ||\vec{x}||$
   Per verifica diretta la disuguaglianza $||A\vec{x}||\leq ||A||\cdot ||\vec{x}||$ vale anche per $\vec{x}\neq \vec{0}$ (in tal caso la disuguaglianza diventa $\vec{0}\leq \vec{0}$).  
3. Prendiamo una qualsiasi costante $C$ che soddisfa $||A\vec{x}||\leq C||\vec{x}||\ \forall \vec{x}\in \Complex^n$  
   Mostriamo che $C\geq ||A||$. Siccome $||A\vec{x}||\leq C ||\vec{x}|| \ \forall \vec{x}\in \Complex^n$ allora:  
   $$\frac{||A\vec{x}||}{||\vec{x}||}\leq C \ \forall \vec{x}\neq \vec{0} \implies \underbrace{\max_{\vec{x}\neq \vec{0}}\frac{||A\vec{x}||}{||\vec{x}||}}_{\frac{||A\vec{x_0}||}{||\vec{x_0}||}=||A||}\leq C \ \implies ||A||\leq C \ (\star)$$.

4. $\forall \vec{x} \in \Complex^n$ fissato, $||(AB)\vec{x}||=||A(B\vec{x})||\leq^2 ||A||\cdot ||B\vec{x}||\leq^2||A||\cdot||B||\cdot||\vec{x}||$ 
Poiché $||AB||$ è la più piccola costante $C$ tale che $||AB\vec{x}||\leq C \cdot ||\vec{x}|| \ \forall \vec{x}\in \Complex^n$ (proprietà 3) e siccome la costante $||AB||\cdot ||B||$ è una delle tante $C$ che soddisfano $||AB\vec{x}||\leq C||\vec{x}|| \ \forall \vec{x}\in \Complex^n$ (per $\star$)
si conclude che $||AB||\leq ||A||\cdot ||B||$.
5. Sia $\lambda$ un autovalore di $A$ di modulo massimo $(|\lambda|=\rho(A))$ e sia $\vec{x}\neq \vec{0}$ un suo autovettore:
 $$||A\vec{x}||=||\lambda \vec{x}||=|\lambda|\cdot ||\vec{x}||=\rho(A)||\vec{x}||\implies \underbrace{\frac{||A\vec{x}||}{||\vec{x}||}}_{\leq \max_{\vec{y}\neq \vec{0}}\frac{||A\vec{y}||}{||\vec{y}||}=||A||}=\rho(A)\implies ||A|| \geq \rho(A)$$ $\square$ 

Le norme matriciali indotte più importanti sono:  

$$||A||_1=\max_{\vec{x}\neq\vec{0}}\frac{||A\vec{x}||_1}{||\vec{x}||_1}=\max_{||\vec{x}||_1=1}||A\vec{x}||_1\\ 
||A||_2=\max_{\vec{x}\neq\vec{0}}\frac{||A\vec{x}||_2}{||\vec{x}||_2}=\max_{||\vec{x}||_2=1}||A\vec{x}||_2\\
||A||_\infty=\max_{\vec{x}\neq\vec{0}}\frac{||A\vec{x}||_\infty}{||\vec{x}||_\infin}=\max_{||\vec{x}||_\infty=1}||A\vec{x}||_\infty$$

#### Teorema 
$\forall A\in \Complex^{n \times n}$ valgono queste formule:
+ $||A||_1=\max(||A^{[1]}||_1,||A^{[2]}||_1,...,||A^{[n]}||_1)=\max_{j=1,...,n}\sum_{i=1}^n |a_{ij}|$
  > Notazione: 
  >Se $A \in \Complex^{n \times n}$ denotiamo con $A^{[1]},A^{[2]},...,A^{[n]}$ le colonne di $A$ e con $A_{[1]},A_{[2]},...,A_{[n]}$ le righe di $A$
+ $||A||_2=\sqrt{\rho(A^*A)}$
+ $||A||_\infin=\max(||A_{[1]}||_1,||A_{[2]}||_1,...,||A_{[n]}||_1)=\max_{i=1,...,n}\sum_{j=1}^n |a_{ij}|$

ES: Calcolare le norme $1,2,\infin$:  

$$A=\begin{bmatrix}
2&-2\\
-3&-1\\
\end{bmatrix}$$

SOL:  

$||A||_1=\max(|2|+|-3|,|-2|+|-1|)=\max(5,3)=5\\
||A||_\infin=\max(|2|+|-2|,|-3|+|-1|)=\max(4,4)=4$

$A^*A=\begin{bmatrix}
2&-3\\
-2&-1\\
\end{bmatrix}\begin{bmatrix}
2&-2\\
-3&-1\\
\end{bmatrix}=\begin{bmatrix}
13&-1\\
-1&5\\
\end{bmatrix}$

$C_{A^*A}(\lambda)=\det(\lambda I-A^*A)=\begin{vmatrix}
\lambda-13&1\\
1& \lambda-5
\end{vmatrix}=(\lambda-13)(\lambda-5)-1=\lambda^2-18\lambda+64$

$\lambda_{1,2}=\frac{18\plusmn\sqrt{18^2-4\cdot64}}{2}=9\plusmn\frac{1}{2}\sqrt{68}=9\plusmn\sqrt{17}$

$\rho(A^*A)=\max(|9+\sqrt{17}|,|9-\sqrt{17}|)=9+\sqrt{17}\implies||A||_2=\sqrt{\rho(A^*A)}=\sqrt{9+\sqrt{17}}$

#### Teorema (Equivalenza delle norme matriciali)  
Tutte le norme matriciali in $\Complex^{n \times n}$, nel senso che se prendiamo 2 norme matriciali $||\cdot||',||\cdot||'':\Complex^{n\times n}\rightarrow\R$ allora si ha:  
$$\alpha ||A||''\leq ||A||'\leq \beta||A||'' \ \forall A \in \Complex^{n \times n}$$  
Dove $\alpha,\beta>0$ sono due costanti indip. da $A$.  

## Successione di matrici  
***DEF***
Una successione di matrici $A^{(0)},A^{(1)},A^{(2)},... \in \Complex^{n \times n}$ si dice convergente alla matrice $A \in \Complex^{n \times n}$ rispetto alla norma matriciale $||\cdot||$ quando $||A^{(k)}-A||\rightarrow 0$. 
Poiché tutte le norme matriciali sono equivalenti, se una successione di matrici converge ad $A$ rispetto ad una norma $||\cdot||$, allora converge ad $A$ rispetto a tutte le norme.
***DEF***
Una successione $A^{(0)},A^{(1)},A^{(2)},... \in \Complex^{n \times n}$ si dice convergente (componente per componente) alla matrice $A \in \Complex^{n \times n}$, cioè se:  
$$a^{(k)}_{ij}\rightarrow a_{ij} \ \forall i,j=1,...,n \iff |a^{(k)}_{ij}|\rightarrow 0 \ \forall i,j=1,...,n \iff \max_{i,j=1,...,n}|a^{(k)}_{ij}-a_{ij}|\rightarrow 0 \iff |A^{(k)}-A|_\infin \rightarrow 0$$

La convergenza componente per componente altro non è che la convergenza in $|\cdot|_\infin$. Ricordando la convergenza di tutte le norme dire che $A^{(k)} \rightarrow A$ componente per componente è lo stesso che dire che $A^{(k)} \rightarrow A$ in una qualsiasi norma.  

#### Teorema 
Sia $A \in \Complex^{n \times n}$, allora:

$$\lim_{k \rightarrow \infty}A^k=0 \iff \rho(A)<1$$

##### Dim (Caso in cui $A$ è diagonalizzabile)

Poiché $A$ è diagonalizzabile, esistono una matrice invertibile $X$ e una matrice diagonale $D=\begin{bmatrix}
1&&\\
&\ddots&\\
&&\lambda_n 
\end{bmatrix}$ (con sulla diagonale gli autovalori di $A$) tali che:  

$A=XDX^{-1}$

$A^2=XD\underbrace{X^{-1}X}_IDX^{-1}=XD^2X^{-1}$

$A^3=XD\underbrace{X^{-1}X}_ID\underbrace{X^{-1}X}_IDX^{-1}=XD^3X^{-1}$
$\vdots$

$A_k=XD^kX^{-1} \ \forall k \geq 0 \ (\$)$ .

$(\impliedby)$

Supponiamo $\rho(A)<1$.  
In base a $(\$)$, $||A^k||_{\infty}=||XD^kX^{-1}||_\infty\leq ||XD^k||_{\infty}||X^{-1}||_\infty \leq^4 ||X||_\infty \ ||D^k||_\infty \ ||X^{-1}||_\infty=||X||_\infty \ \rho(A)^k \ ||X^{-1}||_\infty \rightarrow 0 \implies \underbrace{||A^k||_\infty}_{=||A^k-0||_\infin} \rightarrow 0 \implies A^k \rightarrow 0$

>$D^k=\begin{bmatrix}
\lambda_1^k&\\
&\lambda_2^k\\
&&\ddots&\\
&&&\lambda^k_n   \\
\end{bmatrix}$
>
>$||D^k||_\infty=\max(|\lambda_1^k|,|\lambda_2^k|,|\lambda_n^k|)=\max(|\lambda_1|^k,|\lambda_2|^k,|\lambda_n|^k)=[\max(|\lambda_1|,|\lambda_2|,|\lambda_n|)]^k=\rho(A)^k$
>
>$|\alpha|^m=|\alpha^m|$  D^k

$(\implies)$  
Supponiamo che $A^k \rightarrow 0$. Dalla $(\$)$ otteniamo $D^k=X^{-1}A^kX$
$\rho(A)^k=||D^k||\infty=||X^{-1}A^kX||_\infty\leq^4||X^{-1}||_\infty \underbrace{||A^k||_\infty}_{\rightarrow 0} ||X||\infty \rightarrow 0 \ \implies \rho(A)^k\rightarrow 0 \implies \rho(A)<1 \\ \square$ 
