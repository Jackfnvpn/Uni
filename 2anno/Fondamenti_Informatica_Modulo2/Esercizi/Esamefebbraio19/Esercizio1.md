## EX 1  

Sia $L \subseteq \Sigma^*$ un linguaggio e $\chi_L: \Sigma^* \rightarrow \set{0,1}$ la funzione tale che:  

$
\chi_L(x) =
\begin{cases} 
1 & \text{se } x \in L, \\
0 & \text{altrimenti}
\end{cases}
$

Dimostrare che $L$ è decidibile se e soltanto se $\chi_L$ è calcolabile  

### dim  

$(\implies)$ 

Siccome $L$ è decidibile allora $\exist \text{ una macchina di Turing riconoscitore } T: \forall x \in \Sigma^*[o_T(x)=q_A \iff x \in L \space \land \space o_T(x)=q_R \iff x \notin L]$  

Da $T$ deriviamo una macchina di Turing trasduttore $T_f$ che per ogni $x \in \Sigma^*$ calcola $\chi_L(x)$. Essa utilizza un nastro (oltre quello di output) sul quale viene scritto l'input $x \in \Sigma^*$. La computazione $T_f(x)$ opera nel seguente modo:  

Viene simulata la computazione $T(x)$: Se la computazione termina nello stato di accettazione, e dunque $x \in L$, viene scritto sul nastro di output 1. Se $T(x)$ termina nello stato di rigetto, dunque $x \not in L$ allora viene scritto sul nastro di output 0.  

Siccome $L$ è un linguaggio decidibile, la computazione $T(x)$ termina sempre. E se $x \in L$ sul nastro di output viene scritto 1, altrimenti 0. Dunque $T_f$ calcola $\chi_L$. Dunque $\chi_L$ è calcolabile 

$(\impliedby)$  

Siccome $\chi_L$ è calcolabile allora $\exist \text{ una macchina di Turing trasduttore } T_f': \text{ che per ogni }  x \in \Sigma^* \text{ calcola il valore } \chi_L(x) \text{ e scrive il valore sul nastro di output se e soltanto se } \chi_L \text{ è definita}$.  

Osserviamo che $\chi_L$ è una funzione totale. Dunque definita per ogni valore del suo dominio.  

Da $T_f'$ deriviamo una nuova macchina di Turing riconoscitore $T'$ che decide $L$.  

Essa utilizza due nastri, sul primo nastro viene scritto l'input $x \in \Sigma^*$. La computazione $T'(x)$ opera nel seguente modo:  

Viene simulata la computazione $T_f(x)$: Se la computazione termina scrivendo 1 sul secondo nastro , la computazione $T'(x)$ terminerà nello stato di accettazione. Se $T_f(x)$ termina scrivendo sul secondo nastro 0, la computazione $T'(x)$ terminerà nello stato di rigetto.  

Siccome $\chi_L$ è una funzione totale, la computazione $T_f(x)$ terminerà sempre per ogni $x \in \Sigma^*$. Inoltre se $\chi_L(x)=1$ allora $T(x)$ terminerà nello stato di accettazione e dunque $x \in L$. Se $\chi_L(x)=0$ allora $T(x)$ terminerà nello stato di rigetto e dunque $x \notin L$. Dunque $T'$ decide $L$. Dunque $L$ è decidibile. 