# Teorema Cook-Levin

Sia $\Gamma \in \bold{NP}$ e sia $L_\Gamma \subseteq \set{0,1}^*$, il linguaggio che contiene la codifica delle istanze si di $\Gamma$.  

Siccome $\Gamma \in \bold{NP} \implies \exist NT,h \text{ costante }: h \in \N \land \forall x \in \Sigma^*[o_{NT}(x)=q_A \iff x \in L_{\Gamma} \land o_{NT}(x)\neq q_A \iff x \notin L_{\Gamma}] \land \forall x \in L_{\Gamma}[ntime(NT,x)\leq |x|^h]$   

L'affermazione $x \in L_{\Gamma}$ è equivalente alla seguente affermazione:  
$\gamma(x)=$ "$x$ (e solo $x$) è scritto sul nastro di input, **e** la testina è posizionata sulla cella contenente il primo carattere di $x$ **e** la macchina si trova nel suo stato iniziale **e** esiste una sequenza di al più $|x|^h$ quintuple che eseguite in successione porta la macchina nel suo stato di accettazione $q_A$".  

Dunque $x \in L_{\Gamma}$ se e soltanto se $\gamma(x)$ è vera.  

Dobbiamo definire $\gamma(x)$ sottoforma di espressione booleana $E(x)$, ove $\gamma(x)$ è vera se e soltanto se $E(x)$ è soddisfacibile.  

$E(x)$ deve descrivere dunque una computazione di $NT$ che ha inizio con $x$ (e solo $x$) scritto sul nastro di input.  
Una computazione di $NT$ non è altro che una successione di stati globali.  

Dunque per costruire $E(x)$ è necessario introdurre le variabili booleane che descrivono per ogni passo $t \ (0 \leq t \leq |x|^h)$ della computazione lo stato globale in cui si trovrebbe la computazione di $NT$ al passo $t$:  
+ L'insieme $N$ che contiene le variabili booleane che permettono di descrivere i caratteri contenuti in ogni cella del nastro ad ogni *passo* della computazione  
+ L'insieme $R$ che contiene le variabili booleane che permettono di descrivere la cella su cui è posizionata la testina della macchina ad ogni *passo* della computazione   
+ L'insieme $M$ che contiene le variabili booleane che permettono di descrivere lo stato che assume la macchina $NT$ ad oggni *passo* della computazione.  

## Insieme M  

Sia $Q=\set{q_0,q_1,q_2,...,q_k}$ l'insieme degli stati della macchina $NT$, con $q_0$ stato iniziale, $q_1=q_A,q_2=q_R$.  

Per ogni passo $t$ della computazione ($0 \leq t \leq |x|^h$) e per ogni $i \in \set{1,...,k} M$ contiene la variabile booleana $M^t_i$:  

$M = \set{M^t_i: 0 \leq t \leq |x|^h \land 0\leq i \leq k}$.  

Assegnando il valore vero alla variabile $M^t_i$ indichiamo che al passo $t$ della computazione la macchina si trova nello stato $q_i$.  

Affinché le variabili booleane $M^t_i$ descrivano correttamente lo stato della macchina dobbiamo imporre che siano coerenti, ovvero la macchina ad ogni passo $t$ della computazione può trovarsi in uno e un solo stato, quindi dobbiamo prendere in considerazione le sole assegnazioni di verità che per $0\leq t \leq |x|^h$ ad una sola delle $M^t_0,...,M^t_k$ sia assegnato il valore **vero**.  

Per fare ciò introduciamo $|x|^h+1$ espressioni $E^0_M,...,E^{|x|^h}_M$.    
Dove $E^t_M$ rappresenta la seguente affermazione:  
"La macchina $NT$ si trova in uno e un solo dei suoi stati interni"  

$E^t_M=[M^t_0 \land \neg M^t_1 \land...\land\neg M^t_k] \lor ...\lor [M^t_k \land \neg M^t_0 \land...\land M^t_{k-1}]$   

E questa espressione è vera se e soltanto se viene assegnato valore vero ad una ed una sola variabile booleana $M^t_i$ con $0\leq t \leq |x^h| \ e \ 0\leq i \leq k$.  

## Insieme R

Siccome la computazione $NT(x)$ accetta in al più $|x|^h$ passi allora utilizza al più $|x|^h$ celle del nastro.

Per ogni passo $t$ della computazione ($0 \leq t \leq |x|^h$) e per ogni $0 \leq i \leq |x|^h R$ contiene la variabile booleana $R^t_i$:  

