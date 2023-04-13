#!/bin/sh

cd src/ressources/WAV

fichier=$(ls *.wav)

whisper $fichier --language French

rm *.json
rm *.srt
rm *.tsv
rm *.vtt
rm *.wav

echo $fichier
