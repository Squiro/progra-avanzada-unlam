hombre(pedro).
hombre(manuel).
hombre(arturo).
mujer(mar�a).
padre(pedro, manuel).
padre(pedro, arturo).
padre(pedro, mar�a).

%ni�o(X,Y)//expresa que X es hijo o hija de Y.
%hijo(X,Y)//expresa que X es un hijo var�n de Y.
%hija(x,y)//expresa que X es una hija de Y.
%hermano-o-hermana(X,Y)//expresa que X es hermano o hermana de Y.
%hermano(X,Y)//expresa que X es un hermano de Y.
%hermana(X,Y)//expresa que X es una hermana de Y.

ni�o(X,Y):-padre(Y,X).

hijo(X,Y):-padre(Y,X),hombre(X).
hija(X,Y):-padre(Y,X),mujer(X).
hermano_o_hermana(X,Y):-(ni�o(X,Z),ni�o(Y,Z)),dif(X,Y).
hermano(X,Y):-(ni�o(X,Z),ni�o(Y,Z),hombre(X)),dif(X,Y).
hermana(X,Y):-(nin�o(X,Z),ni�o(Y,Z),mujer(X)),dif(X,Y).

