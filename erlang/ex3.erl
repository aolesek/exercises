%% -*- coding: utf-8 -*-
-module(ex3).

-compile([export_all]).

insert(Elem,{X,null,null}) when Elem<X -> {X,Elem,null}.
insert(Elem,{X,null,null}) when Elem>=X -> {X,null,Elem}.
