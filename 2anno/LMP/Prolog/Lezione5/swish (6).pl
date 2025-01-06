/*
Abbiamo individuato un business molto interessante: vendere sogni alle persone. Si vuol far credere che il 
futuro delle persone dipenda dall’uso delle vocali all’interno dell’oroscopo per il loro segno zoodiacale. La 
giornata è positiva se nell’oroscopo il la frequenza media delle vocali è esattamente uguale alla frequenza 
media delle consonanti. 
Si vuole dunque definire un predicato prolog che consenta di calcolare la frequenza media delle vocali e 
quella delle consonanti e di un altro che poi permetta di dire se una giornata è fortunata
*/
vocali(a).
vocali(e).
vocali(i).
vocali(o).
vocali(u).

myNot(P):-
    P,
    !,
   	fail.
myNot(_).

mediaVocali([],0).
mediaVocali([H|T],NV):-
    vocali(H),
    mediaVocali(T,M),
    NV is M+1.

mediaVocali([_H|T],M):-
    %myNot(vocali(H)),
    mediaVocali(T,M).

mediaConsonanti([],0).
mediaConsonanti([H|T],NC):-
    myNot(vocali(H)),
    mediaConsonanti(T,M),
    NC is M+1.

mediaConsonanti([_H|T],M):-
    %vocali(H),
    mediaConsonanti(T,M).

giornataF(L):-
    mediaConsonanti(L,NC),
    mediaVocali(L,NV),
    !,
    NV/4 =:= NC/4.

giornataFortunata(L):-
    giornataF(L),
    write("Giornata Fortunata").
    
    

    