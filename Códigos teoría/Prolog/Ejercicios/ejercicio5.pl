%Una agencia matrimonial tiene un fichero de candidatos al matrimonio
% organizado según las declaraciones siguientes:
%hombre(N,A,C,E).
%mujer(N,A,C,E).

% donde n es el nombre de un hombre o una mujer, a su altura (alta,
% media, baja), c el color de su cabello (rubio, castaño, pelirrojo,
% negro) y e su edad (joven,adulta,vieja).

%gusta(N,M,L,S).

% que indica que a la persona n le gusta el género de música m (clásica,
% pop, jazz), el género de literatura L (aventura, ciencia-ficción,
% policíaca), y practica el deporte s (tenis, natación, jogging).

%busca(N,A,C,E).

% que expresa que la persona n busca una pareja de altura a, con cabello
% color c y edad e. Se considera que dos personas e y de sexos diferentes
% son adecuadas si x conviene a y e y conviene a x. Se dice que x
% conviene a y si x conviene físicamente a y (la altura, edad y color de
% cabello de y son los que busca x) y si, además, los gustos de x e y en
% música, literatura y deporte coinciden.


hombre(jose, alta, rubio, joven).
hombre(pepe, media, castaño, adulta).
hombre(carlos, baja, pelirrojo, vieja).
hombre(martin, alta, negro, joven).

mujer(martina, alta, rubio, joven).
mujer(pepa, media, castaño, adulta).
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
