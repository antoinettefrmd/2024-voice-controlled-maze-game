#!/bin/sh

if [ -d src/ressources/WAV/ ]; 
then
cd src/ressources/WAV
else
mkdir src/ressources/WAV
cd src/ressources/WAV
fi

if [ "$pwd" | tail -c4 = "WAV" ];
then
cat Hello
rm *
fi

NOW=$(date +"%Y%m%d_%H%M%S.wav")
echo $NOW
arecord -d 5 -r 16000 -f S16_LE "$NOW"

