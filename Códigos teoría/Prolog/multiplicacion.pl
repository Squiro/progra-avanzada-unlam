multiplicacion(X,1,X):-!.
multiplicacion(X,Y,R):-Y1 is Y-1, multiplicacion(X,Y1,R1), R is X+R1.
