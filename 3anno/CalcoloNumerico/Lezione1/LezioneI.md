# Interpolazione polinomiale  
Esistenza e unicità del polinomio d'interpolazione ( forma canonica polin. d'interpolazione, forma di Lagrange ).  

Data una funzione $f: [a,b] \rightarrow \R$ di cui sono noti i valori $f(x_0),f(x_1),...,f(x_n)$ in $n+1$ punti distinti $x_0,x_1,...,x_n \in [a,b]$.  

Si sceglie una classe $C$ di funzioni definite su $[a,b]$ e valori in $\R$ e si vuole approssimare la funzione $f(x)$ con una funzione $g(x)$ della classe $C$ tale che $g(x_0)=f(x_0), g(x_1)=f(x_1),..., g(x_n)=f(x_n) \ ( \ g(x_i)=f(x_i) \ \forall i=0,...,n \ )$.
 
La scelta più comune per la sua semplicità è quella di prendere:  

$C=\R_n[x]= \text{spazio vettoriale dei polinomi di grado} \leq n= \set{a_0+a_1x+a_2+x^2,...,a_n+x^n: a_0,a_1,a_2,...,a_n \in \R}$  

Con questa scelta, siamo sicuri che $\exist! \ g\in \R_n[x] \ t.c\ g(x_i)=f(x_i) \ \forall i=0,...,n$  

### <span style="color:red">Teorema  
$\text{Siano } (x_0,y_0),(x_1,y_1),...,(x_n,y_n) \in \R^2 \ t.c \ x_0,x_1,...,x_n \text{ sono tutti distinti}.\newline \text{Allora } \exist! \ \text{polinomio } p(x) \in \R_n[x] \ t.c \ p(x_i)=y_i \ \forall i=0,...,n$  


