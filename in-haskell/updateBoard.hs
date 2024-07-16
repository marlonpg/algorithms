updateBoard :: [[Int]] -> Int -> Int -> Int -> [[Int]]
updateBoard board row col val = 
  take row board ++ 
  [take col (board !! row) ++ [val] ++ drop (col + 1) (board !! row)] ++ 
  drop (row + 1) board

-- Function to print the board
printBoard :: [[Int]] -> IO ()
printBoard board = mapM_ putStrLn [concat [show cell ++ " " | cell <- row] | row <- board]

testUpdateBoard :: IO ()
testUpdateBoard = do
  let initialBoard = [[0, 0, 0, 0],
                      [0, 0, 0, 0],
                      [0, 0, 0, 0],
                      [0, 0, 0, 0]]
  let updatedBoard = updateBoard initialBoard 1 1 1

  putStrLn "\nUpdated Board:"
  printBoard updatedBoard

main :: IO ()
main = testUpdateBoard