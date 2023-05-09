#!/bin/sh

if [ -d src/ressources/modele_voix/WAV/ ];
then
cd src/ressources/modele_voix/WAV
else
mkdir src/ressources/modele_voix/WAV
cd src/ressources/modele_voix/WAV
fi

NOW=$(date +"%Y%m%d_%H%M%S.wav")
echo $NOW
arecord -d 10 -r 16000 -f S16_LE "$NOW"

cp $NOW ../LST
