#!/usr/bin/python
#
# tone.py   play a tone on raspberry pi 
#

#import myPyLib   # get control-C handler
import signal
import sys
import time
import math
import pyaudio
import thread
from numpy import linspace,sin,pi,int16

pa = None;
s  = None;
input_audio = None;
chunk = 1024;
frame = []
def signal_handler(sig, frame):
	print('You called ctrl+c')
	sys.exit(0)

def init_audio(rate=44100):
  global pa,s, input_audio, output_audio
  print ("init_audio: Create PyAudio object")
  pa = pyaudio.PyAudio()
  print ("init_audio: Open stream")
  s = pa.open(output=True,
            channels=1,
            rate=rate,
            format=pyaudio.paInt16,
            output_device_index=0)
  output_audio = s
  input_audio = pa.open(format=pyaudio.paInt16,
			channels =1,
			rate=rate,
			input=True,
			frames_per_buffer=chunk)
  print ("init_audio: audio stream initialized")

def close_audio():
  global pa,s
  print ("close_audio: Closing stream")
  s.close()
  print ("close_audio: Terminating PyAudio Object")
  pa.terminate()


def note(freq, len, amp=5000, rate=8000):
 t = linspace(0,len,len*rate)
 data = sin(2*pi*freq*t)*amp
 return data.astype(int16) # two byte integers

def tone(freq=440.0, tonelen=0.5, amplitude=5000, rate=8000):
  global s
  # generate sound
  tone = note(freq, tonelen, amplitude, rate)

  # play it    
  #print "tone.main(): start playing tone"
  s.write(tone)

class  RecordingThread(Thread):
	def run(self):
		global frame, input_audio
		while True:
			if(len(frame) < chunk):
				frames.append(input_audio.read(chunk))
				print("Recording audio")
			else:
				print("not recording")
				time.sleep(1)

class OutputAudioThread(Thread):
	def run(self):
		global frame, output_audio
		while True:
			if(len(frame) > 0):
				s.write(frame.pop())
				print("Outputing audio")
			else:
				print("no audio to output")
# ##### MAIN ######
def main():
	signal.signal(signal.SIGINT, close_audio)  # Set CNTL-C handler 
  # open audio channel
	init_audio()

  # play tones forever    
	print ("tone.main(): start playing tones")
	while True:
		s.write(input_audio.read(chunk))


if __name__ == "__main__":
    main()

