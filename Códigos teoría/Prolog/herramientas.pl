herramienta(pinza,150).
herramienta(martillo,100).
herramienta(tenaza,120).
herramienta(taladro,340).
herramienta(amoladora,340).

%Hallar la herramienta de mayor precio

%Hacemos el producto cartesiano entre los precios
%(150,150) (150,100) (150,120) ... (150,340)
producto_cartesiano(X,Y):-herramienta(_,X),herramienta(_,Y).

%Seleccionamos aquellos de la proyeccion tales que la coord. X sea menor
%que la coord. Y
%150, 100, 120
seleccion_mayor(X,Y):-producto_cartesiano(X,Y),X<Y.
%La proyeccion nos devuelve todos menos el maximo
proyeccion(Precio):-seleccion_mayor(Precio,_).
%Al negar la proyeccion, nos devuelve el maximo
mayor_precio(Her,Precio):-herramienta(Her,Precio),not(proyeccion(Precio)).
