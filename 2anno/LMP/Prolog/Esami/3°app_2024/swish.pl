stessaStruttura(P1,P2,L):-
    P1=(Testa1:-Corpo1),
    P2=(Testa2:-Corpo2),
    stsArg([Testa1],[Testa2]),
    stsCorpo(Corpo1,Corpo2,L),!.

listifica(',', (H, T), [H | L]) :-
    !,
    listifica(',', T, L).

listifica(_, H, [H]) :-
    H \= (_, _). 
    
stsCorpo(Corpo1, Corpo2, ListaCorpo) :-
    Corpo1 =.. [Funt1 | _],
    Corpo2 =.. [Funt2 | _],
    listifica(Funt1, Corpo1, Lista1),
    listifica(Funt2, Corpo2, Lista2),
    
    length(Lista1,N1),
    length(Lista2,N2),
    N1 = N2,

	stsArg(Lista1,Lista2).

stsArgomenti([],[]).
stsArgomenti([_],[]):- !,fail.
stsArgomenti([],[_]):- !,fail.
stsArgomenti([_|T],[_|Y]):-
    stsArgomenti(T,Y).
    

stsArg([],[]).
stsArg([H|T],[X|Y]):-
    H=..[_|D],
    X=..[_|E],
    stsArgomenti(D,E),
    stsArg(T,Y).


    
    
    
    