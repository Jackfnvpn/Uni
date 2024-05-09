# NOT  
$\neq / =$ 

amici(mario,maria).  
amici(mario,dario).  
amici(mario,pino).  

?- not amici(mario,pino) NO!!! Non esiste questa scrittura.  

**! = cut** (taglia un ramo nell'albero di ricerca del prolog)  

**fail.** ci fa guardare un altro ramo  

Dato:  

$P:-$ $a,!b,c$ (il cut taglia l'albero di risoluzione del prolog, in che modo ad ogni punto di questi possiamo scegliere differente opzioni se andando avanti otteniamo un fail possiamo tornare indietro, con il cut non possiamo più tornare indietro prima del cut (non possiamo tornare ad a) non posso neanche più riverificare P);  


JACK?? DOVE SEI????
