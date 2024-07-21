type Board = [[Int]]

-- Function to check if a position is safe for a queen
isSafe :: Board -> Int -> Int -> Bool
isSafe board row col = not (hasQueenInRow || hasQueenInUpperDiagonal || hasQueenInLowerDiagonal)
  where
    hasQueenInRow = any (== 1) (take col (board !! row))
    hasQueenInUpperDiagonal = any (== 1) [board !! y !! x | (y, x) <- zip [row, row-1 .. 0] [col, col-1 .. 0]]
    hasQueenInLowerDiagonal = any (== 1) [board !! y !! x | (y, x) <- zip [row, row+1 .. length board-1] [col, col-1 .. 0]]

-- Update the board by placing a queen
updateBoard :: Board -> Int -> Int -> Int -> Board
updateBoard board row col val = take row board ++ [take col (board !! row) ++ [val] ++ drop (col + 1) (board !! row)] ++ drop (row + 1) board

-- Recursive utility function to solve N-Queen problem
solveNQUtil :: Int -> Board -> Int -> [Board]
solveNQUtil 0 board _ = [board]
solveNQUtil n board col =
    -- List Comprehensions A concise way to create lists based on existing lists. Syntax: [expression | item <- list, condition].
  concat [solveNQUtil (n-1) (updateBoard board row col 1) (col + 1) | row <- [0..(length board - 1)], isSafe board row col]

-- Example usage
main :: IO ()
main = do
  let board = [[0, 0, 0, 0],
               [0, 0, 0, 0],
               [0, 0, 0, 0],
               [0, 0, 0, 0]]
  print $ solveNQUtil 4 board 0  -- Should print False because there's a queen in upper diagonal left (2, 2)