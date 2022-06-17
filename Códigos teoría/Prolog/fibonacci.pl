%Calcular fibonacci


%1 1 2 3 5 8
%1 2 3 4 5 6

fibonacci(1,1):-!.
fibonacci(2,1):-!.
fibonacci(X,Fibo):-X1 is X-1,fibonacci(X1,F1), X2 is X-2,fibonacci(X2,F2),
                   Fibo is F1 + F2.
