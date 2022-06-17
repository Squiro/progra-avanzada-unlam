%Supongamos una funcion definida por partes
%         -x+1 si x<0
% f(x) =   1 si 0 <= x <= 1
%          x si x > 1
%


%Poner las condiciones de X primero, en este caso, no afecta

funcion(X,Y):-X<0, Y is -X+1, !.
funcion(X,Y):-X>=0 , X=<1, Y is 1, !.
funcion(X,Y):-X>1, Y is X.

%Prolog se queda esperando hasta evaluar las condiciones de abajo.
%Para resolver esto se introduce el operador de corte !

%Mejoradas
%funcion(X,Y):-Y is X+1, X<0,!.
%funcion(X,1):- X>=0, X<=1;!.
%funcion(X,X):- X>1.

%factorial(X) = 1 si x=0
%             = x*factorial(x-1) para todo otro X


factorial(X,F):-X==0,F is 1,!.
%Declaramos variables nuevas
% X1 && factorial(X1,F1) va a cumplirse solo para ese valor, y nos va a
% devolver un F1. Ese F1 lo multiplicamos por F.
factorial(X,F):-X1 is X-1,factorial(X1,F1),F is X*F1,X>0.

%Mejorada
%factorial(0,1):-!.
%factorial(X,F):-X>0,X1 is X-1, factorial(X1,F1), F is X*F1.
