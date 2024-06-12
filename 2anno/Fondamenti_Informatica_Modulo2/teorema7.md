## Teoremi Dispensa 7


### <span style="color:red"> Teorema  </span>

Sia $Γ = \lang I_Γ, S_Γ, π_Γ\rang$ un problema decisionale e sia $χ : I_Γ → Σ^∗$ una sua codifica ragionevole. Se $χ(I_Γ) ∈ \bold{P}$ , allora $L_Γ(χ) ∈ \bold{NP} ⇒ L_{Γ^c}(χ) ∈ \bold{coNP}$

#### <span style="color:yellowgreen"> dim </span>

Poichè $χ(I_Γ) ∈ \bold{P}$, allora esistono una macchina di Turing deterministica $T$ ed un intero $h$ tali che, per
ogni $x ∈ Σ^∗$, $T$ decide se $x ∈ χ(I_Γ)$ e $dtime(T, x) ∈ O(|x|^h)$.

Se $L_Γ(χ) ∈ \bold{NP}$, allora esistono una macchina di Turing non deterministica $NT_Γ$ ed un intero $k$ tali che, per ogni $x ∈ L_Γ(χ)$, $NT_Γ$ accetta $x$ e $ntime(NT_Γ, x) ∈ O(|x|^k)$. 

Combinando $T$ e $NT_Γ$, deriviamo una nuova macchina non deterministica $NT'$ che, con input $x ∈ Σ^∗$, opera in due fasi, come di seguito descritto.

1. Simula la computazione $T(x)$: se $T(x)$ termina nello stato di rigetto, allora $NT'$ termina nello stato di accettazione, altrimenti ha inizio la 2.
2. Simula la computazione $NT_Γ(x)$.  

Quindi, $NT'(x)$ accetta se e soltanto se $x \notin χ(I_Γ)$ oppure $x ∈ L_Γ(χ)$, ossia, se e soltanto se $x$ appartiene al linguaggio complemento di $L_{Γ^c}(χ)$. Inoltre, è semplice verificare che $ntime(NT', x) ∈ O(|x|^{max\set{h,k}})$. 
In conclusione, il linguaggio complemento di $L_{Γ^c}(χ)$ è in $\bold{NP}$, e dunque $L_{Γ^c}(χ) ∈ \bold{coNP}$. 

