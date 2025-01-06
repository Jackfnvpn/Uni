n(1,a,b).
n(2,a,c).
n(3,b,d).

nld(N):-
    setof(B,(A,C)^n(A,C,B),L),
    length(L,N).
