package AudioManiplation;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;

import AudioDevices.AudioInput;
import AudioDevices.AudioOutput;

public class VolumeControl
{
	
	private BooleanControl muteControl;
	private FloatControl masterGainControl;
	public VolumeControl(AudioOutput out)
	{
		this.muteControl = (BooleanControl) out.getLineOut().getControl(BooleanControl.Type.MUTE);
		this.muteControl.setValue(false);
		this.masterGainControl = (FloatControl) out.getLineOut().getControl(FloatControl.Type.MASTER_GAIN);
	}//end of VolumeControl
	
	public void switchMuteButton() { this.muteControl.setValue(!this.muteControl.getValue());}
	public void mute() { this.muteControl.setValue(true);}
	public void unMute() { this.muteControl.setValue(false);}
	
	public float getMinMasterGain() { return this.masterGainControl.getMinimum();}
	public float getMaxMasterGain() { return this.masterGainControl.getMaximum();}
	public float getCurrentMasterGain() { return this.masterGainControl.getValue();}
	
	public void setMasterGain(float value) { this.masterGainControl.setValue(value);}
	
	
	public void printRange()
	{
		System.out.println("Master Gain Range = " + "[" + this.masterGainControl.getMinimum() + " : " + this.masterGainControl.getMaximum() + "]");
		System.out.println("Master Gain CurrentValue = " + this.masterGainControl.getValue());
	}
	
	public static void printControls(AudioInput input)
	{
		System.out.println("Input Controls");
		Control controls[] = input.getInputLine().getControls();
		int index = 0;
		for(Control temp : controls)
		{
			System.out.println(0 + ": " + temp.getType());
			index++;
		}
	}
	public static void printControls(AudioOutput out)
	{
		System.out.println("Output controls");
		Control controls[] = out.getLineOut().getControls();
		int index = 0;
		for(Control temp : controls)
		{
			System.out.println(0 + ": " + temp.getType());
			index++;
		}
	}
}//end of method
