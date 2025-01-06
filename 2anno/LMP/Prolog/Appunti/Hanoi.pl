ord([]).
ord([_]):-!.
ord([X,Y|T]):-
    X < Y,
    ord([Y|T]).


/*e(h([X|A],B,C),h(A,[X|B],C)):-
    ord([X|A]),ord([X|B]),ord(C).*/