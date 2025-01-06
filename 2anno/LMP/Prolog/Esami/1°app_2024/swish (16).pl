:- dynamic matrice/2.


riga([A,B],[A,C],M):-
    abs(B-C)>M.

riga([A,_],[X,_],_):-
    A\=X.

colonna([B,A],[C,A],M):-
    abs(B-C)>M.

colonna([_,A],[_,X],_):-
    A\=X.

diagonale([A,B],[C,D],M):-
    X is abs(A - C),
    Y is abs(B - D),
    X = Y,
    X>M,
    Y>M.

diagonale([A,B],[C,D],_):-
    X is A-C,
    Y is B-D,
    X =\= Y,
    X =\= -Y.


verifica([],_,[_,_],_).
verifica([[S,T]|E],M,[X,Y],V):-
    matrice(X,Y),
    \+member([X,Y],V),
    riga([S,T],[X,Y],M),
    colonna([S,T],[X,Y],M),
    diagonale([S,T],[X,Y],M),
    verifica(E,M,[X,Y],V).

creaMatrice([]).
creaMatrice([[X,Y]|T]):-
    assert(matrice(X,Y)),
    creaMatrice(T).


soluzione(C,D,M,[[X,Y]|T]):-
    creaMatrice(C),
    soluzioneDef(D,M,[[X,Y]|T],[]).
   

soluzioneDef(_,_,[],_).

soluzioneDef(D,M,[[X,Y]|T],V):-
    verifica(D,M,[X,Y],V),
    append(V,[X,Y],Rest),
    soluzioneDef(D,M,T,Rest).
