% Dichiarazione delle vocali
vocale("a").
vocale("e").
vocale("i").
vocale("o").
vocale("u").

getVocale([],0).

getVocale(t(R,[]),0):-
    \+ vocale(R).

getVocale(t(R,[]),1):-
    vocale(R).

getVocale(t(R,Children),Count):-
    vocale(R),
    getVocale(Children,N2),
    Count is N2+1.

getVocale(t(R,Children),Count):-
    \+ vocale(R),
    getVocale(Children,Count).

getVocale([H|T],M):-
    getVocale(H,A),!,
    getVocale(T,B),!,
    M is A+B.

leaf([],0).
leaf(t(_,[]),1).

leaf(t(_,Children),Count):-
    leaf(Children,Count).

leaf([H|T],Count):-
    leaf(H,N1)!,
    leaf(T,N2)!,
    Count is N1+N2.


    