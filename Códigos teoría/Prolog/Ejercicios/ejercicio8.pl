%Basado en el ejemplo de paises.pl visto en clase, complete la base de
% conocimientos

%pais_superficie(P,A)

% con todos los países de latinoamérica y codifique las reglas prolog que
% permitan encontrar el país de mayor superficie.

pais(argentina).
pais(chile).
pais(brasil).
pais(peru).
pais(bolivia).
provincia(uruguay).

pais_superficie(argentina, 1).
pais_superficie(chile, 2).
pais_superficie(brasil, 3).
pais_superficie(peru, 4).
pais_superficie(bolivia, 5).
pais_superficie(uruguay, 6).

producto_cartesiano(X,Y):-pais_superficie(_,X),pais_superficie(_,Y).
seleccion_mayor(X,Y):-producto_cartesiano(X,Y),X<Y.
proyeccion(X):-seleccion_mayor(X,_).
%La proyeccion se hace CON  LA SUPERFICIE
mayor_superficie(X):-pais_superficie(X,Y),not(proyeccion(Y)).

