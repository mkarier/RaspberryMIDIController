package GuitarEffects;

import javax.sound.sampled.SourceDataLine;

public interface I_AudioOutput 
{
	public void outputAudio(byte[] buffer);
	
	public SourceDataLine getLineOut();
}
