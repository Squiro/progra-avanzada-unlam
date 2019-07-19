formas(1,1):-!.
formas(2,2):-!.
formas(3,4):-!.
formas(X,F):- X1 is X-1, formas(X1,F1), X2 is X-2, formas(X2,F2), X3 is X-3, formas(X3,F3), F is F1+F2+F3.