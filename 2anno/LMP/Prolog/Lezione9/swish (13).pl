e(a,b).
e(b,c).
e(d,e).
e(d,f).

bfs(A,B):-
    e(A,B).

bfs(A,B):-
    e(A,X),
    pf([A],PF),
    member(X,PF),
    bfs(X,B).

pf([],[]).
pf([X|R],PF):-
    setof(B,e(X,B),FiglX),
    pf(R,FiglR),
    append(FiglX,FiglR,PF).
    

pf2([], []).
pf2([H|T], F) :-
    setof(Z, e(H, Z), RAG_Z),
    pf(T, PF),
    append(RAG_Z, PF, F).

pathBFS(X, Y) :-
    opf([X], FF, Y). % se esiste una frontiera in cui c'e Y raggiungibile da [X]

opf(F, FR, Y) :-
    pf2(F, FR),
    member(Y, FR).
opf(F, FR, Y) :-
    pf2(F, FRZ),
    opf(FRZ, FR, Y).



