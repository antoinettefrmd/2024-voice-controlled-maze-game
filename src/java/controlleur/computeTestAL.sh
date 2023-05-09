#!/bin/sh

cd src/ressources/modele_voix/LST

touch fichier.lst

nom=$(ls *.wav | sed -e "s/.wav$//g")

echo $nom > fichier.lst

touch nomndx.ndx

echo "$nom modeleAlec" > nomndx.ndx

cd ..

./feature_extract.sh LST/fichier.lst 39MFCC 

cd CFG

touch resultat.txt

./EnergyDetector --config EnergyDetector.cfg

./NormFeat --config NormFeat_Paroles.cfg

./ComputeTest --config ComputeTest.cfg

cd ..

rm LST/*.wav
rm WAV/*.wav
rm PRM/*
rm LBL/*
