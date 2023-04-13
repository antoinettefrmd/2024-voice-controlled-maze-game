#!/bin/sh

if [ -d src/ressources/WAV/ ]; 
then
cd src/ressources/WAV
else
mkdir src/ressources/WAV
cd src/ressources/WAV
fi

rm *

NOW=$(date +"%Y%m%d_%H%M%S.wav")
echo $NOW
arecord -d 5 -r 16000 -f S16_LE "$NOW"

