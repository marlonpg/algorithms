module Main where

import Test.HUnit
import Solution 

-- Sample boards for testing
testBoard1 :: Board
testBoard1 = [[0, 0, 0, 0],
              [0, 0, 0, 0],
              [0, 0, 0, 0],
              [0, 0, 0, 0]]

testBoard2 :: Board
testBoard2 = [[1, 0, 0, 0],
              [0, 0, 0, 0],
              [0, 0, 0, 0],
              [0, 0, 0, 0]]

-- Tests for updateBoard
testUpdateBoard :: Test
testUpdateBoard = TestCase $ do
    let updatedBoard = updateBoard testBoard1 0 0 1
    assertEqual "for (updateBoard testBoard1 0 0 1)," testBoard2 updatedBoard

-- Tests for isSafe
testIsSafe :: Test
testIsSafe = TestCase $ do
    assertBool "for (isSafe testBoard2 0 1)," (not $ isSafe testBoard2 0 1)
    assertBool "for (isSafe testBoard2 1 2)," (isSafe testBoard2 1 2)
    assertBool "for (isSafe testBoard2 1 1)," (not $ isSafe testBoard2 1 1)

-- Tests for solveNQUtil
testSolveNQUtil :: Test
testSolveNQUtil = TestCase $ do
    let solutions = solveNQUtil 4 (initBoard 4) 0
    assertBool "solutions should not be empty" (not $ null solutions)

-- Main test suite
tests :: Test
tests = TestList [TestLabel "testUpdateBoard" testUpdateBoard,
                  TestLabel "testIsSafe" testIsSafe,
                  TestLabel "testSolveNQUtil" testSolveNQUtil]

main :: IO ()
main = do
    _ <- runTestTT tests
    return ()
