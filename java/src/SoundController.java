
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
import AudioDevices.AudioOutputThread;
import AudioManipulation.VolumeControl;
import GUI.ControllerCanvas;
import GuitarEffects.*;
import AudioDevices.*;
public class SoundController 
{
	public static void main(String args[]) throws MidiUnavailableException
	{
		AudioInput audioInput = new AudioInput();
		AudioInputThread inputThread = new AudioInputThread(audioInput);
		I_AudioOutput listener = new AudioOutputBase(audioInput);
		listener = new DistortionEffect(listener);
		listener = new PitchShift(listener);
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
	
	
	public static double log2(double x)
	{
		return (Math.log(x) /Math.log(2));
	}
	
	private static void loopAudio(AudioInput input, I_AudioOutput output)
	{
		while(true)
		{
			output.outputAudio(input.captureAudio());
		}
	}//end of loopAudio
	
}//end of SOundController
