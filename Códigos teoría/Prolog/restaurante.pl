%clauses

entrada(paella).
entrada(gazpacho).
entrada(consomé).

carne(filete_de_cerdo).
carne(pollo_asado).

pescado(trucha).
pescado(bacalao).

postre(flan).
postre(helado).
postre(pastel).

%fin clauses

%PUNTO A

% listar_menu(X,Y,Z):-entrada(X),carne(Y),postre(Z);entrada(X),pescado(Y),postre(Z).
%
listar_menu(X,Y,Z):-entrada(X),(carne(Y);pescado(Y)),postre(Z).
%FIN A

%PUNTO B --> Hacer una consulta con CONSOME
menu_consome(Y,Z):-entrada(consomé),(carne(Y);pescado(Y)),postre(Z).
%listar_menu(X,Y,Z),X==consomé,write(X+Y+Z),nl,fail.
%FIN B

%PUNTO C
menu_sin_flan(X,Y,Z):-entrada(X),(carne(Y);pescado(Y)),postre(Z),dif(Z,flan).
%listar_menu(X,Y,Z),dif(Z,flan),write(X+Y+Z),nl,fail.
%FIN C
