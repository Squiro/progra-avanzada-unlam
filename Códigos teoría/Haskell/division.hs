division :: Integer -> Integer -> Integer
division x y
 | x < y = 0
 | otherwise = 1 + division (x-y) y

main = print(division 8 2)