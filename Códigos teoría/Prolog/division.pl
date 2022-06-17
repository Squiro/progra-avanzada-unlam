
restas_sucesivas(X,Y,Res):-X<Y,Res is 0,!.
restas_sucesivas(X,Y,Res):-X1 is X-Y,
    restas_sucesivas(X1,Y,Res1), Res is Res1+1.

division(X,Y,Res):-Y>0,
    restas_sucesivas(X,Y,Res1), Res is Res1.
