# Calcolo Relazionale  
Con il termine *calcolo relazionale* si fa riferimento a una famiglia di linguaggi di interrogazione, basati sul calcolo dei predicati del primo ordine, che hanno la caratteristica di essere *dichiarativi*, cioè di specificare le proprietà del risultato delle interrogazioni anziché la procedura seguita per generarlo.  

## Calcolo relazionale su domini  

Le espressioni del calcolo relazionale sui domini hanno la forma:  
{$A_1: x_1,...,A_k:x_k | f$}  

dove:  

+ $A_1,...,A_k$ sono attributi distinti;  
+ $x_1,...,x_k$ sono *variabili*;  
+ $f$ è una formula, secondo le seguenti regole:  
  - Vi sono formule *atomiche*, di due tipi:  
    - $R(A_1: x_1,...,A_p:x_p)$, dove $R(A_1,...,A_p)$ è uno schema di relazione e $x_1,...,x_p$ sono variabili;  
    - $x\theta y$ o $x\theta c$, con $x$ e $y$ variabili , $c$ costante, $\theta$ *operatore di confronto*  
  - Se $f_1$ e $f_2$ sono formule, allora $f_1 \lor f_2, f_1 \land f_2$ e $\lnot f_1$;  
  - Se $f$ è una formula
