## Ex 3

Sia $L_1 \subseteq \Sigma^*$ e $L_2 \subseteq \Sigma^*$. Si dimostri che se $L_1 \in \bold{NP}$ e $L_2 \in \bold{NP}$ allora $L_1 \cup L_2 \in \bold{NP}$

### dim  

Sia $L_1 \in \bold{NP} \implies \exist \text{ una macchina di Turing non deterministica }NT_1 \text{ che accetta } L_1 \text{ e una costante } k \in \N: \forall x \in \Sigma^*[ntime(NT_1,x) \in O(|x|^k)]$

Sia $L_2 \in \bold{NP} \implies \exist \text{ una macchina di Turing non deterministica }NT_1 \text{ che accetta } L_2 \text{ e una costante } h \in \N: \forall x \in \Sigma^*[ntime(NT_2,x) \in O(|x|^h)]$  

Da $NT_2$ e $NT_1$ deriviamo una nuova macchina di Turing non deterministica ad un nastro $NT'$ che accetta il linguaggio $L_1 \cup L_2$. Essa opera su alfabeto $\Sigma^*$. E con input $x \in \Sigma^*$ (scritto sul nastro della macchina) la computazione $NT'(x)$ opera nel seguente modo:  

1. Viene simulata la computazione $NT_1(x)$ eseguendo una singola istruzione, ovvero una quintupla di $NT_1$. Se questa porta la macchina $NT_1$ nello stato di accettazione la computazione $NT'(x)$ terminerà nello stato di accettazione. Altrimenti viene eseguito il passo 2.  
2. Viene simulata la computazione $NT_1(x)$ eseguendo una singola istruzione, ovvero una quintupla di $NT_1$. Se questa porta la macchina $NT_1$ nello stato di accettazione la computazione $NT'(x)$ terminerà nello stato di accettazione. Altrimenti viene eseguito il passo 2.   

Se $x \in L_1$, allora la computazione $NT'(x)$, prima o poi terminerà nel passo 1. Se $x \in L_2$ prima o poi terminerà nello stato di accettazione nel passo 2. Dunque $NT'$ accetta $L$. Dobbiamo ora mostrare che lo fa in tempo polinomiale in |x|:  

Siccome $NT_1$ accetta in $ntime(NT_1,x) \in O(|x|^k)$ passi e $NT_2$ accetta in $ntime(NT_2,|x|^h)$ passi. Allora la computazione $NT(x)$ terminerà nello stato di accettazione in $O(min(|x|^h,|x|^k))$ passi.  
Siccome $h$ e $k$ sono due costanti, allora $NT'$ accetta $L_1 \cup L_2$ in tempo polinomiale in $|x|$.
