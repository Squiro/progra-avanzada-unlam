

multiplicacion(X,Y,Y,Res):-Res is X,!.
multiplicacion(X,Y,Iter,Res):-Iter1 is Iter+1, multiplicacion(X,Y,Iter1,Res1),
    Res is Res1*X.

potencia(X,Y,Res):-Y>=0,multiplicacion(X,Y,1,Res1), Res is Res1.
