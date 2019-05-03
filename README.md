# RaspberryMIDIController
This project started so that A person could use a RaspberryPi tablet as a MIDI Controller
for a guitar. 
Although I have yet to create classes for different effects, the current state is very moduler and has GUI.

NOTE: A more accurate discription is that it is an electronic Killswitch for guitar on a RaspberryPi.
This program was developed on Ubuntu 18.04.1 desktop. It was run and tested on a 
raspberrypi B+ running UbuntuMate 16

To Install clone Master Repository
run 'make BuildAll'
Then you should be able to double click the jar file and run that.

For Developers,

The way that I figured the effects would be implemented would be 
classes that extend AudioOutput, this way it can still be passed to the AudioOutputThread 
and depending on what you are trying to do you can manipulate the buffer by overriding the 'outPutAudio' method.
For multiple effects a way to wrap these objects will need to be created.

This program was originally built for Ubuntu Mate. Although I did get it installed on Raspbian. You do have to install
PulseAudio. (sudo apt install pulseaudio) then run the make command. 
# RaspberryMIDIController
