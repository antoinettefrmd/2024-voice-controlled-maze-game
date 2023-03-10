#!/bin/sh

if[-d src/WAV/];
then
cd src/WAV
else
mkdir src/WAV
cd src/WAV
fi

NOW=$(date +"%Y%m%d_%H%M%S.wav")
echo $NOW
arecord -d 10 -r 16000 -f S16_LE "$NOW"