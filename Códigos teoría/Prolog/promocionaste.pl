parcial1(alesio,6).
parcial1(ana,8).
parcial1(jose, 2).
parcial1(maria,8).
parcial1(estefan,10).

parcial2(alesio,8).
parcial2(ana, 5).
parcial2(jose, 3).
parcial2(maria,7).
parcial2(estefan,10).

alumnos_promocion(Alumno,Nota):-parcial1(Alumno,Nota1),parcial2(Alumno,Nota2),
    Nota1>=7,
    Nota2>=7,
    Nota is (Nota1+Nota2)/2.

alumnos_cursada(Alumno):-parcial1(Alumno,Nota1),parcial2(Alumno,Nota2),
    Nota1>=4,Nota2>=4,not(alumnos_promocion(Alumno,_)).

alumnos_recursan(Alumno):-parcial1(Alumno,Nota1),parcial2(Alumno,Nota2),
    Nota1=<3, Nota2=<3.

producto_cartesiano(X,Y):-alumnos_promocion(_,X),alumnos_promocion(_,Y).
seleccion(X,Y):-producto_cartesiano(X,Y),X<Y.
proyeccion(X):-seleccion(X,_).


medalla(Alumno,Nota):-alumnos_promocion(Alumno,Nota),not(proyeccion(Nota)).
