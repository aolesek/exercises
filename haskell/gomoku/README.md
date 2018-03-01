# Instrukcja uruchomienia

Uruchamianie w ghci

Aby uruchomiæ grê w ghci nale¿y wykonaæ polecenia:```
:l gomoku.hs
:main
```

Kompilacja ghc

Aby skompilowaæ program nale¿y wydaæ polecenia w pow³oce:
```
ghc --make gomoku.hs
gomoku.exe  	//Windows
./gomoku	//Linux
```

# Funkcjonalnoœæ

Program jest na etapie generowania drzewa gry, tzn. generuje drzewo zawieraj¹ce ocenione plansze. Podj¹³em próbê implementacji algorytmu minimax, ale nie dzia³a on prawid³owo. Na pocz¹tku gry mo¿na wybraæ rodzaj przeciwnika AI. SimpleAI to prosty algorytm, jego dzia³anie jest zbli¿one do przeszukiwania drzewa o g³êbokoœci 1 i wyboru najlepszego mo¿liwego ruchu. MinimaxAi to nieprawid³owo dzia³aj¹cy algorytm minimax, niestety generowanie ruchu trwa d³ugo a same generowane ruchy nie wydaj¹ siê byæ najlepszymi z mo¿liwych.

Generowanie drzewa gry

Aby wygenerowaæ i obejrzeæ drzewo gry nale¿y w ghci wydaæ polecenia:
```
let game = Game exampleBoard19' Black 0 --exampleBoard19' to plansza z jednym bia³ym kamieniem
let root = Node game []
generateTree root 2 -- 2 to liczba poziomów wg³¹b drzewa
```
Aby unikn¹æ sprawdzania ca³ej planszy, funkcja ogranicza generowanie nowych ruchów tylko do tych pól, które maj¹ s¹siada, tzn. takich które s¹ puste, ale maj¹ bia³y lub czarny kamieñ w kwadracie o rozmiarze 3x3, którego œrodkiem jest sprawdzane pole.
# Autor
Arkadiusz Olesek