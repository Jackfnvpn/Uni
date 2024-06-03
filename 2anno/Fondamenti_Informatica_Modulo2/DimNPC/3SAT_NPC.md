# Il problema 3SAT è NP-completo  

$\mathcal{I}_{3SAT}=\set{\langle f,X \rangle: X \text{ è un insieme di variabili booleane } \land \space f \text{ e un predicato su } X \text{ in } 3CNF }$   

$\mathcal{S}_{3SAT}(X,f)= \set{a: X \rightarrow \set{vero,falso}}$  

$\mathcal{\pi}_{3SAT}(\mathcal{S}_{3SAT}(X,f),X,f)=\exist a \in \mathcal{S}_{3SAT}(X,f): f(a(x))=vero$  

Osserviamo immediatamente che l’unica differenza fra i due problemi è nella definizione dell’insieme delle istanze.
In effetti, essi sono legati dalla relazione di inclusione: $\mathcal{I}_{3SAT} \subseteq \mathcal{I}_{SAT}$ .  

Sappiamo che $SAT \in \bold{NP}$ e, poichè, come abbiamo appena osservato, le istanze di $3SAT$ costituiscono un sottoinsieme dell’insieme delle istanze di $SAT$, anche $3SAT \in \bold{NP}$.  

Dimostriamo, ora, la completezza di $3SAT$ mediante una riduzione da $SAT$  

Sia $f \in \mathcal{I}_{SAT}$ una istanza di $SAT$; indichiamo con $X = \set{x_1, . . . , x_n}$ l’insieme delle variabili booleane che compaiono in $f$. Poichè $f$ è in forma congiuntiva normale, possiamo considerare $f$ come un insieme di clausole che devono essere tutte soddisfatte affinchè $f$ sia soddisfacibile, ossia, $f = \set{c_1, . . . , c_m}$.  

Mostreremo, in quanto segue, come trasformare ciascuna clausola $c_j \in f$ in un insieme $C_j$ di clausole a 3 variabili, in cui compaiono le stesse variabili che compaiono in $c_j$ e, in alcuni casi, variabili che non compaiono in $f$ , in modo tale
che un’assegnazione di verità soddisfa $c_j$ se e soltanto se la stessa assegnazione alle variabili che compaiono anche in
$C_j$ soddisfa anche tutte le clausole in $C_j$, per ogni $j = 1, . . . , m$.

Una volta definiti gli insiemi $C_j$, poniamo $\bar{f} = C_1 \cup C_2 \cup . . . \cup C_m: \bar{f}$ è un predicato in forma 3-congiuntiva normale e,dunque, è una istanza di $3SAT$. 
Inoltre, per costruzione degli insiemi $C_j$, una assegnazione di verità soddisfa tutte le clausole in $\bar{f}$ se e soltanto la stessa assegnazione alle variabili che compaiono anche in $\bar{f}$ soddisfa tutte le clausole in
$\bar{f}$ , ossia, $\bar{f} \in SAT$ se e soltanto se $\bar{f} \in 3SAT$.

### <span style="color:red"> Caso 1) </span>

$c_j$ contiene 1 letterale, ossia $c_j = ℓ$ , con $ℓ = x_i$ o $ℓ = ¬ x$ allora,   
$C_j = ( ℓ ∨ y_{j1} ∨ y_{j2} ) ∧ ( ℓ ∨ ¬y_{j1} ∨ y_{j2} ) ∧ ( ℓ ∨ y_{j1} ∨ ¬ y_{j2} ) ∧ ( ℓ ∨ ¬ y_{j1} ∨ ¬ y_{j2} )$  

La clausola $c_j = ℓ$ è soddisfatta solo dall’assegnazione $ℓ = vero$ ed è immediato verificare che tutte le clausole in $C_j$ sono soddisfatte se e soltanto se $ℓ = vero$.

### <span style="color:red"> Caso 2) </span>

$c_j$ contiene 2 letterali, ossia $c_j = ℓ_1 ∨ ℓ_2$,
allora $C_j = ( ℓ_1 ∨ ℓ_2 ∨ y_j ) ∧ ( ¬ y_j ∨ ℓ_1 ∨ ℓ_2 )$ dove $y_j$ è una nuova variabile.
Se viene assegnato valore vero a $ℓ_1$ oppure a $ℓ_2$ allora $C_j$ assume valore vero qualunque valore di verità si assegni a $y_j$ se viene assegnato valore falso sia a $ℓ_1$ che a $ℓ_2$ allora $C_j$ assume valore falso qualunque valore di verità si assegni a $y_j$  

### <span style="color:red"> Caso 3) </span>

$c_j$ contiene 3 letterali, ossia $c_j = ℓ_1 ∨ ℓ_2 ∨ ℓ_3$, allora $C_j = c_j = ℓ_1 ∨ ℓ_2 ∨ ℓ_3$, è il caso più facile!  

### <span style="color:red"> Caso 4) </span>

$c_j$ contiene 4 letterali, ossia $c_j = ℓ_1 ∨ ℓ_2 ∨ ℓ_3 ∨ ℓ_4$, allora $C_j = ( ℓ_1 ∨ ℓ_2 ∨ y_j ) ∧ ( ¬ y_j ∨ ℓ_3 ∨ ℓ4 )$ dove $y_j$ è una nuova variabile.  
Se viene assegnato valore vero a $ℓ_1$ , oppure a $ℓ_2$ , oppure a $ℓ_3$ , oppure a $ℓ_4$ allora esiste una assegnazione di verità a $y_j$ che fa assumere a $C_j$ valore vero.
Se viene assegnato valore falso sia a $ℓ_1$ che a $ℓ_2$ che a $ℓ_3$ che a $ℓ_4$ allora $C_j$ assume valore falso qualunque valore di verità si assegni a $y$

### <span style="color:red"> Caso 5,...,n) </span>


Se $c_j$ contiene $k \geq 4$ letterali, ossia, $c_j = ℓ_1 ∨ ℓ_2 . . . ℓ_k:$ sia $Y_j = \set{y_{j1}, . . . , y_{jk−3}}$ un nuovo insieme di variabili booleane; allora,
$C_j = \set{(ℓ_1 ∨ ℓ_2 ∨ ℓ_{y_j1}), (¬y_{j1} ∨ ℓ3 ∨ y_{j2}), . . . , (¬y_{ji} ∨ ℓ_{i+2} ∨ y_{ji+1}), . . . , (¬y_{jk−3} ∨ ℓ_{k−1} ∨ ℓ_k)}$.

La clausola $c_j$ è soddisfatta dalle assegnazioni tali che, per almeno un indice $i = 1, . . . , k$, $ℓ_i = vero$ ed è immediato verificare che le $k − 3$ clausole in $C_j$ sono soddisfatte se e soltanto se $ℓ_i = vero$ per almeno un indice $i = 1, . . . , k$.

Costruire l’insieme $C_j$ a partire dalla clausola $c_j$ richiede tempo lineare nel numero di letterali in $c_j$: poichè tale numero non può essere maggiore di $2n$, è possibile costruire $C_j$ in tempo lineare in $n$.
Allora costruiamo $\bar{f}$ a partire da $f$ in tempo proporzionale a $nm$, e dunque in tempo in $O(| f |^2)$