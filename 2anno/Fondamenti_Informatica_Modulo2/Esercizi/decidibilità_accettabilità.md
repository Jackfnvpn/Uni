# Esercizi decidibilità accettabilità  

## Problema 1  

Sia $\Sigma$ un alfabeto (finito) e siano $L_1 \subseteq \Sigma^∗$ e $L_2 \subseteq \Sigma^∗$ due linguaggi decidibili. Dimostrare che $L_1 \cup L_2$
è decidibile.  

#### dim  

Siccome  

$L_1 \text{ è decidibile } \implies \exist T_1: \forall x \in \Sigma^*[o_{T_1}(x)=q_A \iff x \in L \space \land \space o_{T_1}(x)=q_R \iff x \notin L_1 ]$  

e  

$L_2 \text{ è decidibile } \implies \exist T_2: \forall x \in \Sigma^*[o _{T_2}(x)=q_A \iff x \in L \space \land \space o_{T_2}(x)=q_R \iff x \notin L ]$    

Per dimostrare che $L_1 \cup L_2$ è decidibile, dobbiamo creare una macchina di Turing $T$ che decide $L_1 \cup L_2$.  
Dunque da $T_1$ e da $T_2$ deriviamo la nuova macchina di Turing $T$ a due nastri, sui quali verrà scritto l'input $x \in \Sigma^*$ che lavora su alfabeto $\Sigma$ e la computazione $T(x)$ opera nel seguente modo:  
1. Viene simulata la computazione $T_1(x)$ usando il primo nastro come nastro di lavoro: se $o_{T_1}(x)=q_A$ allora la computazione $T(x)$ termina nel suo stato di accettazione. Se $o_{T_1}(x)=q_R$ allora viene eseguito il passo 2.  

2. Viene simulata la computazione $T_2(x)$ usando il secondo nastro come nastro di lavoro: se $o_{T_2}(x)=q_A$ allora la computazione $T(x)$ termina nel suo stato di accettazione. Se $o_{T_2}(x)=q_R$ allora la computazione $T(x)$ termina nello stato di rigetto.   

Siccome $T_1$ decide $L_1$ il passo 1. termina sempre. Siccome $T_2$ decide $L_2$ anche il passo 2. termina sempre. E se $x \in L_1$ allora $x\in L_1 \cup L_2$, se $x \in L_2$ allora $x\in L_1 \cup L_2$, se $x \notin L_1$ o $x \notin L_2$ $ x \notin L_1 \cup L_2$.

Dunque $T \text{ decide } L_1 \cup L_2$  

## Problema 2  

Sia $\Sigma$ un alfabeto (finito) e siano $L_1 \subseteq \Sigma^∗$ e $L_2 \subseteq \Sigma^∗$ due linguaggi accettabili. Dimostrare che $L_1 \cap L_2$ è accettabile.  

#### dim

Siccome   

$L_1 \text{ è accettabile } \implies \exist T_1: \forall x \in \Sigma^*[o_{T_1}(x)=q_A \iff x \in L \space \land \space o_{T_1}(x)\neq q_A \iff x \notin L_1 ]$  

e  

$L_2 \text{ è accettabile } \implies \exist T_2: \forall x \in \Sigma^*[o _{T_2}(x)=q_A \iff x \in L \space \land \space o_{T_2}(x) \neq q_A \iff x \notin L ]$    

Per dimostrare che $L_1 \cap L_2$ è acettabile, dobbiamo creare una macchina di Turing $T$ che accetta $L_1 \cup L_2$.    

Deriviamo da $T_1$ e $T_2$ la nuova macchina di Turing $T$ a due nastri, sui quali è scritto l'input $x \in \Sigma^*$. Esso lavora su alfabeto $\Sigma$, l'insieme degli stati $Q=Q_1\cup Q_2 \cup \set{q_{AT},q_{RT}}$ dove $Q_1$ è l'insieme degli stati della macchina $T_1$ e $Q_2$ è l'insieme degli stati della macchina $T_2$ e $q_AT, q_RT \notin Q_1 \cup Q_2$. La computazione $T(x)$ opera nel seguente modo:  

