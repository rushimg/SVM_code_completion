#! /bin/bash


./svm_learn $1"train.dat" $1"model"
./svm_classify $1"test.dat" $1"model" $1"predictions"




