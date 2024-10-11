f(b).
f(a).
g(a).
g(b).
g(j).
k(a).

/*
p(A):-  
  f(A),
%  write('10: '),write(A),nl,
  !,
  g(A),
%  write('13: '),write(A),nl,
  k(A).


fail_g(A):-
  g(A),!,fail.
% fail. 

fail_g(_).
*/

% Negation as failure

mynot(P):-
  P,!,fail.  

mynot(_).  

num_elementi(_,[],0).

num_elementi(X,[X|L],N):-
  !,
  num_elementi(X,L,N1),  
  N is N1+1. 

num_elementi(X,[Y|L],N):-
  %X =\= Y,
  /*Usando bene il cut possiamo fare a meno del not*/
  % \+ (X = Y), % Not
  num_elementi(X,T,N).
