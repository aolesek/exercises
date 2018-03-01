take' :: (Num i, Ord i) => i -> [a] -> [a]
take' _ [] = []
take' n _
  | n <= 0 = []
take' n (x:xs) = x : take' (n-1) xs
