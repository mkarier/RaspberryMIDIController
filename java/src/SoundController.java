
import java.io.*;
import java.util.*;
/*
import org.usb4java.*;
import org.usb4java.javax.*;
import javax.usb.*;//*/

import javax.sound.midi.*;
import javax.sound.sampled.*;
import javax.sound.sampled.BooleanControl.Type;
import javax.sound.sampled.Line.Info;

import AudioDevices.AudioInput;
import AudioDevices.AudioInputThread;
import AudioDevices.AudioOutput;
import AudioDevices.AudioOutputThread;
import AudioManiplation.VolumeControl;
import GUI.ControllerCanvas;
import AudioDevices.*;
public class SoundController 
{
	
	public static void main(String args[])
	{
		AudioInput audioInput = new AudioInput();
		AudioInputThread inputThread = new AudioInputThread(audioInput);
		AudioOutput listener = new AudioOutput(audioInput);
		AudioOutputThread outputThread = new AudioOutputThread(listener, inputThread);
		
		VolumeControl volController = new VolumeControl(listener);
		
		
		inputThread.setPriority(Thread.MAX_PRIORITY);
		//outputThread.setPriority(Thread.MAX_PRIORITY);
		inputThread.start();
		outputThread.start();
		//**/
		ControllerCanvas controllerCanvas = new ControllerCanvas();
		controllerCanvas.addVolumeSilde(volController);
		controllerCanvas.addKillswitch(volController);
		
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
