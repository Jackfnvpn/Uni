% Algoritmo di risoluzione del prolog
% ----> Albero delle dimostrazioni.
/* edge(S,E) */

edge(a,b).
edge(b,c).
edge(c,d).
edge(a,e).
edge(e,f). 
edge(f,k).
edge(f,c).

%PB
path(X,Y):-
    edge(X,Y).
%PI
path(X,Y):-    
    edge(X,Z), 
    path(Z,Y). 

/* 
X=a, Y=d, Z=b
edge(a,b),
>>>> path(b,d).
				
X=b, Y=d, Z=c
>>> edge(b,c),
path(c,d).
*/




				
			   