$R = \set{R^t_i: 0 \leq t \leq |x|^h \land 0\leq i \leq k}$.  

Assegnando il valore vero alla variabile $R^t_i$ indichiamo che al passo $t$ della computazione la testina della macchina si trova sulla cella $i$.  

Affinché le variabili booleane $R^t_i$ descrivano correttamente la cella su cui è posizionata la testina della macchina dobbiamo imporre che siano coerenti, ovvero la testina della macchina ad ogni passo $t$ della computazione può trovarsi su una e un sola cella del nastro, quindi dobbiamo prendere in considerazione le sole assegnazioni di verità che per $0\leq t \leq |x|^h$ ad una sola delle $R^t_1,...,R^t_{|x|^h}$ sia assegnato il valore **vero**.  

Per fare ciò introduciamo $|x|^h+1$ espressioni $E^0_R,...,E^{|x|^h}_R$.    
Dove $E^t_R$ rappresenta la seguente affermazione:  
"La testina della macchina $NT$ si trova su una e un sola cella del nastro"  

$E^t_R=[R^t_0 \land \neg R^t_1 \land...\land\neg R^t_k] \lor ...\lor [R^t_{|x|^h} \land \neg R^t_0 \land...\land R^t_{|x|^h-1}]$   

E questa espressione è vera se e soltanto se viene assegnato valore vero ad una ed una sola variabile booleana $R^t_i$ con $0\leq t \leq |x^h| \ e \ 0\leq i \leq k$.  

## Insieme N  
Siccome la computazione $NT(x)$ accetta in al più $|x|^h$ passi allora utilizza al più $|x|^h$ celle del nastro.  
Ed $L_\Gamma \subseteq \set{0,1}^*$ allora in ogni cella del nastro può trovarsi il carattere 0, 1 o $\square$  

Per ogni passo $t$ della computazione ($0 \leq t \leq |x|^h$) e per ogni $0 \leq i \leq |x|^h $ e per ogni $j \in \set{0,1,\square} N$  contiene la variabile booleana $N^t_{ij}$:  

$N = \set{N^t_{ij}: 0 \leq t \leq |x|^h \land 0\leq i \leq k \land j \in \set{1,0,\square}}$.    

Assegnando il valore vero alla variabile $N^t_{ij}$ indichiamo che al passo $t$ della computazione nella cella $i$ si trova il carattere $j$.  

Affinché le variabili booleane $N^t_{ij}$ descrivano correttamente i caratteri contenuti in ciascuna cella del nastro al passo t della computazione dobbiamo imporre che siano coerenti, ovvero in ogni cella del nastro ad ogni passo $t$ della computazione può trovarsi uno e un solo carattere, quindi dobbiamo prendere in considerazione le sole assegnazioni di verità che per $0\leq t \leq |x|^h$ e per $1 \leq i \leq |x|^h$ ad una sola delle $N^t_{i1},N^t_{i0},N^t_{i\square}$ sia assegnato il valore **vero**.    

Per fare ciò introduciamo $|x|^h+1$ espressioni $E^0_N,...,E^{|x|^h}_N$.    
Dove $E^t_N$ rappresenta la seguente affermazione:  
"Al passo $t$ della computazione su ogni cella del nastro vi è uno e un solo carattere compreso tra 0,1,$\square$" .  
Ma prima dobbiamo introdurre una nuova espressione $E^{ti}_j$ che corrisponde alla seguente affermazione: "al passo $t$ della computazione, nella cella $i$ vi è scritto uno e un solo carattere $j \in \set{0,1,\square}$".  

$E^{ti}_j=[N^t_{i0} \land \neg N^t_{i1} \land \neg N^t_{i\square}]\lor[N^t_{i1} \land \neg N^t_{i0} \land \neg N^t_{i\square}]
\lor[N^t_{i\square} \land \neg N^t_{i0} \land \neg N^t_{i1}]$  

Questa espressione è vera se e soltanto se viene assegnato vero ad una ed una sola delle variabili booleane $N^t_{i0},N^t_{i1},N^t_{i\square}$.  
Dunque ora possiamo definire $E^t_N$
$E^t_N=E^{t1}_j\land...\land E^{t|x|^h}_j$  

$E^t_N$ assume il valore vero se e soltanto se al passo $t$ della computazione nella cella $i$ è scritto uno e un solo carattere $j$ compreso tra 0,1,$\square$.  

## Rappresentare un generico stato globale

