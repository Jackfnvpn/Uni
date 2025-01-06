% fatti
edge(a, b).
edge(a, e).
edge(b, c).
edge(e, f).
edge(f, c).
edge(c, d).
edge(f, k).
edge(c, a).
edge(c, d).
edge(d, a).

cammino(X,Z):-
    edge(X,Z).
cammino(X,Z):-
    edge(X,Y),
	cammino(Y,Z).

cycle(X) :-
    edge(X, Y),
    edge(Y, X).

cycle(X) :-
    edge(X, Y),
    cammino(Y, X).