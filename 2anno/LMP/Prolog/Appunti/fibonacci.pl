:-dynamic f/2.
f(1,1) :- !.
f(2,1) :-!.

f(N,M):-
    write(n),nl,
    X is N-1,
    Y is N-2,
    X > 0, 
    Y > 0,
    f(X,M1),
    f(Y,M2),
    M is M1+M2,
    asserta(f(N,M):- !).
