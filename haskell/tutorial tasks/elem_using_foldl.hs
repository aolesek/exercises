elem' :: (Eq a) => a -> [a] -> Bool
elem' y = foldl (\acc x -> if y == x then True else acc) False
