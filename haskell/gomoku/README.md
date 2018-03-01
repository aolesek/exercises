# Instrukcja uruchomienia

Uruchamianie w ghci

Aby uruchomi� gr� w ghci nale�y wykona� polecenia:```
:l gomoku.hs
:main
```

Kompilacja ghc

Aby skompilowa� program nale�y wyda� polecenia w pow�oce:
```
ghc --make gomoku.hs
gomoku.exe  	//Windows
./gomoku	//Linux
```

# Funkcjonalno��

Program jest na etapie generowania drzewa gry, tzn. generuje drzewo zawieraj�ce ocenione plansze. Podj��em pr�b� implementacji algorytmu minimax, ale nie dzia�a on prawid�owo. Na pocz�tku gry mo�na wybra� rodzaj przeciwnika AI. SimpleAI to prosty algorytm, jego dzia�anie jest zbli�one do przeszukiwania drzewa o g��boko�ci 1 i wyboru najlepszego mo�liwego ruchu. MinimaxAi to nieprawid�owo dzia�aj�cy algorytm minimax, niestety generowanie ruchu trwa d�ugo a same generowane ruchy nie wydaj� si� by� najlepszymi z mo�liwych.

Generowanie drzewa gry

Aby wygenerowa� i obejrze� drzewo gry nale�y w ghci wyda� polecenia:
```
let game = Game exampleBoard19' Black 0 --exampleBoard19' to plansza z jednym bia�ym kamieniem
let root = Node game []
generateTree root 2 -- 2 to liczba poziom�w wg��b drzewa
```
Aby unikn�� sprawdzania ca�ej planszy, funkcja ogranicza generowanie nowych ruch�w tylko do tych p�l, kt�re maj� s�siada, tzn. takich kt�re s� puste, ale maj� bia�y lub czarny kamie� w kwadracie o rozmiarze 3x3, kt�rego �rodkiem jest sprawdzane pole.
# Autor
Arkadiusz Olesek