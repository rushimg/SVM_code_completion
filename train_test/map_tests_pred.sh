#! /bin/bash

cat $1"test_corr" $1"test_wrong" > $1"temp"

paste $1"predictions" $1"temp" | column -s $'\t' -t > $1"map"

rm $1"temp"
