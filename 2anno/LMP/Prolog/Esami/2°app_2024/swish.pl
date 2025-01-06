:- op(100,fx,il).
:- op(200,xfx,si).
:- op(100,xfx,di).

:- op(200,xfx,del).
:- op(201,xfx,è).
:- op(100,fx,la).

:- op(200,xfx,mangia).
:- dynamic mangia/2.
    
il fragola si nutre di biada.
il cibo del cammello è la biada.
il cavallo mangia la fragola.
il ragno si nutre di cavallo.



mangiare(X,C):-
    il X si _ di C,
    assert(mangia(X,C)).

mangiare(X,C):-
    il _ del X è la C,
    assert(mangia(X,C)).

mangiare(X,C):-
    il X mangia la C,
    assert(mangia(X,C)).

distInq(X,C,N):-
    dist(X,C,N1),
    N is 0.81**N1,!.

dist(X,C,1):-
    mangiare(X,_),
    mangia(X,C).
dist(X,C,M):-
    mangiare(X,_),
    mangia(X,Z),
    dist(Z,C,N),
    M is N+1.
    

