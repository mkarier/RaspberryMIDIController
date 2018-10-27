
import java.io.*;
import java.util.*;
/*
import org.usb4java.*;
import org.usb4java.javax.*;
import javax.usb.*;//*/

import javax.sound.midi.*;
import javax.sound.sampled.*;
import javax.sound.sampled.Line.Info;


public class SoundController 
{
	
	public static void main(String args[])
	{
		AudioInput audioInput = new AudioInput();
		AudioListener listener = new AudioListener(audioInput);
		SourceDataLine out = listener.getLineOut();
		loopAudio(audioInput, listener);
	}//end of main
	
	private static void loopAudio(AudioInput input, AudioListener output)
	{
		byte[] buffer;
		while(true)
		{
			buffer = input.captureAudio();
			output.outputAudio(buffer);
		}
	}//end of loopAudio
	
}//end of SOundController
