pow :: Integer -> Integer -> Integer
pow x y
 | y == 1 = x
 | otherwise = x * pow x (y-1)
main = print(pow 2 5)