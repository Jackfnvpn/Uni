:-dynamic appoggio/1.

n(1).
n(3).
n(7).
n(11).

appoggio([]).


numeri(L):-
    n(A),
    appoggio(L),
    append(L,[A],LN),
    retract(appoggio(L)),
    assert(appoggio(LN)),
    write(A),nl,
    fail.

numeri(L):-
    appoggio(L),
    %retract(appoggio(L)),
    %assert(appoggio([])),
    write(fine),nl.