Attraverso le variabili booleane introdotte precedentemente ora siamo in grado di descrivere un generico stato globale. Gli stati globali di $NT$ al passo $t$ sono descritti dalle assegnazioni di verità che rendono soddisfacibile la seguente espressione $S^t=E^t_N \land E^t_M \land E^t_R$  

Infatti un'assegnazione di verità che rende soddisfacibile $S^t$ rappresenta la seguente affermazione:  
"La macchina $NT$ si trova in uno e un solo stato al passo $t$, la testina si trova su una e una sola cella del nastro al passo $t$ e su ciascuna cella del nastro è scritto uno e un solo carattere al passo $t$ della computazione".  

## Rappresentare una computazione $NT(x)$

Adesso che possiamo rappresentare un generico stato globale attraverso un espressione booleane dobbiamo rappresentare una computazione accettante di $NT$ in $|x|^h$ passi, ossia:  

"Esiste un sequenza di al più $|x|^h$ quintuple, che eseguite in successione porta la macchina $NT$ al suo stato di accettazione"  

Possiamo dire che $NT(x)$ è una computazione accettante in $|x|^h$ passi se:  

+ al passo 0 viene eseguita una quintupla
+ al passo 1 viene eseguita una quintupla o la macchina $NT$ è in $q_A$
  ...
+ al passo $|x|^h-1$ viene eseguita una quintupla o la macchina $NT$ è in $q_A$
+ al passo $|x|^h$ la macchina $NT$ è in $q_A$   

Dobbiamo dunque rappresentare sotto espressione booleana la seguente affermazione:  
"$NT$ è in $q_A$ o viene eseguita una quintupla".  

Sia $\lang q_{i1},s_{i1},s_{i2},q_{i2},m\rang$ con $m=1$ se il movimento della testina viene effettuato a destra, $m=-1$ a sinistra e $m=0$ se rimane ferma.  

Possiamo descrivere attraverso espressione booleana la seguente affermazione: "La quintupla $\lang q_{i1},s_{i1},s_{i2},q_{i2},m\rang$ viene eseguita al passo $t$ mentre la testina si trova sulla cella $u$" nel seguente modo:  

$G^t(u,\lang q_{i1},s_{i1},s_{i2},q_{i2},m\rang)=M^t_{i1}\land R^t_u \land N^t_{us_{i1}}\land N^{t+1}_{us_{i2}} \land M^{t+1}_{i2} \land R^t_{u+m}$  

La seguente espressione booleana rappresenta la seguente affermazione: "Al passi $t$ della computazione la testina è posizionata sulla cella $u$, la macchina è nello stato $q_{i1}$ e nella cella $u$ viene letto il carattere $s_{i1}$ e al passo $t+1$ nella cella $u$ vi sarà il carattere $s_{i2}$ la macchina si troverà nello stato $q_{i2}$ e la testina sarà posizionata sulla cella $u+m2$".  

Ma la testina potrebbe trovarsi su una delle qualsiasi $|x|^h$ celle del nastro dunque   

$G^t(\lang q_{i1},s_{i1},s_{i2},q_{i2},m\rang)= (1,\lang q_{i1},s_{i1},s_{i2},q_{i2},m\rang) \lor (2,\lang q_{i1},s_{i1},s_{i2},q_{i2},m\rang) \lor ... \lor (|x|^h,\lang q_{i1},s_{i1},s_{i2},q_{i2},m\rang)$

Al passo $t$ la macchina è nello stato $q_{i1}$, la testina è posizionata su una qualsiasi cella $u$ (con $1 ≤ u ≤ |x|^h$) e legge il simbolo $s_{i1}$, e al passo $t+1$ la macchina è nello stato $q_{i2}$, nella cella $u$ è stato scritto $s_{i2}$ e la testina è stata spostata sulla cella $u + m$  

Se  l’insieme delle quintuple di $NT_Γ$ è $\set{⟨ q_{11}, s_{11}, s_{12}, q_{12}, m_1 ⟩ ,⟨ q_{21}, s_{21}, s_{22}, q_{22}, m_2 ⟩ , … , ⟨ q_{h1}, s_{h1}, s_{h2}, q_{h2}, m_h ⟩}$ 

allora per esprimere la seguente espressione:  "Viene eseguita una quintupla di $NT$", in forma booleana scriviamo la seguente espressione  

