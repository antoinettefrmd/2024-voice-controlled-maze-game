#!/bin/sh

if [ -d src/WAV/ ];
then
cd src/ressources/WAV
else
mkdir src/ressources/WAV
cd src/WAV
fi

NOW=$(date +"%Y%m%d_%H%M%S.wav")
echo $NOW
arecord -d 10 -r 16000 -f S16_LE "$NOW"