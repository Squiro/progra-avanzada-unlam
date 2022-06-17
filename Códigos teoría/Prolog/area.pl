
triangulo(2,2).
triangulo(2,3).
cuadrado(4).
cuadrado(2).
rectangulo(6, 3).
rectangulo(10,6).
circulo(10).
circulo(3).




area(triangulo, A):-triangulo(B,H),A is B*H/2.
area(cuadrado,A):-cuadrado(L), A is L*L.
area(rectangulo,A):-rectangulo(B,H), A is B*H.
area(circulo,A):-circulo(R), A is pi*R*R.

prod_cad(Fig1, X, Fig2, Y):-area(Fig1,X),area(Fig2,Y).
seleccion(Fig1,X,Fig2,Y):-prod_cad(Fig1,X,Fig2,Y),X<Y.
proyeccion(Fig1, X):-seleccion(Fig1,X,_,_).
mayor_area(Fig, X):-area(Fig, X),not(proyeccion(Fig,X)).





%area_triang(X,Y,Res):-triangulo(X,Y),Res is (X*Y)/2.
%area_cuad(X,Res):-cuadrado(X),Res is X*X.
%area_rec(X,Y,Res):-rectangulo(X,Y),Res is X*Y.
%area_circ(X,Res):-circulo(X),Res is X*X*3.14.


%prod_cad_triang(X,Y):-area_triang(_,_,X),area_triang(_,_,Y).
%seleccion_triang(X,Y):-prod_cad_triang(X,Y),X<Y.
%proyeccion_triang(X):-seleccion_triang(X,_).
%mayor_triang(X):-area_triang(_,_,X),not(proyeccion_triang(X)).


%prod_cad_cuadrado(X,Y):-area_cuad(_,X),area_cuad(_,Y).
%seleccion_cuad(X,Y):-prod_cad_cuadrado(X,Y),X<Y.
%proyeccion_cuad(X):-seleccion_cuad(X,_).
%mayor_cuad(X):-area_cuad(_,X),not(proyeccion_cuad(X)).

%prod_cad_rec(X,Y):-area_rec(_,_,X),area_rec(_,_,Y).
%seleccion_rect(X,Y):-prod_cad_rec(X,Y),X<Y.
%proyeccion_rect(X):-seleccion_rect(X,_).
%mayor_rect(X):-area_rec(_,_,X),not(proyeccion_rect(X)).

%prod_cad_circ(X,Y):-area_circ(_,X),area_circ(_,Y).
%seleccion_circ(X,Y):-prod_cad_circ(X,Y),X<Y.
%proyeccion_circ(X):-seleccion_circ(X,_).
%mayor_circ(X):-area_circ(_,X),not(proyeccion_circ(X)).

%es_mayor(Num, X,Y,Z):-Num>X,Num>Y,Num>Z.

%mayor_area():-mayor_triang(X),
%    mayor_cuad(X1),
%    mayor_rect(X2),
%    mayor_circ(X3),
%    es_mayor(X, X1, X2, X3),write('El mayor es Triangulo').

%mayor_area():-mayor_triang(X),
%    mayor_cuad(X1),
%    mayor_rect(X2),
%    mayor_circ(X3),
%    es_mayor(X1, X, X2, X3),write('El mayor es Cuadrado').

%mayor_area():-mayor_triang(X),
%    mayor_cuad(X1),
%    mayor_rect(X2),
%    mayor_circ(X3),
%    es_mayor(X2, X1, X, X3),write('El mayor es Rectangulo').

%mayor_area():-mayor_triang(X),
%    mayor_cuad(X1),
%    mayor_rect(X2),
%    mayor_circ(X3),
%    es_mayor(X3, X1, X2, X),write('El mayor es Circulo').
