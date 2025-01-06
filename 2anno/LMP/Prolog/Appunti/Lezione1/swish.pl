% genitore(parent, child)

% fatti
genitore(mario, dario).
genitore(mario, luca).
genitore(pino, rino).
genitore(luca, pino).

% Per ogni x e y (x è frattello di y) se esiste z per cui z è genitore di x e z è genitore di y e x e y sono diversi
fratello(X, Y) :- 
    genitore(Z, X),
    genitore(Z, Y),
    X\=Y.

nonno(X, Y) :- 
    genitore(X, Z), 
    genitore(Z, Y).

% induzione 
avo(X, Y):- 
    genitore(X, Y).

avo(X, Y) :-
    genitore(X, Z), 
    avo(Z, Y).