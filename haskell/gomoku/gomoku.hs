-- Arkadiusz Olesek
-- Instrukcja uruchomienia i kompilacji znajduje się w pliku README

import qualified Data.Map as Map
import qualified Data.List.Split as Split
import Data.List
import Data.Tree
import System.Random
import Data.Ord
import Data.Char
import System.Exit

data Color = White | Black | None deriving Eq
data Board = Board [[Color]]
data Game = Game Board Color Int
type GameTree = Tree Game

instance Show Color where
  show White  = " W "
  show Black  = " B "
  show None   = " . "

instance Show Board where
  show = showBoard

instance Show Game where
  show (Game board player rating) = "\nPlayer " ++ show player ++ "Rating " ++ show rating ++ "\n" ++ show board

instance Read Color where
  readsPrec _ "W" = [(White, "")]
  readsPrec _ "B" = [(Black, "")]
  readsPrec _ " " = [(None, "")]
  readsPrec _ str = []

instance Read Board where
  readsPrec _ str = [( (Board brd),"")]
    where brd = (map (map (\x -> read [x] :: Color)) $ Split.splitOn "\n" str)

exampleBoard19 :: Board
exampleBoard19 = read "                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n    BWWBWWW        \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   " :: Board

exampleBoard19' :: Board
exampleBoard19' = read "                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n         W         \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   " :: Board

emptyBoard19 :: Board
emptyBoard19 = read "                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   \n                   " :: Board

showRow :: [Color] -> String
showRow [] = "|\n"
showRow (he:ta) = show he ++ showRow ta

showBoard' :: Int -> (Board) -> String
showBoard' _ (Board []) = ""
showBoard' n (Board (he:ta)) = (if n > 8 then " " ++ show (18-n) else show (18-n)) ++ "|" ++  showRow he ++ showBoard' (n-1) (Board ta)

showBoard :: (Board) -> String
showBoard x = " y/x0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 \n" ++ showBoard' 18 x

updateBoard :: Board -> Int -> Int -> Color -> Board
updateBoard (Board board) x y player = Board (replace y (replace x player (board !! y)) board)
  where
    replace i color list = (take i list) ++ [color] ++ (drop (i+1) list)

getColor :: Board -> Int -> Int -> Color
getColor (Board b) x y = b !! y !! x

isRight :: Board -> Int -> Int -> Bool
isRight (Board x) c r = (getColor (Board x) c r) == None

opponent :: Color -> Color
opponent Black = White
opponent White = Black
opponent None = None

diagonals :: [[a]] -> [[a]]
diagonals = tail . go [] where
  go b es_ = [h | h:_ <- b] : case es_ of
    []   -> transpose ts
    e:es -> go (e:ts) es
    where ts = [t | _:t <- b]

diagonals' :: [[a]] -> [[a]]
diagonals' x = diagonals $ reverseMatrix $ x
  where
    reverseMatrix x = map reverse x

