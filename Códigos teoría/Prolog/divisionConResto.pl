division(X,Y,Co,Resto):-X<Y, Resto is X, Co is 0.
division(X,Y,Co,Resto):-X1 is X-Y, division(X1,Y,C,R1), Resto is R1, Co is 1+C.
