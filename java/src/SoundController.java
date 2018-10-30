
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
		System.out.println("The Buffer Size = " + audioInput.getBufferSize());
		AudioInputThread inputThread = new AudioInputThread(audioInput);
		AudioOutput listener = new AudioOutput(audioInput);
		AudioOutputThread outputThread = new AudioOutputThread(listener, inputThread);
		inputThread.setPriority(Thread.MAX_PRIORITY);
		//outputThread.setPriority(Thread.MAX_PRIORITY);
		inputThread.start();
		outputThread.start();
		//**/
		
		//loopAudio(audioInput, listener);
	}//end of main
	
	private static void loopAudio(AudioInput input, AudioOutput output)
	{
		while(true)
		{
			output.outputAudio(input.captureAudio());
		}
	}//end of loopAudio
	
}//end of SOundController
