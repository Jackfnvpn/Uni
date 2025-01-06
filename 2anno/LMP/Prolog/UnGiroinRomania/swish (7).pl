città(sibiu).
città(fagaras).
città(rimnicuVilcea).
città(pitesti).
città(craiova).
città(bucarest).
città(giurgiu).
città(urziceni).

strada(sibiu,fagaras,99).
strada(fagaras,sibiu,99).
strada(fagaras,bucarest,211).
strada(bucarest,fagaras,211).
strada(sibiu,rimnicuVilcea,80).
strada(rimnicuVilcea,sibiu,80).
strada(rimnicuVilcea,pitesti,97).
strada(pitesti,rimnicuVilcea,97).
strada(rimnicuVilcea,craiova,146).
strada(craiova,rimnicuVilcea,146).
strada(pitesti,craiova,138).
strada(craiova,pitesti,138).
strada(pitesti,bucarest,101).
strada(bucarest,pitesti,101).

mynot(Predicato) :-
    Predicato, !, fail.
mynot(_).

appartiene(X, [X|_]). 
appartiene(X, [_|T]) :-
    appartiene(X, T).

concatenazione([], A, [A]).
concatenazione([H|T], B, [H|L]) :-
    concatenazione(T, B, L).


camminiRid(X,L):-
    appartiene(X,L),
    fail.
camminiRid(X,L):-
    mynot(appartiene(X,L)).
	
camminoF(X,Y,M):-
    cammino(X,Y,M,[X]).

cammino(X,Y,M,_):-
    strada(X,Y,M).

cammino(X,Y,M,L):-
    strada(X,Z,N2),
    camminiRid(Z,L),
    concatenazione(L,Z,P),
    cammino(Z,Y,N1,P),
    !,
    M is N1+N2.