-- łączenie planszy z transponowaną planszą, przekatnymi planszy i 'odwrotnymi przekatanymi' dla łatwiejszego poszukiwania wzorów
flatBoard :: Board -> [[Color]]
flatBoard (Board board) = board ++ (transpose board) ++ (diagonals board) ++ (diagonals' board)

evaluate :: Game -> Int
evaluate (Game board color _) =
  let ai_patterns = [ ( [color, color, color, color, color] , 10000 ),
                      ( [color, color, color, color, None]  , 1000 ),
                      ( [opponent color, opponent color, opponent color, opponent color, None]  , -2000 ),
                      ( [color, color, color, None, color]  , 1000 ),
                      ( [opponent color, opponent  color, opponent color, None, opponent color]  , -2000 ),
                      ( [color, color, None, color, color]  , 1000 ),
                      ( [opponent color, opponent color, None, opponent color, opponent color]  , -2000 ),
                      ( [color, color, color, None, None]   , 100 ),
                      ( [opponent color, opponent color, opponent color, None, None]   , -500 ),
                      ( [None, color, color, color, None]   , 100 ),
                      ( [None, opponent color, opponent color, opponent color, None]   , -500 ),
                      ( [color, color, None, None, None]    , 10 ),
                      ( [opponent color, opponent color, None, None, None]    , -10 ),
                      ( [None, color, color, None, None]    , 10 ),
                      ( [None, opponent color, opponent color, None, None]    , -10 )
                      ]
      search p = map (\(k,s) -> if (isInfixOf k p) then s else 0) ai_patterns
      searchAndReverse p = (search p) ++ (search $ reverse p)
    in sum $ concat $ map searchAndReverse (flatBoard board)

neighborhood :: Board -> Int -> Int -> [Color]
neighborhood (Board b) x y
  | ((x == 0) && (y == 0))  = (take 2 $ b!!y) ++ (take 2 $ b !! (y+1))
  | ((x == 0) && (y == 18)) = (take 2 $ b!!(y-1)) ++(take 2 $ b!!y)
  | x == 0                  = (take 2 $ b!!(y-1)) ++(take 2 $ b!!y) ++ (take 2 $ b !! (y+1))
  | ((y == 0) && (x == 18)) = (take 2 $ drop (x-1) $ b!!(y)) ++ (take 2 $ drop (x-1) $ b!!(y+1))
  | y == 0                  = (take 3 $ drop (x-1) $ b!!(y)) ++ (take 3 $ drop (x-1) $ b!!(y+1))
  | ((x == 18) && (y == 18))= (drop (x-1) $ b!!(y-1)) ++ (drop (x-1) $ b !! (y))
  | x == 18                = (drop (x-1) $ b!!(y-1)) ++ (drop (x-1) $ b !! (y)) ++ (drop (x-1) $ b !! (y+1))
  | y == 18                = (take 3 $ drop (x-1) $ b!!(y)) ++ (take 3 $ drop (x-1) $ b!!(y-1))
  | otherwise = (take 3 $ drop (x-1) $ b!!(y-1)) ++ (take 3 $ drop (x-1) $ b!!(y)) ++ (take 3 $ drop (x-1) $ b!!(y+1))

hasNeighbors :: Board -> Int -> Int -> Bool
hasNeighbors (Board b) x y = foldl (\acc x -> if x /= None then True else acc) False (neighborhood (Board b) x y)

nextMoves :: Game -> [Game]
nextMoves (Game board player _) = (foldl (\acc (a,b) -> if ((hasNeighbors board a b) && (isRight board a b)) then (Game (updateBoard board a b player) player (evaluate (Game (updateBoard board a b player) player 0))):acc else acc) [] [(a,b)| a <- [0..18], b <- [0..18]])

subTree :: GameTree -> [GameTree]
subTree (Node (Game board player rating) []) = map (\x -> (Node x [])) (nextMoves (Game board (opponent player) rating))

generateTree :: GameTree -> Int -> GameTree
generateTree  (Node (Game (Board brd) player rating) []) 1 = Node (Game (Board brd) player rating) (subTree  (Node (Game (Board brd) player rating) []))
generateTree  (Node (Game (Board brd) player rating) []) depth = Node (Game (Board brd) (player) rating) [generateTree tree (depth - 1) | tree <- subTree (Node (Game (Board brd) (opponent player) rating) [])]

getSimpleAiMove :: Game -> Game
getSimpleAiMove (Game board player rating) = foldl (simpleBestMove) (Game board player (-10000)) (nextMoves (Game board (opponent player) rating))
  where
    simpleBestMove (Game accboard accplayer accrating) (Game board player rating) = if rating>accrating then (Game board player rating) else (Game accboard accplayer accrating)

playGame :: Game -> (Game -> Game) -> IO ()
playGame (Game brd player rating) ai = do
  putStrLn "Enter x..."
  xio <- getLine
  putStrLn "Enter y..."
  yio <- getLine
  let x = (read xio :: Int)
  let y = (read yio :: Int)

  if (isRight brd x y) then putStr "" else do
    putStrLn "bad move"
    playGame (Game brd player rating) ai
  let playerMove = Game ((updateBoard brd x y Black)) Black rating
  putStrLn $ show playerMove
  putStrLn $ "Wait for AI move..."
  let aiMove = ai playerMove
  putStrLn $ show aiMove
  if ((evaluate playerMove) >= 10000) || ((evaluate aiMove) >= 10000) then (winner playerMove) else (playGame aiMove ai)

winner :: Game -> IO ()
winner player = if (evaluate player >= 10000) then do
                                                    (putStrLn "Player has won!")
                                                    exitSuccess
                                              else (putStrLn "AI has won!")

main :: IO ()
main = do
          putStrLn "0 - simpleAI \n1 - minimax Ai"
          aimode <- getLine
          let x = (read aimode :: Int)
          let game = Game emptyBoard19 Black 0
          putStr $ show game

          if (x == 0) then (playGame game getSimpleAiMove) else (playGame game getAiMove)


--Próba implementacji algorytmu minimax
getAiMove :: Game -> Game
getAiMove (Game board player rating) =
  let gameTree = (generateTree (Node (Game board (opponent player) rating) []) 3)
      bestMoveIndex = getGreatestIndex $ getMinimaxList $ gameTree
  in  extractGameFromTree $ (getSubTrees gameTree) !! bestMoveIndex

extractGameFromTree :: GameTree -> Game
extractGameFromTree (Node game _ ) = game

getMinimaxList :: GameTree -> [Int]
getMinimaxList tree = foldr (\x acc -> [(minmax 2 x)] ++ acc) [] (getSubTrees tree)

getGreatestIndex :: [Int] -> Int
getGreatestIndex xs = snd $ maximumBy (comparing fst) (zip xs [0..])

minmax :: Int -> GameTree -> Int
minmax 0 (Node (Game board player rating) []) = rating
minmax depth (Node (Game board player rating) xs) = if (player == White) then (foldl (maximize) (-1000000) xs) else (foldl (minimize) (1000000) xs)
  where
    maximize acc (Node (Game board player rating) xss) = max acc (minmax (depth-1) (Node (Game board White rating) xss))
    minimize acc (Node (Game board player rating) xss) = min acc (minmax (depth-1) (Node (Game board Black rating) xss))

getSubTrees :: GameTree -> [GameTree]
getSubTrees (Node game list) = list
