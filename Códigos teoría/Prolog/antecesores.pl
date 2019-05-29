
padre(jose, david).
padre(juan, jose).
padre(esteban, juan).
padre(carlos, esteban).

antecesor(Padre,Hijo):-padre(Padre,Hijo).

antecesor(Padre,Hijo):-padre(Padre,H),antecesor(H,Hijo).