1. Viene simulata la computazione $T_1(x)$ usando il primo nastro come nastro di lavoro . Se questa termina nel suo stato di accettazione, allora la computazione $T(x)$ viene eseguito il passo 2. Se $T_1(x)$ termina nel suo stato di rigetto allora la computazione termina nel suo stato di rigetto $q_{RT}$.  
<br>
2. Viene simulata la computazione $T_2(x)$ usando il secondo nastro come nastro di lavoro. Se questa termina nel suo stato di accettazione, allora la computazione $T(x)$ termina in  Se $T_2(x)$ termina nel suo stato di rigetto allora la computazione termina nel suo stato di rigetto $q_{RT}$.    

Si osservi come non si può dire nulla nel caso $x \notin L_1$ o $x \notin L_2$, nel caso $T(x)$ non termini.

Se $x \in L_1$ e $x \in L_2$ allora $x \in L_1 \cap L_2$ allora la computazione $T(x)$ termina nello stato di accettazione. Dunque $T$ accetta $L_1 \cap L_2$  

### Problema 3  

Sia $\Sigma$ un alfabeto (finito) e siano $L_1 \subseteq \Sigma^∗$ un linguaggio accettabile e $L_2 \subseteq \Sigma^∗$ un linguaggio decidibile. Cosa si può dire rispetto all'accettabilità/decidibilità del linguaggio $L=L_1 \cap L_2$ è accettabile? Motivare una risposta con una dimostrazione.  

#### dim  


$L_1 \text{ è accettabile } \implies \exist T_1: \forall x \in \Sigma^*[o_{T_1}(x)=q_A \iff x \in L \space \land \space o_{T_1}(x)\neq q_A \iff x \notin L_1 ]$  


$L_2 \text{ è decidibile } \implies \exist T_2: \forall x \in \Sigma^*[o _{T_2}(x)=q_A \iff x \in L \space \land \space o_{T_2}(x)=q_R \iff x \notin L ]$     

Dimostriamo anzitutto l'accettabilità di $L$.  

Per farlo deriviamo una nuova macchina di Turing $T$ che accetta $L$, essa utilizza due nastri sui quali viene scritto l'input $x \in \Sigma^*$. Lavora su alfabeto $\Sigma$ e con stati $Q=Q_1 \cup Q_2 \cup \set{q_{AT},q_{RT}}$. Con $Q_1$ insieme deli stati della macchina $T_1$ e $Q_2$ insieme deli stati della macchina $T_2$ e $q_{AT}, q_{RT} \notin Q_1 \cup Q_2$. La computazione $T(x)$ opera nel seguente modo:  

1. Viene simulata la computazione $T_1(x)$ che termina sempre: se la computazione termina nello stato di rigetto la computazione $T(x)$ termina nel suo stato di rigetto $q_{RT}$. Se $T_1(x)$ termina nel suo stato di accettazione allora viene eseguito il passo 2. 
<br>  
1. Viene simulata la computazione $T_2(x)$ : se $T_2(x)$ termina nel suo stato di accettazione allora la computazione $T(x)$ termina nello stato di accettazione.  Dunque $T$ accetta $L$

Siccome se $x \in L_1$ e $x \in L_2$ $x \in L$ , $T$ accetta $L$, $L$ è accettabile. Siccome non possiamo dedurre nulla se $x \notin L$ (siccome la computazione $T(x)$ in quel caso potrebbe anche non terminare), $L$ non è decidibile.   

### Problema 4  

Siano $L_1 \subseteq \Sigma^*$ e $L_2 \subseteq \Sigma^*$ due linguaggi decidibile e sia  

$L=\set{x \in L_1: \exist k \in \N [ |x |=2k]} \cup \set{x \in L_2: \exist k \in \N [|x|=2k+1]}$  

Dimostrare che $L$ è decidibile  

#### dim  







## Problema 5  

Siano $L_1 \subseteq \Sigma^*$ un linguaggio decidibile e $L_2 \subseteq \Sigma^*$ un linguaggio accettabile. Cosa si può dedurre circa
le proprietà di accettabilità decidibilià di $L = L_1 − L^c_2$?
(Si ricordi che $L^c_2$ è il linguaggio complemento di $L_2$)  

#### dim  

Osserviamo che $L_1 - L^c_2= L_1 \cap L_2$ in quanto  

$L_1 - L^c_2= \set{x \in L_1 : x \notin L^c_2} = \set{x \in L_1: x \in L_2}$  

Dimostriamo l'accettabilità di $L$


$L_1 \text{ è decidibile } \implies \exist T_1: \forall x \in \Sigma^*[o _{T_1}(x)=q_A \iff x \in L_1 \space \land \space o_{T_1}(x)=q_R \iff x \notin L_1 ]$     

