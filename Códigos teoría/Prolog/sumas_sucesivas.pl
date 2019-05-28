
suma_sucesiva(X,Y,X,Res):-Res is Y,!.
suma_sucesiva(X,Y,Iter, Res):-Iter1 is Iter+1
,suma_sucesiva(X,Y,Iter1,Res1), Res is Res1+Y.

producto(X,Y,Resultado):-X>0,Y>0,suma_sucesiva(X,Y,1,Res),
    Resultado is Res.
