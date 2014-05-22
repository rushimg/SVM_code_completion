#! /bin/bash

#cat $1"test_corr" $1"test_wrong" > $1"temp"

paste $1"predictions" $1"test_map.dat" | column -s $'\t' -t > $1"map"

#paste $1"predictions" $1"test_corr" | column -s $'\t 1' -t > $1"map"
#rm $1"temp"
