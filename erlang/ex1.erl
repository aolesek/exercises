%% -*- coding: utf-8 -*-
-module(ex1).
% nazwa modułu

-compile([export_all]).
% opcje kompilatora, w tym wypadku eksport wszystkich funkcji
% przydatne przy testowaniu

-import(maps,[put/3]).
-import(io,[fwrite/2]).

% ZADANIE 1
% map_append(Key, Value, Map) i map_update(Key,Value,Map) przy użyciu map syntax, bez pakietu maps
% użycie(erl):
% c(ex1).
% A = #{ a=>1 }.
% B = ex1:map_append(b,2,A).
% C = ex1:map_update(a,7,B).
map_append(Key, Value, Map) -> Map#{Key => Value}.
map_update(Key, Value, Map) -> Map#{Key := Value}.

% ZADANIE 2
% użycie (erl)
% X = #{a => 7,b => 2}.
% ex1:map_display(X).
single_display(Key,Value) -> io:fwrite("~s->~p\n",[Key,Value]).
map_display(M) -> maps:map(fun(K,V) -> ex1:single_display(K,V) end,M),ok.