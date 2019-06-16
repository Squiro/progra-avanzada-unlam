quiere(pedro,maria).
quiere(pedro,belen).
quiere(manuel,belen).
quiere(maria,pedro).

quiere(_,juan):-!.
quiere(_,maria):-!.
quiere(X,X):-!.

alguien_quiere(X,juan,pedro):-quiere(X,juan),quiere(X,pedro).
alguien_quiere(X,maria):-quiere(X,maria).
%alguien_quiere(X,Y):-alguien































