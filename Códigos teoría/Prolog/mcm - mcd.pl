mcd(X,0,R):-R is X,!.
mcd(X,Y,R):-Z is mod(X,Y),mcd(Y, Z, R1),R is R1.

mcm(X,Y,R):-mcd(X,Y,R1),R is (X*Y)/R1.
