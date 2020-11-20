#!/usr/bin/env bash

for i in {00..17}
do
    prog=Samples/Samples_APS0/prog0$i.aps
    echo "Test de $prog :" 
    echo "  prologTerm :"
	./prologTerm $prog
	echo "  typrog :"
	./typrog $prog
	echo "  eval :"
	./eval $prog
	echo ""
done

for i in {00..20}
do
    prog=Samples/Samples_APS1/prog1$i.aps
    echo "Test de $prog" 
    echo "  prologTerm :"
	./prologTerm $prog
	echo "  typrog :"
	./typrog $prog
	echo "  eval :"
	./eval $prog
	echo ""
done
