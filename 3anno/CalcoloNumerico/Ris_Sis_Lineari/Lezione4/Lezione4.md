## Teoremi di convergenza per i metodi di Jacobi  e Gauss-Seidel

#### Teorema
Supponiamo che $A \in \Complex^{n \times n}$ soddisfi almeno una delle seguenti condizioni:
+ $A$ è a diagonale dominante e irriducibile
+ $A$ è a diagonale dominante in senso stretto
+ $A$ è a diagonale dominante per colonne e irriducibile
+ $A$ è a diagonale dominante in senso stretto per colonne

Allora i metodi di Jacobi e Gauss-Seidel per risolvere un sistema lineare di matrice $A$ sono convergenti.  

OSS:
Se $A \in \Complex^{n \times n}$ soddisfa almeno una delle condizioni del teorema allora:
+   $A$ è invertibile per un teorema visto.
+   Gli elementi diagonali di $A$ sono diversi da 0. Infatti, se per assurdo esistesse un elemento diagonale di $A$ uguale a 0 allora tutta la corrispondente riga (o colonna) sarebbe nulla in quanto $A$ è a diagonale dominante (oppure a diagonale dominante per colonna): ciò è assurdo perché $A$ è invertibile!
  
Se $A \in \Complex^{n \times n}$ soddisfa almeno una delle condizioni del teorema, allora i metodi di J e GS per risolvere un sistema lineare di matrice $A$ sono applicabili.

##### dim
Faccio  la dim. per il metodo di GS sotto l'ipotesi che $A$ è a diagonale dominante e irriducibile (la dim. per gli altri 7 casi è lasciata per ex. )
Devo dimostrare che $\rho(G)<1$ dove $G$ è la matrice d'iterazione di $GS=I-E^{-1}A$. 
Per l'osservazione smart, gli autovalori di $G$ sono le soluzioni dell'equazione:
$$\det(\lambda E+A-E)=0$$ $$\det(\lambda E+A-E)=^{n=4}\begin{vmatrix}
    \lambda a_{11}&a_{12}&a_{13}&a_{14}\\
    \lambda a_{21}&\lambda  a_{22}&a_{23}&a_{24}\\
    \lambda a_{31}&\lambda  a_{32}&\lambda a_{33}&a_{34}\\
    \lambda a_{41}&\lambda  a_{42}&\lambda a_{43}&\lambda a_{44}\\
\end{vmatrix}$$

Dimostreremo che nessun numero $\lambda$ di modulo $|\lambda|\geq 1$ può essere soluzione di $(\star)$ perché <span style="color:red"> se $\lambda$ è un numero di modulo $|\lambda|\geq 1$ allora la matrice $\lambda E+A-E$ è a diagonale dominante e irriducibile esattamente come $A$ </span>, dunque è invertibile e dunque 
$$\det(\lambda E+A-E)\neq 0$$ In definitiva, gli autovalori di $G$ (le soluzioni di $(\star)$) sono in modulo $<1 \implies \rho(G)=$ massimo dei moduli degli autovalori di $G<1$.
Dimostriamo ora la frasse rossa.
Sia $\lambda$ un generico numero di modulo $\geq 1$.
+ $\lambda E+A-E$ è irriducibile perché gli elementi nulli di $\lambda E+A-E$ si trovano nelle stesse posizioni in cui si trovano gli elementi nulli di $A \implies \lambda E+A-E$ ed $A$ hanno lo stesso grafo $\implies \lambda E+A-E$ è irriducibile perché $A$ è irriducibile.
+ $\lambda E+A-E$ è a diagonale dominante come $A$. Infatti $\forall i=1,...,n$
$$|\lambda a_{ii}|=|\lambda||a_{ii}|\overbrace{\geq}^{\text{ perché A è a diagonale dominante}} \textcolor{red}{>} |\lambda|(|a_{i1}|+...+|a_{i\ i-1}|+|a_{i\ i+1}|+...+|a_{i \ n}|)$$  $$= |\lambda a_{i1}|+...+|\lambda a_{i \ i-1}|+|\lambda|\cdot |a_{i\ i+1}|+...+|\lambda|\cdot|a_{in}|$$ $$\underbrace{\geq}_{\text{ perché }|\lambda|\geq 1} |\lambda a_{i1}|+...+|\lambda a_{i \ i-1}|+ |a_{i\ i+1}|+...+|a_{in}|$$ 
<span style="color:red"> vale il $>$ se sulla riga $i$ di $A$ vale il $>$ </span>

$\square$

#### Teorema
Supponiamo che $A \in \Complex^{n \times n}$ sia hermitiana definita positiva.
Allora il metodo di GS per risolvere un sistema lineare di matrice $A$ è convergente.

OSS:
Se $A \in \Complex^{n \times n}$ è hermitiana definita positiva allora:
1. $A$ è invertibile perché di $A$ sono positivi (reali in quanto $A$ è hermitiana e con parte reale positiva in quanto $A$ è definita positiva) e dunque 0 non è un autovalore di $A$.
2. Gli elementi diagonali di $A$ sono positivi per un esercizio obbligatorio assegnato.  

Se $A$ è HDP allora J e GS sono applicabili per risolvere un sistema lineare di matrice $A$.
##### dim
Devo dimostrare che $\rho(G)<1 \ G=I-E^{-1}A$ matrice d'iterazione di GS.

La dimostrazione è divisa in due parti.  

