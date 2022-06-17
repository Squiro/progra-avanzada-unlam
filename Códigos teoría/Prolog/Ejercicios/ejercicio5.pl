%Una agencia matrimonial tiene un fichero de candidatos al matrimonio
% organizado seg�n las declaraciones siguientes:
%hombre(N,A,C,E).
%mujer(N,A,C,E).

% donde n es el nombre de un hombre o una mujer, a su altura (alta,
% media, baja), c el color de su cabello (rubio, casta�o, pelirrojo,
% negro) y e su edad (joven,adulta,vieja).

%gusta(N,M,L,S).

% que indica que a la persona n le gusta el g�nero de m�sica m (cl�sica,
% pop, jazz), el g�nero de literatura L (aventura, ciencia-ficci�n,
% polic�aca), y practica el deporte s (tenis, nataci�n, jogging).

%busca(N,A,C,E).

% que expresa que la persona n busca una pareja de altura a, con cabello
% color c y edad e. Se considera que dos personas e y de sexos diferentes
% son adecuadas si x conviene a y e y conviene a x. Se dice que x
% conviene a y si x conviene f�sicamente a y (la altura, edad y color de
% cabello de y son los que busca x) y si, adem�s, los gustos de x e y en
% m�sica, literatura y deporte coinciden.


hombre(jose, alta, rubio, joven).
hombre(pepe, media, casta�o, adulta).
hombre(carlos, baja, pelirrojo, vieja).
hombre(martin, alta, negro, joven).

mujer(martina, alta, rubio, joven).
mujer(pepa, media, casta�o, adulta).
mujer(carla, baja, pelirrojo, vieja).
mujer(josefa, alta, negro, joven).

gusta(jose, clasica, aventura, tenis).
gusta(pepe, pop, ciencia-ficcion, natacion).
gusta(carlos, jazz, policiaca, jogging).
gusta(martin, pop, aventura, tenis).

gusta(martina, pop, aventura, tenis).
gusta(pepa, jazz, policiaca, natacion).
gusta(carla, clasica, ciencia-ficcion, jogging).
gusta(josefa, pop, aventura, natacion).

busca_hombre(N,A,C,E):-hombre(N1, A, C, E),gusta(N,M,L,S),gusta(N1,M,L,S).
