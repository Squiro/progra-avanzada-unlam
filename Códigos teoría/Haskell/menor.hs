menor :: Integer -> Integer -> Integer
menor x y
 | x < y = x
 | otherwise = y
main = print(menor 2 0)