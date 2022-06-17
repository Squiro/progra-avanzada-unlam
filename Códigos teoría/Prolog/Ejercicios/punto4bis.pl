parcial1(ana,7).
parcial1(juan,4).
parcial1(alesio,5).
parcial1(pepe,10).
parcial1(jose,2).
parcial2(alesio,7).
parcial2(ana,9).
parcial2(juan,8).
parcial2(pepe,10).
parcial2(jose,1).


promedio(Y,Z,Prom):-Prom is(Z+Y)/2.

promociona(X,Prom):-parcial1(X,Y),parcial2(X,Z),Y>=7,Z>=7,promedio(Y,Z,Prom).

cursada(X):-parcial1(X,Y),parcial2(X,Z),Y>=4,Z>=4,not(promociona(X,_)).

recursan(X):-parcial1(X,Y),parcial2(X,Z),Y=<3,Z=<3.

%Obtiene los alumnos sin que me interesen las notas
% _: variables anonimas
%
alumnos(X):-parcial1(X,_),parcial2(X,_).

% Obtener el maximo en algebra relacional

producto_cartesiano(X,Y):-promociona(_,X),promociona(_,Y).
%Busco todos los X que sea menores que Y
seleccion(X,Y):-producto_cartesiano(X,Y),X<Y.
proyeccion(X):-seleccion(X,_).
%A los numeros X les resto aquellos

medalla(X,Prom):-
    promociona(X,Prom),not(proyeccion(Prom)).

ausentes(X):-parcial1(X,_),not(parcial2(X,_)).
ausentes(X):-parcial2(X,_),not(parcial1(X,_)).
