bleh :: Int -> Int
bleh n = n

isEven :: Int -> Bool
isEven n = n `mod` 2 == 0

solve :: Int -> [Int]
solve param = 
    [bleh number | number <- [1,3..param], isEven number]

-- Example usage
main :: IO ()
main = do
  let result = solve 5
  print $ result 