lung([],0).
lung([_|T],L):-
    lung(T,A),
    L is A+1.

%% numero di volte in cui Elemento appare in L

occVocE(0,[],_).
occVocE(N,[E|T],E):-
    occVocE(NP,T,E),
    N is NP+1.
occVocE(N,[H|T],E):-
    H\=E,
    occVocE(N,T,E).

	
	