$G^t=G^t(⟨ q_{11}, s_{11}, s_{12}, q_{12}, m_1 ⟩)\lor G^t(⟨ q_{21}, s_{21}, s_{22}, q_{22}, m_2 ⟩)\lor...\lor G^t(⟨ q_{h1}, s_{h1}, s_{h2}, q_{h2}, m_h ⟩)$

Quindi per rappresentare in forma booleana l'affermazione:  
"$NT$ è in $q_A$ o esegue una quintupla" ,ricordando che $q_1$ corrisponde allo stato di accettazione di $NT$, possiamo usare la seguente espressione:  

$G^t \lor M^T_1$  

## Rappresentare la configurazione iniziale della macchina

Rappresentiamo ora la configurazione iniziale della macchina ovvero rappresentiamo la seguente affermazione:  
"$x$ (e solo $x$) è scritto sul nastro di input, la macchina si trova nel suo stato inziale e la testina è posizionata sulla cella contenente il primo carattere di x"
attraverso un espressione booleana:  

Assegniamo vero alla variabile $M^0_0$, vero alla variabile $R^0_1$.  

Sia $x$ (e solo $x$) scritta sul nastro di input e sia $x=x_1...x_n$ allora per ogni $i=1,...,n$ assegno alla variabile $N^t_{ix_i}$ il valore vero e per ogni $i=n+1,...,p(n)$ assegno alla variabile $N^t_{i\square}$ il valore vero.  

Quindi lo stato globale iniziale della computazione $NT(x)$ è completamente descritto dalla seguente espressione:  

$H= M^0_0 ∧ R^0_1∧ N^0_{1x_1} ∧ N^0_{2x_2}∧ … ∧ N^0_{nx_n} ∧ N^0_{n+1\square} ∧ N^0_{n+2\square} ∧ … ∧ N^0_{p(n)\square}$  

Dunque se $H$ è vera allora esiste un assegnazione di verità in $M,N,R$ che descrive la configurazione iniziale della macchina ovvero $x$ (e solo $x$) scritto sul nastro di input, la testina posizionata sulla cella contenente il primo carattere di $x$ e la macchina che si trova nel suo stato iniziale  

Se $S^t$ è vera allora vengono assegnati alle variabili che descrivono lo stato globale al passo $t$ valori di verità consistenti  

Se $G^t$ è vera allora vengono assegnati valori di verità che corrispondo all'esecuzione di una quintupla di $NT$ al passo $t$  

Ricordando che $M^t_1$ è la variabile che descrive se al passo $t$ la macchina è in $q_A$ allora siccome una computazione $NT$ accettante in $|x|^h$ passi se:  

+ al passo 0 viene eseguita una quintupla
+ al passo 1 viene eseguita una quintupla o la macchina $NT$ è in $q_A$
  ...
+ al passo $|x|^h-1$ viene eseguita una quintupla o la macchina $NT$ è in $q_A$
+ al passo $|x|^h$ la macchina $NT$ è in $q_A$    

Allora $E(x)=H\land S^0 \land (M^0_1 \lor G^0)\land...\land S^{|x|^h-1}\land(M^{|x|^h-1}_1 \lor G^{|x|^h-1}) \land S^{|x|^h} \land M^{|x|^h}_1 $.  

## $x \in L_\Gamma \implies E(x)$ è soddisfacibile  

Se $x \in L_\Gamma$ allora esiste una computazione $NT(x)$ che in $|x|^h$ passi termina nello stato di accettazione.  Ossia esiste una successione di stati globali $SG_0,...,SG_u$ con $u\leq |x|^h$ e una successione di quintuple $\lang q_{t1},s_{t1},s_{t1},q_{t2},m_t \rang$ con $0 \leq t \leq u-1$, tali che:  
+ $SG_0$ è lo stato globale iniziale di $NT$, ove $x$ è scritto sul nastro di input, la macchina si trova sul suo stato iniziale e la testina è poisizionata sulla cella contenente il primo carattere di $x$  
+ per $0\leq t\leq u-1$, lo stato interno di $SG_t$ è $q_{t1}$ il simbolo letto dalla testina è $s_{t1}$ e $SG_{t+1}$ è lo stato corrispondente all'esecuzione della $t$-esima quintupla della sequenza a partire da $SG_{t}$  
+ Lo stato interno di $SG_u$ è $q_A$  

