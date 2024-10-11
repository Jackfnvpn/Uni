%leaf(t(R,[]),R).

leaf(t(R,_),R).

t(a,[t(b,[t(a,[]),t(b,[])]),t(c,[]),t(d,[])]).

leaf(t(R,Child),L):-
  member(C,Child),
  leaf(C,L).

