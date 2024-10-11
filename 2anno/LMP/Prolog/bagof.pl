%bagof il primo elemento è la struttura che dovrà essere nella lista finale,
%il secondo è il predicato da analizzare, il terzo e la lista completa

n(11,a).
n(11,r).
n(4,k).
n(5,g).
n(8,f).
n(4,f).
n(8,d).

l(L):-
n(_,L).

num(L):-
    n(L,_).


%bagof(N,(L,B)^(N,L,B),Lista). 

/*Creare un predicato che conti quante lettere diverse ci sono in posizione X*/

nld(N):-
    setof(X,M^n(M,X),Lista),
    length(Lista,N).




