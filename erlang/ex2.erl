%% -*- coding: utf-8 -*-
% ZADANIE 3
% program wczytuje plik, dzieli go na tokeny i zwraca mapę która zawiera tokeny jako klucze i ilość ich wystąpień jako wartości
% użycie:
% c(ex2).
% ex2:resolve('file.txt').

-module(ex2).
% nazwa modułu

-compile([export_all]).
-import(file,[open/2]).
-import(binary,[bin_to_list/1]).
-import(lists,[flatten/1]).
-import(string,[tokens/2,to_upper/1,to_lower/1]).
-import(string,[replace/3]).
-import(maps,[put/3,update/3,is_key/2,get/2]).

openFile(Filename) -> file:open(Filename,read).

readFile(Filename) -> {ok,Data} = file:read_file(Filename), binary:bin_to_list(Data).

parseString(String) ->  string:tokens(string:trim(String), " ").

updateMap(Key, Map, true) -> maps:update(Key, maps:get(Key,Map) + 1, Map);

updateMap(Key, Map, false) -> maps:put(Key, 1, Map).


resolve(Filename) ->  Data = readFile(Filename),
                          TokenList = parseString(Data),
                          lists:foldl(
                          fun(X,Map) -> updateMap(string:trim(X,trailing,"."),Map,maps:is_key(string:trim(X,trailing,"."),Map)) end,
			                    #{},
                          TokenList).
