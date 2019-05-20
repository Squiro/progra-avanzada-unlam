% This buffer is for notes you don't want to save.
% If you want to create a file, visit that file with C-x C-f,
% then enter the text in that file's own buffer.

es_un_pais(argentina).
es_un_pais(chile).
es_un_pais(peru).

limita_con(argentina,chile).
limita_con(argentina,brasil).
limita_con(bolivia,brasil).
limita_con(bolivia,paraguay).
limita_con(bolivia,chile).
limita_con(bolivia,argentina).
limita_con(bolivia,peru).
limita_con(peru,ecuador).
limita_con(peru,chile).
limita_con(paraguay,argentina).


son_limitrofes(X,Y):-limita_con(X,Y);limita_con(Y,X).
son_translimitrofes(X,Y):-son_limitrofes(X,Z),son_limitrofes(Y,Z),dif(X,Y),not(son_limitrofes(X,Y)).