**PARTE 1:** Dimostriamo che $A-G^{*}AG$ è HDP.
+ $A-G^{*}AG$ è hermitiana perché
  $$(A-G^{*}AG)^*=A^*-(G^*AG)^*=A-G^*A^*G^{**}=A-G^*AG \implies A-G^*AG \text{ è hermitiana }$$
+ Dimostriamo che $A-G^{*}AG$ è definita positiva
  $$A-G^{*}AG=A-(I-E^{-1}A)^*A(I-E^{-1}A)=$$

  chiamo $F=E^{-1}A$
  $$=A-(I-F^*)A(I-F)=\cancel{A}-\cancel{A}+F^*A+AF-F^*AF$$
  > Notare che $F$ è invertibile perché è il prodotto di due matrici invertibili, e si ha: $$F^{-1}=(E^{-1}A)^{-1}=A^{-1}E$$
  
  > Per $F$ come per ogni matrice invertibile vale: 
  > $$(F^{-1})^*=(F^*)^{-1}$$ si pone $$F^{-*}=(F^*)^{-1}$$
  > <span style="color:red" ><b> dim </b> </span>
  > $$\textcolor{red}{\begin{cases}
  >     F^*(F^{-1})^*=(F^{-1}F)^*=I^*=I\\
  >     (F^{-1})^*F^*=(FF^{-1})^*=I^*=I
  >\end{cases}}$$

  $$=F^*(AF^{-1}+F^{-*}A-A)F=$$
  >$AF^{-1}=E; \\ F^{-*}A=(A^*E^{-*})^{-1}A=(AE^{-*})^{-1}A=E^*A^{-1}A=E^*$

  $$=F^*(E+E^*-A)F^*$$

  > Dim che $E+E^*-A=D=$ parte diagonale di $A$
  > Caso 3 x 3:
  > $A=\begin{bmatrix}
    a_{11}&\overline{a_{21}}&\overline{a_{31}}\\
    a_{21}&a_{22}&\overline{a_{32}}\\
    a_{31}&a_{32}&a_{33}\\
  \end{bmatrix}E^*=\begin{bmatrix}
    a_{11}&\overline{a_{21}}&\overline{a_{31}}\\
    0&a_{22}&\overline{a_{32}}\\
    0&0&a_{33}\\
  \end{bmatrix}\\ E+E^*=A+D$

  $$=F^*DF$$

    $$\forall \vec{y}\neq 0 \vec{y}^*(A-G^*AG)\vec{y}=\vec{y}^*F^*DF\vec{y}=(F\vec{y})^*D(\underbrace{F\vec{y}}_{\vec{u}\neq 0})= \vec{u}^*D\vec{u}=$$ $$=\begin{bmatrix}
        \overline{u_{1}},...,\overline{u_{n}}
    \end{bmatrix}\begin{bmatrix}
        a_{11}u_1   \\
        a_{22}u_2   \\
        \vdots      \\
        a_{nn}u_n   \\
    \end{bmatrix}=a_{11}\overbrace{\overline{u_{1}}u_1}^{|u_1|^2}+a_{22}\overline{u_{2}}u_2+...+a_{nn}\overline{u_{n}}u_n=\sum_{i=1}^n \underbrace{a_{ii}}_{>0}|u_i|^2>0$$

    $\implies A-G^*AG$ è definita positiva per un teorema visto ma non dimostrato.

**PARTE 2:** Dimostriamo che se $\lambda$ è un generico autovalore di $G$ allora $|\lambda|<1$. Fatto questo avremo che $\rho(G)<1$ e la tesi è dimostrata. 
Sia dunque $\lambda$ un autovalore di $G$ e sia $\vec{y} \neq 0$ un corrispondente autovettore: $G\vec{y}=\lambda \vec{y}$.
Siccome $A-G^*AG$ è HDP
$$0< \vec{y}^*(A-G^*AG)\vec{y}=\vec{y}^*A\vec{y}-\vec{y}^*G^*AG\vec{y}=\vec{y}^*A\vec{y}-(G\vec{y})^*A(G\vec{y})=\vec{y^*}A\vec{y}-(\lambda\vec{y})^*A(\lambda \vec{y})=$$
>$\vec{y}=\begin{bmatrix}
   y_1 \\
   y_2 \\ 
\end{bmatrix}, (\lambda \vec{y})^*=\begin{bmatrix}
   \lambda y_1  \\
   \lambda y_2  \\ 
\end{bmatrix}^*=\begin{bmatrix}
   \ \overline{\lambda y_1}, \  
   \overline{\lambda y_2}   \
\end{bmatrix}=\begin{bmatrix}
   \ \overline{\lambda} \overline{y_1}, \  
   \overline{\lambda} \overline{y_2}   \
\end{bmatrix}=\overline{\lambda}\begin{bmatrix}
   \    \overline{y_1}, \  
        \overline{y_2}   \
\end{bmatrix}=\bar{\lambda}\vec{y}^*$

$$\vec{y}^*A\vec{y}-\bar{\lambda}\vec{y}^*A(\lambda\vec{y})=\vec{y}^*A\vec{y}-|\lambda|^2\vec{y}^*A\vec{y}=\underbrace{\vec{y}^*A\vec{y}}_{>0 \text{ perché A è HDP e }\vec{y} \ > 0}(1-|\lambda|^2)$$  

$\implies 1-|\lambda|^2>0 \iff |\lambda|^2<1 \iff |\lambda|<1$.
Dunque $|\lambda|<1 \implies \rho(G)<1.\\ \square$