$L_2 \text{ è accettabile } \implies \exist T_2: \forall x \in \Sigma^*[o _{T_2}(x)=q_A \iff x \in L_2 \space \land \space o_{T_2}(x) \neq q_A \iff x \notin L_2 ]$  

Deriviamo una nuova macchina di Turing $T$ che accetta $L$, essa utilizza due nastri sui quali viene scritto l'input $x \in \Sigma^*$. Lavora su alfabeto $\Sigma$ e con stati $Q=Q_1 \cup Q_2 \cup \set{q_{AT},q_{RT}}$. Con $Q_1$ insieme deli stati della macchina $T_1$ e $Q_2$ insieme deli stati della macchina $T_2$ e $q_{AT}, q_{RT} \notin Q_1 \cup Q_2$. La computazione $T(x)$ opera nel seguente modo:  

1. Viene simulata la computazione $T_1(x)$ che termina sempre: se la computazione termina nello stato di rigetto la computazione $T(x)$ termina nel suo stato di rigetto $q_{RT}$. Se $T_1(x)$ termina nel suo stato di accettazione allora viene eseguito il passo 2. 
<br>  
1. Viene simulata la computazione $T_2(x)$ : se $T_2(x)$ termina nel suo stato di accettazione allora la computazione $T(x)$ termina nello stato di accettazione.  Dunque $T$ accetta $L$

Siccome se $x \in L_1$ e $x \in L_2$ $x \in L$ , $T$ accetta $L$, $L$ è accettabile. Siccome non possiamo dedurre nulla se $x \notin L_2$ e dunque $x \notin L$ (siccome la simulazione della computazione $T_2(x)$ in quel caso potrebbe anche non terminare), $T$ non decide $L$, dunque $L$ non è decidibile.       

## Problema 6  

Siano $L_1 \subseteq \N \times \N$ un liguaggio decidibile e $L_H \subseteq \N \times \N$ il linguaggio che definisce l’Halting Problem.
Dimostrare se il linguaggio

$L_2 = L_1 − L^c_H$

è accettabile (ove, ricordiamo, $L^c_H$ è il linguaggio complemento di $L_H$ ).  


Osserviamo che $L_1 - L^c_H= L_1 \cap L_H$ in quanto  

$L_1 - L^c_H= \set{x \in L_1 : x \notin L^c_H} = \set{x \in L_1: x \in L_H}$  

Dimostriamo l'accettabilità di $L$

#### <span style="color:red">TEOREMA </span>  

$L_H$ è un linguaggio accettabile.  

Siccome   

$L_1 \text{ è decidibile } \implies \exist T_1: \forall (i,x) \in \N \times \N[o _{T_1}((i,x))=q_A \iff (i,x) \in L_1 \space \land \space o_{T_1}((i,x))=q_R \iff (i,x) \notin L_1 ]$     

$L_H \text{ è accettabile } \implies \exist T_H: \forall (i,x) \in \N \times \N[o _{T_H}((i,x))=q_A \iff (i,x) \in L_H \space \land \space o_{T_H}((i,x)) \neq q_A \iff (i,x) \notin L_H ]$   

Da $T_1$ e $T_H$ deriviamo una nuova macchina di Turing $T$ che accetta $L$.  

Essa utilizza due nastri sui quali viene scritto l'input $(i,x) \in \N \times \N$. Con stati $Q=Q_1 \cup Q_2 \cup \set{q_{AT},q_{RT}}$. Con $Q_1$ insieme deli stati della macchina $T_1$ e $Q_2$ insieme deli stati della macchina $T_2$ e $q_{AT}, q_{RT} \notin Q_1 \cup Q_2$. La computazione $T(x)$ opera nel seguente modo:  

1. Viene simulata la computazione $T_1((i,x))$ che termina sempre: se la computazione termina nello stato di rigetto la computazione $T((i,x))$ termina nel suo stato di rigetto $q_{RT}$. Se $T_1((i,x))$ termina nel suo stato di accettazione allora viene eseguito il passo 2. 
<br>  
1. Viene simulata la computazione $T_H((i,x))$ : se $T_H((i,x))$ termina nel suo stato di accettazione allora la computazione $T((i,x))$ termina nello stato di accettazione.

Siccome se $(i,x) \in L_1$ e $(i,x) \in L_H$ $(i,x) \in L$ , $T$ accetta $L$, $L$ è accettabile.      





