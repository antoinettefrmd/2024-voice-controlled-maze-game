#!/bin/sh

cd src/ressources/WAV

fichier = $(ls *.wav | sed -e "s/.wav$//g")

whisper fichier --language French

rm fichier.json
rm fichier.srt
rm fichier.tsv
rm fichier.vtt