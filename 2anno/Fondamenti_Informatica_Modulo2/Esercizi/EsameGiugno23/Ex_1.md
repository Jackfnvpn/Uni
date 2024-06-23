$L_1$ e $L_2$ due linguaggi decidibili.  

Sia $L=\set{x:|x|=2k \ \land \ x\in L_1 } \ \cup \ \set{|x|=2k+1 \ \land \ x\in L_2}$  

$L$ è decidibile?


Sia $L_1$ decidbile $\implies \exist T_1: \forall x \in \Sigma^*_1[o_{T_1}(x)=q_A \iff x \in L_1 \ \land \ o_{T_1}(x)=q_R \iff x \notin L_1]$  

Sia $L_2$ decidbile $\implies \exist T_2: \forall x \in \Sigma^*_1[o_{T_2}(x)=q_A \iff x \in L_2 \ \land \ o_{T_2}(x)=q_R \iff x \notin L_2]$    

Da $T_1$ e $T_2$ derivo $T$ che decide $L$. Essa utilizza un nastro solo, sul quale viene scritto l'input $x \in \Sigma^*$. L'insieme dei suoi stati $Q=Q_1\cup Q_2 \cup \set{q_p,q_d,q_p^{ind},q_d^{ind},q_A',q_R'}$, nell'insieme delle sue quintuple sono codificate le quintuple di $T_1$ e di $T_2$ oltre le seguenti:  

$\lang q_p, a,a,q_d, dx \rang \ \forall a \in \Sigma^*$  
$\lang q_d, a,a,q_p,dx \rang \ \forall a \in \Sigma^*$

$\lang q_p, \square,\square,q_d^{ind},sx \rang$
$\lang q_d, \square,\square,q_p^{ind},sx \rang$

$\lang q_d^{ind}, a,a, q_d^{ind} ,sx\rang \ \forall a \in \Sigma^*$  
$\lang q_p^{ind}, a,a, q_p^{ind} ,sx\rang \ \forall a \in \Sigma^*$

$\lang  q_d^{ind}, \square,\square,q_d,dx \rang$
$\lang q_p^{ind}, \square,\square,q_p,dx\rang$

La computazione $T(x)$ opera nel seguente modo:  
1. Verifica se la lunghezza dell'input $x$ sia pari o dispari.

2. Se pari (dunque la fase 1 termina con la computazione $T(x)$ nello stato $q_p$) simula la computazione $T_1(x)$, se questa termina nello stato di accettazione allora $T(x)$ termina nel suo stato di accettazione, altrimenti $T$ rigetta.  
Se dispari (dunque la fase 1 termina con la computazione $T(x)$ nello stato $q_d$) simula la computazione $T_2(x)$, se questa termina nello stato di accettazione allora $T(x)$ termina nel suo stato di accettazione, altrimenti $T$ rigetta.  

Dunque $T$ decide $L$. $L$ è decidibile.  



