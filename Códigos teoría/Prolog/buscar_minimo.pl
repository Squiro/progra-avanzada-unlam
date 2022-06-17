numero(10).
numero(4).
numero(1).

producto_cartesiano(X,Y):-numero(X),numero(Y).
seleccion(X,Y):-producto_cartesiano(X,Y),X>Y.
proyecion(X):-seleccion(X,_).

minimo(Num):-numero(Num),not(proyeccion(Num)).
