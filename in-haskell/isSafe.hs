type Board = [[Int]]

isSafe :: Board -> Int -> Int -> Bool
isSafe board row col = not (checkRow || checkUpperDiagonal || checkLowerDiagonal)
  where
    -- Check this row on the left side
    checkRow = any (== 1) (take col (board !! row))
    
    -- Check upper diagonal on the left side
    checkUpperDiagonal = any (== 1) [board !! y !! x | (y, x) <- zip [row, row-1 .. 0] [col, col-1 .. 0]]

    -- Check lower diagonal on the left side
    checkLowerDiagonal = any (== 1) [board !! y !! x | (y, x) <- zip [row,row+1..length board-1] [col,col-1..0]]

-- Example usage
main :: IO ()
main = do
  let board = [[0, 0, 0, 0],
               [0, 1, 0, 0],
               [0, 0, 0, 0],
               [0, 0, 0, 0]]
  print $ isSafe board 2 2  -- Should print False because there's a queen in upper diagonal left (2, 2)
  print $ isSafe board 2 3  -- Should print True because there's no threat to (2, 3)
  print $ isSafe board 1 2  -- Should print False because there's a queen in the same row (1, 2)
  print $ isSafe board 0 2  -- Should print False because there's a queen in lower diagonal left (0, 2)
