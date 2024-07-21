module Solution where
  
type Board = [[Int]]

-- function to solve the N-Queen problem
solveNQ :: Int -> [Board]
solveNQ n = solveNQUtil n (initBoard n) 0

-- recursive function to solve N-Queen problem
solveNQUtil :: Int -> Board -> Int -> [Board]
solveNQUtil 0 board _ = [board]
solveNQUtil n board col =
    -- List Comprehensions: A concise way to create lists based on existing lists. Syntax: [expression | item <- list, condition].
  concat [solveNQUtil (n-1) (updateBoard board row col 1) (col + 1) | row <- [0..(length board - 1)], isSafe board row col]

-- verify if x,y position is safe to place a queen
isSafe :: Board -> Int -> Int -> Bool
isSafe board row col = not (hasQueenInRow || hasQueenInUpperDiagonal || hasQueenInLowerDiagonal)
  where
    hasQueenInRow = any (== 1) (take col (board !! row))
    hasQueenInUpperDiagonal = any (== 1) [board !! y !! x | (y, x) <- zip [row, row-1 .. 0] [col, col-1 .. 0]]
    hasQueenInLowerDiagonal = any (== 1) [board !! y !! x | (y, x) <- zip [row, row+1 .. length board-1] [col, col-1 .. 0]]

-- put a queen in x,y position
updateBoard :: Board -> Int -> Int -> Int -> Board
updateBoard board row col val = 
  take row board ++ 
  [take col (board !! row) ++ [val] ++ drop (col + 1) (board !! row)] ++ 
  drop (row + 1) board

-- utility functions
-- init empty board
initBoard :: Int -> Board
initBoard n = replicate n (replicate n 0)

-- Pretty prints the board
printBoard :: Board -> IO ()
printBoard board = do
  mapM_ (putStrLn . concatMap show) board
  putStrLn ""

-- Main function to solve and print the N-Queens problem
main :: IO ()
main = do 
  mapM_ printBoard (solveNQ 4)
