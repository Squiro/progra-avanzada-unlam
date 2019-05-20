numero(1).
numero(2).
numero(3).

%Selecciona aquellos que sean mayores

producto_cartesiano(X,Y):-numero(X),numero(Y).
%Busco todos los X que sea menores que Y
seleccion(X,Y):-producto_cartesiano(X,Y),X<Y.
proyeccion(X):-seleccion(X,_).
%A los numeros X les resto aquellos de la proyeccion (los menores)
mayor(X):-numero(X),not(proyeccion(X)).