a partire da ciò, costruiamo un'assegnazione di verità $a$ che soddisfa $E(x)$.  
1. Partiamo da $SG_0$:  
    poniamo $a(M^0_0)=vero$ e $a(R^0_1)=vero$ e per ogni $0\leq j\leq|x|$ se il $j$-esimo bit di $x$ è 1 poniamo $a(N^0_{j1})=vero$, se 0 poniamo $a(N^0_{j0})=vero$ inoltre per ogni $|x|+1 \leq j \leq p(|x|)$ poniamo $a(N^0_{j\square})=vero$.  
    $a$ assegna falso a tutte le variabili in $M^0,R^0,N^0$, pertanto $a$ soddisfa $H$ e $S^0$  
    <br>
2. Continuiamo con $SG_1,...,SG_u$, definiamo $a(M^t_i),a(R^t_i),a(N^t_{ij})$ usando $SG_t$ come abbiamo fatto per il punto 1. Questo garantisce che, per ogni $t=1, … , u$: 
esiste un solo $i$ per cui $a(M^t_i)=vero$ ed esiste un solo $i$ per cui $a(R^t_i)=vero$ e per ogni $0\leq j \leq |x|^h$ esiste un solo i per cui $a(N^t_{ji})=vero$.
e quindi per ogni $t=1, … , u$, $a$ soddisfa $S^t$   
<br> 
3. Per ogni $0 \leq t \leq u-1$ può essere eseguita la $t$-esima quintupla della sequenza quindi $a$ soddisfa $G^t$  
<br>
4. Lo stato globale $SG_u$ è nel suo stato interno $q_A$. Dunque poniamo $a(M^u_1)=vero$. Dunque $a$ soddisfa l'espressione $(M^u \lor G^u)$, anche se non viene eseguita alcuna quintupla al passo $u$.  
<br>
5. Per $t > u$ e per ogni $i=0,...,k$ poniamo $a(M^t_i)=a(M^u_i) \ e \ a(R^t_i)=a(R^u_i) \ e \ a(N^t_{i0})=a(N^u_{i0}), a(N^t_{i1})=a(N^t_{i1}), a(N^t_{i\square})=a(N^t_{i\square})$. Quindi $a$ soddisfa $S^t \ e \ M^t_1$ 

Dunque $a$ soddisfa $E(x)$

##  $E(x)$ è soddisfacibile  $\implies x \in L_\Gamma$  

Supponiamo ora che esista un'assegnazione di verità $a$ per le variabili in $N,M,R$ che soddisfa $E(x)$.  
Ossia $a$ soddisfa $H,S^{|x|^h}e M^{|x|^h}$  
e per $t=0,...,|x|^h-1$ $a$ soddisfa $S^t$ ovvero descrive in modo consistente lo stato globale in cui si trova la computazione al passo $t$.  
Dunque $a$ descrive una successione di stati globali $SG_0,...,SG_{|x|^h-1}$.  

poichè $a$ soddisfa $H$, allora $a(S^0)$ descrive lo stato globale iniziale dove $x$ è scritto sul nastro di input, la macchina si trova nel suo stato iniziale, e la testina si trova sulla cella contenente il primo carattere di $x$.  

Siccome $a$ soddisfa per $t=0,...,|x|^h-1 \  (G^t \lor M^t_1)$ allora o viene eseguita una quintupla al passo $t$ e dunque $a(G^t)=vero$ o ,al passo $t$,la macchina e nel suo stato di accettazione e dunque $a(M^{|x|^h}_1)=vero$  

Notiamo bene che è impossibile che $a(G^t)=vero$ e $a(M^{|x|^h}_1)=vero$ contemporaneamente perchè se la macchina si trova nel suo stato di accettazione non può più eseguire alcuna quintupla perché la computazione è terminata.  

Dunque per $t=0,...,|x|^h-1$ se $a(G^t)=vero$ allora viene eseguita una quintupla che fa passare dallo stato $SG_t$ allo stato globale $SG_{t+1}$.  

D'altra parte $a$ soddisfa $M^{|x|^h}_1$ dunque esiste un indice $h$ tale $a(M^h_1)=vero$ e per ogni $t>h$ si ha che $a(M^t_1)=vero$.  

Dunque sia $u=0,...,|x|^h$ il primo intero per cui $a(M^u_1)=vero$, allora per $t=0,...,u-1 \ a(G^t)=vero$ e quindi viene eseguita una quintupla che fa passare da $SG_t$ a $SG_{t+1}$.  

Dunque la successione di stati globali $\lang SG_1,...,SG_u\rang$ corrisponde a una computazione accettante di $NT(x)$.  
Dunque $x \in L_\Gamma$